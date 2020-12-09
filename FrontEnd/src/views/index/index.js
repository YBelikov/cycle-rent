require('./index.scss');

// import 'bootstrap';

// import 'bootstrap/js/src/alert';
// import 'bootstrap/js/src/button';
// import 'bootstrap/js/src/carousel';
import 'bootstrap/js/src/collapse';
// import 'bootstrap/js/src/dropdow n';
// import 'bootstrap/js/src/modal';
// import 'bootstrap/js/src/popover';
// import 'bootstrap/js/src/scrollspy';
// import 'bootstrap/js/src/tab';
// import 'bootstrap/js/src/toast';
// import 'bootstrap/js/src/tooltip';
// import 'bootstrap/js/src/util';

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