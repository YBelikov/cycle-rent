import 'bootstrap/js/src/collapse';
import {bicycleEditorTemplate} from "./sections/bicycle-edit-area";

require('./bicycle-editor.scss');

window.addEventListener('load', function () {
    const bicycleId = document.getElementById("bicycleEditor").getAttribute(
        "data-bicycle-id");
    const url = "/api/bicycle/" + bicycleId
    detailsFromServer(url);
});

function detailsFromServer(url) {
    $.get(url, function (data) {
       document.getElementById("bicycleEditor").innerHTML = bicycleEditorTemplate(data || {})
    })
}
