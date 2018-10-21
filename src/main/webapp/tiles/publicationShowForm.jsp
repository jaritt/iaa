<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h1><s:text name="header.publicationForm"/></h1>

<s:form>
    <s:hidden name="publication.id"/>
    <s:textfield name="publication.key" key="label.publicationKey" disabled="true"/>
    <s:textfield name="selectedTypeId" key="label.publicationType" disabled="true"/>
    <s:textfield name="publication.title" key="label.publicationTitle" disabled="true"/>
    <s:textfield name="publication.author" key="label.publicationAuthor" disabled="true"/>
    <s:textfield name="publicationDate" key="label.publicationReleaseDate" disabled="true"/>
    <s:textfield name="publication.publisher" key="label.publicationPublisher" disabled="true"/>
    <s:textfield name="publication.isbn" key="label.publicationIsbn" disabled="true"/>
    <s:textfield name="selectedKeywordId" key="label.publicationKeywords" disabled="true"/>
    <s:textfield name="publication.copies" key="label.publicationCopies" disabled="true"/>
    <s:submit key="button.lendPublication" action="lendPublication"/>
    <s:submit key="button.back" action="showPublicationList"/>
</s:form>
