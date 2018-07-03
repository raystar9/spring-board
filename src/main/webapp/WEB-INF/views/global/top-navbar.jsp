<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-brand">
		<a href="/mvc/board">게시판</a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav navbar-left">
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
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
	</div>
</nav>