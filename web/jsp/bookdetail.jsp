<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <%@include file="layout2/_header.jsp" %>
    <body>
        <div class="wrap">
            <%@include file="layout2/_headermain.jsp" %>
            <div class="main">
                <div class="section group">
                    <div class="cont-desc span_1_of_2">
                        <div class="product-details">
                            <div class="grid images_3_of_2">
                                <div id="container">
                                    <img src="${pageContext.request.contextPath}/images/book/${b.image}" alt=""  height="210" width="210"/>
                                </div>
                            </div>
                            <div class="desc span_3_of_2">
                                <h2>${b.title}</h2>
                                <p> Tác giả: <span style="color:#CD1F25">${b.author}</span></p>
                                <p> Mô tả: ${b.description}</p>
                                <div class="price">
                                    <p>Giá: <span>${b.price} VNĐ</span></p>
                                </div>

                            </div>
                            <div class="clear"></div>
                        </div>
                        <div class="product_desc">
                            <div id="horizontalTab">
                                <ul class="resp-tabs-list">
                                    <div class="clear"></div>
                                </ul>
                            </div>
                            <div class="resp-tabs-container">

                                <div class="review">
                                    <div class="your-review">
                                        <h3>Bạn có nhận xét gì về cuốn sách này?</h3>
                                        <s:if test="#session.USER != null">
                                            <p>Hãy để lại bình luận dưới đây?</p>
                                            <s:form method="post" theme="simple" action="postComment">
                                                <div>
                                                    <span><label>Nội dung<span class="red">*</span></label></span>
                                                    <span><s:textarea name="content"></s:textarea></span>
                                                    <s:hidden name="b.id" ></s:hidden>
                                                    </div>
                                                    <div>
                                                        <span><s:submit value="Gửi"></s:submit></span>
                                                    </div>
                                            </s:form>
                                        </s:if>
                                            <s:if test="#session.USER == null">
                                                <p><a href="<s:url action="loginclient"></s:url>">Đăng nhập</a> để bình luận</p>
                                                <p>Chưa có tài khoản? <a href="<s:url action="register"></s:url>">Đăng ký</a> tại đây</p>
                                            </s:if>
                                    </div>				
                                </div>

                            </div>
                        </div>
                        <!--<div class="media-grids">-->
                        <%--<c:forEach var="c" items="${listCom}">--%>
                        <!--                                <div class="media">
                                                            <h5>${c.userName}</h5>
                                                            <div class="media-left">
                                                                <a href="#"><img src="${pageContext.request.contextPath}${c.avatar}" width="50px" height="auto"/></a>
                                                            </div>
                                                            <div class="media-body">
                                                                <p>${c.content}</p>
                                                            </div>
                                                        </div>-->
                        <%--</c:forEach>--%>
                        <!--</div>-->

                        <div class="media-grids">
                            <c:forEach var="c" items="${listCom}">
                                <div class="image group" id="comment-pr">
                                    <div class="grid images_3_of_1" style="width: 10%">
                                        <img src="${pageContext.request.contextPath}/images/${c.avatar}" alt="" />
                                    </div>
                                    <div class="grid news_desc">
                                        <h3>${c.userName}</h3>
                                        <h6 id="comment" style="display: none;">${c.createdDate}</h6>
                                        <p>${c.content}</p>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <script>
                            $(document).ready(function () {
                                $(document).on('mouseenter', '#comment-pr', function () {
                                    $(this).find('#comment').show();
                                }).on('mouseleave', '#comment-pr', function () {
                                    $(this).find((#comment)).hide();
                                });
                            });
                        </script>





                    </div>



                </div>
            </div>
        </div>
        <%@include file="layout2/_footer.jsp" %>
    </body>
</html>