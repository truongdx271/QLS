
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
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
                                <h2>Đổi mật khẩu</h2>
                                <c:if test="${message != null}">
                                    <div class="alert alert-danger" id="alertField">
                                        <p>${message}</p>
                                    </div>
                                </c:if>
                                <s:form method="post"theme="simple" action="prChangePassword" >
                                    <s:token/>
                                    <div>
                                        <span><label>Mật khẩu cũ</label></span>
                                        <s:password name="oldPassword" cssStyle="width:98%;padding:8px"></s:password>
                                        </div>
                                        <div>
                                            <span><label>Mật khẩu mới</label></span>
                                        <s:password name="newPassword" cssStyle="width:98%;padding:8px"></s:password>
                                        </div>
                                        <div>
                                            <span><label>Nhập lại mật khẩu mới</label></span>
                                        <s:password name="rePassword" cssStyle="width:98%;padding:8px"></s:password>
                                        </div>

                                    <botDetect:captcha id="exampleCaptcha" userInputID="captchaCode"/>

            
                                    <s:textfield name="captchaCode" id="captchaCode"></s:textfield>
                                    <%--<s:textfield name="captchaAnswer"></s:textfield>--%>

                                        <div>
                                        <s:submit value="Thay đổi"  ></s:submit>
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
        $('#prChangePassword_oldPassword').off('click').on('click', function () {
            $('#alertField').fadeOut('slow');
        });
        $('#prChangePassword_newPassword').off('click').on('click', function () {
            $('#alertField').fadeOut('slow');
        });
        $('#captchaCode').off('click').on('click', function () {
            $(this).val("");
            $('#alertField').fadeOut('slow');
        });
    });
</script>