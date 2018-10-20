<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h1><s:text name="header.publicationForm"/></h1>

<!-- Möglicherweise eigene publicationUpdateForm, da die select-Felder im Falle des Bearbeitens bereits gesetzt sind -->

<s:form>
    <s:hidden name="publication.id"/>
    <s:hidden name="publication.key"/>
    <s:select name="selectedTypeId" headerKey="0" headerValue="Select..."
              list="publicationTypeList" listKey="id" listValue="title"
              key="label.publicationTypeText" requiredLabel="true"/>
    <s:textfield name="publication.title" key="label.publicationTitle" requiredLabel="true"/>
    <s:textfield name="publication.author" key="label.publicationAuthor" requiredLabel="true" />
    <s:textfield name="publicationDate" key="label.publicationReleaseDate" requiredLabel="true"/>
    <s:textfield name="publication.publisher" key="label.publicationPublisher" requiredLabel="true"/>
    <s:textfield name="publication.isbn" key="label.publicationIsbn" requiredLabel="true"/>
    <s:select name="selectedKeywordId"
              list="keywordList" listKey="id" listValue="word"
              key="label.publicationKeywords" requiredLabel="true"/>
    <s:textfield name="publication.copies" key="label.publicationCopies" requiredLabel="true"/>
    <s:submit key="button.save" action="savePublication"/>
    <s:submit key="button.cancel" action="showPublicationList"/>
</s:form>
