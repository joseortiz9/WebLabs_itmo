/*
* Filling the X and R inputs
* */
const valuesX = ['-5', '-4', '-3', '-2', '-1', '0', '1', '2', '3'];
const valuesR = ['1', '1.5', '2', '2.5', '3'];
for (const number of valuesX) {
    $('#options_x')
        .append(`<input type="checkbox" id="${number}" name="x[]" value="${number}">`)
        .append(`<label for="${number}">${number}</label>`);
}
for (const number of valuesR) {
    $('#options_r')
        .append(`<input type="radio" id="${number}" name="r" value="${number}">`)
        .append(`<label for="${number}">${number}</label>`);
}


/*
* validate input
* TODO: Validation
* */
function validateY(input) {
    let y = parseFloat(input.value.replace(',', '.'));
    return (isNaN(input) && (y >= -5 && y <= 3));
}

function validateInput() {
    var validX, validY, validR = true;
    validY = validateY($('input#y')[0]);
    console.log(validY)
    $('#submit-btn')[0].disabled = validX && validY && validR;
}


/*
* send data async
* */
$('#request-form').submit(function (event) {
    event.preventDefault();

    let action = "handler.php";
    let data = $(this).serialize();

    $.post(action, data, function (response) {
        if (response.RESULT_CODE == 1) {

        } else {
            alert(response.RESPONSE);
        }
    });
});


