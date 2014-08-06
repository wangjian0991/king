<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="value" %>
<%@ attribute name="defaultValue" %>

<c:out value="${value}" default="${defaultValue}" />
