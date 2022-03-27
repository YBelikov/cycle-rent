import {bicycleAdminRowTemplate} from './sections/bicycle-admin-row-template.js';

export function fillList(bicycleItems) {
    let items = '';

    (bicycleItems || []).forEach(bicycleItems => {
        items += bicycleAdminRowTemplate(bicycleItems)
    });

    let itemsList = document.getElementById( 'bicyclesAdminList');
    console.log("items: " + items);
    itemsList.innerHTML = items;
}