<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원 관리</h1>
	<hr>
	
	<table>
	<tr>
		<th>아이디</th>
		<th>암호</th>
		<th>이름</th>
		<th>나이</th>
		<th>성별</th>
		<th>주소</th>
		<th>비고</th>
	</tr>
	<tr>
		<td colspan="6">
			<form action="register.do" method="post">
				<input type="text" name="id" placeholder="아이디를 입력하세요"><br>
				<input type="password" name="passwd" placeholder="비밀번호를 입력하세요"><br>
				<input type="text" name="name" placeholder="이름을 입력하세요"><br>
				<input type="text" name="age" placeholder="나이를 입력하세요"><br>
				<input type="radio" id="m" name="gender" value="M"><label for="m">M</label>
				<input type="radio" id="f" name="gender" value="F"><label for="f">F</label><br>
				<input type="text" name="address" placeholder="주소를 입력하세요"><br>
				<button>전송</button>
			</form>
		</td>
	</tr>
	
	<c:forEach var="m" items="${requestScope.list }">
	<tr>
		<td>${m.id }</td>
		<td>${m.passwd }</td>
		<td>${m.name }</td>
		<td>${m.age }</td>
		<td>${m.gender }</td>
		<td>${m.address }</td>
		<td><a href="delete.do?id=${m.id }">삭제</a> / <a href="updateView.do?id=${m.id }">수정</a></td>
	</tr>
	</c:forEach>
	
	</table>
</body>
</html>