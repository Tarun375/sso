package com.aashdit.sso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aashdit.sso.entity.Department;

@Repository
public interface DeptRepository extends JpaRepository<Department, Integer>{

}
