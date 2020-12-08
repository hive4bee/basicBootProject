package com.boot08.persistence;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.boot08.domain.Member;

@Repository
public interface MemberRepository extends CrudRepository<Member, String> {
	
	@Query("SELECT COUNT(*) FROM Member WHERE uid=?1 AND upw=?2")
	public int loginPro(String uid, String upw);
	
//	@Modifying
//	@Query("INSERT INTO Member(uid, upw, uname) VALUES (:uid, :upw, :uname)")
//	public int joinMember(String uid, String upw, String uname);

}
