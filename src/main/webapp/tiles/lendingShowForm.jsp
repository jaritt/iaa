<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h1><s:text name="header.lendingForm"/></h1>

<s:form>
    <s:hidden name="lending.id"/>
    <s:hidden name="lending.publicationId"/>
    <s:hidden name="customer.id"/>
    <s:textfield name="lending.customerFullName" key="label.lendingCustomer" disabled="true"/>
    <s:submit key="button.toCustomer" action="sendCustomerId"/>
    <s:textfield name="lending.startDate" key="label.lendingStartDate" disabled="true"/>
    <s:textfield name="lending.endDate"  key="label.lendingEndDate" disabled="true"/>
    <s:textfield name="lending.status" key="label.lendingStatus" disabled="true"/>
    <s:textfield name="lending.publicationKey" key="label.publicationKey" disabled="true"/>
    <s:textfield name="lending.publicationTitle" key="label.publicationTitle" disabled="true"/>
    <s:submit key="button.toPublication" action="sendPublicationIdForPublicationShowForm"/>
    <s:submit key="button.back" action="showLendingList"/>
</s:form>
