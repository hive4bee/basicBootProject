package com.boot06.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.boot06.domain.WebBoard;
import com.boot06.domain.WebReply;

public interface WebReplyRepository extends CrudRepository<WebReply, Long> {
	
	@Query("SELECT r FROM WebReply r WHERE r.board =?1 "
			+ "AND r.rno > 0 ORDER BY r.rno ASC")
	public List<WebReply> getRepliesOfBoard(WebBoard board);
	
	@Modifying
	@Query("UPDATE WebReply r SET r.replyText=?2 WHERE r.rno=?1")
	public int modifyReply(Long rno, String replyText);
	
	

}
