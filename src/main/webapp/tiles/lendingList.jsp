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

    .errormessage li {
        color: #ff0000;
    }
</style>

<h1><s:text name="header.lendingList"/></h1>

<s:actionerror/>
<s:form>
    <table class="sortable">
        <thead>
        <tr>
            <th></th>
            <th><s:text name="label.lendingPublication"/></th>
            <th><s:text name="label.lendingCustomer"/></th>
            <th><s:text name="label.lendingStartDate"/></th>
            <th><s:text name="label.lendingEndDate"/></th>
            <th><s:text name="label.lendingStatus"/></th>
            <th><s:text name="label.lendingReminderSent"/></th>
            <th><s:text name="label.lendingReminderDue"/></th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="openLendings">
            <tr>
                <td><s:radio list="#{id:''}" name="lendingId" theme="simple"/></td>
                <td><s:property value="publicationTitle"/></td>
                <td><s:property value="customerFullName"/></td>
                <td><s:property value="startDate"/></td>
                <td><s:property value="endDate"/></td>
                <td><s:if test="overDue"><s:text name="label.lendingStateOverDue"/></s:if><s:else><s:text
                        name="label.lendingStateLent"/></s:else></td>
                <td><s:property value="reminders.size"/></td>
                <td><s:if test="reminders.size == 3"><s:text name="label.MaxRemindersReached"/></s:if>
                    <s:else>
                        <s:if test="reminderDue"><s:text name="label.ReminderIsDue"/></s:if>
                        <s:else><s:text name="label.ReminderNotDueYet"/></s:else>
                    </s:else>
                </td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
    <br>
    <div>
        <button formaction="showLendingForm"><s:text name="button.showLending"/></button>
        <button formaction="prolongLending"><s:text name="button.prolongLending"/></button>
        <button formaction="receivedLending"><s:text name="button.receiveLending"/></button>
        <button formaction="sendReminderFromLendingList"><s:text name="button.sendReminder"/></button>
        <button formaction="markAsLostFromLendingList"><s:text name="button.markAsLost"/></button>
    </div>
</s:form>