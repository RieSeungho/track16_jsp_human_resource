const $departDelectable = document.querySelectorAll('.delectable');

$departDelectable.forEach((element) => {
    element.addEventListener('click', (e) => {
        const $identifier = e.currentTarget.children[0].dataset['grade'];
        console.log($identifier);

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