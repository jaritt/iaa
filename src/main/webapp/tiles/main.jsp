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

<h1><s:text name="header.main"/></h1>

<s:actionerror/>
<s:fielderror fieldName="reminder"/>
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
            <th><s:text name="label.lendingReminderDue"/></th>
        </tr>
        <s:iterator value="overdueLendings">
            <tr>
                <td><s:radio list="#{id:''}" name="lendingId" theme="simple"/></td>
                <td><s:property value="publicationTitle"/></td>
                <td><s:property value="customerFullName"/></td>
                <td><s:property value="startDate"/></td>
                <td><s:property value="endDate"/></td>
                <td><s:if test="overDue"><s:text name="label.lendingsStateOverDue"/></s:if>
                    <s:else><s:text name="label.lendingsStateLent"/></s:else></td>
                <td><s:property value="reminders.size"/></td>
                <td><s:if test="reminders.size == 3"><s:text name="label.MaxRemindersReached"/></s:if>
                    <s:else>
                        <s:if test="reminderDue"><s:text name="label.ReminderIsDue"/></s:if>
                        <s:else><s:text name="label.ReminderNotDueYet"/></s:else>
                    </s:else>
                </td>
            </tr>
        </s:iterator>
    </table>
    <br>
    <s:submit key="button.sendReminder" action="sendReminderFromMain"/>
    <s:submit key="button.showLending" action="showLendingForm"/>
</s:form>
