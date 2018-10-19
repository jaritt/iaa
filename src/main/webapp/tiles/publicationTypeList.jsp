<%--
  Created by IntelliJ IDEA.
  User: VSHARMA
  Date: 15.10.2018
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h1><s:text name="header.publicationTypeList"/></h1>

<s:actionerror/>
<s:form>
    <table>
        <tr>
            <th></th>
            <th><s:text name="label.publicationTypeId"/></th>
            <th><s:text name="label.publicationTypeText"/></th>
        </tr>
        <s:iterator value="publicationTypes">
        <tr>
            <td><s:radio list="#{id:''}" name="publicationTypeId" theme="simple"/></td>
            <td><s:property value="id"/></td>
            <td><s:property value="title"/></td>
        </tr>
        </s:iterator>
    </table>
    <s:submit key="button.addPublicationType" action="addPublicationType" value="Add"/>
    <s:submit key="button.editPublicationType" action="loadPublicationType" value="Rename"/>
    <s:submit key="button.deletePublicationType" action="deletePublicationType" value="Delete"/>
</s:form>

