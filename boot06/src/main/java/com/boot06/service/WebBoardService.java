package com.boot06.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.boot06.domain.WebBoard;
import com.boot06.vo.PageVO;

public interface WebBoardService {

	Page<WebBoard> getListpage(String type, String keyword, Pageable page);

	void registerBoard(WebBoard vo);

	WebBoard viewBoard(Long bno);

	WebBoard getModifyForm(Long bno);

	void deleteBoard(Long bno);
	
}
