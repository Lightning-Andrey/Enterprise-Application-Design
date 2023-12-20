<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ships</title>
    <style>
        button {
            margin: 10px;
            padding: 10px;
            border-radius: 50px;
            border-width: 5px;
            background-color: yellow;
            font-weight: 1000;
        }
    </style>
</head>

<body>
    <h1>Корабли</h1>
    <hr>
    <p>Тип:</p>
    <#list ships as ship>
        <div>
            <p><b>${ship.type}</b> | <a href="/ship/${ship.id}">Подробнее...</a></p>
        </div>
    </#list>
    <hr>
    <form action="/ships/parse" method="post">
        <button>Спарсить корабли</button>
    </form>
    <hr>
    <form action="/ships/delete" method="post">
        <button>Очистить базу данных</button>
    </form>
</body>

</html>