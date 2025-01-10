<head>
    <title>Books</title>
    <link rel="stylesheet" href="../css/books_style.css">
</head>
<body>

<#if books?size == 0>
    <p>Книги отсутствуют</p>
<#else>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Название</th>
                <th>Автор</th>
                <th>Год</th>
                <th>ISBN</th>
                <th>Доступно</th>
                <th>Жанр</th>
            </tr>
        </thead>
        <tbody>
        <#list books as book>
            <tr>
                <td>${book.id}</td>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.publication_year}</td>
                <td>${book.ISBN}</td>
                <td>${book.availabilityStatus?string('Yes', 'No')}</td>
                <td>${book.genre}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</#if>

<#if role == 2>
    <form action="/addBook" method="POST">
        <label for="title">Название книги:</label>
        <input type="text" id="title" name="title" required>

        <label for="author">Автор:</label>
        <input type="text" id="author" name="author" required>

        <label for="genre">Жанр:</label>
        <select id="genre" name="genre">
            <option value="FANTASY">Фэнтези</option>
            <option value="ACTION">Экшен</option>
            <option value="COMEDY">Комедия</option>
            <option value="DRAMA">Драма</option>
            <option value="DETECTIVE">Детектив</option>
            <option value="NOVEL">Роман</option>
            <option value="SCIENCE">Научная</option>
            <option value="TUTORIAL">Учебник</option>
        </select>

        <label for="publicationYear">Год публикации:</label>
        <input type="number" id="publicationYear" name="publicationYear" required>

        <label for="ISBN">ISBN:</label>
        <input type="text" id="ISBN" name="ISBN" required>

        <label for="availabilityStatus">Статус доступности:</label>
        <select id="availabilityStatus" name="availabilityStatus">
            <option value="true">Доступна</option>
            <option value="false">Не доступна</option>
        </select>

        <button type="submit">Добавить книгу</button>
    </form>
</#if>

<#if role == 0>
    <form action="/registration" method="POST">
        <input type="submit" value="Регистрация"/>
    </form>
</#if>

</body>



