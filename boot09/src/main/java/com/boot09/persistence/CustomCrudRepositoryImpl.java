package com.boot09.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.boot09.domain.QWebBoard;
import com.boot09.domain.QWebReply;
import com.boot09.domain.WebBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.java.Log;
@Log
public class CustomCrudRepositoryImpl extends QuerydslRepositorySupport implements CustomWebBoard {
	
	public CustomCrudRepositoryImpl() {
		super(WebBoard.class);
	}
	
	@Override
	public Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QWebBoard board = QWebBoard.webBoard;
		
		//type if ~ else
		//bno>0
		builder.and(board.bno.gt(0));
		
		//////////////////////////////////
		if(type==null) {
			return builder;
		}
		//////////////////////////////////
		switch(type) {
		case "t":
			builder.and(board.title.like("%"+keyword+"%"));
			break;
		case "c":
			builder.and(board.content.like("%"+keyword+"%"));
			break;
		case "w":
			builder.and(board.writer.like("%"+keyword+"%"));
			break;
		}
		return builder;
	}
	
	@Override
	public Page<Object[]> getCustomPage(String type, String keyword, Pageable page) {
		
		log.info("=================================");
		log.info("TYPE: "+type);
		log.info("KEYWORD: "+keyword);
		log.info("PAGE: "+page);
		log.info("=================================");
		
		QWebBoard b = QWebBoard.webBoard;
		QWebReply r = QWebReply.webReply;
		
		JPQLQuery<WebBoard> query = from(b);
		JPQLQuery<Tuple> tuple = query.select(b.bno, b.title, r.count(), b.writer, b.regdate);
		
		tuple.leftJoin(r);
		tuple.on(b.bno.eq(r.board.bno));
		tuple.where(b.bno.gt(0L));
		
		if(type!=null) {
			switch(type.toLowerCase()) {
			case "t":
				tuple.where(b.title.like("%"+keyword+"%"));
				break;
			case "c":
				tuple.where(b.content.like("%"+keyword+"%"));
				break;
			case "w":
				tuple.where(b.writer.like("%"+keyword+"%"));
				break;
			}
		}
		
		tuple.groupBy(b.bno);
		tuple.orderBy(b.bno.desc());
		tuple.offset(page.getOffset());
		tuple.limit(page.getPageSize());
//		log.info("getOffset(): " + page.getOffset());
//		log.info("getPageSize(): " + page.getPageSize());
//		log.info("tuple: " + tuple.fetch());
		List<Tuple> list = tuple.fetch();
		List<Object[]> resultList = new ArrayList();
		
		list.forEach(t -> {
			resultList.add(t.toArray());
		});
		
		long total = tuple.fetchCount();
		return new PageImpl<>(resultList, page, total);
	}
	
	
	
}
