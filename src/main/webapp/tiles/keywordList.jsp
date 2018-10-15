<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h1><s:text name="header.keywordManagement" /></h1>

<s:actionerror/>
<s:form>
    <table>
        <tr>
            <th></th>
<<<<<<< Updated upstream
            <th><s:text name="label.keywordId"/></th>
            <th><s:text name="label.keywordText"/></th>
=======
            <th><s:text name="labal.keywordId"/></th>
            <th><s:text name="labal.keywordText"/></th>
>>>>>>> Stashed changes
        </tr>
        <s:iterator value="keywords">
            <tr>
                <td><s:radio list="#{id:''}" name="keywordId" theme="simple"/></td>
                <td><s:property value="id"/></td>
                <td><s:property value="word"/></td>
            </tr>
        </s:iterator>
    </table>
<<<<<<< Updated upstream
    <s:submit key="button.addKeyword" action="addKeyword" value="Add"/>
    <s:submit key="button.editKeyword" action="loadKeyword" value="Edit"/>
    <s:submit key="button.deleteKeyword" action="deleteKeyword" value="Delete"/>
=======
    <s:submit key="button.addKeyword" action="addKeyword" />
    <s:submit key="button.editKeyword" action="loadKeyword"/>
    <s:submit key="button.deleteKeyword" action="deleteKeyword"/>
>>>>>>> Stashed changes
</s:form>