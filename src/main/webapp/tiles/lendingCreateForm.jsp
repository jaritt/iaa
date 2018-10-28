<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h1><s:text name="header.lendingForm" /></h1>

<s:actionerror/>
<s:form>
    <s:hidden name="lending.id"/>
    <s:hidden name="publicationId"/>
    <s:select name="selectedCustomerId"
              headerKey="0"
              headerValue="Select..."
              list="customerList"
              listKey="id"
              listValue="%{name +', ' + firstName}"
              key="label.lendingCustomer"/>
    <s:textfield name="publication.title" key="label.publicationTitle" disabled="true"/>
    <s:textfield name="startDate" key="label.lendingStartDate" disabled="true"/>
    <s:textfield name="endDate"  key="label.lendingEndDate" disabled="true"/>
    <s:submit key="button.lendPublication" action="lendPublication"/>
    <s:submit key="button.back" action="showPublicationShowForm"/>
    <s:submit key="button.cancel" action="showPublicationList"/>
</s:form>

<%--
<s:actionerror/>
<s:form>
    <s:hidden name="lending.id"/>
    <s:hidden name="publicationiId"/>
    <s:text name="label.lendingCustomer"/>
    <s:select name="selectedCustomerId"
              headerKey="0"
              headerValue="Select..."
              list="customerList"
              listKey="id"
              listValue="%{name + ', ' + firstName}"
              key="label.lendingCustomer"/>
    <table>
        <tr>
            <th><s:text name="label.lendingPublicationTitle"/></th>
            <td><s:property value="publication.title"/></td>
        </tr>
        <tr>
            <th><s:text name="label.lendingStartDate"/></th>
            <td><s:property value="startDate"/></td>
        </tr>
        <tr>
            <th><s:text name="label.lendingEndDate"/></th>
            <td><s:property value="endDate"/></td>
        </tr>
    </table>
    <br>
    <div>
        <button formaction="saveLending"><s:text name="button.lendPublication"/></button>
        <button formaction="showPublicationShowForm"><s:text name="button.back"/></button>
        <button formaction="showPublicationList"><s:text name="button.cancel"/></button>
    </div>
</s:form>
--%>