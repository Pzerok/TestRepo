package com.org.test.controller.web;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.org.test.ServiceResult;
import com.org.test.controller.service.IReviewService;
import com.org.test.vo.ReviewVO;

@Controller
@RequestMapping("/review")
public class ReviewInsertController {
	
	@Inject
	private IReviewService service;
	
	@RequestMapping(value = "/form.do",method = RequestMethod.GET)
	public String reviewForm() {
		return "reviewboard/form";
	}
	
	@RequestMapping(value = "/form.do", method = RequestMethod.POST)
	public String reviewInsert(ReviewVO reviewVO, Model model) {
		String goPage = "";
		Map<String, String> errors = new HashMap<String, String>();
		
		if(StringUtils.isBlank(reviewVO.getReTitle())) {
			errors.put("reTitle", "제목 입력해");
		}
		if(StringUtils.isBlank(reviewVO.getReContent())) {
			errors.put("reContent", "내용입력해");
		}
		
		if(errors.size()>0) {
			model.addAttribute("errors",errors);
			model.addAttribute("reviewVO",reviewVO);
			goPage = "reviewboard/form";
			
		}else {
			reviewVO.setReWriter("test");
			ServiceResult result = service.insertReview(reviewVO);
			if(result.equals(ServiceResult.OK)) {
				goPage = "redirect:/review/list.do";
			}else {
				model.addAttribute("message", "서버에러, 다시시도.");
				goPage = "reviewboard/form";
			}
		}
		return goPage;
	}
	
}
