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
            <td colspan="2" rowspan="2">
                <p>삭제처리의 오류에 대한 알림</p>
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
    <tr id="search_row">
        <th>
            <select name="" id="">
                <option value="">사원번호</option>
                <option value="">성명</option>
                <option value="">직위</option>
                <option value="">부서</option>
                <option value="">나이</option>
            </select>
        </th>
        <th colspan="3">
            <input type="text">
        </th>
        <th>
            <button>검색</button>
        </th>
    </tr>
    </tbody>
</table>
<script>

    const $body = document.querySelector('body');

    const $selectable = document.querySelectorAll('.selectable');

    $selectable.forEach((element) => {
        element.addEventListener('click', (e) => {
            const $identfier = e.currentTarget.children[0].textContent;
            updateEmploy($identfier);
        })
    })

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
