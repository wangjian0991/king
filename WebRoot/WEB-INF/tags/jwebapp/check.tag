<%@ taglib tagdir="/WEB-INF/tags/jwebapp/support" prefix="tf" %>

<!-- HTML id -->
<%@ attribute name="id" %>

<!-- HTML name -->
<%@ attribute name="name" %>

<!-- If value and selectedValue are equal the checkbox will be selected -->
<%@ attribute name="value" %>
<%@ attribute name="selectedValue" %>

<!-- Any additional attributes you want asigned to the input field (ex. attributes="style='width:100px;' size='20'" -->
<%@ attribute name="attributes" %>

<!-- Use or ignore params (i.e. param.value) -->
<%@ attribute name="useParams" type="java.lang.Boolean" %>

<tf:input 
    id="${id}"
    name="${name}"
    value="${value}"
    attributes="${attributes} type='checkbox' ${((empty useParams || useParams) && !empty param[name] && param[name] == value) || ((empty useParams || useParams) && !empty jwaParam[name] && jwaParam[name] == value) || (!empty selectedValue && selectedValue == value) ? 'checked=\"\"' : ''}" 
/>
