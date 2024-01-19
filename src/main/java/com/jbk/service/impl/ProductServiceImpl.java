package com.jbk.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.dao.ProductDao;
import com.jbk.entity.ProductEntity;
import com.jbk.exceptions.ResourceNotExist;
import com.jbk.model.CategoryModel;
import com.jbk.model.ProductModel;
import com.jbk.model.SupplierModel;
import com.jbk.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ProductDao productDao;

	@Override
	public int addProduct(ProductModel productModel) {
		return productDao.addProduct(mapper.map(productModel, ProductEntity.class));
	}

	@Override
	public ProductModel getProductById(long productId) {
		ProductEntity productEntity = productDao.getProductById(productId);

		System.out.println("in service");
		ProductModel productModel = null;

		if (productEntity != null) {
			productModel = mapper.map(productEntity, ProductModel.class);
		}
		return productModel;
	}

	@Override
	public List<ProductModel> getAllProducts() {
		List<ProductEntity> allProduct = productDao.getAllProducts();
		List<ProductModel> list = null;
		if (!allProduct.isEmpty()) {

			list = allProduct.stream().map(entity -> mapper.map(entity, ProductModel.class))
					.collect(Collectors.toList());

		} else {
			throw new ResourceNotExist("Product Not Exists");
		}
		return list;
	}

	@Override
	public int updateProduct(ProductModel productModel) {
		ProductModel dbProduct = getProductById(productModel.getProductId());
		if (dbProduct != null) {
			return productDao.updateProduct(mapper.map(productModel, ProductEntity.class));
		} else {
			return 2;
		}

	}

	@Override
	public int deleteProduct(long productId) {
		
		return productDao.deleteProduct(productId);
	}

	@Override
	public ProductModel getProductByName(String productName) {
		ProductEntity productEntity = productDao.getProductByName(productName);
		if(productEntity!=null) {
			return mapper.map(productEntity, ProductModel.class);
		}else {
			return null;
		}
		
	}

	@Override
	public List<ProductModel> sortProductByName() {
		
		List<ProductEntity> allProduct = productDao.sortProductByName();
		
		List<ProductModel> list = null;
		if (allProduct!=null && !allProduct.isEmpty()) {

			list = allProduct.stream().map(entity -> mapper.map(entity, ProductModel.class))
					.collect(Collectors.toList());

		} else {
			throw new ResourceNotExist("Product Not Exists");
		}
		return list;

	}

	@Override
	public double getMaxPrice() {
		return productDao.getMaxPrice();
		
	}

	@Override
	public List<ProductModel> getMaxPriceProduct() {
	List<ProductEntity> maxPriceProducts = productDao.getMaxPriceProduct();
	List<ProductModel> list = null;
	if (maxPriceProducts!=null && !maxPriceProducts.isEmpty()) {

		list = maxPriceProducts.stream().map(entity -> mapper.map(entity, ProductModel.class))
				.collect(Collectors.toList());

	} else {
		throw new ResourceNotExist("Product Not Exists");
	}
	return list;
		
	}
	
	private List<ProductModel> readExcel(String filePath) {
		List<ProductModel> list=new ArrayList<>();
		try {
			FileInputStream fis=new FileInputStream(filePath);
			
			//Workbook workbook=new XSSFWorkbook(fis);
			
			Workbook workbook=new XSSFWorkbook(filePath);
			Sheet sheet = workbook.getSheetAt(0);
			
			Iterator<Row> rows = sheet.rowIterator();
			
			while (rows.hasNext()) {
				Row row = (Row) rows.next();
				
				int rowNum = row.getRowNum();
				if(rowNum==0) {
					continue;
				}
				
				ProductModel productModel=new ProductModel();
				String productId = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
				productModel.setProductId(Long.parseLong(productId+rowNum));
				
				Iterator<Cell> cells = row.cellIterator();
				
				while (cells.hasNext()) {
					Cell cell = (Cell) cells.next();
					
					int columnIndex = cell.getColumnIndex();
					switch (columnIndex) {
					case 0:
					{
						productModel.setProductName(cell.getStringCellValue());
						break;	
					}
					case 1:
					{
						SupplierModel supplierModel=new SupplierModel();
						supplierModel.setSupplierId((long)cell.getNumericCellValue());
						productModel.setSupplier(supplierModel);
						break;
					}
					
					case 2:
					{
						CategoryModel categoryModel=new CategoryModel();
						categoryModel.setCategoryId((long)cell.getNumericCellValue());
						productModel.setCategory(categoryModel);
						break;
					}
					case 3:{
						productModel.setProductQty((int)cell.getNumericCellValue());
						break;
					}
					case 4:{
						productModel.setProductPrice(cell.getNumericCellValue());
						break;
					}
							
					}

					
				}
				list.add(productModel);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	

	@Override
	public String uploadSheet(MultipartFile file) {
		String msg=null;
		try {
			String filePath="uploads";
			String fileName=file.getOriginalFilename();
			
			FileOutputStream fos=new FileOutputStream(filePath+File.separator+fileName);
			
			byte[] data = file.getBytes();
			fos.write(data);
			
			// read data from sheet
			
		List<ProductModel> excelList=	readExcel(filePath+File.separator+fileName);
		for (ProductModel productModel : excelList) {
			
			addProduct(productModel);
			
		}
		
			
		} catch (Exception e) {
			msg="upload failed";
			e.printStackTrace();
		}
		return msg;
	}
}
