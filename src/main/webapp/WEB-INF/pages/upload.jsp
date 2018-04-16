<%--
  Created by IntelliJ IDEA.
  User: 10743
  Date: 2017/12/31
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/user/upload" method="post" enctype="multipart/form-data">
        <input type="file" name="file"/>
        <input type="file" name="file"/>
        <input type="submit" value="提交"/>
    </form>
    <a href="/user/download">下载</a>
</body>
</html>
