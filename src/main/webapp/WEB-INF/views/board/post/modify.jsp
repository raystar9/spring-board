<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/global/default-setting.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/views/global/top-navbar.jsp"/>
<div class="container">
<form:form action="/mvc/board/post" method="put" autocomplete="off">
<table class="table table-bordered">
	<tr>
		<th colspan=2>글 수정하기</th>
	</tr>
	<tr>
		<td>글쓴이</td>
		<td>${post.writer}</td>
	</tr>
	<tr>
		<td><label for="title">글 제목</label></td>
		<td><input class="form-control" type="text" name="title" id="title" value="${post.title}"/></td>
	</tr>
	<tr>
		<td colspan=2><textarea class="form-control" name="content" id="content" cols="30" rows="10" >${post.content}</textarea></td>
	</tr>
</table>
<input type="hidden" name="postNo" value="${post.postNo}" />
<input class="btn btn-default" type="submit" value="수정하기"/>
</form:form>
</div>
</body>
</html>