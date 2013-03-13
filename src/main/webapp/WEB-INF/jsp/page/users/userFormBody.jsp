<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="formAction" value="/users" scope="page" />
<c:set var="formMethod" value="post" scope="page" />
<c:if test="${userForm.edit}">
	<c:url var="formAction" value="/users/${userForm.id}" scope="page" />
	<c:set var="formMethod" value="put" scope="page" />
</c:if>

<div class="section">
	<form:form modelAttribute="userForm" action="${formAction}" method="${formMethod}" id="userForm">
		<form:hidden path="id" id="id" />
		<form:hidden path="edit" />

		<div class="globalErrorsContainer">
			<form:errors cssClass="formError" />
		</div>

		<div>
			<label for="firstName"><spring:message code="userForm.firstName" /></label>
			<form:input path="firstName" id="firstName" cssClass="inputField" />
			<form:errors path="firstName" cssClass="formError" />
		</div>

		<div>
			<label for="lastName"><spring:message code="userForm.lastName" /></label>
			<form:input path="lastName" id="lastName" cssClass="inputField" />
			<form:errors path="lastName" cssClass="formError" />
		</div>

		<div>
			<label for="admin"><spring:message code="userForm.admin" /></label>
			<form:checkbox path="admin" id="admin" cssClass="inputField" value="true" />
			<form:errors path="admin" cssClass="formError" />
		</div>

		<div class="buttons">
			<input type="submit" value="<spring:message code="userForm.submit" />" />
		</div>
	</form:form>

	<c:if test="${userForm.edit}">
		<div class="userActionLinksContainer">
			<a href="<c:url value="/users/${userForm.id}" />" class="control-view">
				<spring:message code="viewAllUsers.viewUserLink" />
			</a>
		</div>
	</c:if>
</div>
