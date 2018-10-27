<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<style>
    head, body {
        margin: 0;
        font-family: Arial, Helvetica, sans-serif;
    }

    h1 {
        margin: 14px 16px;
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
</style>

<h1><s:text name="header.publicationTypeForm"/></h1>

<s:form>
    <s:hidden name="publicationType.id"/>
    <table>
        <s:textfield name="publicationType.title" key="label.publicationTypeText" requiredLabel="true"/>
    </table>
    <br>
    <div>
        <button formaction="savePublicationType"><s:text name="button.save"/></button>
        <button formaction="showPublicationTypeList"><s:text name="button.cancel"/></button>
    </div>
</s:form>
