import 'bootstrap/js/src/collapse';
require('./bicycle-editor.scss');

$(document).ready(function () {
    $('#bicycleImageInput').change(function() {
        updateImage(this);
    });
});

function updateImage(imageFile) {
    const file = imageFile.files[0];
    const reader = new FileReader();
    reader.onload = function (e) {
        $('#bicycleImage').attr('src', e.target.result);
    };
    reader.readAsDataURL(file)
};