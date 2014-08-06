<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Messages sent from the web application -->

<c:if test="${!empty jwaErrorMessage}">
    <p class="jwaErrorMessage">${jwaErrorMessage}</p>
</c:if>

<c:if test="${!empty jwaWarningMessage}">
    <p class="jwaWarningMessage">${jwaWarningMessage}</p>
</c:if>

<c:if test="${!empty jwaApplicationMessage}">
    <p class="jwaApplicationMessage">${jwaApplicationMessage}</p>
</c:if>
