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

<h1><s:text name="header.publicationForm"/></h1>

<s:form>
    <table>
        <s:hidden name="publication.id"/>
        <s:textfield name="publication.key" key="label.publicationKey" requiredLabel="true"/>
        <s:select name="selectedTypeId"
                  headerKey="-1"
                  headerValue="Select..."
                  list="publicationTypeList"
                  listKey="id"
                  listValue="title"
                  key="label.publicationTypeText"
                  requiredLabel="true"/>
        <s:textfield name="publication.title" key="label.publicationTitle" requiredLabel="true"/>
        <s:textfield name="publication.author" key="label.publicationAuthor"/>
        <s:textfield name="publication.releaseYear" key="label.publicationReleaseYear" requiredLabel="true"/>
        <s:textfield name="publication.releaseMonth" key="label.publicationReleaseMonth"/>
        <s:textfield name="publication.releaseDay" key="label.publicationReleaseDay"/>
        <s:textfield name="publication.publisher" key="label.publicationPublisher"/>
        <s:textfield name="publication.isbn" key="label.publicationIsbn"/>
        <s:select name="keywordIds"
                  list="keywordList"
                  listKey="id"
                  listValue="word"
                  multiple="true"
                  key="label.publicationKeywords"/>
        <s:textfield name="publication.copies" key="label.publicationCopies" requiredLabel="true"/>
    </table>
    <div>
        <button formaction="savePublication"><s:text name="button.save"/></button>
        <button formaction="showPublicationList"><s:text name="button.cancel"/></button>
    </div>
</s:form>