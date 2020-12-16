package com.hive4bee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hive4bee.domain.WebBoard;
import com.hive4bee.persistence.CustomCrudRepository;
import com.hive4bee.service.WebBoardsService;
import com.hive4bee.vo.PageMaker;
import com.hive4bee.vo.PageVO;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/boards/*")
@Log
public class WebBoardController {
	
	@Autowired
	private WebBoardsService webBoardsService;
	
	@Autowired
	private CustomCrudRepository customCrudRepository;
	
//	@GetMapping("/list")
//	public void list(@ModelAttribute("pageVO") PageVO vo, Model model) {
//		log.info("list() called....");
//		Pageable page = vo.makePageable(0, "bno"); 
//		
//		Page<WebBoard> result = webBoardsService.getList(vo.getType(), vo.getKeyword(), page);
//		
////		log.info("====================================");
////		log.info("result: " + result);//Page 1 of 31 containing com.hive4bee.domain.WebBoard
////		log.info("result.getNumber(): "+result.getNumber());//현재 페이지-1 값이 출력 됨
////		log.info("result.getSize(): "+result.getSize());//한 페이지 당 출력되는 글 수
////		log.info("result.getTotalElements(): "+result.getTotalElements());//검색된 총 글 수
////		log.info("result.getTotalPages(): "+result.getTotalPages());//검색된 총 페이지 수
////		log.info("result.getPageable(): "+result.getPageable());//Page request [number: 30, size 10, sort: bno: DESC]
////		log.info("====================================");
//		
//		model.addAttribute("result", new PageMaker(result));
//	}
	
	@GetMapping("/list")
	public void list(@ModelAttribute("pageVO") PageVO vo, Model model) {
		Pageable page = vo.makePageable(0, "bno");
		Page<Object[]> result = customCrudRepository.getCustomPage(vo.getType(), vo.getKeyword(), page);
		model.addAttribute("result", new PageMaker<>(result));
	}
	
	@GetMapping("/register")
	public void registerGET(@ModelAttribute("vo") WebBoard vo) {
		log.info("register get");
	}
	
	@PostMapping("/register")
	public String registerPOST(@ModelAttribute("vo") WebBoard vo, RedirectAttributes rttr) {
		log.info("register post");
		log.info("vo: " + vo);
		
		webBoardsService.writerBoard(vo);
		rttr.addFlashAttribute("msg", "success");
		return "redirect:/boards/list";
	}
	
	@GetMapping("/view")
	public void view(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model) {
		log.info("bno: " + bno);
		WebBoard webBoard = webBoardsService.viewBoard(bno);
		model.addAttribute("vo", webBoard);
	}
	
	@Secured(value= {"ROLE_BASIC", "ROLE_MANAGER", "ROLE_ADMIN"})
	@GetMapping("/modify")
	public void modify(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model) {
		log.info("Modify BNO: " + bno);
		WebBoard webBoard = webBoardsService.viewBoard(bno);
		model.addAttribute("vo", webBoard);
	}
	
	@Secured(value= {"ROLE_BASIC", "ROLE_MANAGER", "ROLE_ADMIN"})
	@PostMapping("/delete")
	public String delete(Long bno, PageVO vo, RedirectAttributes rttr) {
		log.info("DELETE bno: " + bno);
		
		webBoardsService.delete(bno);
		
		rttr.addFlashAttribute("msg", "success");
		
		//페이징과 검색했던 결과로 이동하는 경우
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());
		
		return "redirect:/boards/list";
		
	}
	
	@Secured(value= {"ROLE_BASIC", "ROLE_MANAGER", "ROLE_ADMIN"})
	@PostMapping("/modify")
	public String modifyPost(WebBoard board, PageVO vo, RedirectAttributes rttr) {
		
		log.info("Modify WebBoard: " + board);
		
		webBoardsService.modify(board);
		rttr.addFlashAttribute("msg", "success");
		rttr.addAttribute("bno", board.getBno());
		
		//페이징과 검색했던 결과로 이동하는 경우
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());
		
		return "redirect:/boards/view";
		
	}

}
