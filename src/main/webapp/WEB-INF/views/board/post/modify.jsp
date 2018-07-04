<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/global/default-setting.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/views/global/top-navbar.jsp"/>
<div class="container">
<f:form action="/mvc/board/${post.postNo}" method="put" autocomplete="off" modelAttribute="post">
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
		<td><f:input class="form-control" path="title"/></td>
	</tr>
	<tr>
		<td colspan=2><f:textarea class="form-control" path="content" cols="30" rows="10" /></td>
	</tr>
</table>
<f:hidden path="writer" />
<input class="btn btn-default" type="submit" value="수정하기"/>
</f:form>
</div>
</body>
</html>