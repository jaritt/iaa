<%--
  Created by IntelliJ IDEA.
  User: VSHARMA
  Date: 16.10.2018
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h1><s:text name="header.publicationTypeForm" /></h1>

<s:form>
    <s:hidden name="publicationType.id"/>
    <s:textfield name="publicationType.title" key="label.publicationTypeText" requiredLabel="true"/>
    <s:submit key="button.save" action="savePublicationType"/>
    <s:submit key="button.cancel" action="showPublicationTypeList"/>
</s:form>