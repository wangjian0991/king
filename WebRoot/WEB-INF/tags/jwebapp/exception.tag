<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Exception message and stack trace -->

<%@ attribute name="showRoot" type="java.lang.Boolean" %>

<c:choose>
    <c:when test="${showRoot && !empty jwaRootException}">
        <p class="jwaException">${jwaRootException}</p>
        
        <!-- only available with Java 1.4+ -->
        <c:forEach var="stackTraceElement" items="${jwaRootException.stackTrace}">
            <p class="jwaException">${stackTraceElement}</p>
        </c:forEach>
    </c:when>
    <c:when test="${!empty jwaException}">
        <p class="jwaException">${jwaException}</p>
        
        <!-- only available with Java 1.4+ -->
        <c:forEach var="stackTraceElement" items="${jwaException.stackTrace}">
            <p class="jwaException">${stackTraceElement}</p>
        </c:forEach>
    </c:when>
</c:choose>
