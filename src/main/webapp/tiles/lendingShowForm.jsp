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

<h1><s:text name="header.lendingForm"/></h1>

<s:actionerror/>
<s:form>
    <s:hidden name="lending.id"/>
    <s:hidden name="publication.id"/>
    <s:hidden name="customer.id"/>
    <table>
        <tr>
            <th><s:text name="label.customerTitle"/></th>
            <td><s:property value="customer.title"/></td>
        </tr>
        <tr>
            <th><s:text name="label.lendingCustomer"/></th>
            <td><s:property value="lending.customerFullName"/></td>
        </tr>
        <tr>
            <th><s:text name="label.customerPlz"/></th>
            <td><s:property value="customer.plz"/></td>
        </tr>
        <tr>
            <th><s:text name="label.customerCity"/></th>
            <td><s:property value="customer.city"/></td>
        </tr>
        <tr>
            <th><s:text name="label.customerStreet"/></th>
            <td><s:property value="customer.street"/></td>
        </tr>
        <tr>
            <th><s:text name="label.customerMatnr"/></th>
            <td><s:property value="customer.matnr"/></td>
        </tr>
    </table>
    <br>
    <table>
        <tr>
            <th><s:text name="label.lendingStartDate"/></th>
            <td><s:property value="lending.startdate"/></td>
        </tr>
        <tr>
            <th><s:text name="label.lendingEndDate"/></th>
            <td><s:property value="lending.enddate"/></td>
        </tr>
        <tr>
            <th><s:text name="label.lendingStatus"/></th>
            <td><s:if test="overDue"><s:text name="label.lendingStateOverDue"/></s:if>
                <s:else><s:text name="label.lendingStateLent"/></s:else>
            </td>
        </tr>
        <tr>
            <th><s:text name="label.lendingTimesProlonged"/></th>
            <td><s:property value="lending.timesProlonged"/></td>
        </tr>
    </table>
    <br>
    <table>
        <tr>
            <th><s:text name="label.lendingPublicationKey"/></th>
            <td><s:property value="publication.key"/></td>
        </tr>
        <tr>
            <th><s:text name="label.publicationTypeText"/></th>
            <td><s:property value="publication.type.title"/></td>
        </tr>
        <tr>
            <th><s:text name="label.lendingPublicationTitle"/></th>
            <td><s:property value="publication.title"/></td>
        </tr>
        <tr>
            <th><s:text name="label.publicationAuthor"/></th>
            <td><s:property value="publication.author"/></td>
        </tr>
        <tr>
            <th><s:text name="label.publicationReleaseDate"/></th>
            <td><s:property value="publication.releaseDate"/></td>
        </tr>
    </table>
    <br>
    <div>
        <button formaction="showLendingList"><s:text name="button.toLendingList"/></button>
    </div>
</s:form>
<s:form>
    <table>
        <s:iterator value="reminders">
            <tr>
                <th><s:text name="label.sendReminderDate"/></th>
                <td><s:property value="date"/></td>
            </tr>
        </s:iterator>
    </table>
</s:form>