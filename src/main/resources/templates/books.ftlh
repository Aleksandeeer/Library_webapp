<head>
    <title>Books</title>
    <link rel="stylesheet" href="../css/books_style.css">
</head>
<body>

<#if books?size == 0>
    <p>Книги отсутствуют</p>
<#else>
     <div class="book-grid">
            <#list books as book>

                <div class="book-item">
                    <img src="${book.URL}" alt="Обложка книги ${book.title}">
                    <h3>${book.title}</h3>
                    <p>${book.author}</p>
                </div>
                <#if book?index % 4 == 0 && book?index != 0>
                    <div class="shelf"></div>
                </#if>
            </#list>
        </div>
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

        <label for="URL">URL:</label>
        <input type="text" id="URL" name="URL" required>

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



