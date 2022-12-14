<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    
<div id="content">
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-4 text-gray-800">BOARD</h1>
					
					                    <div class="row">

                        <!-- Earnings (Monthly) Card Example -->
                        <div class="col-xl-12 col-md-12 mb-12">
                            <div class="card border-left-primary shadow h-100 py-2">
								<div class="card-header">
									${reviewVO.reTitle }
									<div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
										작성자 : ${reviewVO.reWriter } 작성일 : ${reviewVO.reDate } 조회수 : ${reviewVO.reHit }
									</div>
								</div>
                                <div class="card-body">
                                    ${reviewVO.reContent }
                                </div>
								<div class="row">
									<div class="col-md-10"></div>
									<div class="col-md-2">
										<button type="button" class="btn btn-info" id="updateBtn">수정</button>
										<button type="button" class="btn btn-danger"id="delBtn">삭제</button>
										<button type="button" class="btn btn-primary" id="listBtn">목록</button>
									</div>
								</div>
                            </div>
							<form action="/review/update.do" method="get" id="nFrm">
								<input type="hidden" name="reNo" value="${reviewVO.reNo }" />
							</form>
						</div>
                    </div>
                </div>
            </div>
<script type="text/javascript">
$(function(){
	var nFrm = $("#nFrm");
	var listBtn = $("#listBtn");
	var updateBtn = $("#updateBtn");
	var delBtn = $("#delBtn");
	
	listBtn.on("click", function(){
		location.href = "/review/list.do";
	});
	
	updateBtn.on("click", function(){
		nFrm.submit();
	});
	
	delBtn.on("click", function(){
		if(confirm("정말로 삭제하시겠습니까?")){
			nFrm.attr("method", "post");
			nFrm.attr("action", "/review/delete.do");
			nFrm.submit();
		}else{
			nFrm.reset();
		}
	});
});
</script>