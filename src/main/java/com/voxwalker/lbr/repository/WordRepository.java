package com.voxwalker.lbr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.voxwalker.lbr.entity.User;
import com.voxwalker.lbr.entity.Word;

public interface WordRepository extends JpaRepository<Word, Long> {
	@Query("SELECT w.txt FROM Word w JOIN w.user u WHERE w.state=0 and u.id = :user_id ")
	List<String> findByUserWordKnown(@Param("user_id") Long user_id);

	@Query("SELECT w.txt FROM Word w JOIN w.user u WHERE w.state>0 and u.id = :user_id")
	List<String> findByUserWordUnknown(@Param("user_id") Long user_id );

	Word findByUserAndTxt(User user, String txt);
	

	/*
	 * 
	 * @Query("select u from User u where u.firstname = :#{#customer.firstname}")
	 * List<User> findUsersByCustomersFirstname(@Param("customer") Customer
	 * customer);
	 */
}
