<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="errorOutput" value="" scope="request" />
<c:choose>
    <c:when test="${not empty errorCode}">
        <spring:message code="${errorCode}" var="errorOutput" scope="request" />
    </c:when>
    <c:when test="${not empty param.errorCode}">
        <spring:message code="${param.errorCode}" var="errorOutput" scope="request" />
    </c:when>
    <c:when test="${not empty errorMessage}">
        <c:set var="errorOutput" value="${errorMessage}" scope="request" />
    </c:when>
    <c:when test="${not empty param.errorMessage}">
        <c:set var="errorOutput" value="${param.errorMessage}" scope="request" />
    </c:when>
</c:choose>
