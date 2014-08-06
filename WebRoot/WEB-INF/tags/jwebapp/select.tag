<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- HTML id -->
<%@ attribute name="id" %>

<!-- HTML name -->
<%@ attribute name="name" %>

<!-- Allow multiple selections -->
<%@ attribute name="multiple" %>

<!-- Use select area instead of drop down box -->
<%@ attribute name="size" %>

<!-- The selected value -->
<%@ attribute name="selectedValue" %>

<!-- The selected value(s) when multiple is true -->
<%@ attribute name="selectedValues" type="java.util.Map" %>

<!-- Comma separated list of values for the select (ex. commaSeparatedValues="value1, value2, value3") -->
<%@ attribute name="commaSeparatedValues" %>

<!-- A Java list with (eg. ArrayList) values -->
<%@ attribute name="valuesList" type="java.util.List" %>

<!-- A Java map with (eg. HashMap) key/value pairs.  The key part is the option value and the value part is the displayed value -->
<%@ attribute name="valuesMap" type="java.util.Map" %>

<!-- Any additional attributes for the select (ex. attributes="style='width:100px;'" -->
<%@ attribute name="selectAttributes" %>

<!-- Any additional attributes for the options (ex. attributes="style='width:100px;'" -->
<%@ attribute name="optionAttributes" %>

<!-- Use or ignore params (i.e. param.value) -->
<%@ attribute name="useParams" type="java.lang.Boolean" %>

<select
    <c:if test="${!empty id}">id="${id}"</c:if>
    <c:if test="${!empty name}">name="${name}"</c:if>
    <c:if test="${!empty size}">size="${size}"</c:if>
    <c:if test="${!empty multiple}">multiple="${multiple}"</c:if>
    
    ${selectAttributes}>

    <c:if test="${!empty commaSeparatedValues}">
        <c:forEach var="listValue" items="${fn:split(commaSeparatedValues,',')}">
        
            <c:set var="listValue" value="${fn:trim(listValue)}" />
            <c:set var="valueSelected" value="false" />
            
            <c:if test="${empty useParams || useParams}">
                <c:forEach var="value" items="${paramValues[name]}">
                    <c:if test="${valueSelected == 'false' && value == listValue}">
                        <c:set var="valueSelected" value="true" />
                    </c:if>
                </c:forEach>
            </c:if>

            <c:if test="${(empty useParams || useParams) && valueSelected == 'false'}">
                <c:forEach var="value" items="${jwaParam[name]}">
                    <c:if test="${valueSelected == 'false' && value == listValue}">
                        <c:set var="valueSelected" value="true" />
                    </c:if>
                </c:forEach>
            </c:if>

            <option ${(!empty selectedValue && selectedValue == listValue) 
                    || valueSelected == "true"
                    || (!empty selectedValues && !empty selectedValues[listValue]) ? 'selected=""' : ''} 
                    value="${listValue}" ${optionAttributes}>&nbsp;${listValue}&nbsp;&nbsp;</option>
        </c:forEach>
    </c:if>
    
    <c:if test="${!empty valuesList}">
        <c:forEach var="listValue" items="${valuesList}">
        
            <c:set var="valueSelected" value="false" />
            
            <c:if test="${empty useParams || useParams}">
                <c:forEach var="value" items="${paramValues[name]}">
                    <c:if test="${valueSelected == 'false' && value == listValue}">
                        <c:set var="valueSelected" value="true" />
                    </c:if>
                </c:forEach>
            </c:if>

            <c:if test="${(empty useParams || useParams) && valueSelected == 'false'}">
                <c:forEach var="value" items="${jwaParam[name]}">
                    <c:if test="${valueSelected == 'false' && value == listValue}">
                        <c:set var="valueSelected" value="true" />
                    </c:if>
                </c:forEach>
            </c:if>

            <option ${(!empty selectedValue && selectedValue == listValue) 
                    || valueSelected == "true"
                    || (!empty selectedValues && !empty selectedValues[listValue]) ? 'selected=""' : ''} 
                    value="${listValue}" ${optionAttributes}>&nbsp;${listValue}&nbsp;&nbsp;</option>
        </c:forEach>
    </c:if>
    
    <c:if test="${!empty valuesMap}">
        <c:forEach var="mapEntry" items="${valuesMap}">

            <c:set var="valueSelected" value="false" />
            
            <c:if test="${empty useParams || useParams}">
                <c:forEach var="value" items="${paramValues[name]}">
                    <c:if test="${valueSelected == 'false' && value == mapEntry.key}">
                        <c:set var="valueSelected" value="true" />
                    </c:if>
                </c:forEach>
            </c:if>

            <c:if test="${(empty useParams || useParams) && valueSelected == 'false'}">
                <c:forEach var="value" items="${jwaParam[name]}">
                    <c:if test="${valueSelected == 'false' && value == mapEntry.key}">
                        <c:set var="valueSelected" value="true" />
                    </c:if>
                </c:forEach>
            </c:if>

            <option ${(!empty selectedValue && selectedValue == mapEntry.key)
                    || valueSelected == "true"
                    || (!empty selectedValues && !empty selectedValues[mapEntry.key]) ? 'selected=""' : ''} 
                    value="${mapEntry.key}" ${optionAttributes}>&nbsp;${mapEntry.value}&nbsp;&nbsp;</option>
        </c:forEach>
    </c:if>
</select>

