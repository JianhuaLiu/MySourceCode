<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Logical Main Test</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<center>
		<table border="1">
			
			<s:form action="upload" enctype="multipart/form-data" theme="simple"
				method="post">
				<thead>Logical Test
				</thead>
				<tr>
					<td>上传:</td>
					<td><s:file name="image" label="Data1"></s:file></td>
				</tr>
				<tr>
					<td></td>
					<td><s:submit value="提交"></s:submit></td>
				</tr>
			</s:form>
			
			<!--
			<form action="upload" method="post">
			<tr>
				<td>File Path</td>
				<td><input type="file" name="image" label="Data1" size="50"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit"></td>
			</tr>
			</form>
			-->
			<tr>
				<td>下载:</td>
				<td><a target="_blank" href="download_list">显示下载列表</a></td>
			</tr>
		</table>
	</center>
</body>
</html>
