<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>

<h1><s:text name="header.publicationManagement"/></h1>

<s:actionerror/>
<s:form>
    <table style="width: 100%">
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
                <td><s:property value="keywords"/></td>
                <td><s:property value="copies"/></td>
            </tr>
        </s:iterator>
    </table>
    <br>
    <s:submit key="button.addPublication" action="addPublication"/>
    <s:submit key="button.editPublication" action="loadPublication"/>
    <s:submit key="button.deletePublication" action="deletePublication"/>
    <s:submit key="button.showPublication" action="showPublication"/>
</s:form>
</body>
</html>