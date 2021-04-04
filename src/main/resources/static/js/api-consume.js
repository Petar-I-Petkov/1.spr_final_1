const BASE_URL = window.location.origin;

function renderAllChapters() {

    const allChaptersUrl = `${BASE_URL}/ata-chapters/api`;
    const chaptersContainer = document.getElementById('chapters-container');

    fetch(allChaptersUrl)
        .then(response => {

            if (response.ok) {
                return response.json();
            } else {
                const statusCode = response.status;
                const statusMessage = response.statusText;
                const message = `ATA CHAPTERS COULD NOT BE LOADED FROM ${allChaptersUrl}`;
                displayError(message);
                return Promise.reject(new Error(message + statusCode + statusMessage));
            }

        })
        .then(chapters => chapters.forEach(chapter => renderChapter(chapter)))
        .catch(error => console.log(error));

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

    function displayError(message) {
        chaptersContainer.innerText = message;
    }
}

