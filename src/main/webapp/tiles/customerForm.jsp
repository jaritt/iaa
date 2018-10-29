<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!--
@author Jannis Bär & Vikash Sharma
-->

<style>
    head, body {
        margin: 0;
        font-family: Arial, Helvetica, sans-serif;
    }

    h1 {
        margin: 14px 16px;
    }

    button {
        background-color: #333;
        color: #f2f2f2;
        padding: 8px 16px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 12px;
    }

    input[type=text], select {
        width: 200%;
        padding: 12px;
        border: 1px solid #ccc;
        border-radius: 4px;
        resize: vertical;
    }

    span {
        color: #ff0000;
    }
</style>

<h1><s:text name="header.customerForm"/></h1>

<s:form>
    <table>
        <s:hidden name="customer.id"/>
        <s:select name="customer.title" key="label.customerTitle" headerValue="--Select Title--" headerKey="-1"
                  list="#{'Herr': 'Herr', 'Frau': 'Frau'}" requiredLabel="true"/>
        <s:textfield name="customer.name" key="label.customerName" requiredLabel="true"/>
        <s:textfield name="customer.firstName" key="label.customerFirstName" requiredLabel="true"/>
        <s:textfield name="customer.plz" key="label.customerPlz" requiredLabel="true"/>
        <s:textfield name="customer.city" key="label.customerCity" requiredLabel="true"/>
        <s:textfield name="customer.street" key="label.customerStreet" requiredLabel="true"/>
        <s:textfield name="customer.matnr" key="label.customerMatnr" requiredLabel="false"/>
    </table>
    <br>
    <div>
        <button formaction="saveCustomer"><s:text name="button.save"/></button>
        <button formaction="showCustomerList"><s:text name="button.cancel"/></button>
    </div>
</s:form>