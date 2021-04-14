import {fillOrderBicycles, fillOrderDetails} from "./fill-cart";
import {getPlacesTemplate} from "./sections/places-template";

require('./cart-page.scss');

import 'bootstrap';
import {getDetailsTemplate} from "../bicycle-page/sections/detail-item-template";

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

window.addEventListener('load', function () {
  $.get("/order/formed", function (data) {
    data = JSON.parse(data);

    fillOrderBicycles(data.bicycles || []);
    fillOrderDetails(data.details || []);
  })
  fillPlaces();
});

function fillPlaces() {
  $.get("/allPlaces", function (data) {
    fillPlacesHtml(data)
  })
}

function fillPlacesHtml(data) {
  console.log(data)
  let placesHtml = '';
  Object.values(data).forEach(val => {
    console.log(val)
    placesHtml += getPlacesTemplate(val);
  })

  document.getElementById('forPlaces').innerHTML = placesHtml;
}


