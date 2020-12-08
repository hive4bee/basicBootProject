package com.boot09.service;

import com.boot09.domain.Member;

public interface MemberService {
	
	public int loginPro(Member vo);

	public void joinMember(Member member);
	
}
