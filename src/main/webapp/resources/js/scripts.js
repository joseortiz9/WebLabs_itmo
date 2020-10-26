

//$('#r_option_values').click(function () {})

function changeR(selectedR) {
    $("#point_form")[0].elements["point_form:r"].value = selectedR.textContent;
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
    let validY = validateRange($('input#y').val(), -5, 3);
    $('#submit-btn').attr('disabled', !validY);
    return validY;
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
$("input[name=r]").change(function () {
    validateInput();
    drawCanvas();
});