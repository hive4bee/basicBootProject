package com.boot08.test;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.boot08.domain.Member;
import com.boot08.domain.MemberRole;
import com.boot08.persistence.MemberRepository;

import lombok.Setter;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class MemberTests {
	
	@Autowired
	private MemberRepository repo;
	
	@Setter(onMethod_=@Autowired)
	private BCryptPasswordEncoder encoder;
	
	@Test
	public void testInsert() {
		//List<MemberRole> roleList = new ArrayList();
		for(int i=0; i<=100; i++) {
			Member member = new Member();
			member.setUid("user"+i);
			//log.info(encoder.encode("aa3572"));
			member.setUpw(encoder.encode("pw"+i));
			member.setUname("사용자"+i);
			
			MemberRole role = new MemberRole();
			if(i<=80) {
				role.setRoleName("BASIC");
			}else if(i<=90) {
				role.setRoleName("MANAGER");
			}else {
				role.setRoleName("ADMIN");
			}
//			roleList.add(role);
			member.setRoles(Arrays.asList(role));
			
			repo.save(member);
		}
	}
	
//	@Test
//	public void testRead() {
//		Optional<Member> result = repo.findById("user85");
//		result.ifPresent(member -> {
//			log.info("===============");
//			log.info("member"+member);
//			log.info("===============");
//		});
//	}
	
}
