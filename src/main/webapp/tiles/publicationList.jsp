<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h1><s:text name="header.publicationManagement"/></h1>

<s:actionerror/>
<s:form>
    <table>
        <tr>
            <th></th>
            <th><s:text name="label.publicationId"/></th>
            <th><s:text name="label.publicationKey"/></th>
            <th><s:text name="label.publicationTitle"/></th>
            <th><s:text name="label.publicationAuthor"/></th>
            <th><s:text name="label.publicationReleaseDate"/></th>
            <th><s:text name="label.publicationPublisher"/></th>
            <th><s:text name="label.publicationType"/></th>
            <th><s:text name="label.publicationIsbn"/></th>
            <th><s:text name="label.publicationKeywords"/></th>
            <th><s:text name="label.publicationCopies"/></th>
        </tr>
        <s:iterator value="keywords">
            <tr>
                <td><s:radio list="#{id:''}" name="publicationId" theme="simple"/></td>
                <td><s:property value="id"/></td>
                <td><s:property value="word"/></td>
            </tr>
        </s:iterator>
    </table>
    <br>
    <!-- <a href="<s:url action="addPublication"></s:url>">Hinzufügeen</a> -->
    <s:submit key="button.addPublication" action="addPublication"/>
    <s:submit key="button.editPublication" action="loadPublication"/>
    <s:submit key="button.deletePublication" action="deletePublication"/>
</s:form>