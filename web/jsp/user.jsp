<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <%@include file="layout/_header.jsp" %>
    <body>
        <div class="page-container">
            <div class="left-content">
                <div class="mother-grid-inner">
                    <%@include file="layout/_headermain.jsp" %>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="<s:url action="adminIndex"></s:url>">Trang chủ</a> <i class="fa fa-angle-right"></i>Người dùng</li>
                        </ol>

                        <div class="agile-tables">
                        <c:if test="${alert != null}">
                            <div class="alert alert-success" id="alertField">
                                ${alert}
                            </div>
                        </c:if>
                        <div class="w3l-table-info">
                            <!--<button class="btn-primary btn">Thêm mới</button>-->
                            <div class="row">
                                <div class="col-sm-4">
                                    <input type="button" class="btn-primary btn" onClick="location.href = '<s:url action="insertUsr"></s:url>'" value="Thêm mới">
                                    </div>
                                <s:form method="post" theme="simple" action="userListPaging">
                                    <div class="col-sm-5" >
                                        <s:textfield name="searchString" cssClass="form-control" placeholder="Tìm..." id="searchField"></s:textfield>
                                        </div>
                                        <div class="col-sm-3">
                                        <s:submit value="Tìm kiếm" cssClass="btn btn-primary" ></s:submit>
                                        <input type="button" class="btn-primary btn" onClick="location.href = '<s:url action="userListPaging"></s:url>'" value="Reset">
                                        </div>
                                </s:form>

                            </div>

                            <table id="table">
                                <thead>
                                    <tr>
                                        <th>Tài khoản</th>
                                        <th>Họ và tên</th>
                                        <th>Email</th>
                                        <th>Chức vụ</th>
                                        <th>Avatar</th>
                                        <th>Điều khiển</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="user" items="${list}">
                                        <tr>
                                            <td>${user.username}</td>
                                            <td>${user.fullname}</td>
                                            <td>${user.email}</td>
                                            <c:if test="${user.role eq 1}">
                                                <td><button class="btn disabled btn-sm btn-block col-2">Người dùng</button></td>
                                            </c:if>
                                            <c:if test="${user.role eq 2}">
                                                <td><button class="btn disabled btn-sm btn-block col-15">Quản lý</button></td>
                                            </c:if>
                                            <c:if test="${user.role eq 3}">
                                                <td><button class="btn disabled btn-sm btn-block col-7">Admin</button></td>
                                            </c:if>
                                            <c:if test="${user.role eq 0}">
                                                <td><button class="btn disabled btn-sm btn-block col-5">Khóa</button></td>
                                            </c:if>
                                            <td><img src="${pageContext.request.contextPath}/images/${user.avatar}" height="60" width="auto" /></td>
                                            <td>
                                                <s:url id="action_edit" action="userEdit">
                                                    <s:param name="id">${user.id}</s:param>
                                                </s:url>
                                                <s:a href="%{action_edit}"><i class="fa fa-pencil-square-o fa-lg" aria-hidden="true"></i></s:a>
                                                    &nbsp;&nbsp;&nbsp;
                                                <s:token/>
                                                <s:url id="action_delete" action="deleteUser">
                                                    <s:param name="id">${user.id}</s:param>
                                                    <s:param name="struts.token.name" value="'token'"/>
                                                    <s:param name="token" value="#session['struts.tokens.token']"/>
                                                </s:url>
                                                <s:a href="%{action_delete}" onclick="return confirm('Bạn có muốn xóa người dùng này?')"><i class="fa fa-trash fa-lg" aria-hidden="true"></i></s:a>
                                                    &nbsp;&nbsp;&nbsp;
                                                <s:token/>
                                                <s:url id="action_reset" action="resetPassword">
                                                    <s:param name="id">${user.id}</s:param>
                                                    <s:param name="struts.token.name" value="'token'"/>
                                                    <s:param name="token" value="#session['struts.tokens.token']"/>
                                                </s:url>
                                                <s:a href="%{action_reset}" onclick="return confirm('Bạn có muốn reset mật khẩu tài khoản này?')"><i class="fa fa-refresh fa-lg" aria-hidden="true"></i></s:a>
                                                </td>
                                            </tr>
                                    </c:forEach>    
                                </tbody>

                            </table>



                            <div class="text-center">
                                <c:if test="${noOfRecords gt 0}">
                                    <ul class="pagination pagination-lg" max-size="4">
                                        <!--previous-->

                                        <c:if test="${currentPage gt 1}">
                                            <s:url id="action_previous" action="userListPaging">
                                                <s:param name="page">${currentPage-1}</s:param>
                                                <c:if test="${not empty searchString}">
                                                    <s:param name="searchString">${searchString}</s:param>
                                                </c:if>
                                            </s:url>
                                            <li><s:a href="%{action_previous}">❮</s:a></li>
                                            </c:if>
                                            <s:url id="action_first" action="userListPaging">
                                                <s:param name="page">${1}</s:param>
                                                <c:if test="${not empty searchString}">
                                                    <s:param name="searchString">${searchString}</s:param>
                                                </c:if>
                                            </s:url>
                                            <c:if test="${currentPage eq 1}">
                                            <li class="active"><s:a href="#">1</s:a></li>
                                            </c:if>
                                            <c:if test="${currentPage gt 1}">
                                            <li><s:a href="%{action_first}">1</s:a></li>
                                            </c:if>
                                        <!--Page-->
                                        <c:if test="${noOfPages lt 9}">
                                            <c:forEach begin="2" end="${noOfPages-1}" var="i">
                                                <c:choose>
                                                    <c:when test="${currentPage eq i}">
                                                        <li class="active"><a href="#">${i}</a></li>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <s:url id="action_page" action="userListPaging">
                                                                <s:param name="page">${i}</s:param>
                                                                <c:if test="${not empty searchString}">
                                                                    <s:param name="searchString">${searchString}</s:param>
                                                                </c:if>
                                                            </s:url>
                                                        <li><s:a href="%{action_page}">${i}</s:a></li>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${noOfPages ge 9}">
                                                <c:if test="${currentPage ge 5 && currentPage le (noOfPages-4)}">
                                                    <c:forEach begin="${currentPage-3}" end="${currentPage + 3}" var="i">
                                                        <c:choose>
                                                            <c:when test="${currentPage eq i}">
                                                            <li class="active"><a href="#">${i}</a></li>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <s:url id="action_page" action="userListPaging">
                                                                    <s:param name="page">${i}</s:param>
                                                                    <c:if test="${not empty searchString}">
                                                                        <s:param name="searchString">${searchString}</s:param>
                                                                    </c:if>
                                                                </s:url>
                                                            <li><s:a href="%{action_page}">${i}</s:a></li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </c:if>
                                                <c:if test="${currentPage lt 5}">
                                                    <c:forEach begin="2" end="8" var="i">
                                                        <c:choose>
                                                            <c:when test="${currentPage eq i}">
                                                            <li class="active"><a href="#">${i}</a></li>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <s:url id="action_page" action="userListPaging">
                                                                    <s:param name="page">${i}</s:param>
                                                                    <c:if test="${not empty searchString}">
                                                                        <s:param name="searchString">${searchString}</s:param>
                                                                    </c:if>
                                                                </s:url>
                                                            <li><s:a href="%{action_page}">${i}</s:a></li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </c:if>
                                                <c:if test="${currentPage gt (noOfPages-4)}">
                                                    <c:forEach begin="${noOfPages-7}" end="${noOfPages-1}" var="i">
                                                        <c:choose>
                                                            <c:when test="${currentPage eq i}">
                                                            <li class="active"><a href="#">${i}</a></li>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <s:url id="action_page" action="userListPaging">
                                                                    <s:param name="page">${i}</s:param>
                                                                    <c:if test="${not empty searchString}">
                                                                        <s:param name="searchString">${searchString}</s:param>
                                                                    </c:if>
                                                                </s:url>
                                                            <li><s:a href="%{action_page}">${i}</s:a></li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </c:if>
                                            </c:if>
                                        <!--Next-->
                                        <s:url id="action_last" action="userListPaging">
                                            <s:param name="page">${noOfPages}</s:param>
                                            <c:if test="${not empty searchString}">
                                                <s:param name="searchString">${searchString}</s:param>
                                            </c:if>
                                        </s:url>
                                        <c:if test="${noOfPages gt 1}">
                                            <c:if test="${currentPage eq noOfPages}">
                                                <li class="active"><s:a href="#">${noOfPages}</s:a></li>
                                                </c:if>
                                                <c:if test="${currentPage lt noOfPages}">
                                                <li><s:a href="%{action_last}">${noOfPages}</s:a></li>
                                                </c:if>
                                            </c:if>

                                        <c:if test="${currentPage lt noOfPages}">
                                            <s:url id="action_next" action="userListPaging">
                                                <s:param name="page">${currentPage + 1}</s:param>
                                                <c:if test="${not empty searchString}">
                                                    <s:param name="searchString">${searchString}</s:param>
                                                </c:if>
                                            </s:url>
                                            <li><s:a href="%{action_next}">❯</s:a></li>
                                            </c:if>
                                    </ul>
                                </c:if>
                            </div>



                        </div>
                    </div>


                    <div class="inner-block">
                    </div>
                </div>
            </div>
            <%@include file="layout/_sidebar.jsp"%>
        </div>


    </body>
</html>

<script>
    $('#table').basictable();
    $(document).ready(function () {
        $('#table').basictable();

        $('#table-breakpoint').basictable({
            breakpoint: 768
        });

        $('#table-swap-axis').basictable({
            swapAxis: true
        });

        $('#table-force-off').basictable({
            forceResponsive: false
        });

        $('#table-no-resize').basictable({
            noResize: true
        });

        $('#table-two-axis').basictable();

        $('#table-max-height').basictable({
            tableWrapper: true
        });
    });
</script>

<%@include file="layout/_script.jsp" %>

<script>
    function removeAlert() {
        $('#alertField').fadeOut('slow');
    }

    $(document).ready(function () {
        setTimeout(removeAlert, 3000);

    });
</script>
