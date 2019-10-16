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

<table border="2">
    <tr>
        <td>猴子地址</td>
        <td>猴子收入</td>
        <td>猴子性别</td>
        <td>猴子国籍</td>
    </tr>
    <#list infos as info>
        <tr>
            <td>${info.addr}</td>
            <td>${info.income}</td>
            <td>${info.gender}</td>
            <td>${info.nationality}</td>
        </tr>
    </#list>
</table>
</body>
</html>
