import {fillBicycle, fillDetails} from "./fill-bicycle";

require('./bicycle-page.scss');

import 'bootstrap';

// // import 'bootstrap/js/src/alert';
// // import 'bootstrap/js/src/button';
// // import 'bootstrap/js/src/carousel';
// import 'bootstrap/js/src/collapse';
// // import 'bootstrap/js/src/dropdown';
// // import 'bootstrap/js/src/modal';
// // import 'bootstrap/js/src/popover';
// // import 'bootstrap/js/src/scrollspy';
// // import 'bootstrap/js/src/tab';
// // import 'bootstrap/js/src/toast';
// // import 'bootstrap/js/src/tooltip';
// // import 'bootstrap/js/src/util';

require('../../js/scrolling');

let totalValue = 0,
    start = toMs($("#timeStart").val()),
    end = toMs($("#timeEnd").val()),
    optionPrice = {},
    bicycleId = 0;

console.log(document.getElementById("bicycle-info").getAttribute("data-bicycle-id"))

window.addEventListener('load', function () {
    bicycleId = document.getElementById("bicycle-info").getAttribute("data-bicycle-id");
    const url = "/api/bicycle/" + bicycleId;
    getBicycleAndDetailsFromServer(url);
});

function getBicycleAndDetailsFromServer(url) {
    $.get(url, function (data) {
        fillBicycle(data || "");
        fillDetails(data.detailDTOS || []);

        $("input[class=custom-control-input]").each((i, item) => {
            console.log(item)
            $(item).on("change", () => {

                const name = $(item).attr('id');

                if ($(item).prop("checked")) optionPrice[name] = Number($(item).attr("data-price"));
                else delete optionPrice[name];

                countPrice();
            })
        })
    })
}

document.getElementById("timeStart").addEventListener("change", () => {
    const startValue = document.getElementById("timeStart").value;
    start = toMs(startValue);

    $("#timeEnd option").each((i, item) => {
        const itemMs = toMs($(item).val());
        if (itemMs < start) $(item).attr('disabled', 'disabled');
        else $(item).removeAttr('disabled')
    })

    if (end < start) {
        $("#timeEnd").val($("#timeStart").val());
        end = start;
    }

    countPrice();
    console.log(totalValue)
});

document.getElementById("timeEnd").addEventListener("change", () => {
    const endValue = document.getElementById("timeEnd").value;
    end = toMs(endValue);

    countPrice();
    console.log(totalValue)
});

$("#addToBasket").click(() => {
    let jsonData = {
        start,
        end,
        optionPrice,
        bicycleId
    };
    $.ajax({
        url: '/bicycle/addToBasket',
        method: 'POST',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(jsonData),
        success: function (data) {
            console.log(data)
            showTotal(data.totalValue)
        }
    });
})

function toMs(hhMM) {
    return (new Date('2021-12-12T' + hhMM)).getTime()
}

function countPrice() {
    let jsonData = {
        start,
        end,
        optionPrice,
        bicycleId
    };
    $.ajax({
        url: '/bicycle/totalPrice',
        method: 'POST',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(jsonData),
        success: function (data) {
            console.log(data)
            showTotal(data.totalValue)
        }
    });
}

function showTotal(totalValue) {
    $("#totalPrice").text(totalValue);
}