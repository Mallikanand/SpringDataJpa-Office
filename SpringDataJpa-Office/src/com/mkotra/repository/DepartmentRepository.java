package com.mkotra.repository;

import org.springframework.data.repository.CrudRepository;

import com.mkotra.entity.Department;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

	public Department findByName(String name);
}
