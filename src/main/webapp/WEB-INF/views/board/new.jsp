<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/global/default-setting.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/views/global/top-navbar.jsp"/>
<div class="container">
	<form class="form-group" action="new" method="post" enctype="multipart/form-data" autocomplete="off">
		<table class="table table-bordered">
			<tr>
				<th colspan="2">글쓰기 폼</th>
			</tr>
			<tr>
				<td><label for="writer">글쓴이</label></td>
				<td>${sessionId}</td>
			</tr>
			<tr>
				<td><label for="title">글제목</label></td>
				<td><input class="form-control" type="text" name="title" id="title" /></td>
			</tr>
			<tr>
				<td><label for="content">내용</label></td>
				<td><textarea class="form-control" name="content" id="content" cols="30" rows="10"></textarea></td>
			</tr>
			<tr>
				<td></td>
				<td><input class="form-control" type="file" name="multipartFile" id="multipartFile" /></td>
			</tr>
			<tr>
				<td colspan="2" class="text-right">
					<input class="btn btn-default" type="submit" value="완료" />
				</td>
			</tr>
		</table>
		
	</form>
	</div>
</body>
</html>