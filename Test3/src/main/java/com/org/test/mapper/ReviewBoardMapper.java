package com.org.test.mapper;

import java.util.List;

import com.org.test.ServiceResult;
import com.org.test.vo.PaginationInfoVO;
import com.org.test.vo.ReviewVO;

public interface ReviewBoardMapper {

	public int insert(ReviewVO reviewVO);
	public int selectReviewCount(PaginationInfoVO<ReviewVO> pagingVO);
	public List<ReviewVO> selectReviewList(PaginationInfoVO<ReviewVO> pagingVO);
	public ReviewVO detail(int reNo);
	public int update(ReviewVO reviewVO);
	public int delete(int reNo);
	public void incrementHit(int reNo);
	
}
