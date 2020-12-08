package com.boot09.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot09.domain.WebBoard;
import com.boot09.domain.WebReply;
import com.boot09.service.WebReplyService;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/replies/")
@Log
public class WebReplyController {
	 
	@Autowired
	private WebReplyService replyService;
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/{bno}")
	public ResponseEntity<String> addReply(@PathVariable Long bno, @RequestBody WebReply reply){
		
		log.info("addReply................");
		log.info("BNO: " + bno);
		log.info("REPLY: " + reply);
		
		int result=replyService.insertReply(bno, reply);
		return result == 1 ? 
				new ResponseEntity<>("success",HttpStatus.CREATED) :
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	@GetMapping("/{bno}")
	public ResponseEntity<List<WebReply>> getReplies(@PathVariable("bno") Long bno){
		log.info("get All Replies....................");
		WebBoard board = new WebBoard();
		board.setBno(bno);
		List<WebReply> list = replyService.getListByBno(board);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping("/delete/{rno}")
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
		
		log.info("delete reply: " + rno);
		int result = replyService.deleteReplyById(rno);
		
		return result == 1 ?
				new ResponseEntity<>("success", HttpStatus.OK):
					new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/modify")
	public ResponseEntity<String> modify(@RequestBody WebReply reply){
		log.info("modify reply: " + reply);
		int result = replyService.modifyBoardById(reply);
		
		return result == 1 ?
				new ResponseEntity<>("success", HttpStatus.OK) :
					new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
