package com.hive4bee.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.hive4bee.domain.Member;
import com.hive4bee.persistence.MemberRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberRepositoryTests {
	@Autowired
	private PasswordEncoder pwEncoder;
	
	@Autowired
	private MemberRepository repo;
	
	@Test
	public void joinTests() {
		Member member = new Member();
		member.setUid("test");
		member.setUname("test");
		member.setUpw(pwEncoder.encode("test"));
		repo.save(member);
	}
}
