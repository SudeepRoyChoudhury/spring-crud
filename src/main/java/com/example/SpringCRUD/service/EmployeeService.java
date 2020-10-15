package com.example.SpringCRUD.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringCRUD.Repository.EmployeeRepository;
import com.example.SpringCRUD.entity.EmployeeEntity;
import com.example.SpringCRUD.exception.RecordNotFoundException;

@Service
public class EmployeeService {

   @Autowired
   private EmployeeRepository employeeRepository;

    public List<EmployeeEntity> getAllEmployees(){
	     List<EmployeeEntity> employeeList = employeeRepository.findAll();
	     if(employeeList.size() > 0)
	       {
	        	return employeeList;
	       }
	       else{
		       return new ArrayList<EmployeeEntity>();
	       }
	
       }

    public EmployeeEntity getEmployeeById(Long Id) throws RecordNotFoundException{
	     Optional<EmployeeEntity> employeeList = employeeRepository.findById(Id);
	
	     if(employeeList!=null) {
		     return employeeList.get();
	      }
	       else {
		    throw new RecordNotFoundException("No Employee Record found for the given Id:");
	     } 
      }

    public EmployeeEntity createOrUpdate(EmployeeEntity entity) throws RecordNotFoundException{
	   Optional<EmployeeEntity> employeeList=employeeRepository.findById(entity.getId());
	
	   if(employeeList.isPresent()) {
		  EmployeeEntity newEntity = employeeList.get();
		  newEntity.setEmail(entity.getEmail());
		  newEntity.setFirstName(entity.getFirstName());
		  newEntity.setLastName(entity.getLastName());
		
		  newEntity=employeeRepository.save(newEntity);
		  return newEntity;
	     }else {
	    	entity=employeeRepository.save(entity);
		    return entity;
	    }
		
     }

    public void deleteEmployeeById(Long Id) throws RecordNotFoundException{
	    Optional<EmployeeEntity> employeeList=employeeRepository.findById(Id);
	
	    if (employeeList.isPresent())
	     {
		    employeeRepository.deleteById(Id);
	     }else {
		     throw new RecordNotFoundException("No Employee Record found for the given Id:");
	     }	
	 }
}
