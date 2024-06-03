
function gradeInsert() {
    grade_insert.submit();
}

function gradeNameUpdate() {
    grade_name_update.submit();
}

function gradeMerge() {
    grade_merge.submit();
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
        $searchKeyInput.setAttribute('value', "GRADE");

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

const $gradeDelectable = document.querySelectorAll('.delectable');

$gradeDelectable.forEach((element) => {
    element.addEventListener('click', (e) => {
        const $identifier = e.currentTarget.children[0].dataset['grade'];

        const $gradeDeleteForm = document.createElement('form');
        $gradeDeleteForm.setAttribute('action', '/grade/delete');
        $gradeDeleteForm.setAttribute('method', 'POST');
        $gradeDeleteForm.setAttribute('name', 'grade_delete');

        const $gradeHiddenInput = document.createElement('input');
        $gradeHiddenInput.setAttribute('name', 'gradeCode');
        $gradeHiddenInput.setAttribute('value', $identifier);

        $gradeDeleteForm.appendChild($gradeHiddenInput);

        $body.appendChild($gradeDeleteForm);

        grade_delete.submit();
    })
})




const $gradeTab = document.querySelectorAll('.grade_tab');
const $gradeTabItem = document.querySelectorAll('.grade_tab_item');

$gradeTab.forEach((tabElement, tabIndex) => {

    tabElement.addEventListener('click', (e) => {

        $gradeTab.forEach((tabElement) => {
            tabElement.classList.remove('tab_active');
        })

        const $activeTab = $gradeTab[tabIndex];
        $activeTab.classList.add("tab_active");

        $gradeTabItem.forEach((itemElement, itemIndex) => {
            itemElement.classList.remove('item_active');

            const $activeItem = $gradeTabItem[itemIndex];

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