<%--
  User: devin
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Spittr</title>
</head>
<body>
<ul>
    <c:forEach items="${spittleList}" var="spittle">
        <li id="spittle_<c:out value="spittle.id"/>">
            <div class="spittleMessage">
                <c:out value="${spittle.message}"/>
            </div>
            <div>
                <span class="spittleTime"><c:out value="${spittle.time}"/></span>
                <span class="spittleLocation">(<c:out value="${spittle.latitude}"/>, <c:out value="${spittle.longitude}"/>)</span>
            </div>
        </li>
    </c:forEach>
</ul>
</body>
</html>
