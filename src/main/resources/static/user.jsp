<%--
  Created by IntelliJ IDEA.
  User: chenqi
  Date: 2021/6/22
  Time: 8:31 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user</title>
</head>
<body>
<c:forEach items="${form}" var="map">
    获取map key: ${map.key}
    <c:forEach items="${map.value}" var="list"><br>　
        获取list内容: ${list.id} ${list.name} ${list.timestamp}
    </c:forEach>
</c:forEach>
<hr/>

</body>
</html>
