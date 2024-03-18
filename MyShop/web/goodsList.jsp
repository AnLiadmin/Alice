<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商品列表页</title>

</head>
<body>
	<%@ include file="header.jsp"%>
	
   
<div class="panel panel-default" style="margin: 0 auto;width: 95%;">
	<div class="panel-heading">
	    <h3 class="panel-title"><span class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;商品列表</h3>
	</div>
	<div class="panel-body">
	   	   <!--列表开始-->
	    <div class="row" style="margin: 0 auto;">
	    	<c:forEach items="${p.list}" var="g" varStatus="i">
		    	<div class="col-sm-3">
				    <div class="thumbnail">
				      <img src="${pageContext.request.contextPath}/${g.p_image}" width="180" height="180"  alt="小米6" />
				      <div class="caption">
				        <h4>商品名称<a href="${pageContext.request.contextPath}/product?action=show&pid=${g.p_id}">${g.p_name}</a></h4>
				        <p>热销指数
				        	<c:forEach begin="1" end="${g.p_state}">
				        		<img src="image/star_red.gif" alt="star"/>
				        	</c:forEach>
				        </p>
				         <p>上架日期:${g.p_time}</p>
			             <p style="color:orange">价格:${g.p_price}</p>
				      </div>
				    </div>
				  </div>
	    	</c:forEach>

			<center>
				<nav aria-label="Page navigation">
					<ul class="pagination">
						<c:if test="${p.currentPage!=1}"> <%-- 不是第一页,则可显示上一页 --%>
							<li>
									<%-- ${param.tid}:从url中取出参数 --%>
								<a href="product?action=productShow&tid=${param.tid}&currentPage=${p.currentPage-1}" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
						</c:if>
						<%-- begin:起始值  end:终止值  varStatus状态-index,
						从begin开始取值,一直取到end--%>
						<c:forEach begin="1" end="${p.pageCount}" varStatus="vs"><%-- 当前页等于索引页 --%>
							<c:if test="${p.currentPage==vs.index}"> <%-- 当前页=索引则激活效果 --%>
								<li class="active"><a href="product?action=productShow&tid=${param.tid}&currentPage=${vs.index}">${vs.index}</a></li>
							</c:if>
							<c:if test="${p.currentPage!=vs.index}">
								<li><a href="product?action=productShow&tid=${param.tid}&currentPage=${vs.index}">${vs.index}</a></li>
							</c:if>
						</c:forEach>
						<c:if test="${p.currentPage!=p.pageCount}"> <%-- 如果不是尾页,则有下一页 --%>
							<li>
								<a href="product?action=productShow&tid=${param.tid}&currentPage=${p.currentPage+1}" aria-label="Next">
									<span aria-hidden="true">&raquo;</span>
								</a>
							</li>
						</c:if>
					</ul>
				</nav>
			</center>
		</div>
   	</div>

</div>
      <!-- 底部 -->
   <%@ include file="footer.jsp"%>
</body>
</html>