package com.hive4bee.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hive4bee.domain.WebBoard;

public interface WebBoardsService {

	public Page<WebBoard> getList(String type, String keyword, Pageable page);

	public void writerBoard(WebBoard vo);

	public WebBoard viewBoard(Long bno);

	public void delete(Long bno);

	public void modify(WebBoard board);
	

}
