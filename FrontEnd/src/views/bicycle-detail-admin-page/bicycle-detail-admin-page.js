import 'bootstrap/js/src/collapse';
import {list} from "postcss";
require('./bicycle-detail-admin-page.scss');

$("#submit").click(() => {
    const checkedBoxesList = $('input[type=checkbox]:checked');
    var listOfId = []
    for (let i = 0; i < checkedBoxesList.length; ++i) {
        listOfId.push(checkedBoxesList[i].id)
    }
    const bicycleId = $(".bicycleId").attr("id")
    console.log(bicycleId);
    let jsonData = { bicycleId, listOfId }
    $.ajax({
        url: '/bicycle/updateBicycleDetails',
        method: 'POST',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(jsonData),
        success: function (data) {
            console.log(data)
            console.log("Success!")
            window.location.href = "/bicycles-admin"
        }
    });
});