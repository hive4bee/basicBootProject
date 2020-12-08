package com.boot09.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot09.domain.WebBoard;
import com.boot09.domain.WebReply;
import com.boot09.persistence.WebReplyRepository;

import lombok.extern.java.Log;

@Service
@Log
public class WebReplyServiceImpl implements WebReplyService {
	
	@Autowired
	private WebReplyRepository replyRepo;
	
	@Override
	public int insertReply(Long bno, WebReply reply) {
		WebBoard board = new WebBoard();
		board.setBno(bno);
		reply.setBoard(board);
		WebReply webReply = replyRepo.save(reply);
		return webReply != null ? 1 : -1; 
	}
	
	@Override
	public List<WebReply> getListByBno(WebBoard board) {
		List<WebReply> list = replyRepo.getRepliesOfBoard(board);
		return list;
	}
	
	@Override
	public int deleteReplyById(Long rno) {
		int result = 0;
		if(replyRepo.findById(rno).isPresent()) {
			replyRepo.deleteById(rno);
			result=1;
		}else {
			result=-1;
		}
		return result;
	}
	
	@Transactional
	@Override
	public int modifyBoardById(WebReply reply) {
		int result = replyRepo.modifyReply(reply.getRno(),reply.getReplyText());
		
		return result;
	}
	
}
