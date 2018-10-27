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
</style>

<h1><s:text name="header.publicationManagement"/></h1>
<br>
<div>
    <s:form>
        <input name="searchTerm"/>
        <button formaction="searchForPublication"><s:text name="button.search"/></button>
    </s:form>
</div>

<s:actionerror/>
<s:form>
    <table class="sortable">
        <thead>
        <tr>
            <th></th>
            <th><s:text name="label.publicationKey"/></th>
            <th><s:text name="label.publicationTitle"/></th>
            <th><s:text name="label.publicationAuthor"/></th>
            <th><s:text name="label.publicationReleaseDate"/></th>
            <th><s:text name="label.publicationPublisher"/></th>
            <th><s:text name="label.publicationType"/></th>
            <th><s:text name="label.publicationIsbn"/></th>
            <th><s:text name="label.publicationKeywords"/></th>
            <th><s:text name="label.publicationCopies"/></th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="publications">
            <tr>
                <td><s:radio list="#{id:''}" name="publicationId" theme="simple"/></td>
                <td><s:property value="key"/></td>
                <td><s:property value="title"/></td>
                <td><s:property value="author"/></td>
                <td><s:property value="releaseDate"/></td>
                <td><s:property value="publisher"/></td>
                <td><s:property value="type.title"/></td>
                <td><s:property value="isbn"/></td>
                <td><s:property value="keywordsAsString"/></td>
                <td><s:property value="copies"/></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
    <br>
    <button formaction="addPublication"><s:text name="button.addPublication"/></button>
    <button formaction="editPublication"><s:text name="button.editPublication"/></button>
    <button formaction="showPublicationShowForm"><s:text name="button.showPublication"/></button>
    <button formaction="deletePublication"><s:text name="button.deletePublication"/></button>
</s:form>
