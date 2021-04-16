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
    start = Number($("#timeStart").val()),
    end = Number($("#timeEnd").val()),
    optionPrice = {},
    bicycleId = 0;

changeTimeStatus();

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
    start = Number(document.getElementById("timeStart").value);

    changeTimeStatus();

    countPrice();
    console.log(totalValue)
});

document.getElementById("timeEnd").addEventListener("change", () => {
    end = Number(document.getElementById("timeEnd").value);

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
    console.log("send: ", jsonData)

    $.ajax({
        url: '/bicycle/addToBasket',
        method: 'POST',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(jsonData),
        success: function (data) {
            $("#bicycleAdded").show();
            const timeout = setTimeout(() => {
                $("#bicycleAdded").hide();
                clearTimeout(timeout)
            }, 4000)

            const pageTop = $('.master-head').offset().top;
            $(window.opera ? 'html' : 'html, body').animate({scrollTop: pageTop}, 1000);
        }
    });
})

function changeTimeStatus() {
    $("#timeEnd option").each((i, item) => {
        const itemNum = Number($(item).val());
        if (itemNum < start) $(item).attr('disabled', 'disabled');
        else $(item).removeAttr('disabled')
    })

    if (end < start) {
        $("#timeEnd").val($("#timeStart").val());
        end = start;
    }
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