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

    input[type=text], select {
        width: 200%;
        padding: 12px;
        border: 1px solid #ccc;
        border-radius: 4px;
        resize: vertical;
    }
</style>

<h1><s:text name="header.editKeyword"/></h1>

<s:form>
    <s:hidden name="keyword.id"/>
    <table>
        <s:textfield name="keyword.word" key="label.keywordText" requiredLabel="true"/>
    </table>
    <br>
    <div>
        <button formaction="saveKeyword"><s:text name="button.save"/></button>
        <button formaction="showKeywordList"><s:text name="button.cancel"/></button>
    </div>
</s:form>
