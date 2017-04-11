
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
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
                                <h2>Đăng Nhập</h2>
                                <c:if test="${message != null}">
                                    <div class="alert alert-danger" id="alertField">
                                        <p>${message}</p>
                                    </div>
                                </c:if>
                                <s:form method="post"theme="simple" action="loginclient" >
                                    <s:token/>
                                    <div>
                                        <span><label>Tài khoản</label></span>
                                        <s:textfield name="usr.username"></s:textfield>
                                        </div>
                                        <div>
                                            <span><label>Mật khẩu</label></span>
                                        <s:password name="usr.password" cssStyle="width:98%;padding:8px"></s:password>
                                        </div>
                                        <div>
                                        <s:submit value="Đăng Nhập"  ></s:submit>
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
<script type="text/javascript">
    $(document).ready(function () {
        $('#loginclient_usr_username').off('click').on('click', function () {
            $(this).val("");
            $('#alertField').remove();
        });
        $('#loginclient_usr_password').off('click').on('click', function () {
            $('#alertField').remove();
        });
    });
</script>