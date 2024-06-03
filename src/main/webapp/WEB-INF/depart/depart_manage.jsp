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
    <link rel="stylesheet" href="<c:url value="/css/depart-style.css"/>">
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
    <tbody id="depart_manage_panel">
    <c:forEach var="depart" items="${departList}">
        <tr>
            <td>${depart.departCode}</td>
            <td>${depart.departName}</td>
            <td>${depart.departPersonnel}명</td>
        </tr>
        <c:if test="${depart.departPersonnel eq '0'}">
            <tr class="delectable">
                <td colspan="3" data-grade="${depart.departCode}">${depart.departName}부서를 삭제할 수 있습니다</td>
            </tr>
        </c:if>
    </c:forEach>
    <c:if test="${not empty errorMessage}">
        <tr class="errorMessage">
            <td colspan="3" rowspan="2">
                <span>${errorMessage}</span>
            </td>
        </tr>
        <tr>&nbsp;</tr>
    </c:if>
    <tr class="anchor_button">
        <td>
            <a class="depart_tab">부서등록</a>
        </td>
        <td>
            <a class="depart_tab">명칭변경</a>
        </td>
        <td>
            <a class="depart_tab">부서병합</a>
        </td>
    </tr>
    </tbody>
</table>

<form class="depart_tab_item" name="depart_insert" action="<c:url value="/depart/insert"/>" method="POST">
    <table>
        <tbody>
        <tr>
            <td colspan="2">부서등록</td>
        </tr>
        <tr class="input_space">
            <td>
                <label for="departCode">부서코드</label>
            </td>
            <td>
                <input type="text" name="departCode" id="departCode">
            </td>
        </tr>
        <tr class="input_space">
            <td>
                <label for="departName">부서명칭</label>
            </td>
            <td>
                <input type="text" id="departName" name="departName">
            </td>
        </tr>
        <tr class="anchor_button">
            <td colspan="2">
                <a href="javascript:departInsert()">부서등록</a>
            </td>
        </tr>
        </tbody>
    </table>
</form>

<form class="depart_tab_item" name="depart_name_update" action="<c:url value="/depart/update"/>" method="POST">
    <table>
        <tbody>
        <tr>
            <td colspan="2">명칭변경</td>
        </tr>
        <tr class="input_space">
            <td>
                <label for="existCode">기존 부서명칭</label>
            </td>
            <td>
                <select name="existCode" id="existCode">
                    <c:forEach var="depart" items="${departList}">
                        <option value="${depart.departCode}">${depart.departName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr class="input_space">
            <td>
                <label for="updateName">변경 부서명칭</label>
            </td>
            <td>
                <input type="text" id="updateName" name="updateName">
            </td>
        </tr>
        <tr class="anchor_button">
            <td colspan="2">
                <a href="javascript:departNameUpdate()">명칭변경</a>
            </td>
        </tr>
        </tbody>
    </table>
</form>

<form class="depart_tab_item" name="depart_merge" action="<c:url value="/depart/merge"/>" method="POST">
    <table>
        <tbody>
        <tr>
            <td colspan="2">부서병합</td>
        </tr>
        <tr class="input_space">
            <td>
                <label for="mergeFrom">병합대상</label>
            </td>
            <td>
                <select name="mergeFrom" id="mergeFrom">
                    <option value="">병합을 실행할 대상을 선택해주세요</option>
                    <c:forEach var="depart" items="${departList}">
                        <option value="${depart.departCode}">${depart.departName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr class="input_space">
            <td>
                <label for="mergeTo">병합목표</label>
            </td>
            <td>
                <select name="mergeTo" id="mergeTo">
                    <option value="">병합을 완료할 목표를 선택해주세요</option>
                    <c:forEach var="depart" items="${departList}">
                        <option value="${depart.departCode}">${depart.departName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr class="anchor_button">
            <td colspan="2">
                <a href="javascript:departMerge()">부서병합</a>
            </td>
        </tr>
        </tbody>
    </table>
</form>
<script src="<c:url value="/js/depart.js"/>"></script>
</body>
</html>
