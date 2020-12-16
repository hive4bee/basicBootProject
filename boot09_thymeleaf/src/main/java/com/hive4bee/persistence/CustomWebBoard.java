package com.hive4bee.persistence;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;

public interface CustomWebBoard {
	
	public Page<Object[]> getCustomPage(String type, String keyword, Pageable page);
	
}
