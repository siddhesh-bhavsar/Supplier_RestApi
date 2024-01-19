package com.jbk.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.exceptions.ResourceAlreadyExistException;
import com.jbk.exceptions.ResourceNotExist;
import com.jbk.model.SupplierModel;
import com.jbk.service.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

	@Autowired
	private SupplierService supplierService;

	@PostMapping("/add-supplier")
	public ResponseEntity<String> addSupplier(@RequestBody @Valid SupplierModel supplierModel) {

		int status = supplierService.addSupplier(supplierModel);

		if (status == 1) {
			return new ResponseEntity<String>("Supplier Added Successfully !!", HttpStatus.CREATED);
		} else if (status == 2) {
			throw new ResourceAlreadyExistException("Supplier Already Exists, Check Unique Fields");

		} else {
			return new ResponseEntity<String>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/get-supplier-by-id/{supplier-id}")
	public ResponseEntity<SupplierModel> getSupplierById(@PathVariable("supplier-id") long supplierId) {
		
		SupplierModel supplierModel = supplierService.getSupplierById(supplierId);
		if(supplierModel!=null) {
			return new ResponseEntity<SupplierModel>(supplierModel,HttpStatus.FOUND);
		}else {
			throw new ResourceNotExist("Supplier Not Found - Id = "+supplierId);
		}	
	}
	
	@PutMapping("/update-supplier")
	public ResponseEntity<String> updateSupplier(@RequestBody SupplierModel supplierModel){
		
		supplierService.updateSupplier(supplierModel);
		return new ResponseEntity<String> ("Supplier Updated Successfully !!",HttpStatus.OK);
		//return ResponseEntity.ok("Supplier Updated Successfully !!");
	}
	
	@DeleteMapping("/delete-supplier")
	public ResponseEntity<String> deleteSupplier(@RequestParam long supplierId){
		
		supplierService.deleteSupplier(supplierId);
		return new ResponseEntity<String> ("Supplier Deleted Successfully !!",HttpStatus.OK);
	
	}
	
	@GetMapping("/get-all-supplier")
	public ResponseEntity<List<SupplierModel>> getAllSupplier(){
		
		List<SupplierModel> allSuppliers = supplierService.getAllSuppliers();
		
		return new ResponseEntity<List<SupplierModel>>(allSuppliers,HttpStatus.FOUND);
		
	}

}
