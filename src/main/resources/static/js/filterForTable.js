function filterValue() {
    let input, filter, data_div_el, data_div_el_title, data_div_el_text, i, titleValue, textValue;
    input = document.querySelector('input[type=search]');

    filter = input.value.valueOf().toLowerCase();

    data_div_el = document.getElementsByClassName("data-div-el");
    data_div_el_title = document.getElementsByClassName("data-div-el-title");
    data_div_el_text = document.getElementsByClassName("data-div-el-text");

    for (i = 0; i < data_div_el.length; i++) {
        titleValue = data_div_el_title[i].textContent.valueOf();
        textValue = data_div_el_text[i].textContent.valueOf();
        if (titleValue !== null || textValue !== null) {
            if (titleValue.toLowerCase().indexOf(filter) > -1 || textValue.toLowerCase().includes(filter)) {
                data_div_el[i].style.display = "";
            } else {
                data_div_el[i].style.display = "none";
            }
        }
    }
}
let inputTag = document.querySelector('#myInput');
inputTag.addEventListener("search", filterValue);


