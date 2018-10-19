t<%--
  Created by IntelliJ IDEA.
  User: VSHARMA
  Date: 17.10.2018
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h1><s:text name="header.customerForm" /></h1>

<s:form>
    <s:hidden name="customer.id"/>
    <s:select name="customer.title" label="label.customerTitle" headerValue="Select Title" headerKey="-1" list="#{'Herr': 'Herr', 'Frau': 'Frau'}" requiredLabel="true"/>
    <s:textfield name="customer.name" key="label.customerName" requiredLabel="true"/>
    <s:textfield name="customer.firstName" key="label.customerFirstName" requiredLabel="true"/>
    <s:textfield name="customer.city" key="label.customerCity" requiredLabel="true"/>
    <s:textfield name="customer.street" key="label.customerStreet" requiredLabel="true"/>
    <s:textfield name="customer.matnr" key="label.customerMatnr" requiredLabel="false"/>
    <s:submit key="button.save" action="saveCustomer"/>
    <s:submit key="button.cancel" action="showCustomerList"/>
</s:form>