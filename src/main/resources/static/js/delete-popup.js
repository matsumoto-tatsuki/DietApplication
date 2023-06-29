function openModalDelete() {
    let gray_out = document.getElementById("fadeLayer");
    gray_out.style.visibility = "visible";
    setTimeout(addClassDelete, 200);
}

function closeModalDelete() {
    let modal = document.getElementById('delete-popup');
    let gray_out = document.getElementById("fadeLayer");
    modal.classList.remove('is-show');
    gray_out.style.visibility ="hidden";
}

function addClassDelete() {
    let modal = document.getElementById('delete-popup');
    modal.classList.add('is-show');
}