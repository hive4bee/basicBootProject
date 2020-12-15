package com.hive4bee.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.hive4bee.persistence.WebReplyRepository;
import com.hive4bee.vo.StartVO;

import lombok.extern.java.Log;

@SpringBootTest
@RunWith(SpringRunner.class)
@Log
@Commit
public class WebReplyRepositoryTests {
	@Autowired
	private WebReplyRepository webReplyRepository;
	
//	@Test
//	public void entityManagerGetListTests() {
//		StartVO startVO = new StartVO();
//		
//		List<WebReply> list = webReplyRepository.selectReplyList(startVO,303L);
//		list.forEach(reply -> log.info("reply: " + reply));
//	}
	
//	@Test
//	public void entityManagerAddReplyTests() {
//		WebBoard board = new WebBoard();
//		board.setBno(311L);
//		WebReply reply = new WebReply();
//		reply.setReplyer("user00");
//		reply.setReplyText("from sts test");
//		reply.setBoard(board);
//		int result = webReplyRepository.addReply(board.getBno(), reply);
//		log.info("=================================");
//		log.info("result: " + result + " / successfully inserted....");
//		log.info("=================================");
//	}
	
//	@Test
//	public void entityManagerGetCntTests() {
//		int cnt = webReplyRepository.getCnt(311L);
//		log.info("=========================");
//		log.info("result: " + cnt);
//		log.info("=========================");
//	}
	
//	@Test
//	public void entityManagerDeleteReplyTests() {
//		int result = webReplyRepository.deleteReply(71L);
//		log.info("=========================");
//		log.info("result: " + result);
//		log.info("=========================");
//	}
	
//	@Test
//	public void entityManagerModifyReplyTests() {
//		WebReply webReply = new WebReply();
//		webReply.setRno(72L);
//		webReply.setReplyText("from sts junit test");
//		int result = webReplyRepository.modifyReply(webReply);
//		log.info("===============");
//		log.info("result: " + result);
//		log.info("===============");
//	}
	
	@Test
	public void entityManagerGetListWithRCountTests() {
		StartVO startVO = new StartVO();
		List<Object[]> list = webReplyRepository.selectReplyListWithRCount(startVO);
		list.forEach(board -> log.info("board: " +Arrays.toString(board)));
	}
	
}
