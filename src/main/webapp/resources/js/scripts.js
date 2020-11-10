
/*
* change the hidden input of r depending on the link selection
* */
function changeR(selectedR) {
    $("#point_form")[0].elements["point_form:r"].value = selectedR.textContent;
    drawCanvas();
}

function clearForm() {
    $("#point_form")[0].elements["point_form:x_input"].value = "";
    $("#point_form")[0].elements["point_form:x_hinput"].value = "";
    $("#point_form")[0].elements["point_form:y_hinput"].value = "";
    $("#point_form")[0].elements["point_form:y_input"].value = "";
}

$(window).resize(drawCanvas);
$(window).on("load", drawCanvas);