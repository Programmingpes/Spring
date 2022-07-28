<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	
</script>
</head>
<body>
	<form action="update.do" method="post">
		<input type="text" name="id" placeholder="아이디를 입력하세요" value="${requestScope.dto.id}" readonly><br>
		<input type="password" name="passwd" placeholder="비밀번호를 입력하세요" value="${requestScope.dto.passwd}"><br>
		<input type="text" name="name" placeholder="이름을 입력하세요" value="${requestScope.dto.name}"><br>
		<input type="text" name="age" placeholder="나이를 입력하세요" value="${requestScope.dto.age}	"><br>
		<input type="radio" id="m" name="gender" value="M"
		<c:if test="${requestScope.dto.gender == 'M'}">checked</c:if>
		><label for="m">M</label>
		<input type="radio" id="f" name="gender" value="F"
		<c:if test="${requestScope.dto.gender == 'F'}">checked</c:if>
		><label for="f">F</label><br>
		<input type="text" name="address" placeholder="주소를 입력하세요" value="${requestScope.dto.address}	"><br>
		<button>수정</button>
	</form>
	
	
		
	
	
	
</body>
</html>