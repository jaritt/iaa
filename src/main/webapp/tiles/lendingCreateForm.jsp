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

<h1><s:text name="header.lendingForm" /></h1>

<s:actionerror/>
<s:form>
    <table>
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
    </table>
    <br>
    <div>
        <button formaction="lendPublication"><s:text name="button.lendPublication"/></button>
        <button formaction="showPublicationShowForm"><s:text name="button.back"/></button>
        <button formaction="showPublicationList"><s:text name="button.cancel"/></button>
    </div>
</s:form>