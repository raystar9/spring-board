<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/global/default-setting.jsp"/>
<script src="/mvc/resources/board/post/js/post.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/global/top-navbar.jsp"/>
<div class="container">
<table class="table table-bordered">
	<tr>
		<th colspan="2">게시물</th>
	</tr>
	<tr>
		<td>글제목</td>
		<td>${post.title}</td>
	</tr>
	<tr>
		<td>글쓴이</td>
		<td>${post.writer}</td>
	</tr>
	<tr>
		<td colspan="2"><textarea rows="10" cols="20" readonly="readonly" draggable="false" style="resize: none; border: 0px; outline: none;">${post.content}</textarea></td>
	</tr>
	<c:if test="${post.attachment!=null}">
	<tr>
		<td>첨부파일</td>
		<td><a href="./${post.postNo}/download">${post.attachment}</a></td>
	</tr>
	</c:if>
	<tr>
	<td colspan="2">
	<div class="contaniner">
		<div class="row">
			<div class="col-sm-9"></div>
			<div class="col-sm-1">
			<c:if test="${mySession.id == post.writer}">
			<button class="btn btn-default" onclick="location.href='./${post.postNo}/modify'">수정</button>
			</c:if>
			</div>
			<div class="col-sm-1">
			<c:if test="${mySession.id == post.writer}">
			<f:form method="delete" id="delete-form">
				<input type="hidden" name="method" value="delete" />
				<input type="hidden" name="postno" value="${post.postNo}" />
				<input class="btn btn-danger" type="button" value="삭제" onclick="deleteConfirm();"/>
			</f:form>
			</c:if>
			</div>
			<div class="col-sm-1">
			<input class="btn btn-default" type="button" onclick="location.href='./';" value="뒤로" />
			</div>
			</div>
		</div>
	</td>
</table>
</div>
</body>
</html>