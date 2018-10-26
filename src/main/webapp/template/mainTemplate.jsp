<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<html>
<head>
    <title><tiles:insertAttribute name="title"/></title>
    <s:head/>
    <link rel="stylesheet" type="text/css" href="/css/stylesMain.css">
    <link rel="stylesheet" type="text/css" href="/css/stylesNavigationBar.css">
    <link rel="stylesheet" type="text/css" href="/css/stylesTable.css">
    <script src="/js/jquery-latest.js"></script>
    <script src="/js/jquery.tablesorter.min.js"></script>
    <script src="/js/sortable-table-init.js"></script>
    <%-- <link rel="stylesheet" type="text/css" href="/css/stylesButton.css"> --%>
</head>
<body>
    <tiles:insertAttribute name="header"/>
    <tiles:insertAttribute name="navigationBar"/>
    <tiles:insertAttribute name="content"/>
</body>
</html>
