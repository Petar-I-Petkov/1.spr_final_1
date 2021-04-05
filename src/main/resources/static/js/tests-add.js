const ROOT_URL = window.location.origin;
const BASE_URL = 'questions/api';
const allQuestionsUrl = `${ROOT_URL}/${BASE_URL}`;


function hideSearchBar() {
    const button = document.getElementById('searchbar-toggle');
    const searchAreaEl = document.getElementById('search-container');

    button.addEventListener('click', (e) => {
        e.preventDefault();
        if (!searchAreaEl.classList.contains('show')) {
            searchAreaEl.classList.add('show');
            button.textContent = 'Hide search options'
        } else {
            searchAreaEl.classList.remove('show');
            button.textContent = 'Show search options'
        }
    })
}

hideSearchBar();

function renderAllQuestions() {

    fetch(allQuestionsUrl, {
        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    }).then(response => response.json())
        .then(questions => console.log(questions))
        .catch(error => console.log(error))

}

renderAllQuestions();