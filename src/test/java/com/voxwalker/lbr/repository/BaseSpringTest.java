package com.voxwalker.lbr.repository;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
//@TransactionConfiguration(transactionManager = "transactionManager",defaultRollback = true)
//@Transactional
public class BaseSpringTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void test() {
		 
	}

}
