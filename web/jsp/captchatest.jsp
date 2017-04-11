<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="botDetect" uri="https://captcha.com/java/jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
    <head>
        <META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Simple CAPTCHA Example</title>
    </head>
    <body>
        <h3>Simple CAPTCHA Example</h3>
        <!--        <label for="captchaCode" class="prompt">
                    Retype the characters from the picture:</label>
        <%--<botDetect:captcha id="exampleCaptchaTag" userInputID="captchaCode"/>--%>
    <input id="captchaCode" type="text" name="captchaCode" />-->
        <s:form>
            <botDetect:captcha id="exampleCaptcha" userInputID="captchaCode"/>

            <s:textfield name="captchaCode" id="captchaCode"/>
            <s:submit name="submit" label="Submit" id="submit"/>

        </s:form>
    </body>
</html>