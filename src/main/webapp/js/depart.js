
function departInsert() {
    depart_insert.submit();
}

function departNameUpdate() {
    depart_name_update.submit();
}

function departMerge() {
    depart_merge.submit();
}

const $body = document.querySelector('body');

const $searchable = document.querySelectorAll('.searchable');

$searchable.forEach((element) => {
    element.addEventListener('click', (e) => {

        const $direct = e.currentTarget.dataset['direct'];

        const $form = document.createElement('form');
        $form.setAttribute('action', '/employ');
        $form.setAttribute('method', 'POST');
        $form.setAttribute('name', 'depart_direct_search');

        const $searchKeyInput = document.createElement('input');
        $searchKeyInput.setAttribute('type', 'hidden');
        $searchKeyInput.setAttribute('name', 'searchKey');
        $searchKeyInput.setAttribute('value', "DEPART");

        const $searchValueInput = document.createElement('input');
        $searchValueInput.setAttribute('type', 'hidden');
        $searchValueInput.setAttribute('name', 'searchValue');
        $searchValueInput.setAttribute('value', $direct);

        $form.appendChild($searchKeyInput);
        $form.appendChild($searchValueInput);
        $body.appendChild($form);

        depart_direct_search.submit();
    })
})

const $departDelectable = document.querySelectorAll('.delectable');

$departDelectable.forEach((element) => {
    element.addEventListener('click', (e) => {
        const $identifier = e.currentTarget.children[0].dataset['grade'];

        const $departDeleteForm = document.createElement('form');
        $departDeleteForm.setAttribute('action', '/depart/delete');
        $departDeleteForm.setAttribute('method', 'POST');
        $departDeleteForm.setAttribute('name', 'depart_delete');

        const $departHiddenInput = document.createElement('input');
        $departHiddenInput.setAttribute('name', 'departCode');
        $departHiddenInput.setAttribute('value', $identifier);

        $departDeleteForm.appendChild($departHiddenInput);

        $body.appendChild($departDeleteForm);

        depart_delete.submit();
    })
})

const $departTab = document.querySelectorAll('.depart_tab');
const $departTabItem = document.querySelectorAll('.depart_tab_item');

$departTab.forEach((tabElement, tabIndex) => {

    tabElement.addEventListener('click', (e) => {

        $departTab.forEach((tabElement) => {
            tabElement.classList.remove('tab_active');
        })

        const $activeTab = $departTab[tabIndex];
        $activeTab.classList.add("tab_active");

        $departTabItem.forEach((itemElement, itemIndex) => {
            itemElement.classList.remove('item_active');

            const $activeItem = $departTabItem[itemIndex];

            if(itemIndex == tabIndex) {
                $activeItem.classList.add("item_active")
            }

        });
    });
});

const $mergeFromOptions = document.querySelectorAll('#mergeFrom option');
const $mergeToOptions = document.querySelectorAll('#mergeTo option');

$mergeFromOptions.forEach((fromOpt) => {

    fromOpt.addEventListener('click', (e) => {

        $mergeToOptions.forEach((optTarget) => {
            optTarget.style = 'display: block';
        });

        $mergeToOptions.forEach((toOpt) => {
            if(e.target.value == toOpt.value) {
                toOpt.style = 'display: none';
            }
        });
    });
});

$mergeToOptions.forEach((toOpt) => {
    toOpt.addEventListener('click', (e) => {

        $mergeFromOptions.forEach((optTarget) => {
            optTarget.style = 'display: block';
        });

        $mergeFromOptions.forEach((fromOpt) => {
            if(e.target.value == fromOpt.value) {
                fromOpt.style = 'display: none';
            }
        });
    });
});