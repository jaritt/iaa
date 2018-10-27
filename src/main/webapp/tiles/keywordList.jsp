<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

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
</style>

<h1><s:text name="header.keywordManagement"/></h1>

<s:actionerror/>
<s:form>
    <table class="sortable">
        <thead>
        <div>
            <tr>
                <th></th>
                <th><s:text name="label.keywordText"/></th>
            </tr>
        </div>
        </thead>
        <tbody>
        <div>
            <s:iterator value="keywords">
                <tr>
                    <td><s:radio list="#{id:''}" name="keywordId" theme="simple"/></td>
                    <td><s:property value="word"/></td>
                </tr>
            </s:iterator>
        </div>
        </tbody>
    </table>
    <br>
    <div>
        <button formaction="addKeyword"><s:text name="button.addKeyword"/></button>
        <button formaction="loadKeyword"><s:text name="button.editKeyword"/></button>
        <button formaction="deleteKeyword"><s:text name="button.deleteKeyword"/></button>
    </div>
</s:form>