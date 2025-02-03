document.addEventListener("DOMContentLoaded", function () {
    const yearFilter = document.getElementById("year-filter");
    const genreFilter = document.getElementById("genre-filter");
    const authorFilter = document.getElementById("author-filter");
    const books = document.querySelectorAll(".book-item");

    function extractData(id) {
        return Array.from(document.querySelectorAll(`#${id} span`)).map(el => el.innerText);
    }

    const authors = [...new Set(extractData("authors-data"))];
    const genres = [...new Set(extractData("genres-data"))];
    const years = [...new Set(extractData("years-data"))];

    function populateFilter(filterElement, values) {
        values.forEach(value => {
            let option = document.createElement("option");
            option.value = value;
            option.textContent = value;
            filterElement.appendChild(option);
        });
    }

    populateFilter(yearFilter, years);
    populateFilter(genreFilter, genres);
    populateFilter(authorFilter, authors);

    function filterBooks() {
        const selectedYear = yearFilter.value;
        const selectedGenre = genreFilter.value;
        const selectedAuthor = authorFilter.value.toLowerCase();

        books.forEach(book => {
            const year = book.getAttribute("data-year");
            const genre = book.getAttribute("data-genre").toLowerCase();
            const author = book.getAttribute("data-author").toLowerCase();

            const matchesYear = selectedYear === "" || year === selectedYear;
            const matchesGenre = selectedGenre === "" || genre === selectedGenre;
            const matchesAuthor = selectedAuthor === "" || author === selectedAuthor;

            book.style.display = matchesYear && matchesGenre && matchesAuthor ? "block" : "none";
        });
    }

    yearFilter.addEventListener("change", filterBooks);
    genreFilter.addEventListener("change", filterBooks);
    authorFilter.addEventListener("change", filterBooks);
});
