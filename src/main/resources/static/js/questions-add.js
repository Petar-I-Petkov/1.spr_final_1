const ROOT_URL = window.location.origin;


let elements = {
    allDocSubchaptersUrl: `${ROOT_URL}/document-subchapters/api`,
    allATASubchaptersUrl: `${ROOT_URL}/ata-subchapters/api`,

    documentDropdown: () => document.getElementById('document'),
    subChapterDropdown: () => document.getElementById('documentSubchapter'),
    ataChapterDropdown: () => document.getElementById('chapter'),
    ataSubChapterDropdown: () => document.getElementById('ataSubChapter'),

}


function populateDocSubchapterDropdown() {

    fetch(elements.allDocSubchaptersUrl)
        .then(response => {

            if (response.ok) {
                return response.json();
            }

            return Response.redirect(new Error(response.status + response.text()));

        })
        .then(data => {

            elements.documentDropdown().addEventListener('change', () => fillDocSubchapterDropdown(data));

        })
        .catch(error => {
            console.log(error)
        })
}

populateDocSubchapterDropdown();

function fillDocSubchapterDropdown(subchapterList) {

    elements.subChapterDropdown().innerHTML = '<option></option>';

    const selectedIndex = elements.documentDropdown().selectedIndex;
    const documentName = elements.documentDropdown().options[selectedIndex].text;
    const subchapterListFiltered = subchapterList.filter(subchapter => subchapter.documentRef === documentName);

    subchapterListFiltered.forEach(subchapter => {
        elements.subChapterDropdown().innerHTML += `<option>${subchapter.docSubchapterName}</option>`;
    })
}


function populateAtaSubchaptersDropdown() {

    fetch(elements.allATASubchaptersUrl)
        .then(response => {

            if (response.ok) {
                return response.json();
            }

            return Response.redirect(new Error(response.status + response.text()));

        })
        .then(data => {

            elements.ataChapterDropdown().addEventListener('change', () => fillATASubchaptersDropdown(data));

        })
        .catch(error => {
            console.log(error)
        })
}

populateAtaSubchaptersDropdown();

function fillATASubchaptersDropdown(ataSubchapterList) {

    elements.ataSubChapterDropdown().innerHTML = '<option></option>';

    const selectedIndex = elements.ataChapterDropdown().selectedIndex;
    const ataCode = Number(elements.ataChapterDropdown().options[selectedIndex].text.split(' - ')[0]);

    const subchapterListFiltered = ataSubchapterList.filter(subchapter => subchapter.ataChapter === ataCode);

    subchapterListFiltered.forEach(subchapter => {

        elements.ataSubChapterDropdown().innerHTML +=
            `<option>${subchapter.ataSubCode} - ${subchapter.subchapterName}</option>`;
    })

}

