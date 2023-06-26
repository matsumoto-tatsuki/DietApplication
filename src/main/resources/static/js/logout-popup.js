    function openModalLogout() {
        let gray_out = document.getElementById("fadeLayer");
        gray_out.style.visibility = "visible";
        setTimeout(addClassLogout, 200);
    }

    function closeModalLogout() {
        let modal = document.getElementById('logout-popup');
        let gray_out = document.getElementById("fadeLayer");
        modal.classList.remove('is-show');
        gray_out.style.visibility ="hidden";
    }

    function addClassLogout() {
        let modal = document.getElementById('logout-popup');
        modal.classList.add('is-show');
    }