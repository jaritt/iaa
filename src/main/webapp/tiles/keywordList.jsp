<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h1><s:text name="header.keywordManagement"/></h1>

<s:actionerror/>
<s:form>
    <table>
        <tr>
            <th></th>
            <th><s:text name="label.keywordId"/></th>
            <th><s:text name="label.keywordText"/></th>
        </tr>
        <s:iterator value="keywords">
            <tr>
                <td><s:radio list="#{id:''}" name="keywordId" theme="simple"/></td>
                <td><s:property value="id"/></td>
                <td><s:property value="word"/></td>
            </tr>
        </s:iterator>
    </table>
    <br>
    <s:submit key="button.addKeyword" action="addKeyword" value="Add Keyword"/>
    <s:submit key="button.deleteKeyword" action="deleteKeyword" value="Delete Keyword"/>
</s:form>