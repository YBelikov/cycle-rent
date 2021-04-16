import {fillOrderBicycles, fillOrderDetails} from "./fill-cart";
import {getPlacesTemplate} from "./sections/places-template";
import 'bootstrap';

require('./cart-page.scss');

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
  fillTable()
  fillPlaces();

  $('#forPlaces').change(function () {
    (this.value > 0) ?
        document.getElementById('btn-checkout').classList.remove('disabled') :
        document.getElementById('btn-checkout').classList.add('disabled')
  })

  $('#btn-checkout').click(function () {
    let data = {
      placeId: $('#forPlaces').val()
    }
    $.ajax({
      url: '/checkout',
      method: 'PUT',
      contentType: 'application/json',
      data: JSON.stringify(data),
      success: function () {
        document.location = "/checkout"
      }
    })
  })

});

function fillTable() {
  $.get("/order/formed", function (data) {
    data = JSON.parse(data);

    $('tr[data-product-type=bicycle]').remove()
    fillOrderBicycles(data.bicycles || []);

    $('tr[data-product-type=details]').remove()
    fillOrderDetails(data.details || []);

    $('.quantity button').click(function () {
      console.log($(this).data('action'))
      $.ajax({
        url: $(this).data('action'),
        type: 'PUT',
        success: function (response) {
          fillTable()
        }
      })
    })

    $('.btn-trash').click(function () {
      $.ajax({
        url: $(this).data('action'),
        type: 'DELETE',
        success: function (response) {
          fillTable()
        }
      })
    })

    $("#total-price").text(data.totalPrice);
  })
}

function fillPlaces() {
  $.get("/allPlaces", function (data) {
    fillPlacesHtml(data)
  })
}

function fillPlacesHtml(data) {
  let placesHtml = '';
  Object.values(data).forEach(val => {
    placesHtml += getPlacesTemplate(val);
  })
  document.getElementById('forPlaces').innerHTML += placesHtml;
}


