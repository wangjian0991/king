<%@ taglib tagdir="/WEB-INF/tags/jwebapp/support" prefix="tf" %>

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

<tf:input 
    id="${id}" 
    name="${name}" 
    value="${value}" 
    defaultValue="${defaultValue}" 
    attributes="${attributes} type='password'" 
    useParams="${empty useParams || useParams ? 'true' : 'false'}"
/>

