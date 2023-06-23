    function openModal() {
        let gray_out = document.getElementById("fadeLayer");
        gray_out.style.visibility = "visible";
        setTimeout(addClass, 200);
    }

    function closeModal() {
        let modal = document.getElementById('popup');
        let gray_out = document.getElementById("fadeLayer");
        modal.classList.remove('is-show');
        gray_out.style.visibility ="hidden";
    }

    function addClass() {
        let modal = document.getElementById('popup');
        modal.classList.add('is-show');
    }