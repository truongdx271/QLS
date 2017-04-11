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
                <div class="content">
                    <div class="section group">
                        <div class="col span_2_of_3">
                            <div class="contact-form">
                                <h2>Thay đổi thông tin cá nhân</h2>
                                <s:form method="post"theme="simple" action="prChangeProfile" enctype="multipart/form-data">
                                    <s:token/>
                                    <div>
                                        <span><label>Họ và tên</label></span>
                                        <s:textfield name="usr.fullname"></s:textfield>
                                        </div>
                                        <div>
                                            <span><label>Email</label></span>
                                        <s:textfield name="usr.email"></s:textfield>
                                        </div>
                                        <div>
                                            <span><label>Ảnh đại diện</label></span>
                                            <img src="${pageContext.request.contextPath}/images/${usr.avatar}" height="60" width="60"/>
                                        <s:hidden name="usr.username"></s:hidden>
                                        <s:hidden name="usr.avatar"></s:hidden>
                                        <s:hidden name="usr.role"></s:hidden>
                                        <s:hidden name="usr.id"></s:hidden>
                                        </div>
                                        <div>
                                            <span><label>Upload</label></span>
                                            <s:file name="userImage"></s:file>
                                        </div>
                                        <div>
                                            <s:submit value="Thay đổi"></s:submit>
                                        </div>
                                </s:form>
                            </div>
                        </div>
                        <div class="col span_1_of_3">

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="layout2/_footer.jsp" %>
    </body>
</html>