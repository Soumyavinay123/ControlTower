package com.soumya.user.org.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soumya.user.org.domain.UserDetailsDomain;

public interface UserRepository extends JpaRepository<UserDetailsDomain, String> {

	Optional<UserDetailsDomain> findByUserName(String userName);

}
