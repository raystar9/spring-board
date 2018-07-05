<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">듀얼코어</a>
		</div>
			<ul class="nav navbar-nav navbar-left">
				<li><a href="/mvc/board">오라클 게시판</a></li>
				<li><a href="/mvc/fake/board">자바 게시판</a></li>
			</ul>
			<c:if test="${mySession.id == null }">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/mvc/login">login</a></li>
				</ul>
			</c:if>
			<c:if test="${mySession.id != null }">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/mvc/logout">logout</a></li>
				</ul>
			</c:if>
	</div>
</nav>