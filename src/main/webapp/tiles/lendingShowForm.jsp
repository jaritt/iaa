<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h1><s:text name="header.lendingForm"/></h1>

<s:form>
    <s:hidden name="lending.id"/>
    <s:textfield name="lending.customerFullName" key="label.lendingCustomer" disabled="true"/>
    <s:textfield name="lending.startDate" key="label.lendingStartDate" disabled="true"/>
    <s:textfield name="lending.endDate"  key="label.lendingEndDate" disabled="true"/>
    <s:textfield name="lending.status" key="label.lendingStatus" disabled="true"/>
    <s:textfield name="publication.title" key="label.publicationTitle" disabled="true"/>
    <s:textfield name="publication.author" key="label.publicationAuthor" disabled="true"/>
    <s:textfield name="publication.releaseDate" key="label.publicationReleaseDate" disabled="true"/>
    <s:textfield name="publication.publisher" key="label.publicationPublisher" disabled="true"/>
    <s:textfield name="publication.isbn" key="label.publicationIsbn" disabled="true"/>
    <s:textfield name="customer.title" key="label.customerTitle" disabled="true"/>
    <s:textfield name="customer.firstName" key="label.customerFirstName" disabled="true"/>
    <s:textfield name="customer.name" key="label.customerName" disabled="true"/>
    <s:textfield name="customer.matnr" key="label.customerMatnr" disabled="true"/>
    <s:textfield name="customer.city" key="label.customerCity" disabled="true"/>
    <s:textfield name="customer.street" key="label.customerStreet" disabled="true"/>
    <s:submit key="button.back" action="showLendingList"/>
</s:form>
