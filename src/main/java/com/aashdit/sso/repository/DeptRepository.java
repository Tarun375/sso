package com.aashdit.sso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aashdit.sso.entity.Department;

/**
 * 
 * @author Tarun Chakrabarty
 * @since 24-Jan-2022
 * 
 *
 */
@Repository
public interface DeptRepository extends JpaRepository<Department, Integer>{

}
