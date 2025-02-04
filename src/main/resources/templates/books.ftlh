<head>
    <title>Books</title>
    <link rel="stylesheet" href="../css/books_style.css">

    <script src="../js/search.js"></script>
    <script src="../js/filter.js"></script>
</head>
<body>



<!-- Скрытые данные для фильтров -->
<div id="authors-data" style="display: none;">
    <#list books?map(book -> book.author)?sort as author>
        <span>${author}</span>
    </#list>
</div>

<div id="genres-data" style="display: none;">
    <#list books?map(book -> book.genre)?sort as genre>
        <span>${genre}</span>
    </#list>
</div>

<div id="years-data" style="display: none;">
    <#list books?map(book -> book.publication_year)?sort as publication_year>
        <span>${publication_year}</span>
    </#list>
</div>

<div class="search-container">
    <input type="text" id="book-search" class="book-search" placeholder="Поиск книг...">
</div>

<div class="filters-container">
    <select id="year-filter">
        <option value="">Все годы</option>
    </select>

    <select id="genre-filter">
        <option value="">Все жанры</option>
    </select>

    <select id="author-filter">
        <option value="">Все авторы</option>
    </select>
</div>

<#if books?size == 0>
    <p>Книги отсутствуют</p>
<#else>
     <div class="book-grid" id="book-container">
         <#list books as book>
             <div class="book-item"
                  data-year="${book.publication_year}"
                  data-genre="${book.genre}"
                  data-author="${book.author}">
                 <div class="book-image-container">
                     <img src="${book.URL}" alt="Обложка книги ${book.title}">
                 </div>
                 <h3 class="book-title" style="color:black">${book.title}</h3>
                 <p><a href="${book.URL_author}" class="book-author">${book.author}</a></p>
             </div>
         </#list>
     </div>
</#if>

<#if role == 2>
    <form action="/addBook" method="POST">
        <label for="title">Название книги:</label>
        <input type="text" id="title" name="title" required>

        <label for="author">Автор:</label>
        <input type="text" id="author" name="author" required>

        <label for="URL_author">Ссылка на автора:</label>
        <input type="text" id="URL_author" name="URL_author" required>

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

<#if role == 3>
    <form action="/addLibraryWorker" method="POST">
        <label for="full_name">ФИО:</label>
        <input type="text" id="full_name" name="full_name" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <label for="phone_number">Телефон:</label>
        <input type="text" id="phone_number" name="phone_number" required>

        <label for="registration_date">Дата регистрации:</label>
        <input type="date" id="registration_date" name="registration_date" required>

        <label for="login">Логин:</label>
        <input type="text" id="login" name="login" required>

        <label for="password">Пароль:</label>
        <input type="password" id="sha256_password" name="sha256_password" required>

        <label for="membership_status">Статус членства:</label>
        <select id="membership_status" name="membership_status">
            <option value="true">Активный</option>
            <option value="false">Неактивный</option>
        </select>

        <button type="submit">Добавить работника</button>
    </form>
</#if>


<#if role == 0>
    <form action="/registration" method="POST">
        <input type="submit" value="Регистрация"/>
    </form>
</#if>

</body>



