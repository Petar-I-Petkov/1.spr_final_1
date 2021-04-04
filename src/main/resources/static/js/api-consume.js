const ROOT_URL = window.location.origin;
const BASE_URL = 'ata-chapters/api';
const allChaptersUrl = `${ROOT_URL}/${BASE_URL}`;

function renderAllChapters() {


    const chaptersContainer = document.getElementById('chapters-container');

    fetch(allChaptersUrl)
        .then(response => {

            if (response.ok) {
                return response.json();
            } else {
                const statusCode = response.status;
                const statusMessage = response.statusText;
                const message = `ATA CHAPTERS COULD NOT BE LOADED FROM ${allChaptersUrl}`;
                displayRestGetAllError(message);

                return Promise.reject(new Error(message + statusCode + statusMessage));
            }

        })
        .then(chapters => chapters.forEach(chapter => renderChapter(chapter)))
        .catch(error => {
            const message = `ATA CHAPTERS COULD NOT BE LOADED FROM ${allChaptersUrl}`;
            displayRestGetAllError(message);
            console.log(error)
        });

    function renderChapter({id, ataCode, name}) {

        //new tr
        let newTr = document.createElement('tr');
        newTr.dataset.key = id;

        //new ATA td
        let newAtaTd = document.createElement('td');
        newAtaTd.textContent = ataCode;

        //new chapter name td
        let newNameTd = document.createElement('td');
        let nameDiv = document.createElement('div');
        nameDiv.classList.add('d-inline')
        nameDiv.textContent = name;
        newNameTd.appendChild(nameDiv)


        //edit button
        let newEditBtn = document.createElement('button');
        newEditBtn.classList.add('btn', 'btn-outline-dark', 'mx-2', 'btn-sm', 'float-end');
        newEditBtn.innerText = 'Edit';
        newEditBtn.addEventListener('click',
            (e) => renderEditForm(e, newNameTd, nameDiv, name, newEditBtn, id));

        //add button to nameTd
        newNameTd.appendChild(newEditBtn);

        //append data
        newTr.appendChild(newAtaTd);
        newTr.appendChild(newNameTd);

        chaptersContainer.appendChild(newTr)

    }

    function renderEditForm(e, nameTdElement, nameDivElement, currentAtaName, editBtn, id) {
        e.preventDefault();

        //new inputEl
        let inputEl = document.createElement('input');
        inputEl.type = 'text';
        inputEl.classList.add('d-inline');
        inputEl.value = currentAtaName;

        //remove old name div
        nameTdElement.firstElementChild.remove();

        //append new input element
        nameTdElement.insertBefore(inputEl, editBtn);


        //remove edit btn
        nameTdElement.lastElementChild.remove();

        //add two new buttons - confirm and cancel
        let cancelBtn = document.createElement('button');
        cancelBtn.classList.add('btn', 'btn-outline-danger', 'mx-2', 'btn-sm', 'float-end');
        cancelBtn.textContent = 'Cancel';
        nameTdElement.appendChild(cancelBtn);

        cancelBtn.addEventListener("click", (e) => {
            e.preventDefault();
            nameTdElement.lastElementChild.remove();
            nameTdElement.lastElementChild.remove();
            nameTdElement.lastElementChild.remove();

            nameTdElement.appendChild(nameDivElement);
            nameTdElement.appendChild(editBtn);
        });


        let confirmBtn = document.createElement('button');
        confirmBtn.classList.add('btn', 'btn-outline-success', 'mx-2', 'btn-sm', 'float-end');
        confirmBtn.textContent = 'Confirm';
        nameTdElement.appendChild(confirmBtn);

        confirmBtn.addEventListener('click',
            (e) => checkInputAndPostChanges(e, id, inputEl))


    }

    function checkInputAndPostChanges(e, id, inputEl) {
        e.preventDefault();

        //check input and display error message
        if (inputEl.value == null || [...inputEl.value].length < 3) {
            document.getElementById('error-message').textContent = 'ATA name must be min 3 characters!'
            $(document).ready(function () {
                $('#wrong-input').toast('show');
            });
            return;
        }
        //all ok, continue

        let editObj = JSON.stringify({
            id: id,
            name: inputEl.value,
        })


        const token = $("meta[name='_csrf']").attr("content");
        const header = $("meta[name='_csrf_header']").attr("content");

        console.log(token);
        console.log(header);


        fetch(`${allChaptersUrl}/${id}`, {
            method: 'PUT',
            body: editObj
        })
            .then(response => console.log(response))


    }


    function displayRestGetAllError(message) {
        chaptersContainer.innerText = message;
    }
}

