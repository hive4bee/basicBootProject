package com.boot09.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.boot09.persistence.WebBoardRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class WebBoardRepositoryTests {
	@Autowired
	WebBoardRepository repo;
	
//	@Test
//	public void insertBoardDummies() {
//		IntStream.range(0, 300).forEach(i -> {
//			WebBoard board = new WebBoard();
//			
//			board.setTitle("Sample Board Title "+i);
//			board.setContent("Content Sample..."+i+" of Board");
//			board.setWriter("user0"+(i%10));
//			
//			repo.save(board);
//		});
//	}
	
	//단순 리스트 
	//testList1()은 아직 검색 조건을 지정하지 않은 상태의 단순 페이징 처리를 하도록 한다.
	//findAll()을 할 때 Predicate를 생성하는 부분에서 repo.makePredicate(null, null)로 작성된부분
//	@Test
//	public void testList1() {
//		Pageable pageable = PageRequest.of(0, 20, Direction.DESC, "bno");
//		Page<WebBoard> result = repo.findAll(repo.makePredicate(null, null), pageable);
//		
//		log.info("PAGE: "+result.getPageable());
//		log.info("-----------------------------");
//		result.getContent().forEach(board -> log.info(""+board));
//	}
	
//	@Test
//	public void testList2() {
//		Pageable pageable = PageRequest.of(0, 20, Direction.DESC, "bno");
//		Page<WebBoard> result = repo.findAll(repo.makePredicate("t", "10"), pageable);
//		
//		log.info("PAGE: "+result.getPageable());
//		log.info("--------------------------------");
//		result.getContent().forEach(board -> log.info(""+board));
//	}
}
