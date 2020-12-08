package com.boot06.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.boot06.domain.WebBoard;

public interface WebBoardService {

	public Page<WebBoard> getListpage(String type, String keyword, Pageable page);
	
	public Page<Object[]> getCustomListPage(String type, String keyword, Pageable page);

	public void registerBoard(WebBoard vo);

	public WebBoard viewBoard(Long bno);

	public WebBoard getModifyForm(Long bno);

	public void deleteBoard(Long bno);

	public int modifyBoard(WebBoard board);
	
}
