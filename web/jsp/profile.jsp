
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
                    <div class="image group">
                        <div class="grid images_3_of_1">
                            <img src="${pageContext.request.contextPath}/images/${usr.avatar}" alt="" width="200px" height="auto"/>
                        </div>
                        <div class="grid news_desc">
                            <c:if test="${message != null}">
                                <div class="alert alert-success" id="alertField" style="font-size: 1.25em">
                                    ${message}
                                </div>
                            </c:if>
                            <h3>${usr.fullname}</h3>
                            <h4>Tài khoản: <span>${usr.username}</span></h4>
                            <h4>Email: <span>${usr.email}</span></h4>
                            <div style="padding-top: 10px">
                                <a id="btn-moi" href="<s:url action="changeProfile"></s:url>">Thay đổi thông tin</a> <a id="btn-moi" href="<s:url action="changePassword"></s:url>">Đổi mật khẩu</a>
                                </div>

                            </div>
                        </div>	
                    </div>
                </div>
            </div>
        <%@include file="layout2/_footer.jsp" %>
    </body>
</html>

<script>
    function removeAlert(){
        $('#alertField').fadeOut('slow');
    }
        
    $(document).ready(function(){
       setTimeout(removeAlert,3000);
        
    });
</script>
