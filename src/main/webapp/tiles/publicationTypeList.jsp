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
        width: 40%;
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

<h1><s:text name="header.publicationTypeList"/></h1>

<s:actionerror/>
<s:form>
    <table class="sortable">
        <thead>
            <tr>
                <th></th>
                <th><s:text name="label.publicationTypeText"/></th>
            </tr>
        </thead>
        <tbody>
            <s:iterator value="publicationTypes">
                <tr>
                    <td><s:radio list="#{id:''}" name="publicationTypeId" theme="simple"/></td>
                    <td><s:property value="title"/></td>
                </tr>
            </s:iterator>
        </tbody>
    </table>
    <br>
    <div>
        <button formaction="addPublicationType"><s:text name="button.addPublicationType"/></button>
        <button formaction="loadPublicationType"><s:text name="button.editPublicationType"/></button>
        <button formaction="deletePublicationType"><s:text name="button.deletePublicationType"/></button>
    </div>
</s:form>

