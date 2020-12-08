package com.boot08.service;

import com.boot08.domain.Member;

public interface MemberService {
	
	public int loginPro(Member vo);

	public void joinMember(Member member);
	
}
