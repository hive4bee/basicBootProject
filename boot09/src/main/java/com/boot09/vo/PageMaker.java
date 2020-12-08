package com.boot09.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;

@Getter
@ToString(exclude="pageList")
@Log
public class PageMaker<T> {
	
	private Page<T> result;//[number:xx, size xx, sort: bno: DESC]
	private Pageable prevPage;//페이지 목록의 맨 앞인 이전으로 이동하는데 필요한 정보를 가진 Pageable
	private Pageable nextPage;//페이지 목록의 맨 뒤인 다음으로 이동하는데 필요한 정보를 가진 Pageable
	
	private int currentPageNum;
	private int totalPageNum;
	
	private Pageable currentPage;//현재 페이지의 정보를 가진 Pageable
	
	private List<Pageable> pageList;//페이지 번호의 시작부터 끝까지의 Pageable들을 지정한 리스트
	
	public PageMaker(Page<T> result) {
		this.result = result;
		this.currentPage = result.getPageable();
		this.currentPageNum = currentPage.getPageNumber()+1;
		this.totalPageNum = result.getTotalPages();
		this.pageList=new ArrayList<>();
		calcPages();
	}
	private void calcPages() {
		int tempEndNum = (int)(Math.ceil(this.currentPageNum/10.0)*10);
		int startNum = tempEndNum-9;
		Pageable startPage = this.currentPage;
		//move to start Pageable
		for(int i = startNum; i<this.currentPageNum; i++) {
			startPage = startPage.previousOrFirst();
		}
		this.prevPage = startPage.getPageNumber() <= 0 ? null : startPage.previousOrFirst();
		//log.info("tempEndNum: "+ tempEndNum);
		//log.info("total: "+totalPageNum);
		if(this.totalPageNum < tempEndNum) {
			tempEndNum = this.totalPageNum;
			this.nextPage = null;
		}
		for(int i = startNum; i<=tempEndNum; i++) {
			pageList.add(startPage);
			startPage = startPage.next();
		}
		this.nextPage = startPage.getPageNumber() + 1 < totalPageNum ? startPage : null;
	}
	
}
