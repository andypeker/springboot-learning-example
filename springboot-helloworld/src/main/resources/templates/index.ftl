<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<table border="1">
    <tr>
        <td>猴子编号</td>
        <td>猴子名称</td>
        <td>猴子地址</td>
    </tr>
    <#list humans as human>
        <tr>
            <td>${human.id}</td>
            <td>${human.username}</td>
            <td>${human.address}</td>
        </tr>
    </#list>
</table>
</body>
</html>
