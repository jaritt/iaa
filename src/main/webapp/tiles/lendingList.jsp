<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
            text-align: center;
        }
    </style>
</head>
<body>

<h1><s:text name="header.lendingList"/></h1>

<s:actionerror/>
<s:form>
    <table>
        <tr>
            <th></th>
            <th><s:text name="label.lendingPublication"/></th>
            <th><s:text name="label.lendingCustomer"/></th>
            <th><s:text name="label.lendingStartDate"/></th>
            <th><s:text name="label.lendingEndDate"/></th>
            <th><s:text name="label.lendingStatus"/></th>
            <th><s:text name="label.lendingReminderSent"/></th>
            <th><s:text name="label.lendingRemeinderDue"/></th>
        </tr>
        <s:iterator value="lendings">
            <tr>
                <td><s:radio list="#{id:''}" name="lendingId" theme="simple"/></td>
                <td><s:property value="publicationTitle"/></td>
                <td><s:property value="customerFullName"/></td>
                <td><s:property value="startDate"/></td>
                <td><s:property value="endDate"/></td>
                <td><s:if test="isOverDue"><s:text name="label.lendinsStateOverDue"/></s:if><s:else><s:text name="label.lendinsStateLent"/></s:else></td>
                <td><s:property value="reminders.size"/></td>
                <td><s:if test="reminderDue"><s:submit key="button.sendReminder" action="sendReminder"/></s:if><s:else><s:text name="label.ReminderNotDueYet"/></s:else></td>
            </tr>
        </s:iterator>
    </table>
    <br>
    <s:submit key="button.prolongLending" action="prolongLending"/>
    <s:submit key="button.receiveLending" action="receivedLending"/>
    <s:submit key="button.showLending" action="showLendingForm"/>
</s:form>