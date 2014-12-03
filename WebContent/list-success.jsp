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

<title>Logical Download</title>

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
<a href="index.jsp">返回主页面</a><br>
	<div
		style="position: absolute; left: 10px; top: 50px; font-family: Verdana; font-size: 9pt; background-color: #CACACA;">
		<div
			style="font-size: 15px; font-weight: bold; text-align: center; margin-top: 10px; margin-bottom: 10px;">文件下载列表</div>
		<div style="padding: 5px 5px 5px 5px; background-color: #EFEFEF">

			<table border="1" align="center">
				<tr>
					<th>文件名称</th>
					<th>类型</th>
					<th>创建时间</th>
					<th>下载</th>
					<th>预览</th>
				</tr>

				<s:iterator value="list">
					<tr>
						<td>${fileName}</td>
						<td>${contentType}</td>
						<td>${createTime}</td>
						<td><a
							href="Filedownload?fileId=${fileId}&fileName=${fileName}&type=${contentType} ">下载</a></td>
						<td><a target="_blank" href="DocumentView?fileId=${fileId}&fileName=${fileName}&type=${contentType}">预览</td>
						<!-- 
						<td><input type="submit" value="DocumentView"
						onclick="$FlexPaper('documentViewer').load({SWFFile : 'docs/'+$('#txt_doc').val()+'.pdf.swf',IMGFiles : 'docs/'+$('#txt_doc').val()+'.pdf_{page}.png',JSONFile : 'docs/'+$('#txt_doc').val()+'.pdf.js',PDFFile : 'pdf/'+$('#txt_doc').val()+'.pdf'})"></td>
						-->
					</tr>
				</s:iterator>
			</table>
		</div>
		
</body>
</html>
