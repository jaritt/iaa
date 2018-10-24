<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h1><s:text name="header.lendingList"/></h1>

<s:actionerror/>
<s:form>
    <table>
        <tr>
            <th></th>
            <th><s:text name="label.lendingPublication"/></th>
            <th><s:text name="label.lendingCustomer"/></th>
            <th><s:text name="label.lendingStartDate"/></th>
            <th><s:text name="label.lendingEndDate"/></th>
            <th><s:text name="label.lendingStatus"/></th>
        </tr>
        <s:iterator value="lendings">
            <tr>
                <td><s:radio list="#{id:''}" name="lendingId" theme="simple"/></td>
                <td><s:property value="publicationTitle"/></td>
                <td><s:property value="customerFullName"/></td>
                <td><s:property value="startDate"/></td>
                <td><s:property value="endDate"/></td>
                <td><s:property value="status"/></td>
                <td></td>
            </tr>
        </s:iterator>
    </table>
    <br>
    <s:submit key="button.extendLending" action="prolongLending"/>
    <s:submit key="button.receiveLending" action="receiveLending"/>
    <s:submit key="button.showLending" action="showLending"/>
</s:form>