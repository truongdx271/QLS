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
                        <li class="breadcrumb-item"><a href="<s:url action="adminIndex"></s:url>">Trang chủ</a> <i class="fa fa-angle-right"></i><a href="<s:url action="bookListPaging"></s:url>">Quản lý sách</a> <i class="fa fa-angle-right"></i>Thêm mới</li>
                        </ol>
                        <div class="grid-form1">
                        <c:if test="${alert!=null}">
                            <div class="alert alert-danger" id="alertField">
                                ${alert}
                            </div>
                        </c:if>
                        <s:form method="post" theme="simple" action="prInsertBook" cssClass="form-horizontal" enctype="multipart/form-data">
                            <s:token/>    
                            <div class="form-group">
                                <label class="col-sm-2 control-label hor-form">Tiêu đề</label>
                                <div class="col-sm-8">
                                    <s:textfield name="b.title" cssClass="form-control" placeholder="Tiêu đề"></s:textfield>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label hor-form">Tác giả</label>
                                    <div class="col-sm-8">
                                    <s:textfield name="b.author" cssClass="form-control" placeholder="Tác giả"></s:textfield>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label hor-form">Giá bán</label>
                                    <div class="col-sm-8">
                                    <s:textfield name="b.price" cssClass="form-control" placeholder="Giá bán"></s:textfield>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label  class="col-sm-2 control-label hor-form">Mô tả</label>
                                    <div class="col-sm-8">
                                        <!--<input type="email" class="form-control" id="inputEmail3" placeholder="Email">-->
                                    <s:textfield name="b.description" cssClass="form-control" placeholder="Mô tả"></s:textfield>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label  class="col-sm-2 control-label hor-form">Hình ảnh</label>
                                    <div class="col-sm-8">
                                    <%--<s:textfield name="usr.avatar" cssClass="form-control" placeholder=""></s:textfield>--%>
                                    <s:file name="bookImage"></s:file>
                                    </div>

                                </div>

                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-8">
                                        <!--<button type="submit" class="btn btn-default">Sign in</button>-->
                                    <s:submit value="Thêm mới" cssClass="btn btn-primary" ></s:submit>
                                    </div>
                                </div>
                                <!--</form>-->
                        </s:form>
                    </div>


                    <div class="inner-block">
                    </div>

                </div>
            </div>
            <%@include file="layout/_sidebar.jsp"%>
        </div>
    </body>
</html>

<%@include file="layout/_script.jsp" %>
<script>
    function removeAlert() {
        $('#alertField').fadeOut('slow');
    }
    $(document).ready(function () {
        setTimeout(removeAlert, 3000);
    });
</script>