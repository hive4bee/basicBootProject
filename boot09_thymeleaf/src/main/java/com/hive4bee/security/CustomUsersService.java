package com.hive4bee.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hive4bee.persistence.MemberRepository;

import lombok.extern.java.Log;

@Service
@Log
public class CustomUsersService implements UserDetailsService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//log.info("UserDetail: " + memberRepository.findById(username).get());
		return memberRepository.findById(username).filter(m -> m != null).map(m -> new CustomUser(m)).get();
	}
	
}
