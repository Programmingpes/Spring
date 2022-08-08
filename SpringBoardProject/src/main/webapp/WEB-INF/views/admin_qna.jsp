<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style type="text/css">
	#container{
		width: 1200px;
		margin:20px auto;
		text-align: center;
	}
	form{
		width: 650px;
		margin-top:100px;
		margin:0 auto;
	}
	talbe{
		border-collapse: collapse;
		width: 100%;
		box-sizing: border-box;
	}
	.title{
		box-sizing: border-box;
		width: 500px;
		height: 50px;
	}
	.content{
		box-sizing: border-box;
		width: 500px;
		height: 150px;
		margin-top: 10px;

	}
	button{
		background-color: lightblue;
		border-color: lightblue;	
		color: white;
		height: 210px;
	}
	.myButton {
	box-shadow:inset 0px 1px 0px 0px #dcecfb;
	background:linear-gradient(to bottom, #bddbfa 5%, #80b5ea 100%);
	background-color:#bddbfa;
	border-radius:6px;
	border:1px solid #84bbf3;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:15px;
	font-weight:bold;
	padding:6px 24px;
	text-decoration:none;
	text-shadow:0px 1px 0px #528ecc;
	}
	.myButton:hover {
		background:linear-gradient(to bottom, #80b5ea 5%, #bddbfa 100%);
		background-color:#80b5ea;
	}
	.myButton:active {
		position:relative;
		top:1px;
	}
	.qna_title ul{
		list-style-type: none; 
		font-size: 0px;
		padding: 0;
	}
	.qna_title li{
		display: inline-block;
		font-size: 16px;
		margin-right: 50px;
	}
	#qna_list{
		width: 900px;
	 	margin:0 auto;
	}
	
	#btn_more{
		width: 700px;
		border : none;
		border-radius: 5px;
		height: 50px;
		font-size: 16px;
		display: block;
		margin:0 auto;
	}
	
</style>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.13.2/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.13.2/jquery-ui.min.js"></script>
<script>
	
</script>
</head>
<body>
	<jsp:include page="template/header.jsp"></jsp:include>
	<h2>문의 내역</h2>
	<table>
		<tr>
			<th>순번</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>날짜</th>
		</tr>
		<c:forEach var="o" items="${requestScope.list }">
			<tr>
				<td>${o.qno }</td>
				<td><a href="adminQnaDetailView.do?qno=${o.qno }">${o.title }</a></td>
				<td>${o.writer }</td>
				<td>${o.wdate }</td>
				<td>
					<c:choose>
						<c:when test="${o.status == 0 }">
							<li>읽지않음</li>
						</c:when>
						<c:when test="${o.status == 1 }">
							<li>읽음</li>
						</c:when>
						<c:otherwise>
							<li>답변완료</li>
						</c:otherwise>
					</c:choose> 
				</td>
			</tr>		
		</c:forEach>
		<tr>
			<td colspan="6">
				<c:set var="page" value="${requestScope.page }"></c:set>
				<c:if test="${page.previousPageGroup }">
					<a href="qnaAdminView.do?page=${page.startPageOfGroup-1 }">
						◀
					</a>
				</c:if>
				<c:forEach var="i" begin="${page.startPageOfPageGroup}" 
							end="${page.endPageOfPageGroup}">
							<a href="qnaAdminView.do?page=${i }">${ i}</a>
				</c:forEach>
				<c:if test="${page.previousPageGroup }">
					<a href="qnaAdminView.do?page=${page.endPageOfGroup+1 }">
						▶
					</a>
				</c:if>
				
			</td>
		</tr>
	</table>	
	
	<jsp:include page="template/footer.jsp"></jsp:include>
</body>
</html>






















