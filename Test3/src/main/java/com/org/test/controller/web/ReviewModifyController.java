package com.org.test.controller.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.org.test.ServiceResult;
import com.org.test.controller.service.IReviewService;
import com.org.test.vo.ReviewVO;

@Controller
@RequestMapping("/review")
public class ReviewModifyController {
	
	@Inject
	private IReviewService service;
	
	@RequestMapping(value = "/update.do",method = RequestMethod.GET)
	public String reviewUpdateForm(int reNo, Model model) {
		ReviewVO reviewVO = service.detail(reNo);
		model.addAttribute("reviewVO",reviewVO);
		model.addAttribute("status","mod");
		return "reviewboard/form";
	}
	
	@RequestMapping(value = "/update.do",method = RequestMethod.POST)
	public String reviewUpdate(ReviewVO reviewVO, Model model) {
		String goPage = "";
		ServiceResult result = service.update(reviewVO);
		
		if(result.equals(ServiceResult.OK)) {
			goPage = "redirect:/review/detail.do?reNo=" + reviewVO.getReNo();
		}else {
			model.addAttribute("message", "수정에 실패하였습니다.");
			model.addAttribute("reviewVO", reviewVO);
			goPage = "reviewboard/form";
		}
		return goPage;
	}
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public String reviewDelete(int reNo, Model model) {
		String goPage = "";
		ServiceResult result = service.delete(reNo);
		
		if(result.equals(ServiceResult.OK)) {
			goPage = "redirect:/review/list.do";
		}else {
			model.addAttribute("message", "서버오류, 다시 시도해주세요.");
			goPage = "redirect:/review/detail.do?reNo="+reNo;
		}
		return goPage;
	}
	
	
}
