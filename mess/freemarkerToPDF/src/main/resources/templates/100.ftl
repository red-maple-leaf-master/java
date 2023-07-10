<html>
<head>
    <title></title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            border-style: solid;
            border-width: 0.5px;
            border-color: #000000;
        }

        table tr td {
            border-width: 0.5px;
            border-style: solid;
            border-color: #000000;
            padding: 9px 9px;
        }

        table thead tr th {
            border-width: 0.5px;
            border-style: solid;
            border-color: #000000;
            text-align: center;
            padding: 9px 9px;
        }
    </style>
</head>
<body>
<table>
    <thead>
    <tr>
        <#list titleList as title>
            <th>${title}</th>
        </#list>
    </tr>
    </thead>
    <#list dataList as dl>
        <tr>
            <#list dl as d>
                <td>${d}</td></#list>
        </tr>
    </#list>

</table>
</body>
</html>
