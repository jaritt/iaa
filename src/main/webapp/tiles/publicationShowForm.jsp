<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h1><s:text name="header.publicationForm"/></h1>

<s:actionerror/>
<s:form>
    <s:hidden name="publication.id"/>
    <s:textfield name="publication.key" key="label.publicationKey" disabled="true"/>
    <s:textfield name="publication.type.title" key="label.publicationType" disabled="true"/>
    <s:textfield name="publication.title" key="label.publicationTitle" disabled="true"/>
    <s:textfield name="publication.author" key="label.publicationAuthor" disabled="true"/>
    <s:textfield name="publication.releaseYear" key="label.publicationReleaseYear" disabled="true"/>
    <s:textfield name="publication.releaseMonth" key="label.publicationReleaseMonth" disabled="true"/>
    <s:textfield name="publication.releaseDay" key="label.publicationReleaseDay" disabled="true"/>
    <s:textfield name="publication.publisher" key="label.publicationPublisher" disabled="true"/>
    <s:textfield name="publication.isbn" key="label.publicationIsbn" disabled="true"/>
    <s:textfield name="publication.keywordsAsString" key="label.publicationKeywords" disabled="true"/>
    <s:textfield name="publication.copies" key="label.publicationCopies" disabled="true"/>
    <s:submit key="button.toLendingCreate" action="sendPublicationIdForLendingCreateForm"/>
    <s:submit key="button.back" action="showPublicationList"/>
</s:form>

<s:actionerror/>
<s:form>
    <table>
        <tr>
            <th><s:text name="label.lendingPublication"/></th>
            <th><s:text name="label.lendingCustomer"/></th>
            <th><s:text name="label.lendingStartDate"/></th>
            <th><s:text name="label.lendingEndDate"/></th>
            <th><s:text name="label.lendingStatus"/></th>
        </tr>
        <s:iterator value="publication.lendings">
            <tr>
                <td><s:property value="publication.title"/></td>
                <td><s:property value="customerFullName"/></td>
                <td><s:property value="startDate"/></td>
                <td><s:property value="endDate"/></td>
                <td><s:property value="status"/></td>
            </tr>
        </s:iterator>
    </table>
</s:form>