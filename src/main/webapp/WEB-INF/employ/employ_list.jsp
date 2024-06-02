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
        <tr>
            <td>
                <a href="javascript:updateEmploy(${employ.no})">${employ.no}</a>
            </td>
            <td>${employ.name}</td>
            <td>${employ.grade}</td>
            <td>${employ.depart}</td>
            <td>${employ.age}</td>
        </tr>
    </c:forEach>
    <c:if test="${not empty errorMessage}">
        <tr>
            <td colspan="5">${errorMessage}</td>
        </tr>
    </c:if>
    <tr>
        <td colspan="5">
            <a href="<c:url value="/employ/form"/>">사원등록</a>
        </td>
    </tr>
    <tr>
        <td colspan="5">
            <select name="" id="">
                <option value="">검색</option>
            </select>
            <input type="text">
            <button>검색</button>
        </td>
    </tr>
    </tbody>
</table>
<script>

    const $body = document.querySelector('body');

    function updateEmploy(no) {
        const $form = document.createElement('form');
        const $input = document.createElement('input');

        $form.setAttribute('method', 'POST');
        $form.setAttribute('action', '/employ/update/form');

        $input.setAttribute('type', 'hidden');
        $input.setAttribute('name', 'no');
        $input.setAttribute('value', no);

        $form.appendChild($input);

        $body.appendChild($form);

        $form.submit()
    }
</script>
</body>
</html>
