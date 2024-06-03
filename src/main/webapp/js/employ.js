
const $body = document.querySelector('body');

const $selectable = document.querySelectorAll('.selectable');

$selectable.forEach((element) => {
    element.addEventListener('click', (e) => {
        const $identfier = e.currentTarget.children[0].textContent;
        updateEmploy($identfier);
    })
})

function updateEmploy(no) {
    const $form = document.createElement('form');
    const $input = document.createElement('input');

    $form.setAttribute('method', 'POST');
    $form.setAttribute('action', '/employ/update/form');

    $input.setAttribute('type', 'hidden');
    $input.setAttribute('name', 'no');
    $input.setAttribute('value', no);

    $form.appendChild($input);

    $body.appendChild($form);

    $form.submit()
}