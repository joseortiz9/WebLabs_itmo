
/*
* Filling the X and R inputs
* */
const valuesX = ['-3', '-2', '-1', '0', '1', '2', '3', '4', '5'];
const valuesR = ['1', '1.5', '2', '2.5', '3'];
for (const number of valuesX) {
    $('#options_x')
        .append(`<div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" id="${number}" name="x[]" value="${number}">
                    <label class="form-check-label" for="${number}">${number}</label>
                 </div>`);
}
for (const number of valuesR) {
    $('select#r').append(`<option value="${number}" ${(number==='1') ? 'selected' : ''}/>${number}</option>`);
}


/*
* validate input
* */
function validateRange(input, minRange, maxRange) {
    let val = parseFloat(input);
    if (isNaN(input))
        return false;

    return (val >= minRange && val <= maxRange);
}

function validateValuesX(values) {
    let allValid = true;
    for (const val of values) {
        if (!validateRange(val, -3, 5)) {
            allValid = false;
            break;
        }
    }
    return values.length > 0 && allValid;
}

function validateInput() {
    let validX = validateValuesX(Array.from($('input:checkbox').filter(':checked'), inp => inp.value));
    let validY = validateRange($('input#y').val(), -3, 3);
    let validR = validateRange($('select#r option:selected').val(), 1, 3);

    $('#submit-btn').attr('disabled', !(validX && validY && validR));

    return validX && validY && validR;
}

/*
* Validate the input before executing the click
* */
$('button#submit-btn').click(function (event) {
    if (!validateInput()) {
        event.preventDefault();
        $('#error-banner').removeClass("d-none").addClass("d-block");
        throw Error("Validation Problem");
    }
    $('#error-banner').removeClass("d-block").addClass("d-none");
});


$(window).resize(drawCanvas);
$(window).on("load", drawCanvas);
$("select#r").change(function () {
    validateInput();
    drawCanvas();
});