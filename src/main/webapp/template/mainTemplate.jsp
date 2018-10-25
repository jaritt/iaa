<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<html>
<head>
    <title><tiles:insertAttribute name="title"/></title>
    <s:head/>
    <link rel="stylesheet" type="text/css" href="/css/stylesNavigationBar.css">
    <link rel="stylesheet" type="text/css" href="/css/stylesTable.css">
    <%-- <link rel="stylesheet" type="text/css" href="/css/stylesButton.css"> --%>
</head>
<body>
    <tiles:insertAttribute name="header"/>
    <tiles:insertAttribute name="navigationBar"/>
    <tiles:insertAttribute name="content"/>
</body>
</html>
