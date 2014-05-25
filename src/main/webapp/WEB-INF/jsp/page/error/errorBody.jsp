<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="errorOutput.jsp" />

<div id="errorsHeader">
    <spring:message code="error.subHeader" />
</div>

<div id="errorsContainer">
    <div class="errorContainer">
        ${errorOutput}
    </div>
</div>
