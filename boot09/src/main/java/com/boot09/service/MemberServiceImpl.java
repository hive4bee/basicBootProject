package com.boot09.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.boot09.domain.Member;
import com.boot09.persistence.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public int loginPro(Member vo) {
		int result = memberRepository.loginPro(vo.getUid(), vo.getUpw());
		return result;
	}
	
	@Transactional
	@Override
	public void joinMember(Member member) {
		//int result = memberRepository.joinMember(member.getUid(), passwordEncoder.encode(member.getUpw()), member.getUname());
		member.setUpw(passwordEncoder.encode(member.getUpw()));
		memberRepository.save(member);
	}
	
}
