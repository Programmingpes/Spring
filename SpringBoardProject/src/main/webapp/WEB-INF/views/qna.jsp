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
	$(function(){
		$("#qna_list").accordion();
		$("#qna_form").submit(function(){
			var title = $("input[name=title]").val();
			var content = $("textarea[name=content]").val();
			console.log(title,content);
			var result = true;
			if(title.length == 0){
				alert("문의 제목을 입력하세요");
				result = false;
			}else if(title.length > 33){
				alert("문의 제목은 33글자까지만 입력하세요");
				result = false;
			}else if(content.length == 0){
				alert("문의 내용을 입력하세요");
				result = false;
			}else if(content.length > 1333){
				alert("문의 내용은 1333글자까지만 입력하세요");
				result = false;
			}
			return result;
		});
		var page = 1;
		$("#btn_more").click(function () {
			page++;
			$.ajax({
				url:"nextQnAList.do",
				data: "page="+page,
				dataType: "json",
				success:function(r){
					if(r.nextPage == 0){
						$("#btn_more").off("click").text("더 이상 불러올 내용이 없습니다");
					}
					var arr = r.list;
					var tag = "";
					for(i=0;i<arr.length;i++){
						tag += "<h3 class='qna_title'><ul>";
						tag += "<li>제목 : " + arr[i].title + "</li>";
						tag += "<li>작성자 : " + arr[i].writer + "</li>";
						tag += "<li>작성일 : " + arr[i].wdate + "</li>";
						switch(arr[i].status){
						case 0:
							tag += "<li>읽지않음</li>";
							break;
						case 1:
							tag += "<li>읽음</li>";
							break;
						case 2:
							tag += "<li>답변완료</li>";
							break;
						}
						tag += "</ul></h3>";
						tag += "<div><p class='qna_content'>문의 내용<br>" + arr[i].content+ "</p><hr>";
						tag += "<p class='qna_content'>답변내용<br>" + arr[i].response+ "</p></div>";
					}
						$("#qna_list").append(tag);
						$("#qna_list").accordion("refresh");
				}
			});
		});
	});
</script>
</head>
<body>
	<jsp:include page="template/header.jsp"></jsp:include>
	<div id="container">
	<form id="qna_form" action="sendQnA.do">
	<table>
	<tr>
	<td>
	<input class="title" type="text" name="title" placeholder="문의글 제목을 입력하세요"><br>
	<textarea class="content" name="content" placeholder="내용을 입력해주세요"></textarea>
	</td>
	<td>
	<button class="myButton">문의하기</button>
	</td>
	</tr>
	</table>
	</form>
	<div id="qna_list">
	<c:forEach var="q" items="${requestScope.list }">
		<h3 class="qna_title">
			<ul>
				<li>제목 : ${q.title }</li>
				<li>작성자 : ${q.writer }</li>
				<li>작성일 : ${q.wdate }</li>
				<li></li>
				<c:choose>
					<c:when test="${q.status == 0 }">
						<li>읽지않음</li>
					</c:when>
					<c:when test="${q.status == 1 }">
						<li>읽음</li>
					</c:when>
					<c:otherwise>
						<li>답변완료</li>
					</c:otherwise>
				</c:choose> 
			</ul>
		</h3>
		<div>
			<p class="qna_content">문의 내용<br>${q.content }</p>
			<hr>
			<p class="qna_content">답변내용<br>${q.response }</p>
		</div>
	</c:forEach>
	</div>
	<button id="btn_more">더보기</button>
	</div>
			
	<jsp:include page="template/footer.jsp"></jsp:include>
</body>
</html>






















