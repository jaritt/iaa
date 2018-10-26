<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h1><s:text name="header.lendingForm"/></h1>

<div>
    <s:form>
        <s:hidden name="lending.id"/>
        <s:hidden name="publication.id"/>
        <s:hidden name="customer.id"/>
        <table>
            <div>
                <tr>
                    <th><s:text name="label.lendingCustomer"/></th>
                    <td><s:property value="lending.customerFullName"/></td>
                </tr>
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
                    <td><s:if test="overDue"><s:text name="label.lendingsStateOverDue"/></s:if>
                        <s:else><s:text name="label.lendingsStateLent"/></s:else>
                    </td>
                </tr>
                <tr>
                    <th><s:text name="label.lendingTimesProlonged"/></th>
                    <td><s:property value="lending.timesProlonged"/></td>
                </tr>
                <tr>
                    <th><s:text name="label.lendingPublicationKey"/></th>
                    <td><s:property value="lending.publicationKey"/></td>
                </tr>
                <tr>
                    <th><s:text name="label.lendingPublicationTitle"/></th>
                    <td><s:property value="lending.publicationTitle"/></td>
                </tr>
            </div>
        </table>
        <br>
        <div>
            <button formaction="sendCustomerIdForCustomerDetailForm"><s:text name="button.toCustomer"/></button>
            <button formaction="sendPublicationIdForPublicationDetailForm"><s:text name="button.toPublication"/></button>
            <button formaction="showLendingList"><s:text name="button.toLendingList"/></button>
        </div>
    </s:form>
</div>
<div>
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
</div>
