package com.spring.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spring.demo.model.Employee;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findByEmail(String email);

    List<Employee> findByDate(Date date);

	// custom query example and return a stream
    @Query("select c from Employee c where c.email = :email")
    Stream<Employee> findByEmailReturnStream(@Param("email") String email);

}