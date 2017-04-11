<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="sidebar-menu">
    <header class="logo1">
        <a href="#" class="sidebar-icon"> <span class="fa fa-bars"></span> </a> 
    </header>
    <div style="border-top:1px ridge rgba(255, 255, 255, 0.15)"></div>
    <div class="menu">
        <ul id="menu" >
            <c:if test="${session.ROLE eq 3}">
            <li><a href="<s:url action='system'/>"><i class="fa fa-cogs"></i> <span>Hệ thống</span><div class="clearfix"></div></a></li>
            <li><a href="<s:url action='userListPaging'/>"><i class="fa fa-users"></i> <span>Người dùng</span><div class="clearfix"></div></a></li>
            </c:if>
            <c:if test="${session.ROLE eq 3 || session.ROLE eq 2}">
            <li><a href="<s:url action='bookListPaging'/>"><i class="fa fa-book"></i> <span>Sách</span><div class="clearfix"></div></a></li>
            </c:if>
        </ul>
    </div>
</div>

<script>
            var toggle = true;

            $(".sidebar-icon").click(function () {
                if (toggle)
                {
                    $(".page-container").addClass("sidebar-collapsed").removeClass("sidebar-collapsed-back");
                    $("#menu span").css({"position": "absolute"});
                }
                else
                {
                    $(".page-container").removeClass("sidebar-collapsed").addClass("sidebar-collapsed-back");
                    setTimeout(function () {
                        $("#menu span").css({"position": "relative"});
                    }, 400);
                }

                toggle = !toggle;
            });
        </script>