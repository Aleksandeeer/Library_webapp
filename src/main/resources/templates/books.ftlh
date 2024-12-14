<#if books?size == 0>
    <p>Книги отсутствуют</p>
<#else>
    <table>
        <thead>
            <tr>
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
