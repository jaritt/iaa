<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h1><s:text name="header.publicationForm"/></h1>

<s:form>
    <s:hidden name="publication.id"/>
    <s:textfield name="publication.key" key="label.publicationKey" requiredLabel="true"/>
    <s:select name="selectedTypeId"
              list="publicationTypeList" listKey="id" listValue="title"
              key="label.publicationTypeText"/>
    <s:textfield name="publication.title" key="label.publicationTitle" requiredLabel="true"/>
    <s:textfield name="publication.author" key="label.publicationAuthor"/>
    <s:textfield name="publicationDate" key="label.publicationReleaseDate"/>
    <s:textfield name="publication.publisher" key="label.publicationPublisher"/>
    <s:textfield name="publication.isbn" key="label.publicationIsbn"/>
    <s:select name="selectedKeywordId"
              list="keywordList" listKey="id" listValue="word"
              key="label.publicationKeywords"/>
    <s:textfield name="publication.copies" key="label.publicationCopies" requiredLabel="true"/>
    <s:submit key="button.save" action="savePublication"/>
    <s:submit key="button.cancel" action="showPublicationList"/>
</s:form>