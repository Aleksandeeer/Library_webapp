<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>

    <#--css-->
    <link rel="stylesheet" href="../css/login_style.css">

</head>
<body>

<div class="main">
    <form action="/login" method="post">
        Имя пользователя: <label>
            <input type="text" name="login"/>
        </label><br><br>

        Пароль: <label>
            <input type="password" name="sha256_password"/>
        </label><br><br>

        <div class="wrap">
            <input type="submit" value="Войти"/>
        </div>
    </form>
</div>

<div>
<form action="/guest" method="post">
<input type="submit" value="Войти как гость"/>
</div>



</body>
</html>