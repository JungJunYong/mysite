<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="/mysite03/board/" method="post">
					<input type="text" name="title" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>


					<c:set var="page" value="${page+1}" />


					<c:set var='listCount' value="${fn:length(list) }" />
					<c:set var='listsize' value="${fn:length(list) }" />

					<c:forEach items="${list }" var='vo'
						begin="${(page-1)*10-((pe*5)*10)}"
						end="${(page*10)-1-((pe*5)*10)}" varStatus='status'>
						<tr>
							<td>${vo.no }</td>
							<td style="text-align:left; padding-left:${vo.depth * 20}px">
								<c:if test="${vo.depth != 0 }">
									<img src='/mysite04/assets/images/reply.png'>
								</c:if> <c:choose>
									<c:when test="${vo.delet == false}">
										<a class="title"
											href="${pageContext.servletContext.contextPath }/board/view/${vo.no}">${vo.title }</a>
										<td>${vo.userName }</td>
										<td>${vo.hit }</td>
										<td>${vo.regDate }</td>
										<td><c:if test="${vo.userName == authUser.name }">
												<a
													href="${pageContext.servletContext.contextPath }/board/delete/${vo.no}"
													class="del"> <img
													src='/mysite04/assets/images/recycle.png' /></a>
											</c:if></td>
									</c:when>
									<c:otherwise>
										<font color="red"> 삭제된글입니다.</font>
										<td>삭제글</td>
										<td>${vo.hit }</td>
										<td>${vo.regDate }</td>
										<td></td>
									</c:otherwise>
								</c:choose>
							</td>


						</tr>
					</c:forEach>
				</table>




				<!-- pager 추가 -->

				<div class="pager">
					<ul>
						<c:choose>
							<c:when test="${kwd eq null}">
								<c:choose>
									<c:when test="${page != 1}">
										<li><a
											href="${pageContext.servletContext.contextPath }/board/${page -1 }">◀</a></li>
									</c:when>
									<c:otherwise>
										<li>◀</li>
									</c:otherwise>
								</c:choose>

								<c:forEach var="i" begin="${(pe*5)+1 }" end="${(pe*5)+5}">
									<c:choose>
										<c:when test="${i == page}">
											<li class="selected">${i }</li>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${listsize/10+1> i-(pe*5) }">
													<li><a
														href="${pageContext.servletContext.contextPath }/board/${i }">${i }</a></li>
												</c:when>
												<c:otherwise>
													<li>${i }</li>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</c:forEach>



								<c:choose>
									<c:when test="${listsize/10 > page }">
										<li><a
											href="${pageContext.servletContext.contextPath }/board/${page +1 }">▶</a></li>
									</c:when>
									<c:otherwise>
										<li>▶</li>
									</c:otherwise>

								</c:choose>
							</c:when>


							<c:otherwise>
								<c:choose>
									<c:when test="${page != 1}">
										<li><a
											href="${pageContext.servletContext.contextPath }/board/${page-1 }/${kwd }">◀</a></li>
									</c:when>
									<c:otherwise>
										<li>◀</li>
									</c:otherwise>

								</c:choose>
								<c:forEach var="i" begin="${pageNum+1 }" end="${pageNum +5}">
									<c:choose>
										<c:when test="${i == page}">
											<li class="selected">${i }</li>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${listsize/10+1> i }">
													<li><a
														href="${pageContext.servletContext.contextPath }/board/${i }/${kwd }">${i }</a></li>
												</c:when>
												<c:otherwise>
													<li>${i }</li>

												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:choose>
									<c:when test="${listsize/10 > page }">
										<li><a
											href="${pageContext.servletContext.contextPath }/board?/${page+1 }/${kwd }">▶</a></li>
									</c:when>
									<c:otherwise>
										<li>▶</li>
									</c:otherwise>

								</c:choose>


							</c:otherwise>

						</c:choose>
					</ul>


				</div>
				<!-- pager 추가 -->
				<c:if test="${authUser.name != null }">
					<div class="bottom">
						<a href="${pageContext.request.contextPath }/board/write"
							id="new-book">글쓰기</a>
					</div>
				</c:if>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>