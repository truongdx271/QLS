

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <%@include file="layout/_header.jsp" %>
    <body>
        <div class="main-wthree">
            <div class="container">
                <div class="sin-w3-agile">
                    <h2>Đăng nhập</h2>
                    <c:if test="${message != null}">
                        <div class="alert alert-danger" id="alert">
                            <p>${message}</p>
                        </div>
                    </c:if>
                    <s:form method="post" theme="simple" action="login">
                        <s:token/>
                        <div class="username">
                            <span class="username">Username:</span>
                            <s:textfield name="usr.username" class="name" placeholder="" required=""></s:textfield>
                                <div class="clearfix"></div>
                            </div>
                            <div class="password-agileits">
                                <span class="username">Password:</span>
                            <s:password name="usr.password" class="password" placeholder="" required=""></s:password>
                                <div class="clearfix"></div>
                            </div>

                            <div class="login-w3">
                            <s:submit value="Login" class="login"></s:submit>
                            </div>
                            <div class="clearfix"></div>
                    </s:form>

                </div>
            </div>
        </div>
    </body>
</html>
<script type="text/javascript">
    $(document).ready(function () {
        $('#login_usr_username').off('click').on('click', function () {
            $(this).val("");
            $('#alert').remove();
        });
        $('#login_usr_password').off('click').on('click', function () {
            $('#alert').remove();
        });
        
    });
</script>
