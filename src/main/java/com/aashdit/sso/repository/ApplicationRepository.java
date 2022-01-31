package com.aashdit.sso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aashdit.sso.entity.Application;

/**
 * @author Tarun Chakrabarty.
 *
 * 29-Jan-2022
 */
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer>{

}
