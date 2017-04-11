<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="botDetect" uri="https://captcha.com/java/jsp"%>
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
                                <h2>Đăng ký</h2>
                                <c:if test="${message != null}">
                                    <div class="alert alert-danger" id="alertField">
                                        <p>${message}</p>
                                    </div>
                                </c:if>
                                <s:form method="post"theme="simple" action="prRegister" enctype="multipart/form-data">
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
                                            <span><label>Nhập lại mật khẩu</label></span>
                                        <s:password name="rePassword" cssStyle="width:98%;padding:8px"></s:password>
                                        </div>
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
                                        <s:file name="userImage"></s:file>
                                        </div>
                                        <!--<div class="g-recaptcha" data-sitekey="6LcrxBQUAAAAAIk5alMO96pIus2TRLiNin3saZem"></div>-->
                                    <botDetect:captcha id="exampleCaptcha" userInputID="captchaCode"/>

                                    <s:textfield name="captchaCode" id="captchaCode"></s:textfield>
                                        <div>
                                        <s:submit value="Đăng ký"  ></s:submit>
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
        $('#prRegister_usr_username').off('click').on('click', function () {
            $('#alertField').fadeOut('slow');
        });
        $('#prRegister_usr_password').off('click').on('click', function () {
            $('#alertField').fadeOut('slow');
        });
        $('#captchaCode').off('click').on('click', function () {
            $(this).val("");
            $('#alertField').fadeOut('slow');
        });
    });
</script>