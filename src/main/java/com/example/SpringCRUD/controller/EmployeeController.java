package com.example.SpringCRUD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringCRUD.Repository.EmployeeRepository;
import com.example.SpringCRUD.entity.EmployeeEntity;
import com.example.SpringCRUD.exception.RecordNotFoundException;
import com.example.SpringCRUD.service.EmployeeService;

@RestController
@RequestMapping("/cision")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping(path="/employees")
	public ResponseEntity<List<EmployeeEntity>> getAllEmployees(){
		List<EmployeeEntity> list = employeeService.getAllEmployees();
		return new ResponseEntity<List<EmployeeEntity>>(list,new HttpHeaders(),HttpStatus.OK);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id") Long id) throws RecordNotFoundException{
		EmployeeEntity entity;
		try{
			entity = employeeService.getEmployeeById(id);
		}catch(RecordNotFoundException e) {
			return new ResponseEntity<EmployeeEntity>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<EmployeeEntity>(entity,new HttpHeaders(),HttpStatus.OK);
	}
	
	@PostMapping(path="/createOrUpdate")
	public ResponseEntity<EmployeeEntity> createOrUpdateEmployee(@RequestBody EmployeeEntity entity) throws RecordNotFoundException{
		EmployeeEntity updatedEntity = employeeService.createOrUpdate(entity);
		return new ResponseEntity<EmployeeEntity>(updatedEntity,new HttpHeaders(),HttpStatus.OK);	
	}
	
	@DeleteMapping(path="/deleteEmployee/{id}")
	public ResponseEntity<Object> deleteEmployeeById(@PathVariable("id") Long id) throws RecordNotFoundException{
		employeeService.deleteEmployeeById(id);
		return ResponseEntity.ok().build();
		
	}
}
