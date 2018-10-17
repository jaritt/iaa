<%--
  Created by IntelliJ IDEA.
  User: VSHARMA
  Date: 16.10.2018
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h1><s:text name="header.customerList"/></h1>

<s:actionerror/>
<s:form>
    <table>
        <tr>
            <th></th>
            <th><s:text name="label.customerId"/></th>
            <th><s:text name="label.customerTitle"/></th>
            <th><s:text name="label.customerName"/></th>
            <th><s:text name="label.customerFirstName"/></th>
            <th><s:text name="label.customerCity"/></th>
            <th><s:text name="label.customerStreet"/></th>
            <th><s:text name="label.customerMatnr"/></th>
        </tr>
        <s:iterator value="customers">
            <tr>
                <td><s:radio list="#{id:''}" name="customerId" theme="simple"/></td>
                <td><s:property value="id"/></td>
                <td><s:property value="title"/></td>
                <td><s:property value="name"/></td>
                <td><s:property value="firstName"/></td>
                <td><s:property value="city"/></td>
                <td><s:property value="street"/></td>
                <td><s:property value="matnr"/></td>
            </tr>
        </s:iterator>
    </table>
    <br>
    <s:submit key="button.addCustomer" action="addCustomer" value="Add Customer"/>
    <s:submit key="button.editCustomer" action="editCustomer" value="Edit Customer"/>
    <s:submit key="button.deleteCustomer" action="deleteCustomer" value="Delete Customer"/>
</s:form>