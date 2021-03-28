function renderAllChapters() {

    const allChaptersUrl = 'http://localhost:8080/chapters/api';
    const chaptersContainer = document.getElementById('chapters-container');

    fetch(allChaptersUrl)
        .then(response => response.json())
        .then(chapters => chapters.forEach(chapter => renderChapter(chapter)));

    function renderChapter(chapter) {

        //new tr
        let newTr = document.createElement('tr');

        //new ATA td
        let newAtaTd = document.createElement('td');
        newAtaTd.textContent = chapter['ataCode'];

        //new chapter name td
        let newNameTd = document.createElement('td');
        newNameTd.textContent = chapter['name'];

        //append on page
        newTr.appendChild(newAtaTd);
        newTr.appendChild(newNameTd);
        chaptersContainer.appendChild(newTr)

    }
}

renderAllChapters();