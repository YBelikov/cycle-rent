import 'bootstrap/js/src/collapse';
require('./detail-editor.scss');

$(document).ready(function () {
    $('#detailImageInput').change(function() {
        updateImage(this);
    });
});

function updateImage(imageFile) {
    const file = imageFile.files[0];
    const reader = new FileReader();
    reader.onload = function (e) {
        $('#detailImage').attr('src', e.target.result);
    };
    reader.readAsDataURL(file)
};