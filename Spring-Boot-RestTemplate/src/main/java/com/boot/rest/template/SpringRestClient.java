package com.boot.rest.template;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.boot.rest.template.model.Employee;

public class SpringRestClient {
	private static final String GET_EMPLOYEES_ENDPOINT_URL = "http://localhost:8088//api/v1/employees";
	private static final String GET_EMPLOYEE_ENDPOINT_URL = "http://localhost:8088//api/v1/employees/{id}";
	private static final String CREATE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8088//api/v1/employees";
	private static final String UPDATE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8088//api/v1/employees/{id}";
	private static final String DELETE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8088//api/v1/employees/{id}";
	
	public static RestTemplate restTemplate = new RestTemplate();
	
	public static void main(String[] args) {
		SpringRestClient springRestClient = new SpringRestClient();
		
		//step1: frist create a new employee
		springRestClient.createEmployee();
		
		//step2: get new created employee from step1
		springRestClient.getEmployeeById();
		
		//step3: get all employees
		springRestClient.getEmployees();
		
		//step4: update employee with id= 1
		springRestClient.updateEmployee();
		
		//step5: delete employee with id=1;
		springRestClient.deleteEmployee();
	}


	private void createEmployee() {
		Employee newEmpoyee = new Employee("admin", "admin", "admin@gmail.com");
		
		RestTemplate restTemplate = new RestTemplate();
		Employee result = restTemplate.postForObject(CREATE_EMPLOYEE_ENDPOINT_URL, newEmpoyee, Employee.class);
		System.out.println(result);
	}

	private void getEmployeeById() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		
		RestTemplate restTemplate = new RestTemplate();
		Employee result = restTemplate.getForObject(GET_EMPLOYEE_ENDPOINT_URL, Employee.class, params);
		
		System.out.println(result);
		
	}

	private void getEmployees() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		ResponseEntity<String> result = restTemplate.exchange(GET_EMPLOYEES_ENDPOINT_URL, HttpMethod.GET, entity, String.class);
		System.out.println(result);
		
		ResponseEntity<Employee[]> responseEntity = restTemplate.getForEntity(GET_EMPLOYEES_ENDPOINT_URL, Employee[].class);
		List<Employee> employees = Arrays.asList(responseEntity.getBody());			
		
	}
	
	private void updateEmployee() {
		Map<String, String> params = new HashMap<String,String>();
		params.put("id", "1");
		Employee updateEmployee = new Employee("admin123", "admin123", "admin123@gmail.com");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(UPDATE_EMPLOYEE_ENDPOINT_URL, updateEmployee, params);
		
	}
	
	private void deleteEmployee() {
		Map<String, String> params = new HashMap<String,String>();
		params.put("id", "1");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(DELETE_EMPLOYEE_ENDPOINT_URL, params);
	}

	
}
