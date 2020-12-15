package com.hive4bee.service;

import com.hive4bee.domain.WebReply;
import com.hive4bee.vo.RepliesPageVO;
import com.hive4bee.vo.StartVO;

public interface WebRepliesService {

	public int addReply(Long bno, WebReply reply);

	public RepliesPageVO getRepliesList(StartVO startVO, Long bno);

	public int deleteReply(Long rno);

	public int modifyReply(Long bno, WebReply webReply);

}
