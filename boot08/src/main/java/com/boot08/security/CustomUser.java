package com.boot08.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.boot08.domain.Member;

public class CustomUser extends User {
	
	private Member member;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public CustomUser(Member dto) {
		
		super(dto.getUid(), 
			  dto.getUpw(), 
			  dto.getRoles().stream()
			  				.map(auth -> new SimpleGrantedAuthority(auth.getRoleName()))
			  				.collect(Collectors.toList())
			  );
		this.member = dto;
	}
	
}
