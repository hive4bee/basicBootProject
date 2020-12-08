package com.boot06.service;

import java.util.List;

import com.boot06.domain.WebBoard;
import com.boot06.domain.WebReply;

public interface WebReplyService {

	int insertReply(Long bno, WebReply reply);

	List<WebReply> getListByBno(WebBoard board);

	int deleteReplyById(Long rno);

	int modifyBoardById(WebReply reply);

}
