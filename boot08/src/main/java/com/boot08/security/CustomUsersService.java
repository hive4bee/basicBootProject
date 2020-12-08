package com.boot08.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.boot08.domain.Member;
import com.boot08.persistence.MemberRepository;

import lombok.extern.java.Log;

@Service
@Log
public class CustomUsersService implements UserDetailsService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("=======================");
		log.info("username: " + username);
//		memberRepository.findById(username).ifPresent(m -> log.info("member: " +m));
		log.info("=======================");
//		User sampleUser = new User(username, "1111", Arrays.asList(new SimpleGrantedAuthority("ROLE_MANAGER"))) ;
		
//		memberRepository.findById(username).filter(m -> m != null).map(m -> new CustomUser(m)).Optional<T>	CustomUser user = null;
//		CustomUser user = null;
//		Optional<Member> opt = memberRepository.findById(username);
//		if(opt.isPresent()) {
//			user= new CustomUser(opt.get());
//			log.info("user: " + user);
//		}
		return memberRepository.findById(username).filter(m -> m != null).map(m -> new CustomUser(m)).get();
		
	}
}

