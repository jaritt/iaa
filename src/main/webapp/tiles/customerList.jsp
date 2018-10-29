<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%--
@author Jannis Bär & Vikash Sharma
--%>

<style>
    head, body {
        margin: 0;
        font-family: Arial, Helvetica, sans-serif;
    }

    h1 {
        margin: 14px 16px;
    }

    table, td {
        border: 1px solid black;
    }

    table {
        border-collapse: collapse;
        width: 100%;
    }

    th, td {
        padding: 8px;
    }

    th {
        text-align: left;
        color: #f2f2f2;
        background-color: #333;
    }

    td {
        text-align: center;
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

    .errormessage li {
        color: #ff0000;
    }

</style>

<h1><s:text name="header.customerList"/></h1>

<s:actionerror/>
<s:form>
    <table class="sortable">
        <thead>
        <tr>
            <th></th>
            <th><s:text name="label.customerTitle"/></th>
            <th><s:text name="label.customerName"/></th>
            <th><s:text name="label.customerFirstName"/></th>
            <th><s:text name="label.customerPlz"/></th>
            <th><s:text name="label.customerCity"/></th>
            <th><s:text name="label.customerStreet"/></th>
            <th><s:text name="label.customerMatnr"/></th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="customers">
            <tr>
                <td><s:radio list="#{id:''}" name="customerId" theme="simple"/></td>
                <td><s:property value="title"/></td>
                <td><s:property value="name"/></td>
                <td><s:property value="firstName"/></td>
                <td><s:property value="plz"/></td>
                <td><s:property value="city"/></td>
                <td><s:property value="street"/></td>
                <td><s:property value="matnr"/></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
    <br>
    <div>
        <button formaction="addCustomer"><s:text name="button.addCustomer"/></button>
        <button formaction="editCustomer"><s:text name="button.editCustomer"/></button>
        <button formaction="deleteCustomer"><s:text name="button.deleteCustomer"/></button>
    </div>
</s:form>