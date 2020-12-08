package com.boot09.persistence;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.boot09.domain.QWebBoard;
import com.boot09.domain.WebBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface CustomCrudRepository extends CrudRepository<WebBoard, Long>, QuerydslPredicateExecutor<WebBoard>, CustomWebBoard {
	
//	public default Predicate makePredicate(String type, String keyword) {
//		BooleanBuilder builder = new BooleanBuilder();
//		QWebBoard board = QWebBoard.webBoard;
//		
//		//type if ~ else
//		//bno>0
//		builder.and(board.bno.gt(0));
//		
//		//////////////////////////////////
//		if(type==null) {
//			return builder;
//		}
//		//////////////////////////////////
//		switch(type) {
//		case "t":
//			builder.and(board.title.like("%"+keyword+"%"));
//			break;
//		case "c":
//			builder.and(board.content.like("%"+keyword+"%"));
//			break;
//		case "w":
//			builder.and(board.writer.like("%"+keyword+"%"));
//			break;
//		}
//		return builder;
//		//////////////////////////////////
//		
//	}
	
	@Modifying
	@Query("UPDATE WebBoard SET title=?2, content=?3 WHERE bno=?1")
	public int modifyBoard(Long bno, String title, String content);
	
}
