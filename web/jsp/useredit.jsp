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
                        <li class="breadcrumb-item"><a href="<s:url action="adminIndex"></s:url>">Trang chủ</a> <i class="fa fa-angle-right"></i><a href="<s:url action="userListPaging"></s:url>">Người dùng</a> <i class="fa fa-angle-right"></i>Sửa</li>
                        </ol>
                        <div class="grid-form1">
                        <c:if test="${alert!=null}">
                            <div class="alert alert-danger" id="alertField">
                                ${alert}
                            </div>
                        </c:if>
                        <s:form method="post" theme="simple" action="preditUser" cssClass="form-horizontal" enctype="multipart/form-data">
                            <s:token/>
                            <div class="form-group">
                                <label class="col-sm-2 control-label hor-form">Id</label>
                                <div class="col-sm-8">
                                    <s:textfield name="usr.id" cssClass="form-control" placeholder="" readonly="true"></s:textfield>
                                    </div>
                                </div>
                                <!--                                <div class="form-group">
                                                                    <label for="" class="col-sm-2 control-label hor-form">Tài khoản</label>
                                                                    <div class="col-sm-10">
                            <%--<s:textfield name="usr.username" cssClass="form-control" placeholder="Tài khoản" readonly="false"></s:textfield>--%>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label hor-form">Mật khẩu</label>
                            <div class="col-sm-10">
                            <%--<s:password name="usr.password" cssClass="form-control" placeholder="Mật khẩu" readonly="false"></s:password>--%>
                            </div>
                        </div>-->

                            <div class="form-group">
                                <label class="col-sm-2 control-label hor-form">Họ và tên</label>
                                <div class="col-sm-8">
                                    <s:textfield name="usr.fullname" cssClass="form-control" placeholder="Họ và tên"></s:textfield>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label  class="col-sm-2 control-label hor-form">Email</label>
                                    <div class="col-sm-8">
                                    <s:textfield name="usr.email" cssClass="form-control" placeholder="Email"></s:textfield>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="selector1" class="col-sm-2 control-label">Quyền</label>
                                    <div class="col-sm-8">
                                    <s:select list="role2" cssClass="form-control1" name="listName" listKey="key" listValue="value" value="usr.role" ></s:select>
                                    </div>
                                </div>    

                                <div class="form-group">
                                    <label  class="col-sm-2 control-label hor-form">Avatar</label>
                                    <div class="col-sm-8">
                                    <%--<s:textfield name="usr.avatar" cssClass="form-control" placeholder=""></s:textfield>--%>
                                    <img src="${pageContext.request.contextPath}/images/${usr.avatar}" height="60" width="60"/>
                                    <s:hidden name="usr.avatar"></s:hidden>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label  class="col-sm-2 control-label hor-form">Upload</label>
                                    <div class="col-sm-8">
                                    <s:file name="userImage"></s:file>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-8">
                                    <s:submit value="Sửa" cssClass="btn btn-primary" ></s:submit>
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