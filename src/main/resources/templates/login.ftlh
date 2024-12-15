<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
</head>
<body>
    <form id="loginForm">
        <label for="username">Логин:</label>
        <input type="text" id="username" name="username" required>

        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password" required>

        <button type="submit">Войти</button>
    </form>

    <script>
        document.getElementById("loginForm").addEventListener("submit", async (e) => {
            e.preventDefault();

            const username = document.getElementById("username").value;
            const password = document.getElementById("password").value;

            const response = await fetch("/authenticate", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ username, password })
            });

            if (response.ok) {
                const token = await response.text();
                localStorage.setItem("jwt", token); // Сохраняем токен в локальном хранилище
                alert("Вы успешно вошли!");
                window.location.href = "/"; // Перенаправляем пользователя на главную страницу
            } else {
                alert("Ошибка авторизации. Проверьте логин и пароль.");
            }
        });
    </script>
</body>
</html>
