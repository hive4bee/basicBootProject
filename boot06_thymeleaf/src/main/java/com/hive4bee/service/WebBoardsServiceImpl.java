package com.hive4bee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hive4bee.domain.WebBoard;
import com.hive4bee.persistence.WebBoardRepository;

@Service
public class WebBoardsServiceImpl implements WebBoardsService {
	
	@Autowired
	private WebBoardRepository webBoardRepository;
	
	@Override
	public Page<WebBoard> getList(String type, String keyword, Pageable page) {
		return webBoardRepository.findAll(webBoardRepository.makePredicate(type, keyword), page);
		
	}
	
	@Override
	public void writerBoard(WebBoard vo) {
		webBoardRepository.save(vo);
	}
	
	@Override
	public WebBoard viewBoard(Long bno) {
		return webBoardRepository.findById(bno).get();
	}
	
	@Override
	public void delete(Long bno) {
		webBoardRepository.deleteById(bno);
	}
	
	@Override
	public void modify(WebBoard board) {
		webBoardRepository.findById(board.getBno()).ifPresent(origin -> {
			origin.setTitle(board.getTitle());
			origin.setContent(board.getContent());
			webBoardRepository.save(origin);
		});
	}
	
}
