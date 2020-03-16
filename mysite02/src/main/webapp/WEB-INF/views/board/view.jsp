<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${vo.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								<pre style="font-size: 13px;">${vo.contents }</pre>
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board?p=1">글목록</a>
					<c:if test="${vo.userName eq authUser.name }">
						<a
							href="${pageContext.request.contextPath }/board?a=modifyform&n=${vo.no}">글수정</a>
					</c:if>
					<c:if test="${not empty authUser.name}">
						<a
							href="${pageContext.request.contextPath }/board?a=modifyDetform&n=${vo.no}">답글</a>
					</c:if>
				</div>

				<c:choose>
					<c:when test="${empty authUser.name }">
						<c:set var="name" value="로그인" />
					</c:when>
					<c:otherwise>
						<c:set var="name" value="${authUser.name }" />
					</c:otherwise>
				</c:choose>

				<form action="/mysite02/board" method="post">
					<input type="hidden" name="a" value="comment"> <input
						type="hidden" name="n" value="${vo.no}">

					<table class="tbl-ex"
						style="vorder-collapse: collapse; border-bottom: 2px solid #333; margin: 10px auto; width: 100%">
						<tr>
							<th colspan="4">댓글</th>
						</tr>
						<tr>
							<td
								style="width: 5%; border-right: 1px dotted #333; text-align: center;">
								<td
								style="width: 10%; border-right: 1px dotted #333; text-align: center;">${name }</td>

						

							<td style="flex: auto; width: 75%"><c:choose>
									<c:when test="${empty authUser }">
										<a href="${pageContext.request.contextPath }/user?a=loginform">로그인
											해주세요</a>
									</c:when>
									<c:otherwise>
										<input type="text" name="contents" value=""
											style="width: 100%;">
									</c:otherwise>
								</c:choose> <input type="hidden" name="un" value="${authUser.no}" /></td>
							<td style="width: 5%;"><c:if
									test="${not empty authUser.name }">
									<input type="submit" value="등록" />
								</c:if></td>
						</tr>
						<c:forEach items="${list }" var='vo' varStatus='status'>

							<tr>
								<td
									style="width: 5%; border-right: 1px dotted #333; text-align: center;">${status.count }</td>
								<td
									style="width: 5%; border-right: 1px dotted #333; text-align: center;">
									${vo.userName }</td>
								<td style="width: 80%;">${vo.contents }</td>

								<td><c:if test="${authUser.name == vo.userName }">
								<a
									href="${pageContext.servletContext.contextPath }/board?a=commentdel&n=${vo.userNo}&b=${vo.no}"
									class="del"> <img src='/mysite02/assets/images/recycle.png' /></a></c:if></td>
							</tr>
						</c:forEach>

					</table>


				</form>
			</div>



		</div>









		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>