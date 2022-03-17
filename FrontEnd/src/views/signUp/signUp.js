import 'bootstrap/js/src/collapse';
require('./signUp.scss');

const obj = {};

document.querySelector('.login100-form-btn').addEventListener('click', () => {
  obj.username = $('#username').val()
  obj.email = $('#email').val()
  obj.password = $('#password').val()
  obj.rePassword = $('#rePassword').val()
  console.log(obj);
  console.log(JSON.stringify(obj));
  // отправка данных формы в API
  $.ajax({
    url: "/signUp",
    type: "POST",
    contentType: 'application/json',
    data: JSON.stringify(obj),
    success: function (result) {
      result = typeof result === "string" ? JSON.parse(result || {}) : result;
      if (result.successful === "true") {
        // продукт был создан, вернуться к списку продуктов
        document.location = '/login';
      } else {
        console.log("We have some errors:", result);
        document.getElementById('errorPlace').innerHTML = '';
        if (result.username) {
          document.getElementById('errorPlace').innerHTML += result.username
              + '<br />';
        }
        if (result.email) {
          document.getElementById('errorPlace').innerHTML += result.email
              + '<br />';
        }
        if (result.rePassword) {
          document.getElementById('errorPlace').innerHTML += result.rePassword;
        }
        document.getElementById('errorPlace').classList.remove('hide');
      }
    },
    error: function (xhr, resp, text) {
      // вывести ошибку в консоль
      console.error(xhr, resp, text);
    }
  });
})

