package com.hive4bee.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hive4bee.domain.WebBoard;
import com.hive4bee.domain.WebReply;
import com.hive4bee.persistence.WebReplyRepository;
import com.hive4bee.vo.RepliesPageVO;
import com.hive4bee.vo.StartVO;

@Service
public class WebRepliesServiceImpl implements WebRepliesService {
	
	@Autowired
	private WebReplyRepository webReplyRepository;
	
	@Override
	public int addReply(Long bno, WebReply reply) {
		return webReplyRepository.addReply(bno, reply);
	}
	
	@Transactional
	@Override
	public RepliesPageVO getRepliesList(StartVO startVO, Long bno) {
		int cnt = webReplyRepository.getCnt(bno);
		List<WebReply> list = webReplyRepository.selectReplyList(startVO,bno);
		RepliesPageVO repliesPageVO = new RepliesPageVO(cnt, list);
		return repliesPageVO;
	}
	
	@Override
	public int deleteReply(Long rno) {
		return webReplyRepository.deleteReply(rno);
	}
	
	@Override
	public int modifyReply(Long bno, WebReply webReply) {
		WebBoard webBoard = new WebBoard();
		webBoard.setBno(bno);
		webReply.setBoard(webBoard);
		return webReplyRepository.modifyReply(webReply);
	}
}
