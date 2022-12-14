package com.org.test.controller.service;

import java.util.List;

import com.org.test.ServiceResult;
import com.org.test.vo.PaginationInfoVO;
import com.org.test.vo.ReviewVO;

public interface IReviewService {

	public ServiceResult insertReview(ReviewVO reviewVO);
	public int selectReviewCount(PaginationInfoVO<ReviewVO> pagingVO);
	public List<ReviewVO> selectReviewList(PaginationInfoVO<ReviewVO> pagingVO);
	public ReviewVO detail(int reNo);
	public ServiceResult delete(int reNo);
	public ServiceResult update(ReviewVO reviewVO);
	
}
