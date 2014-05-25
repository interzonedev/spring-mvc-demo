<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="section">
    <div>
        <spring:message code="viewUser.id" /> ${user.id}
    </div>
    <div>
        <spring:message code="viewUser.firstName" /> ${user.firstName}
    </div>
    <div>
        <spring:message code="viewUser.lastName" /> ${user.lastName}
    </div>
    <div>
        <spring:message code="viewUser.admin" /> ${user.admin}
    </div>
    <div class="userActionLinksContainer">
        <a href="<c:url value="/users/${user.id}/edit" />"class="control-edit">
            <spring:message code="viewUser.editUserLink" />
        </a>
        <a href="<c:url value="/users/${user.id}?_method=delete" />" class="control-delete">
            <spring:message code="viewUser.deleteUserLink" />
        </a>
    </div>
</div>
