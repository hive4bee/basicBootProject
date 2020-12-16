package com.hive4bee.persistence;

import org.springframework.data.repository.CrudRepository;

import com.hive4bee.domain.WebBoard;

public interface CustomCrudRepository extends CrudRepository<WebBoard, Long>, CustomWebBoard {

}
