import 'bootstrap';
require('./checkout-page.scss');

let name = $('#username')
let card = $('#cardNumber')
let mm = $('#MM')
let yy = $('#YY')
let cvv = $('#cvv')
let errorMsg = $('#error-msg')

window.addEventListener('load', function () {
  $.get("/order/formed", function (data) {
    data = JSON.parse(data);
    console.log(data)
    document.getElementById("total-price").innerText = data.totalPrice;
    document.getElementById("pick-up-place").innerText = data.place;
  })

  $('#confirm-btn').click(function () {
    cleanWrongInputs()

    if (!name.val().match('[a-zA-Z]+\\s[a-zA-Z]+')) {
      name.addClass('failed')
      errorMsg.show().text("Wrong name!")
    }

    if (!checkLuhn(card.val()) || !card.val().match('\\d{16}')) {
      card.addClass('failed')
      errorMsg.show().text("Wrong card!")
    }

    if (!mm.val().match('\\d{1,2}') || !mm.val().match('[1-9]|1[0-2]')) {
      mm.addClass('failed')
      errorMsg.show().text("Wrong mm!")
    }

    if (!yy.val().match('\\d{2}') || !yy.val().match('2[1-9]')) {
      yy.addClass('failed')
      errorMsg.show().text("Wrong yy!")
    }

    if (!cvv.val().match('\\d{3}')) {
      cvv.addClass('failed')
      errorMsg.show().text("Wrong cvv!")
    }

    setTimeout(() => {
      if (errorMsg.text() === '') {
        $.ajax({
          url: '/checkout',
          method: 'POST',
          success: function () {
            document.location = "/myOrders"
          }
        })
      }
    }, 1000)

  })
})

function cleanWrongInputs() {
  errorMsg.hide()
  name.removeClass('failed')
  card.removeClass('failed')
  mm.removeClass('failed')
  yy.removeClass('failed')
  cvv.removeClass('failed')
}

function checkLuhn(cardNo) {
  let s = 0;
  let doubleDigit = false;
  for (let i = cardNo.length - 1; i >= 0; i--) {
    let digit = +cardNo[i];
    if (doubleDigit) {
      digit *= 2;
      if (digit > 9) {
        digit -= 9;
      }
    }
    s += digit;
    doubleDigit = !doubleDigit;
  }
  return s % 10 === 0;
}
