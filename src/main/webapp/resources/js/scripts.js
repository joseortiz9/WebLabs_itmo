
/*
* change the hidden input of r depending on the link selection
* */
function changeR(selectedR) {
    $("#point_form")[0].elements["point_form:r"].value = selectedR.textContent;
    drawCanvas();
}

$(window).resize(drawCanvas);
$(window).on("load", drawCanvas);