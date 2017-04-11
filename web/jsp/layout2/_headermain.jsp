<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!--Con thieu div nay <div class="wrap">-->
<div class="header">
    <div class="headertop_desc">
        <div class="call">
        </div>
        <div class="account_desc">
            <ul>
                <s:if test="#session.USER == null">
                    <li><a href="<s:url action="register"></s:url>">Đăng ký</a></li>
                    <li><a href="<s:url action="loginClientInput"></s:url>">Đăng nhập</a></li>
                    </s:if>
                    <s:if test="#session.USER != null">
                    <li><a href="<s:url action="viewProfile"></s:url>"><s:property value="#session.USER"/></a></li>
                    <li><a href="<s:url action="logoutclient"></s:url>">Đăng xuất</a></li>
                    </s:if>
            </ul>
        </div>
        <div class="clear"></div>
    </div>
    <div class="header_top">
        <div class="logo">
            <a href="<s:url action="index"></s:url>"><img src="${pageContext.request.contextPath}/components/images/logo.png" alt="" /></a>
            </div>
            <div class="cart">
                <p>Welcome to our Online Store! <span>Cart:</span><div id="dd" class="wrapper-dropdown-2"> 0 item(s) - $0.00
                    <ul class="dropdown">
                        <li>you have no items in your Shopping cart</li>
                    </ul></div></p>
            </div>
            <script type="text/javascript">
                function DropDown(el) {
                    this.dd = el;
                    this.initEvents();
                }
                DropDown.prototype = {
                    initEvents: function () {
                        var obj = this;

                        obj.dd.on('click', function (event) {
                            $(this).toggleClass('active');
                            event.stopPropagation();
                        });
                    }
                }

                $(function () {

                    var dd = new DropDown($('#dd'));

                    $(document).click(function () {
                        // all dropdowns
                        $('.wrapper-dropdown-2').removeClass('active');
                    });

                });

            </script>
            <div class="clear"></div>
        </div>
        <div class="header_bottom">
            <div class="menu">
                <ul>
                <li class="active"><a href="<s:url action="index"></s:url>">Home</a></li>
<!--                <li><a href="about.html">About</a></li>
                <li><a href="delivery.html">Delivery</a></li>
                <li><a href="news.html">News</a></li>
                <li><a href="contact.html">Contact</a></li>-->
                <div class="clear"></div>
            </ul>
        </div>
        <!--            <div class="search_box">
                        <form>
                            <input type="text" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {
                                                    this.value = 'Search';
                                                }"><input type="submit" value="">
                        </form>
                    </div>-->
        <div class="clear"></div>
    </div>
</div>
