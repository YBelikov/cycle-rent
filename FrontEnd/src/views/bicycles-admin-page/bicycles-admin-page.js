import 'bootstrap/js/src/collapse';
import {fillList} from "./fill-list";

require('./bicycles-admin-page.scss');

window.addEventListener('load', function () {
    const url = "/bicycles/all";
    itemsFromServer(url);
});

function itemsFromServer(url) {
    $.get(url, function(data) {
        const dataObject = JSON.parse(data);
        fillList(dataObject.bicycles || []);
    })
};