const ROOT_URL = window.location.origin;

const allQuestionsUrl = `${ROOT_URL}/questions/api`;
const testApiUrl = `${ROOT_URL}/tests/api`;


const questionContainer = document.getElementById('questions-container');
const testQuestionList = document.getElementById('question-list');

const token = $("meta[name='_csrf']").attr("content");
const header = $("meta[name='_csrf_header']").attr("content");


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
    }).then(response => {
        if (response.ok) {
            return response.json();
        }

        const statusCode = response.status;
        const statusMessage = response.statusText;
        const message = `ATA CHAPTERS COULD NOT BE LOADED FROM ${allChaptersUrl}`;

        //todo - implement
        displayRestGetAllError(message);
        return Promise.reject(new Error(message + statusCode + statusMessage))

    })
        .then(questions => questions.forEach(question => renderQuestion(question)))
        .catch(error => console.log(error))

}

renderAllQuestions();

function renderQuestion(question) {

    let id = question.id;
    let questionText = question['question'];
    let documentRef = 'FCOM';
    let documentSubchapter = 'Limitations';
    let ataChapter = 'Aircraft General';

    let questionTemplate =
        `<div class="row mt-2" id="${id}" onclick="transferQuestion(event)">

                    <div class="col-sm-12 shadow rounded  mx-2 p-1  bg-light ">

                        <div class="input-group">
                            <button class="input-group-text add-question">
                                <
                            </button>
                            <div class="col-sm-10 p-2 border border-1 flex-grow-1">
                                <div class="row">
                                    <div class="col text small fw-bold mb-1 " id="question-text">
                                     Q: ${questionText}
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col text text-truncate">
                                        ${documentRef} - ${documentSubchapter}
                                    </div>
                                    <div class="col text  text-truncate mx-2">
                                        ${ataChapter}
                                    </div>
                                </div>
                            </div>
                            <button class="input-group-text remove-question" style="display: none">
                                >
                            </button>
                        </div>

                    </div>
        </div> `;

    questionContainer.innerHTML += questionTemplate;

}

function transferQuestion(e) {
    e.preventDefault();

    let questionEl = e.currentTarget;
    let clickedEl = e.target;


    if (clickedEl.classList.contains('add-question')) {
        testQuestionList.appendChild(questionEl);
        clickedEl.style.display = 'none';
        clickedEl.parentElement.lastElementChild.style.display = 'block';
    }

    if (clickedEl.classList.contains('remove-question')) {
        questionContainer.appendChild(questionEl);
        clickedEl.style.display = 'none';
        clickedEl.parentElement.firstElementChild.style.display = 'block';
        clickedEl.parentElement.firstElementChild.classList.add('bg-secondary')
    }
}

function postTestAndHandleErrors() {

    const saveBtn = document.getElementById('add-test');

    saveBtn.addEventListener('click', (e) => {

        let nameEl = document.getElementById('test-name');
        let dueDateEl = document.getElementById('due-date');
        let questionIds = [];

        //get question list from page
        Array.from(document
            .getElementById('question-list').children)
            .forEach(child => questionIds.push(child.id));

        //todo - validate inputs for test adding

        let addTestObj = JSON.stringify({
            name: nameEl.value,
            dueDate: dueDateEl.value,
            questionIds: questionIds
        })


        fetch(`${testApiUrl}`, {

            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                [header]: token
            },
            body: addTestObj
        })
            .then(response => {

                if (response.ok) {
                    return response.json();
                }

                return Promise.reject(response.json());

            })
            .then(data => {
                console.log(data)
                if (data['bindingErrors']) {

                    let errors = {};

                    data['bindingErrors'].forEach(error => {

                        let field = error.split(' - ')[0].replace('@', '');
                        let errMsg = error.split(' - ')[1];

                        errors[field]
                            ? errors[field].push(errMsg)
                            : errors[field] = [errMsg]
                    })

                    let nameErrEl = document.getElementById('test-name-err');
                    if (errors.name) {
                        nameErrEl.innerText = errors['name'].join('\n');
                        nameEl.classList.add('border', 'border-danger');
                    } else {
                        nameErrEl.textContent = '';
                        nameEl.classList.remove('border', 'border-danger');
                    }


                } else {
                    console.log('no errors')
                }
            })
            .catch(error => {

                console.log(error)

            });
    })

}

postTestAndHandleErrors();

