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
    <title>이승호 - 사원관리</title>
</head>
<body>
<nav>
    <a href="<c:url value="/employ"/>">사원관리</a>
    <a href="<c:url value="/grade"/>">직급관리</a>
    <a href="<c:url value="/depart"/>">부서관리</a>
    <a href="https://github.com/RieSeungho/track16_jsp_human_resource">코드확인</a>
</nav>
<header>
    <h1>사원관리</h1>
</header>
<table>
    <thead>
        <tr>
            <th>사원번호</th>
            <th>성명</th>
            <th>직위명</th>
            <th>부서명</th>
            <th>나이</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="employ" items="${employList}">
        <tr class="selectable">
            <td>${employ.no}</td>
            <td>${employ.name}</td>
            <td>${employ.grade}</td>
            <td>${employ.depart}</td>
            <td>${employ.age}</td>
        </tr>
    </c:forEach>
    <c:if test="${not empty errorMessage}">
        <tr class="errorMessage" style="color: red">
            <td colspan="5" rowspan="2">
                <span>${errorMessage}</span>
            </td>
        </tr>
        <tr>&nbsp;</tr>
    </c:if>
    <tr class="anchor_button">
        <td colspan="5">
            <a href="<c:url value="/employ/form"/>">사원등록</a>
        </td>
    </tr>
    <form action="<c:url value="/employ"/>" method="post">
        <tr id="search_row">
            <th>
                <select name="searchKey">
                    <option value="NO" <c:if test="${searchKey eq 'NO'}">selected</c:if>>사원번호</option>
                    <option value="NAME" <c:if test="${searchKey eq 'NAME'}">selected</c:if>>성명</option>
                    <option value="GRADE" <c:if test="${searchKey eq 'GRADE'}">selected</c:if>>직위</option>
                    <option value="DEPART" <c:if test="${searchKey eq 'DEPART'}">selected</c:if>>부서</option>
                    <option value="AGE" <c:if test="${searchKey eq 'AGE'}">selected</c:if>>나이</option>
                </select>
            </th>
            <th colspan="3">
                <input type="text" name="searchValue" <c:if test="${not empty searchValue}">placeholder="${searchValue}"</c:if>>
            </th>
            <th>
                <button type="submit">검색</button>
            </th>
        </tr>
    </form>
    </tbody>
</table>
<script src="<c:url value="/js/employ.js"/>"></script>
</body>
</html>
