<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/global/default-setting.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/views/global/top-navbar.jsp"/>
<div class="container" style="max-width: 800px">
<form method="post" action="./join"  autocomplete="off">
	<table class="table table-bordered">
		<tr>
			<td class="col-sm-4" style="vertical-align: middle"><label for="nickname">Nickname</label></td>
			<td class="col-sm-5"><input class="form-control" type="text" id="nickname" name="nickname"></td>
			<td class="col-sm-3" style="vertical-align: middle"><div id="nickname-check"></div></td>
		</tr>
		<tr>
			<td style="vertical-align: middle"><label for="email">E-Mail</label></td>
			<td colspan=2><input class="form-control" type="text" id="email" name="email"></td>
		</tr>
		<tr>
			<td style="vertical-align: middle"><label for="password">Password</label></td>
			<td><input class="form-control" type="password" id="password" name="password"></td>
			<td style="vertical-align: middle"><div id="password-length"></div></td>
		</tr>
		<tr>
			<td style="vertical-align: middle"><label for="password-confirm">Password confirm</label></td>
			<td><input class="form-control" id="password-confirm" type="password"></td>
			<td style="vertical-align: middle"><div id="password-check"></div></td>
		</tr>
	</table>
	<input class="btn btn-default" type="submit" value="회원가입">
</form>
</div>
<script src="/mvc/resources/join/js/join.js"></script>
</body>
</html>