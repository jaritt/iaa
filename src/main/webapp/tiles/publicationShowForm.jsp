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
        width: 50%;
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

    span {
        color: #ff0000;
    }
</style>

<h1><s:text name="header.publicationForm"/></h1>

<s:actionerror/>
<s:form>
    <s:hidden name="publication.id"/>
    <table>
        <tr>
            <th><s:text name="label.publicationKey"/></th>
            <td><s:property value="publication.key"/></td>
        </tr>
        <tr>
            <th><s:text name="label.publicationType"/></th>
            <td><s:property value="publication.type.title"/></td>
        </tr>
        <tr>
            <th><s:text name="label.publicationTitle"/></th>
            <td><s:property value="publication.title"/></td>
        </tr>
        <tr>
            <th><s:text name="label.publicationAuthor"/></th>
            <td><s:property value="publication.author"/></td>
        </tr>
        <tr>
            <th><s:text name="label.publicationReleaseYear"/></th>
            <td><s:property value="publication.releaseYear"/></td>
        </tr>
        <tr>
            <th><s:text name="label.publicationReleaseMonth"/></th>
            <td><s:property value="publication.releaseMonth"/></td>
        </tr>
        <tr>
            <th><s:text name="label.publicationReleaseDay"/></th>
            <td><s:property value="publication.releaseDay"/></td>
        </tr>
        <tr>
            <th><s:text name="label.publicationPublisher"/></th>
            <td><s:property value="publication.publisher"/></td>
        </tr>
        <tr>
            <th><s:text name="label.publicationIsbn"/></th>
            <td><s:property value="publication.isbn"/></td>
        </tr>
        <tr>
            <th><s:text name="label.publicationKeywords"/></th>
            <td><s:property value="publication.keywordsAsString"/></td>
        </tr>
        <tr>
            <th><s:text name="label.publicationCopies"/></th>
            <td><s:property value="publication.copies"/></td>
        </tr>
        <tr>
            <th><s:text name="label.publicationKey"/></th>
            <td><s:property value="publication.key"/></td>
        </tr>
    </table>
    <br>
    <div>
        <button formaction="sendPublicationIdForLendingCreateForm"><s:text name="button.toLendingCreate"/></button>
        <button formaction="showPublicationList"><s:text name="button.back"/></button>
    </div>
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
        <s:iterator value="openLendings">
            <tr>
                <td><s:property value="publication.title"/></td>
                <td><s:property value="customerFullName"/></td>
                <td><s:property value="startDate"/></td>
                <td><s:property value="endDate"/></td>
                <td><s:if test="overDue"><s:text name="label.lendingsStateOverDue"/></s:if>
                    <s:else><s:text name="label.lendingsStateLent"/></s:else></td>
            </tr>
        </s:iterator>
    </table>
</s:form>