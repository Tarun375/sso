package com.aashdit.sso.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aashdit.sso.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByUserName(String username);

	public List<User> findByUserNameOrMobileNoOrEmail(String username, String mobileNo, String email);

	@Modifying
	@Transactional
	@Query(value = "update User u set u.password = :password where u.userName = :userName")
	public Integer resetSuccess(@Param(value = "userName") String userName, @Param(value = "password") String password);

	public List<User> findByDepartmentName(String departmentName);

}
