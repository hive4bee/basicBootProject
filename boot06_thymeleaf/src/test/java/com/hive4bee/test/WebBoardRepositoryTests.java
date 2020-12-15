package com.hive4bee.test;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.hive4bee.domain.WebBoard;
import com.hive4bee.persistence.WebBoardRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class WebBoardRepositoryTests {
	
	@Autowired
	private WebBoardRepository webBoardRepository;
	
	
	@Test
	public void insertBoardDummies() {
		IntStream.range(0, 300).forEach(i -> {
			WebBoard board = new WebBoard();
			board.setTitle("Sample Board Title"+i);
			board.setContent("Content Sample"+i);
			board.setWriter("user0"+(i%10));
			webBoardRepository.save(board);
		});
	}
	
	
	
}
