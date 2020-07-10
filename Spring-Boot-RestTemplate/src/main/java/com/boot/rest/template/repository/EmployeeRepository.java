package com.boot.rest.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.rest.template.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
