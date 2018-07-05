<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge" name="viewport" content="width=device-width, initial-scale=1">
<title>게시판</title>
<jsp:include page="/WEB-INF/views/global/default-setting.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/views/global/top-navbar.jsp"/>
로그인 : ${mySession.id}
<div class="container">
<table class="table table-bordered" border=1>
	<tr>
		<th class="col-md-1">글번호</th>
		<th class="col-md-8">글 제목</th>
		<th class="col-md-2">작성자</th>
		<th class="col-md-1">조회수</th>
	</tr>
	<c:forEach items="${posts}" var="post">
	<tr>
		<td class="text-center">${post.postNo}</td>
		<td><a href="board/${post.postNo}">${post.title}</a></td>
		<td>${post.writer}</td>
		<td class="text-center">${post.readCount}</td>
	</tr>
	</c:forEach>
</table>
<div class="container">
	<div class="row">
	<div class="col-sm-2">
		<input type="button" value="처음" class="btn btn-default" onclick="location.href='./board'"/>
	</div>
	<div class="col-sm-8 text-center">
	<ul class="pagination">
	<c:if test="${pageSelectInfo.start != 1 }">
		<li> <a href="?page=${pageSelectInfo.start - 1}">이전</a> </li>
	</c:if>
	
		<c:forEach begin="${pageSelectInfo.start}" end="${pageSelectInfo.end}" varStatus="pageSelector">
			
			<c:if test="${(query != null) && (searchBy != null)}">
				<c:if test="${pageSelectInfo.current == pageSelector.index }">
					<li class="active"> <a href='#'>${pageSelector.index}</a> </li>
				</c:if>
				<c:if test="${pageSelectInfo.current != pageSelector.index }">
					<li> <a href='?page=${pageSelector.index}&searchby=${searchBy}&query=${query}'>${pageSelector.index}</a> </li>
				</c:if>
			</c:if>
			<c:if test="${!((query != null) && (searchBy != null))}">
				<c:if test="${pageSelectInfo.current == pageSelector.index }">
					<li class="active"> <a href='#'>${pageSelector.index}</a> </li>
				</c:if>
				<c:if test="${pageSelectInfo.current != pageSelector.index }">
					<li> <a href='?page=${pageSelector.index}'>${pageSelector.index}</a> </li>				
				</c:if>
			</c:if>
		</c:forEach>
	<c:if test="${pageSelectInfo.end != pageSelectInfo.last}">
		<li> <a href="?page=${pageSelectInfo.end + 1}">다음</a> </li>
	</c:if>
	</ul>
	</div>
	<div class="col-sm-2">
		<input type="button" class="btn btn-default" value="글쓰기" onclick="location.href='./board/new'" />
	</div>
	</div>
	<form>
	<div class="form-group row">
	<div class="col-sm-6"></div>
	  <div class="col-sm-2">
	  <select class="form-control col-sm-2" name="searchby" id="search-by">
			<option value="title">제목</option>
			<option value="writer">작성자</option>
			<option value="content">내용</option>
		</select>
		</div>
	    <div class="col-sm-3">
	    <div class="input-group">
	    <input type="text" class="form-control" name="query" placeholder="Search">
	    <div class="input-group-btn">
	      <button class="btn btn-default" type="submit">
	        <i class="glyphicon glyphicon-search"></i>
	      </button>
	      </div>
	      </div>
	  </div>
	</div>
	</form> 
</div>
</div>
</body>
</html>