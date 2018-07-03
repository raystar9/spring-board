<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/global/default-setting.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/views/global/top-navbar.jsp"/>
<div class="container">
<form method="post" action="./login"  autocomplete="off">
	<table class="table table-bordered">
		<tr>
			<td>ID</td><td><input class="form-control" type="text" name="nickname"></td>
		</tr>
		<tr>
			<td>Password</td><td><input class="form-control" type="password" name="password"></td>
		</tr>
	</table>
	<input class="btn btn-default" type="submit" value="로그인하기">
	<input class="btn btn-default" type="button" value="회원가입" onclick="location.href='/mvc/join'">
</form>
</div>
</body>
</html>