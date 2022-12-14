<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div id="content">
	<nav
		class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
	</nav>
	<div class="container-fluid">
		<c:set value="등록" var="name"/>
		<c:if test="${status eq 'mod' }">
			<c:set value="수정" var="name"/>
		</c:if>
		
		<h1 class="h3 mb-4 text-gray-800">BOARD</h1>
		<div class="row">
			<div class="col-xl-12 col-md-12 mb-12">
				<div class="card border-left-primary shadow h-100 py-2">
					<form:form action="/review/form.do" method="post" id="reviewForm" modelAttribute="reviewVO">
						<c:if test="${status eq 'mod' }">
							<input type="hidden" name="reNo" value="${reviewVO.reNo }"/>
						</c:if>
						<div class="card-header">
							<input type="text" class="form-control" name="reTitle" value="${reviewVO.reTitle }" id="exampleFirstName" placeholder="Insert Title">
						</div>
						<div class="card-body">
							<textarea cols="30" class="form-control" name="reContent"  rows="10">${reviewVO.reContent }</textarea>
						</div>
						<div class="row">
							<div class="col-md-10"></div>
							<div class="col-md-2">
								
									
								
<!-- 								<button type="button" id="formBtn" class="btn btn-info">등록</button> -->
								<button type="button" class="btn btn-primary"><a href="/review/list.do"><font style="color:red;">목록</font></a></button>
								<input type="button" id="formBtn" value="${name }" class="btn btn-info"/>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(function(){
		CKEDITOR.replace('reContent',{
			filebrowserUploadUrl: '${pageContext.request.contextPath}/imageUpload.do'
		})
		var formBtn = $("#formBtn");
		
		formBtn.on("click", function(){
// 			if($("#reTitle").val() == null || $("#reTitle").val() == ""){
// 				alert("제목을 입력해주세요.");
// 				$("#reTitle").focus();
// 				return false;
// 			}
			
			if($(this).val() == "수정"){
				$("#reviewForm").attr("action", "/review/update.do");
			}
			
			$("#reviewForm").submit();
		});
	})
</script>