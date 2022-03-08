require('./index.scss');

import 'bootstrap/js/src/collapse';

require('../../js/scrolling');

$(document).ready(function () {
  $.ajax({
    url: 'allPlaces',
    method: 'GET',
    contentType: 'application/json',
    dataType: 'json',
    success: function (data) {
      console.log(data)
      let places = [];
      data.forEach(item => {
        places.push({
          lat: item.lat,
          lng: item.len,
        })
      })
      console.log(places)
      // The map, centered at Uluru
      const map = new google.maps.Map(document.getElementById("map"), {
        zoom: 12,
        center: places[0],

      });
      places.forEach(function (item) {
        new google.maps.Marker({
          position: item,
          map: map,
        });
      })
    }
  });
});