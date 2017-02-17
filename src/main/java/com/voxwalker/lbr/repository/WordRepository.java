package com.voxwalker.lbr.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.voxwalker.lbr.entity.User;
import com.voxwalker.lbr.entity.Word;

public interface WordRepository extends JpaRepository<Word, Long> {
	@Query("SELECT w.txt FROM Word w JOIN w.user u WHERE w.lang= :lang and w.state=0 and u.id = :user_id ")
	List<String> findByUserAndLangWordKnown(@Param("user_id") Long user_id, @Param("lang") String lang);

	@Query("SELECT w.txt FROM Word w JOIN w.user u WHERE w.lang= :lang and w.state>0 and u.id = :user_id" )
	List<String> findByUserAndLangWordUnknown(@Param("user_id") Long user_id , @Param("lang") String lang);

	Word findByUserAndTxt(User user, String txt);
	List<Word> findByUserAndLangAndState(User user, String lang, int state);
	Page<Word> findByUserAndLangAndState(User user, String lang, int state, Pageable page );

	
	
	

	/*
	 * 
	 * @Query("select u from User u where u.firstname = :#{#customer.firstname}")
	 * List<User> findUsersByCustomersFirstname(@Param("customer") Customer
	 * customer);
	 */
}
