<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- HTML id -->
<%@ attribute name="id" %>

<!-- HTML name -->
<%@ attribute name="name" %>

<!-- Input field current value -->
<%@ attribute name="value" %>

<!-- Input field's default value if value is undefined -->
<%@ attribute name="defaultValue" %>

<!-- Any additional attributes you want asigned to the input field (ex. attributes="style='width:100px;' size='20'" -->
<%@ attribute name="attributes" %>

<!-- Use or ignore params (i.e. param.value) -->
<%@ attribute name="useParams" type="java.lang.Boolean" %>

<input 
    <c:if test="${!empty id}">id="${id}"</c:if>
    <c:if test="${!empty name}">name="${name}"</c:if>
    
    <c:choose>
        <c:when test="${!empty value}">
            value="<c:out value='${value}' />"
        </c:when> 
        <c:when test="${(empty useParams || useParams) && !empty param[name]}">
            value="<c:out value='${param[name]}' />"
        </c:when> 
        <c:when test="${(empty useParams || useParams) && !empty jwaParam[name]}">
            value="<c:out value='${jwaParam[name]}' />"
        </c:when> 
        <c:when test="${!empty defaultValue}">
            value="<c:out value='${defaultValue}' />"
        </c:when> 
    </c:choose>
    
    ${attributes} 
/>
