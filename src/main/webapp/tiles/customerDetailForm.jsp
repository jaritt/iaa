<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h1><s:text name="header.customerForm" /></h1>

<s:form>
    <s:hidden name="customer.id"/>
    <s:hidden name="lending.id"/>
    <s:textfield name="customer.title" key="label.customerTitle" disabled="true"/>
    <s:textfield name="customer.name" key="label.customerName" disabled="true"/>
    <s:textfield name="customer.firstName" key="label.customerFirstName" disabled="true"/>
    <s:textfield name="customer.city" key="label.customerCity" disabled="true"/>
    <s:textfield name="customer.street" key="label.customerStreet" disabled="true"/>
    <s:textfield name="customer.matnr" key="label.customerMatnr" disabled="true"/>
    <s:submit key="button.back" action="showLendingForm"/>
    <s:submit key="button.back" action="sendLendingIdForLendingShowForm"/>
</s:form>
