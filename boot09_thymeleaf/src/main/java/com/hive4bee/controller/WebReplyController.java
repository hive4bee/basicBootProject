package com.hive4bee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hive4bee.domain.WebBoard;
import com.hive4bee.domain.WebReply;
import com.hive4bee.service.WebRepliesService;
import com.hive4bee.vo.RepliesPageVO;
import com.hive4bee.vo.StartVO;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/replies/")
@Log
public class WebReplyController {
	
	@Autowired
	private WebRepliesService webRepliesService;
	
	@Secured(value= {"ROLE_BASIC", "ROLE_MANAGER", "ROLE_ADMIN"})
	@PostMapping("/{bno}")
	public ResponseEntity<String> addReply(@PathVariable("bno") Long bno, @RequestBody WebReply reply){
		log.info("addReply......................");
		log.info("bno: " + bno);
		log.info("reply: " + reply);
		WebBoard webBoard = new WebBoard();
		webBoard.setBno(bno);
		reply.setBoard(webBoard);
		return webRepliesService.addReply(bno, reply)==1 ?
				new ResponseEntity<>("success", HttpStatus.OK) :
					new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/{bno}/{page}")
	public ResponseEntity<RepliesPageVO> getRepliesList(@PathVariable("bno") Long bno, @PathVariable("page") int page){
		log.info("getRepliesList..................");
		StartVO startVO = new StartVO(page);
		RepliesPageVO repliesPageVO = webRepliesService.getRepliesList(startVO, bno);
		return new ResponseEntity<>(repliesPageVO, HttpStatus.OK);
	}
	
	@Secured(value= {"ROLE_BASIC", "ROLE_MANAGER", "ROLE_ADMIN"})
	@DeleteMapping("/{rno}")
	public ResponseEntity<String> deleteReply(@PathVariable("rno") Long rno){
		return webRepliesService.deleteReply(rno) == 1 ?
				new ResponseEntity<>("success", HttpStatus.OK):
					new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Secured(value= {"ROLE_BASIC", "ROLE_MANAGER", "ROLE_ADMIN"})
	@PutMapping("/{bno}")
	public ResponseEntity<String> modifyReply(@PathVariable("bno") Long bno, @RequestBody WebReply webReply){
		log.info("modify reply: " + webReply);
		return webRepliesService.modifyReply(bno, webReply) == 1 ?
				new ResponseEntity<>("success", HttpStatus.OK):
					new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
