import 'bootstrap/js/src/collapse';
import {fillList} from "./fill-list";

require('./details-admin-page.scss');

window.addEventListener('load', function () {
    const bicycleId = document.getElementById("detailsList").getAttribute(
        "data-bicycle-id");
    const url = "/api/bicycles/" + bicycleId + "/details";
    detailsFromServer(url);
});

function detailsFromServer(url) {
    $.get(url, function (data) {
        const dataObject = JSON.parse(data);
        fillList(dataObject.details || [])
    })
}
