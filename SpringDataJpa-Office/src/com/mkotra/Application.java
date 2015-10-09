package com.mkotra;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.Assert;

import com.mkotra.entity.Department;
import com.mkotra.entity.Employee;
import com.mkotra.repository.DepartmentRepository;

@SpringBootApplication
public class Application implements CommandLineRunner{
	
	@Autowired
	private DepartmentRepository deptRepository;
	
	public static void main(String [] args){
        SpringApplication.run(Application.class);
	}

	@Override
	public void run(String... args) throws Exception {

		Employee hr1 = new Employee("s","l");
		Employee hr2 = new Employee("e","b");
		
		Set<Employee> hrEmployees = new HashSet<Employee>();
		hrEmployees.add(hr1);
		hrEmployees.add(hr2);
		
		Employee fin1 = new Employee("k","d");
		Employee fin2 = new Employee("m","n");
		
		Set<Employee> finEmployees = new HashSet<Employee>();
		finEmployees.add(fin1);
		finEmployees.add(fin2);
		
		Department finDept = new Department("finance",finEmployees);
		Department hrDept = new Department("hr",hrEmployees);
		
		hr1.setDept(hrDept);
		hr2.setDept(hrDept);
		
		fin1.setDept(finDept);
		fin2.setDept(finDept);
		
		//------Save Dept and their employees
		deptRepository.save(finDept);
		deptRepository.save(hrDept);
		
		//------Retrieve saved HR Department
		Department savedHRDept = deptRepository.findByName("hr");
		Assert.isTrue(savedHRDept != null);
		System.out.println("HR DEPARTMENT ID: "+savedHRDept.getId());
		
		Assert.isTrue(savedHRDept.getEmployees()!= null && savedHRDept.getEmployees().size() == 2);
		
		savedHRDept.getEmployees().stream().forEach(
				(e) -> {
						System.out.println(e.getFirstName() +" " + e.getLastName() + " deptId: " + e.getDept().getId());
						}
		);
		
	}

	public DepartmentRepository getRepository() {
		return deptRepository;
	}

	public void setRepository(DepartmentRepository repository) {
		this.deptRepository = repository;
	}

}
