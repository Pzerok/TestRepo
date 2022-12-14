package com.pdf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/*
 * 2.1.7 은 Laowagie라는 도메인을 갖고 있었던 마지막 버전이다. 일단 테스트용으로 2.1.7로 구성해봤지만,
 * 전자문서 서명의 변경 등.. 여러 이유로 5 버전을 사용할 예정이다.(이것도 업데이트는 더 이상 없다.)
 * 
 * --------------------------------------------------------------------------------------------
 * https://stackoverflow.com/questions/13515210/what-is-the-difference-between-lowagie-and-itext
 * 개발자가 직접 품팔이한 것을 보자면 결과적으로 7이 상용화될 것을 염두해두고 새로 모듈화를 시킨 것이며 꾸준히 업뎃중.
 * 혼자 사용하는데 있어서는 가능하지만 상용화 사용(회사 등) 억대급을 부른다고 한다..
 * 
 * ---------------------------------------------------------------------------------------------
 * 
 * PDF (Itext 2.1.7)
 * 
 * <기본 설정>
 * 1. pom.xml으로 의존성 추가
 * 
 * 2. root-context
 * -  
 * 
 * 
 */
@Controller
public class PdfController {
	
	@RequestMapping(value = "/download/pdf", method = RequestMethod.GET)
	public ModelAndView downloadPdf() {
	    ModelAndView modelAndView = new ModelAndView("pdfDownView", "fileName", "test.pdf");
	    return modelAndView;
	}
	
	private static Logger logger = LoggerFactory.getLogger(PdfController.class);
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	// RequestMapping 은 <a> 태그와 일치하면 되며 원하는 출력단을 pdf로서 동작시키기 위해선 return 뷰 이름에 pdf가 포함되어야하며 
	// 이 뷰 리턴이 bean 객체에 포함된다.
	@RequestMapping(value="test", method=RequestMethod.GET)
	public String pdfList(Model model){
		
		List<String> list = new ArrayList<String>();
		list.add("Java");
		list.add("파이썬");
		list.add("R");
		list.add("C++");
		list.add("자바스크립트");
		list.add("Ruby");
		list.add("스칼라");
		list.add("클로져");
		list.add("자바");
		
		//뷰에게 전달할 데이터 저장
		model.addAttribute("list",list);
		
		//출력할 뷰 이름 리턴 _ Servlet-context에서 설정한 bean값의 id와 일치하면 AbstractPdfView가 동작하여 pdf문서를 작성한다.
		return "pdf";
	}
	@RequestMapping(value="test2", method=RequestMethod.GET)
	public String pdfMap(Model model){
		Map<String, String> map = new HashMap<String, String>();
		String[] names = {"Java","파이썬","R","C++","자바스크립트","Ruby","타입스크립트","리액트","뷰","JPA","jQuery"};
		for(int i = 0 ; i < 9 ; i++) {
			map.put(i+"",names[i] );
		}
		logger.info("map.toString()"+map.toString());
		//뷰에게 전달할 데이터 저장
		model.addAttribute("map",map);
		
		//출력할 뷰 이름 리턴 _ Servlet-context에서 설정한 bean값의 id와 일치하면 AbstractPdfView가 동작하여 pdf문서를 작성한다.
		return "pdf";
	}
}