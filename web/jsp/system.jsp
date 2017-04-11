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
                        <li class="breadcrumb-item"><a href="<s:url action="adminIndex"></s:url>">Trang chủ</a> <i class="fa fa-angle-right"></i>Backup Hệ thống</li>
                        </ol>

                        <div class="grid-form1">
                            <h3 id="forms-horizontal">Back up</h3>

                        <s:form method="post" theme="simple" action="prBackUp" cssClass="form-horizontal">
                            <s:token/>
                            <div class="form-group">
                                <label  class="col-sm-2 control-label hor-form">Tên file</label>
                                <div class="col-sm-8">
                                    <s:textfield name="fileName" cssClass="form-control" placeholder="Tên file"></s:textfield>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-8">
                                    <s:submit value="Run" cssClass="btn btn-primary" ></s:submit>
                                    </div>
                                </div>
                        </s:form>
                        <div>
                            <c:if test="${alert!=null}">
                                ${alert}
                            </c:if>
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