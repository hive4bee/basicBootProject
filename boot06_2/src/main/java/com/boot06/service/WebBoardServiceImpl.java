package com.boot06.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.boot06.domain.WebBoard;
import com.boot06.persistence.CustomCrudRepository;

import lombok.extern.java.Log;

@Service
@Log
public class WebBoardServiceImpl implements WebBoardService {
	
//	@Autowired
//	private WebBoardRepository repo;
	@Autowired
	private CustomCrudRepository repo;
	
	@Override
	public Page<WebBoard> getListpage(String type, String keyword, Pageable page) {
		return repo.findAll(repo.makePredicate(type, keyword), page);
	}
	
	@Override
	public Page<Object[]> getCustomListPage(String type, String keyword, Pageable page) {
		return repo.getCustomPage(type, keyword, page);
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
	
	@Transactional
	@Override
	public int modifyBoard(WebBoard board) {
		log.info("============================");
		log.info("webBoard: " + board);
		log.info("============================");
//		repo.findById(board.getBno()).ifPresent(origin -> {
//			origin.setTitle(board.getTitle());
//			origin.setContent(board.getContent());
//			
//			repo.save(origin);
//		});
		int result = 0;
		if(repo.findById(board.getBno()).isPresent()) {
			result = repo.modifyBoard(board.getBno(), board.getTitle(), board.getContent());
		}
		
		return result;
	}
	
	@Override
	public void deleteBoard(Long bno) {
		repo.deleteById(bno);
	}
	
}
