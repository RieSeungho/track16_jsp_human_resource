
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
    <link rel="stylesheet" href="<c:url value="/css/grade-style.css"/>">
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
        <th>직급코드</th>
        <th>직급명칭</th>
        <th>해당인원</th>
    </tr>
    </thead>
    <tbody id="grade_manage_panel">
    <c:forEach var="grade" items="${gradeList}">
        <tr <c:if test="${grade.gradePersonnel ne '0'}">class="searchable" data-direct="${grade.gradeName}"</c:if>>
            <td>${grade.gradeCode}</td>
            <td>${grade.gradeName}</td>
            <td>${grade.gradePersonnel}명</td>
        </tr>
        <c:if test="${grade.gradePersonnel eq '0'}">
            <tr class="delectable">
                <td colspan="3" data-grade="${grade.gradeCode}">${grade.gradeName}직급을 삭제할 수 있습니다</td>
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
        <td colspan="3">
            <a href="<c:url value="/employ"/>">초기화면</a>
        </td>
    </tr>
    <tr class="anchor_button">
        <td>
            <a class="grade_tab">직급등록</a>
        </td>
        <td>
            <a class="grade_tab">명칭변경</a>
        </td>
        <td>
            <a class="grade_tab">직급병합</a>
        </td>
    </tr>
    </tbody>
</table>

<form class="grade_tab_item" name="grade_insert" action="<c:url value="/grade/insert"/>" method="POST">
    <table>
        <tbody>
        <tr>
            <td colspan="2">직급등록</td>
        </tr>
        <tr class="input_space">
            <td>
                <label for="gradeCode">직급코드</label>
            </td>
            <td>
                <input type="text" name="gradeCode" id="gradeCode">
            </td>
        </tr>
        <tr class="input_space">
            <td>
                <label for="gradeName">직급명칭</label>
            </td>
            <td>
                <input type="text" id="gradeName" name="gradeName">
            </td>
        </tr>
        <tr class="anchor_button">
            <td colspan="2">
                <a href="javascript:gradeInsert()">직급등록</a>
            </td>
        </tr>
        </tbody>
    </table>
</form>

<form class="grade_tab_item" name="grade_name_update" action="<c:url value="/grade/update"/>" method="POST">
    <table>
        <tbody>
        <tr>
            <td colspan="2">명칭변경</td>
        </tr>
        <tr class="input_space">
            <td>
                <label for="existCode">기존 직급명칭</label>
            </td>
            <td>
                <select name="existCode" id="existCode">
                    <c:forEach var="grade" items="${gradeList}">
                        <option value="${grade.gradeCode}">${grade.gradeName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr class="input_space">
            <td>
                <label for="updateName">변경 직급명칭</label>
            </td>
            <td>
                <input type="text" id="updateName" name="updateName">
            </td>
        </tr>
        <tr class="anchor_button">
            <td colspan="2">
                <a href="javascript:gradeNameUpdate()">명칭변경</a>
            </td>
        </tr>
        </tbody>
    </table>
</form>

<form class="grade_tab_item" name="grade_merge" action="<c:url value="/grade/merge"/>" method="POST">
    <table>
        <tbody>
        <tr>
            <td colspan="2">직급병합</td>
        </tr>
        <tr class="input_space">
            <td>
                <label for="mergeFrom">병합대상</label>
            </td>
            <td>
                <select name="mergeFrom" id="mergeFrom">
                    <option value="">병합을 실행할 대상을 선택해주세요</option>
                    <c:forEach var="grade" items="${gradeList}">
                        <option value="${grade.gradeCode}">${grade.gradeName}</option>
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
                    <c:forEach var="grade" items="${gradeList}">
                        <option value="${grade.gradeCode}">${grade.gradeName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr class="anchor_button">
            <td colspan="2">
                <a href="javascript:gradeMerge()">직급병합</a>
            </td>
        </tr>
        </tbody>
    </table>
</form>

<script src="<c:url value="/js/grade.js"/>"></script>
</body>
</html>
