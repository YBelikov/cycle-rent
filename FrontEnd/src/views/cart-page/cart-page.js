import 'bootstrap';
import {fillOrderBicycles, fillOrderDetails} from "./fill-cart";
import {getPlacesTemplate} from "./sections/places-template";
require('./cart-page.scss');

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


