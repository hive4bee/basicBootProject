package com.hive4bee.test;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.hive4bee.persistence.CustomCrudRepository;

import lombok.extern.java.Log;

@SpringBootTest
@RunWith(SpringRunner.class)
@Log
public class CustomRepositoryTests {
	
	@Autowired
	private CustomCrudRepository customCrudRepository;
	
	@Test
	public void customCrudRepositoryTests() {
		Pageable pageable = PageRequest.of(0, 10, Direction.DESC, "bno");
		
		String type = "w";
		String keyword="user09";
		
		Page<Object[]> result = customCrudRepository.getCustomPage(type, keyword, pageable);
		
		log.info("result: " + result);
		log.info("TOTAL PAGES: " + result.getTotalPages());
		log.info("TOTAL SIZE: " + result.getTotalElements());
		
		result.getContent().forEach(arr -> {
			log.info(Arrays.deepToString(arr));
		});
	}
}
