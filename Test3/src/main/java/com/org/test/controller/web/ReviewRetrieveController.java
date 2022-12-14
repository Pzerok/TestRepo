package com.org.test.controller.web;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.org.test.controller.service.IReviewService;
import com.org.test.vo.PaginationInfoVO;
import com.org.test.vo.ReviewVO;

@Controller
@RequestMapping("/review")
public class ReviewRetrieveController {
	
	private static Logger logger = LoggerFactory.getLogger(ReviewRetrieveController.class);
	@Inject
	private IReviewService service;
	
	@RequestMapping(value="/list.do", method = RequestMethod.GET)
	public ModelAndView reviewList(
			@RequestParam(name="page", required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord
			) {
		ModelAndView mav = new ModelAndView();
		PaginationInfoVO<ReviewVO> pagingVO = new PaginationInfoVO<ReviewVO>();
		
		if(StringUtils.isNotBlank(searchWord)) {
			if("title".equals(searchType)) {
				pagingVO.setSearchType("title");	// 제목으로 검색
			}else {
				pagingVO.setSearchType("writer");	// 작성자로 검색		
			}
			pagingVO.setSearchWord(searchWord);
			mav.addObject("searchType", searchType);
			mav.addObject("searchWord", searchWord);
		}
		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.selectReviewCount(pagingVO);	// 전체 게시물 수
		
		pagingVO.setTotalRecord(totalRecord);
		List<ReviewVO> dataList = service.selectReviewList(pagingVO);
		
		pagingVO.setDataList(dataList);
		logger.info("currrentPage" + currentPage);
		logger.info("totalRecord" + totalRecord);
		logger.info("currrentPage" + currentPage);
		
		mav.addObject("pagingVO", pagingVO);
		mav.setViewName("reviewboard/list");
		return mav;
	}
	
	@RequestMapping(value = "/detail.do",method = RequestMethod.GET)
	public String reviewDetail(int reNo, Model model) {
		ReviewVO reviewVO = service.detail(reNo);
		model.addAttribute("reviewVO",reviewVO);
		return "reviewboard/detail";
	}
	
}
