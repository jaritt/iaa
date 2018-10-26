<%--
  Created by IntelliJ IDEA.
  User: VSHARMA
  Date: 20.10.2018
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
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
    <s:submit key="button.lendPublication" action="saveLending"/>
    <s:submit key="button.back" action="showPublicationShowForm"/>
    <s:submit key="button.cancel" action="showPublicationList"/>
</s:form>
