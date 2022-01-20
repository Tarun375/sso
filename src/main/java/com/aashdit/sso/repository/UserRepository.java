package com.aashdit.sso.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aashdit.sso.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByUserName(String username);
	
	@Modifying
	@Transactional
	@Query("update sso_usermaster u set u.password = ?2 where u.userName = ?1")
	public void resetSuccess(String username, String password);
	

}
