
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
    <h1>직급관리</h1>
</header>
<table>
    <thead>
    <tr>
        <th>직위명</th>
        <th>직위코드</th>
        <th>해당인원</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="grade" items="${gradeList}">
        <tr>
            <td>${grade.gradeCode}</td>
            <td>${grade.gradeName}</td>
            <td>${grade.gradePersonnel}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
