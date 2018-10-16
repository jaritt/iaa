<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h1><s:text name="header.editKeyword" /></h1>

<s:form>
    <s:hidden name="keyword.id"/>
    <s:textfield name="keyword.word" key="label.keywordText" requiredLabel="true"/>
    <s:submit key="button.save" action="saveKeyword"/>
    <s:submit key="button.cancel" action="showKeywordList"/>
</s:form>
