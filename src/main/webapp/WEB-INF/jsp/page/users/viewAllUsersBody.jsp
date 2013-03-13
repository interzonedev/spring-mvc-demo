<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="section viewAllUsers">
	<c:choose>
		<c:when test="${fn:length(allUsers) > 0}">
			<c:forEach items="${allUsers}" var="user">
				<div class="userContainer">
					<div>
						<spring:message code="viewAllUsers.id" /> ${user.id}
					</div>
					<div>
						<spring:message code="viewAllUsers.firstName" /> ${user.firstName}
					</div>
					<div>
						<spring:message code="viewAllUsers.lastName" /> ${user.lastName}
					</div>
					<div>
						<spring:message code="viewAllUsers.admin" /> ${user.admin}
					</div>
					<div class="userActionLinksContainer">
						<a href="<c:url value="/users/${user.id}" />" class="control-view">
							<spring:message code="viewAllUsers.viewUserLink" />
						</a>
						<a href="<c:url value="/users/${user.id}/edit" />" class="control-edit">
							<spring:message code="viewAllUsers.editUserLink" />
						</a>
						<a href="<c:url value="/users/${user.id}?_method=delete" />" class="control-delete">
							<spring:message code="viewAllUsers.deleteUserLink" />
						</a>
					</div>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<spring:message code="viewAllUsers.noResults" />
		</c:otherwise>
	</c:choose>
</div>
