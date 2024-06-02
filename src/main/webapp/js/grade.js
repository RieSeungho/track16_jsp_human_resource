const $grade = document.querySelectorAll('.delectable');

$grade.forEach((element) => {
    element.addEventListener('click', (e) => {
        const $identifier = e.currentTarget.children[0].dataset['grade'];
        console.log($identifier);

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

const $body = document.querySelector('body');

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