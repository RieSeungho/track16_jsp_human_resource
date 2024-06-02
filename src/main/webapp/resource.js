
const $processArea = document.getElementById("process-area");

const $manageArea = document.querySelectorAll('#employ-manage-area tr');

const $modifyButton = document.getElementById('modify-button');

$manageArea.forEach((element) => {
    element.addEventListener('click', (e) => {

        const $employ = e.currentTarget.children;

        const $no = $employ[0].textContent;
        const $name = $employ[1].textContent;
        const $grade = $employ[2].textContent;

        generateUpdateRequest($no, $name, $grade);

    })
})

function generateUpdateRequest(no, name, grade) {

    const $a = document.createElement('a');
    $a.setAttribute('id', 'modify-button');
    $a.setAttribute('href', '#');
    $a.appendChild(document.createTextNode('수정'));
    $a.appendChild(document.createTextNode('('))
    $a.appendChild(document.createTextNode(name))
    $a.appendChild(document.createTextNode(' '))
    $a.appendChild(document.createTextNode(grade));
    $a.appendChild(document.createTextNode(')'))

    $processArea.appendChild($a);
}