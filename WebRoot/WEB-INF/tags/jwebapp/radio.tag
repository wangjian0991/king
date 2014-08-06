<%@ taglib tagdir="/WEB-INF/tags/jwebapp/support" prefix="tf" %>

<!-- HTML id -->
<%@ attribute name="id" %>

<!-- HTML name - radio button series should all have the same name -->
<%@ attribute name="name" %>

<!-- Radio button value -->
<%@ attribute name="value" %>

<!-- Radio button is the default selection -->
<%@ attribute name="selected" %>

<!-- If selectedValue is equal to value the radio button is selected -->
<%@ attribute name="selectedValue" %>

<!-- Any additional attributes you want asigned to the input field (ex. attributes="style='width:100px;' size='20'" -->
<%@ attribute name="attributes" %>

<!-- Use or ignore params (i.e. param.value) -->
<%@ attribute name="useParams" type="java.lang.Boolean" %>

<tf:input 
    id="${id}" 
    name="${name}" 
    value="${value}" 
    attributes="${attributes} type='radio' ${((empty useParams || useParams) && !empty param[name] && param[name] == value) || ((empty useParams || useParams)  && !empty jwaParam[name] && jwaParam[name] == value) || (!empty selectedValue && selectedValue == value) || (empty selectedValue && ((empty useParams || useParams) && empty param[name]) && ((empty useParams || useParams) && empty jwaParam[name]) && !empty selected) ? 'checked=\"\"' : ''}" 
/>
