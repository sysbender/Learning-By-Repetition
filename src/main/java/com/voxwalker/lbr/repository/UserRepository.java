package com.voxwalker.lbr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voxwalker.lbr.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
