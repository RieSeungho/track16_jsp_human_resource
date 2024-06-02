<%--
  Created by IntelliJ IDEA.
  User: dooft
  Date: 24. 5. 30.
  Time: 오후 8:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/alpha-style.css"/>">
    <title>이승호 - 부서관리</title>
</head>
<body>
<nav>
    <a href="<c:url value="/employ"/>">사원관리</a>
    <a href="<c:url value="/grade"/>">직급관리</a>
    <a href="<c:url value="/depart"/>">부서관리</a>
</nav>
<header>
    <h1>부서관리</h1>
</header>
<table>
    <thead>
        <tr>
            <th>부서명</th>
            <th>부서코드</th>
            <th>소속인원</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="depart" items="${departList}">
        <tr>
            <td>${depart.departCode}</td>
            <td>${depart.departName}</td>
            <td>${depart.departPersonnel}명</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
