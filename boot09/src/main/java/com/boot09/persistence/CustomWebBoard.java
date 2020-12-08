package com.boot09.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

public interface CustomWebBoard {
	public Predicate makePredicate(String type, String keyword);
	
	public Page<Object[]> getCustomPage(String type, String keyword, Pageable page);
}
