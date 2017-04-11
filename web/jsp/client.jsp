<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <%@include file="layout2/_header.jsp" %>
    <body>
        <div class="wrap">
            <%@include file="layout2/_headermain.jsp" %>
            <%@include file="layout2/_slider.jsp" %>
            <div class="main">
                <div class="content">
                    <div class="content_top">
                        <div class="heading">
                            <h3>Sách mới</h3>
                        </div>
                        <div class="search_box">
                            <s:form method="post" theme="simple" action="GetAllPaging">
                                <!--<input type="text" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}">-->
                                <s:textfield name="searchString" cssClass="form-control"></s:textfield>
                                <s:submit value=""></s:submit>
                            </s:form>
                        </div>
                        <div class="clear"></div>
                    </div>
                    <div class="section group">
                        <c:forEach var="b" items="${listHead}" varStatus="state">

                            <div class="grid_1_of_4 images_1_of_4">
                                <s:url id="action_preview" action="viewDetail">
                                    <s:param name="id">${b.id}</s:param>
                                </s:url>
                                <s:a href="%{action_preview}"><img src="${pageContext.request.contextPath}/images/book/${b.image}" alt=""  height="210" width="210"/></s:a>
                                <h2>${b.title} </h2>
                                <div class="price-details">
                                    <div class="price-number">
                                        <p><span class="rupees">${b.price} VNĐ</span></p>
                                    </div>
                                    <div class="add-cart">								
                                        <h4><a href="%{action_preview}">Đặt sách</a></h4>
                                    </div>
                                    <div class="clear"></div>
                                </div>
                            </div>

                        </c:forEach>
                    </div>
                    <div class="section group">
                        <c:forEach var="b" items="${listTail}" varStatus="state">

                            <div class="grid_1_of_4 images_1_of_4">
                                <s:url id="action_preview" action="viewDetail">
                                    <s:param name="id">${b.id}</s:param>
                                </s:url>
                                <s:a href="%{action_preview}"><img src="${pageContext.request.contextPath}/images/book/${b.image}" alt=""  height="210" width="210"/></s:a>
                                <s:a href="%{action_preview}"><h2>${b.title} </h2></s:a>
                                <div class="price-details">
                                    <div class="price-number">
                                        <p><span class="rupees">${b.price} VNĐ</span></p>
                                    </div>
                                    <div class="add-cart">								
                                        <h4><a href="%{action_preview}">Đặt sách</a></h4>
                                    </div>
                                    <div class="clear"></div>
                                </div>
                            </div>

                        </c:forEach>
                    </div>



                    <div class="text-center">
                        <ul class="pagination">
                            <!--previous-->
                            <c:if test="${currentPage != 1}">
                                <s:url id="action_previous" action="index">
                                    <s:param name="page">${currentPage-1}</s:param>
                                    <c:if test="${not empty searchString}">
                                        <s:param name="searchString">${searchString}</s:param>
                                    </c:if>
                                </s:url>
                                <li><s:a href="%{action_previous}">❮</s:a></li>
                                </c:if>
                            <!--Page-->
                            <c:forEach begin="1" end="${noOfPages}" var="i">
                                <c:choose>
                                    <c:when test="${currentPage eq i}">
                                        <li class="active"><a href="#">${i}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <s:url id="action_page" action="index">
                                                <s:param name="page">${i}</s:param>
                                                <c:if test="${not empty searchString}">
                                                    <s:param name="searchString">${searchString}</s:param>
                                                </c:if>
                                            </s:url>
                                        <li><s:a href="%{action_page}">${i}</s:a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            <!--Next-->
                            <c:if test="${currentPage lt noOfPages}">
                                <s:url id="action_next" action="index">
                                    <s:param name="page">${currentPage + 1}</s:param>
                                    <c:if test="${not empty searchString}">
                                        <s:param name="searchString">${searchString}</s:param>
                                    </c:if>
                                </s:url>
                                <li><s:a href="%{action_next}">❯</s:a></li>
                                </c:if>

                        </ul>



                    </div>



                </div>
            </div>
        </div>
        <%@include file="layout2/_footer.jsp" %>
    </body>
</html>