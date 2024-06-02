<%--
  Created by IntelliJ IDEA.
  User: dooft
  Date: 24. 5. 31.
  Time: 오전 7:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/alpha-style.css"/>">
    <title>이승호 - 사원수정</title>
</head>
<body>
<nav>
    <a href="<c:url value="/employ"/>">사원관리</a>
    <a href="<c:url value="/grade"/>">직급관리</a>
    <a href="<c:url value="/depart"/>">부서관리</a>
</nav>
<header>
    <h1>사원수정</h1>
</header>
<form action="<c:url value="/employ/update"/>" method="POST">
<table>
    <tbody>
        <tr>
            <td>
                <span>사원번호</span>
            </td>
            <td>
                <span>${employMap.no}</span>
            </td>
        </tr>
        <tr>
            <td>
                <label for="name">성명</label>
            </td>
            <td>
                <input type="text" name="name" id="name" value="${employMap.name}">
            </td>
        </tr>
        <tr>
            <td>
                <label for="grade">직위명</label>
            </td>
            <td>
                <select name="grade" id="grade">
                    <c:forEach var="grade" items="${gradeList}">
                        <option value="${grade.gradeCode}" <c:if test="${grade.gradeCode == employMap.grade}">selected</c:if>>${grade.gradeName} (해당 인원 : ${grade.gradePersonnel}명)</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <label for="depart">부서명</label>
            </td>
            <td>
                <select name="depart" id="depart">
                    <c:forEach var="depart" items="${departList}">
                        <option value="${depart.departCode}" <c:if test="${depart.departCode == employMap.depart}">selected</c:if>>${depart.departName} (소속 인원 : ${depart.departPersonnel}명)</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <label for="age">나이</label>
            </td>
            <td>
                <input type="text" name="age" id="age" value="${employMap.age}">
            </td>
        </tr>
        <c:if test="${not empty errorMessage}">
            <tr>
                <td colspan="5">${errorMessage}</td>
            </tr>
        </c:if>
        <tr>
            <td colspan="5">
                <button type="submit">수정처리</button>
            </td>
        </tr>
        <tr>
            <td colspan="5">
                <a href="javascript:deleteEmploy('${employMap.no}')">삭제처리</a>
            </td>
        </tr>
    </tbody>
</table>
<input type="hidden" name="no" value="${employMap.no}">
</form>
<script>
    const $body = document.querySelector('body');

    function deleteEmploy(no) {
        const $form = document.createElement('form');
        const $input = document.createElement('input');

        $form.setAttribute('method', 'POST');
        $form.setAttribute('action', '/employ/delete');

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
