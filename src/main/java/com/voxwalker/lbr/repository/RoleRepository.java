package com.voxwalker.lbr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voxwalker.lbr.entity.Role;


public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(String name);
	

}
