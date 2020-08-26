$(document).ready(function() {
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


});