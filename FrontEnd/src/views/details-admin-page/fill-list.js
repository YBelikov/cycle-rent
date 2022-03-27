import {detailsAdminRowTemplate} from './sections/details-admin-row-template';

export function fillList(details) {
    let detailsHTML = '';

    (details || []).forEach(details => {
        detailsHTML += detailsAdminRowTemplate(details)
    })

    let detailsList = document.getElementById('detailsList');

    detailsList.innerHTML = detailsHTML
}