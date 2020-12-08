package com.boot06.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.boot06.domain.WebBoard;
import com.boot06.persistence.WebBoardRepository;
import com.boot06.vo.PageVO;

@Service
public class WebBoardServiceImpl implements WebBoardService {
	
	@Autowired
	private WebBoardRepository repo;
	
	@Override
	public Page<WebBoard> getListpage(String type, String keyword, Pageable page) {
		return repo.findAll(repo.makePredicate(type, keyword), page);
	}
	
	@Override
	public void registerBoard(WebBoard vo) {
		repo.save(vo);
	}
	
	@Override
	public WebBoard viewBoard(Long bno) {
		return repo.findById(bno).get();
	}
	
	@Override
	public WebBoard getModifyForm(Long bno) {
		return repo.findById(bno).get();
	}
	
	@Override
	public void deleteBoard(Long bno) {
		repo.deleteById(bno);
	}
	
}
