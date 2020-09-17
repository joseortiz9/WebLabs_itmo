
/*
* Filling the X and R inputs
* */
const valuesX = [ '-4', '3', '-2', '-1', '0', '1', '2', '3', '4'];
const valuesR = ['1', '2', '3', '4', '5'];
for (const number of valuesX) {
    $('#x')
        .append(`<div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" id="${number}" name="x" value="${number}" checked>
                    <label class="form-check-label" for="${number}">${number}</label>
                 </div>`);
}
for (const number of valuesR) {
    $('#r')
        .append(`<div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" id="${number}" name="r" value="${number}" checked>
                    <label class="form-check-label" for="${number}">${number}</label>
                 </div>`);
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

function validateInput() {
    let validX = validateRange($('input#x').is(':checked').val(), -4, 4);
    let validY = validateRange($('input#y').val(), -3, 5);
    let validR = validateRange($('input#r').is(':checked').val(), 1, 5);

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
$("input#r").change(function () {
    validateInput();
    drawCanvas();
});