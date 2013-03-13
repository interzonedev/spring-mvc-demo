<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="topNav">
	<a href="<c:url value="/home" />"><spring:message code="home.link" /></a>
	|
	<a href="<c:url value="/users/new" />"><spring:message code="userForm.link" /></a>
	|
	<a href="<c:url value="/users" />"><spring:message code="viewAllUsers.link" /></a>
</div>
