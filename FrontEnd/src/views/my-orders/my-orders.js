import {fillPagination, fillTable} from "./fill-table";

require('./my-orders.scss');

// import 'bootstrap';

// import 'bootstrap/js/src/alert';
// import 'bootstrap/js/src/button';
// import 'bootstrap/js/src/carousel';
import 'bootstrap/js/src/collapse';
// import 'bootstrap/js/src/dropdown';
// import 'bootstrap/js/src/modal';
// import 'bootstrap/js/src/popover';
// import 'bootstrap/js/src/scrollspy';
// import 'bootstrap/js/src/tab';
// import 'bootstrap/js/src/toast';
// import 'bootstrap/js/src/tooltip';
// import 'bootstrap/js/src/util';

let pageNumber = 1;
const url = "/order/payed/page/";

window.addEventListener('load', function () {
  getOrdersFromServer(url + pageNumber)
})

function getOrdersFromServer(urlCustom) {
  $.get(urlCustom, function (data) {
    data = JSON.parse(data);
    console.log(data)
    fillTable(data.orders, data.orderBicycleMap, data.priceMap);
    fillPagination(data.totalPages, data.currentPage);
  })
}

export function navActions() {
  $('#previousButton').click(() => {
    document.getElementById('forOrders').innerHTML = ''
    if (pageNumber > 1) {
      pageNumber--;
      const urlCustom = url + pageNumber;
      console.log("previous", urlCustom);
      getOrdersFromServer(urlCustom);
    }
  })
  $('#nextButton').click(() => {
    document.getElementById('forOrders').innerHTML = ''
    pageNumber++;
    const urlCustom = url + pageNumber;
    console.log("next", urlCustom);
    getOrdersFromServer(urlCustom);
  })
}


