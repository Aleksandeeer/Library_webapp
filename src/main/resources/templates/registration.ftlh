<head>
    <title>Registration</title>
    <link rel="stylesheet" href="../css/registration_style.css">
</head>
<body>
    <form action="/register" method="POST">
        <label for="login">Логин:</label>
        <input type="text" id="login" name="login" required>

        <label for="full_name">Полное имя:</label>
        <input type="text" id="full_name" name="full_name" required>

        <label for="email">Электронная почта:</label>
        <input type="email" id="email" name="email" required>

        <label for="phone_number">Номер телефона:</label>
        <input type="tel" id="phone_number" name="phone_number" required>

        <label for="sha256_password">Пароль:</label>
        <input type="password" id="sha256_password" name="sha256_password" required>

        <button type="submit">Зарегистрироваться</button>
    </form>
</body>