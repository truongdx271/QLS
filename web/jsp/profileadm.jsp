<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <%@include file="layout/_header.jsp" %>
    <body>
        <div class="page-container">
            <div class="left-content">
                <div class="mother-grid-inner">
                    <%@include file="layout/_headermain.jsp" %>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="<s:url action="adminIndex"></s:url>">Trang chủ</a> <i class="fa fa-angle-right"></i> Thông tin cá nhân</li>
                    </ol>
                    
                    
                    <div class="col-md-12">
                        <div class="col-md-4">
                            <img src="${pageContext.request.contextPath}/images/${usr.avatar}" alt="" width="200px" height="auto"/>
                        </div>
                        <div class="col-md-8">
                            <h3>${usr.fullname}</h3>
                            <h4>Tài khoản: <span>${usr.username}</span></h4>
                            <h4>Email: <span>${usr.email}</span></h4>
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

<%@include file="layout/_script.jsp" %>