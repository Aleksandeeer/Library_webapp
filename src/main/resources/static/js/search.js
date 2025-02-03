document.addEventListener("DOMContentLoaded", function () {
    let searchInput = document.getElementById("book-search");
    let books = document.querySelectorAll(".book-item");

    searchInput.addEventListener("input", function () {
        let query = searchInput.value.toLowerCase();

        books.forEach(book => {
            let title = book.querySelector("h3").innerText.toLowerCase();
            let author = book.querySelector("p a").innerText.toLowerCase();

            if (title.includes(query) || author.includes(query)) {
                book.style.display = "block";
            } else {
                book.style.display = "none";
            }
        });
    });
});
