<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- HTML id -->
<%@ attribute name="id" %>

<!-- HTML name -->
<%@ attribute name="name" %>

<!-- Input field current value -->
<%@ attribute name="value" %>

<!-- The number of rows for the textarea -->
<%@ attribute name="rows" %>

<!-- The number of columns for the textarea -->
<%@ attribute name="cols" %>

<!-- Input field's default value if value is undefined -->
<%@ attribute name="defaultValue" %>

<!-- Any additional attributes you want asigned to the input field (ex. attributes="style='width:100px;' size='20'" -->
<%@ attribute name="attributes" %>

<!-- Use or ignore params (i.e. param.value) -->
<%@ attribute name="useParams" type="java.lang.Boolean" %>


<textarea 
<c:if test="${!empty id}">id="${id}"</c:if>
<c:if test="${!empty name}">name="${name}"</c:if>
<c:if test="${!empty rows}">rows="${rows}"</c:if>
<c:if test="${!empty cols}">cols="${cols}"</c:if>
${attributes}><c:choose><c:when test="${!empty value}"><c:out value="${value}" /></c:when><c:when test="${(empty useParams || useParams) && !empty param[name]}"><c:out value="${param[name]}" /></c:when><c:when test="${(empty useParams || useParams) && !empty jwaParam[name]}"><c:out value="${jwaParam[name]}" /></c:when><c:otherwise><c:out value="${defaultValue}" /></c:otherwise></c:choose></textarea>

