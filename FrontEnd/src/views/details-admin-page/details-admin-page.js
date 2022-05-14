import 'bootstrap/js/src/collapse';
import {fillList} from "./fill-list";

require('./details-admin-page.scss');

window.addEventListener('load', function () {
    const url = "/api/details/all";
    detailsFromServer(url);
});

function detailsFromServer(url) {
    $.get(url, function (data) {
        const dataObject = JSON.parse(data);
        fillList(dataObject.details || [])
    })
}
