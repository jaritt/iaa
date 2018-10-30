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

    input {
        margin: 14px 16px;
    }

    .errormessage li {
        color: #ff0000;
    }
</style>
<script type="text/javascript">
    function SelectAll(id) {
        document.getElementById(id).focus();
        document.getElementById(id).select();
    }
</script>

<h1><s:text name="header.publicationManagement"/></h1>
<br>
<s:form>
    <input id="searchField" name="searchTerm" onclick="SelectAll('searchField');" value=<s:property
            value="searchTerm"/>>
    <button formaction="searchForPublication"><s:text name="button.search"/></button>
</s:form>

<s:actionerror/>
<text style="font-size: 10px" ><s:text name="label.sortableTables"/></text>
<br>
<s:form>
    <table class="sortable">
        <thead>
        <tr>
            <th></th>
            <th><s:text name="label.publicationTitle"/></th>
            <th><s:text name="label.publicationAuthor"/></th>
            <th><s:text name="label.publicationReleaseDate"/></th>
            <th><s:text name="label.publicationType"/></th>
            <th><s:text name="label.publicationKeywords"/></th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="publications">
            <tr>
                <td><s:radio list="#{id:''}" name="publicationId" theme="simple"/></td>
                <td><s:property value="title"/></td>
                <td><s:property value="author"/></td>
                <td><s:property value="releaseDate"/></td>
                <td><s:property value="type.title"/></td>
                <td><s:property value="keywordsAsString"/></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
    <br>
    <div>
        <button formaction="addPublication"><s:text name="button.addPublication"/></button>
        <button formaction="editPublication"><s:text name="button.editPublication"/></button>
        <button formaction="showPublicationShowForm"><s:text name="button.showPublication"/></button>
        <button formaction="deletePublication"><s:text name="button.deletePublication"/></button>
    </div>
</s:form>
