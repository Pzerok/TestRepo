package com.org.test.controller.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.org.test.ServiceResult;
import com.org.test.mapper.ReviewBoardMapper;
import com.org.test.vo.PaginationInfoVO;
import com.org.test.vo.ReviewVO;

@Service
public class ReviewServiceImpl implements IReviewService {
	@Inject
	private ReviewBoardMapper reviewMapper;
	
	@Override
	public ServiceResult insertReview(ReviewVO reviewVO) {
		ServiceResult result = null;
		int cnt = reviewMapper.insert(reviewVO);
		if(cnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public int selectReviewCount(PaginationInfoVO<ReviewVO> pagingVO) {
		return reviewMapper.selectReviewCount(pagingVO);
	}

	@Override
	public List<ReviewVO> selectReviewList(PaginationInfoVO<ReviewVO> pagingVO) {
		// TODO Auto-generated method stub
		return reviewMapper.selectReviewList(pagingVO);
	}

	@Override
	public ReviewVO detail(int reNo) {
		reviewMapper.incrementHit(reNo);
		return reviewMapper.detail(reNo);
	}

	@Override
	public ServiceResult delete(int reNo) {
		ServiceResult result = null;
		int cnt = reviewMapper.delete(reNo);
		if(cnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	@Override
	public ServiceResult update(ReviewVO reviewVO) {
		ServiceResult result = null;
		int cnt = reviewMapper.update(reviewVO);
		if(cnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}
	
}
