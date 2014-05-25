<%@ page contentType="application/json; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

{
    <spring:hasBindErrors name="${beanName}">
        "hasErrors": true
        <c:if test="${fn:length(errors.globalErrors) > 0}">
            ,
            "globalErrors": {
                <c:forEach items="${errors.globalErrors}" var="globalError" varStatus="globalErrorStatus">
                    "${globalError.objectName}": "<spring:message code="${globalError.code}" />"
                    <c:if test="${!globalErrorStatus.last}">,</c:if>
                </c:forEach>
            }
        </c:if>
        <c:if test="${fn:length(errors.fieldErrors) > 0}">
            ,
            "fieldErrors": {
                <c:forEach items="${errors.fieldErrors}" var="fieldError" varStatus="fieldErrorStatus">
                    "${fieldError.field}": "<spring:message code="${fieldError.code}.${fieldError.objectName}.${fieldError.field}" />"
                    <c:if test="${!fieldErrorStatus.last}">,</c:if>
                </c:forEach>
            }
        </c:if>
    </spring:hasBindErrors>
}
