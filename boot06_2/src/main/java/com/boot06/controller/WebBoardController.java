package com.boot06.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot06.domain.WebBoard;
import com.boot06.service.WebBoardService;
import com.boot06.vo.PageMaker;
import com.boot06.vo.PageVO;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/boards/*")
@Log
public class WebBoardController {
	
	@Autowired
	private WebBoardService service;
	
//	@GetMapping("/list")
//	public void list(@PageableDefault(direction=Sort.Direction.DESC, sort="bno", size=10, page=0) Pageable page) {
//		log.info("list() called..." + page);
//	}
	@GetMapping("/list")
	public void list(@ModelAttribute("pageVO") PageVO vo, Model model) {
		Pageable page = vo.makePageable(0, "bno");
		
		//Page<WebBoard> result = service.getListpage(vo.getType(), vo.getKeyword(), page);
//		log.info("page: "+page);
//		log.info("result: "+result);
//		log.info("result.getPageable(): "+result.getPageable());
//		log.info("result.getContent(): "+result.getContent());
		Page<Object[]> result = service.getCustomListPage(vo.getType(), vo.getKeyword(), page); 
//		log.info("================================");
//		log.info("TOTAL PAGES: " + result.getTotalPages());
//		log.info("TOTAL SIZE: " + result.getTotalElements());
//		result.getContent().forEach(arr -> {
//			log.info(Arrays.toString(arr));
//		});
//		log.info("================================");
		
		model.addAttribute("result", new PageMaker(result));
		
	}
	
	@GetMapping("/register")
	public void registerGET(@ModelAttribute("vo") WebBoard vo) {
		log.info("register get");
	}
	
	@PostMapping("/register")
	public String registerPOST(@ModelAttribute("vo") WebBoard vo, RedirectAttributes rttr) {
		log.info("register post");
		log.info("vo: " + vo);
		
		service.registerBoard(vo);
		rttr.addFlashAttribute("msg", "success");
		return "redirect:/boards/list";
	}
	
	@GetMapping("/view")
	public void view(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model) {
		log.info("bno: " + bno);
		WebBoard board = service.viewBoard(bno);
		model.addAttribute("vo", board);
	}
	
	@GetMapping("/modify")
	public void modify(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model) {
		log.info("MODIFY BNO: "+bno);
		WebBoard board = service.getModifyForm(bno);
		model.addAttribute("vo", board);
	}
	
	@PostMapping("/modify")
	public String modifyPost(WebBoard board, PageVO vo, RedirectAttributes rttr) {
		log.info("Modify WebBoard: " + board);
		
		int result = service.modifyBoard(board);
		log.info("====================");
		log.info("result: " + result);
		log.info("====================");
		String red = null;
		if(result == 1) {
			rttr.addFlashAttribute("msg", "success");
			rttr.addAttribute("bno", board.getBno());
			rttr.addAttribute("page", vo.getPage());
			rttr.addAttribute("size", vo.getSize());
			rttr.addAttribute("type", vo.getType());
			rttr.addAttribute("keyword", vo.getKeyword());
			red = "redirect:/boards/view";
		}else {
			red = null;
		}

		
		return red;
	}
	
	@PostMapping("/delete")
	public String delete(Long bno, PageVO vo, RedirectAttributes rttr) {
		log.info("DELETE BNO: " + bno);
		service.deleteBoard(bno);
		rttr.addFlashAttribute("msg", "success");
		
		//페이징과 검색했던 결과로 이동하는 경우
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());
		
		return "redirect:/boards/list";
	}
	
}
