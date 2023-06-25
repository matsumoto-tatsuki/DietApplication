'use strict'
    flatpickr(".dateText", {
    dateFormat: "Y/m/d" // 日付の表示形式
    });

    function insertOpenModal(element) {
        let gray_out = document.getElementById("fadeLayer");
        gray_out.style.visibility = "visible";
        setTimeout(insertAddClass, 200);
        setDietName(element,'insertDietName');
        
    }

    function setDietName(element,nameId){
        let row = element.parentNode.parentNode;

        // 行内の各セルの情報を取得
        let cells = row.getElementsByTagName("td"); // td要素に置き換えてください
        console.log('cells:' + cells);
        document.getElementById(nameId).textContent = cells[2].textContent;
        document.getElementById(`${nameId}-input`).value = cells[2].textContent;

    }

    function insertCloseModal() {
        let modal = document.getElementById('insert-modal');
        let gray_out = document.getElementById("fadeLayer");
        modal.classList.remove('is-show');
        gray_out.style.visibility ="hidden";
    }

    function insertAddClass() {
        let modal = document.getElementById('insert-modal');
        modal.classList.add('is-show');
    }

    function editOpenModal(element) {
        let gray_out = document.getElementById("fadeLayer");
        gray_out.style.visibility = "visible";
        setTimeout(editAddClass, 200);
        setDietName(element,'editDietName');
        getUserSelect(element);
    }

    function editCloseModal() {
        let modal = document.getElementById('edit-modal');
        let gray_out = document.getElementById("fadeLayer");
        modal.classList.remove('is-show');
        gray_out.style.visibility ="hidden";
    }

    function editAddClass() {
        let modal = document.getElementById('edit-modal');
        modal.classList.add('is-show');
    }

    async function getUserSelect(element) {
        try {
            let row = element.parentNode.parentNode;
            
            let cells = row.getElementsByTagName("td"); // td要素に置き換えてください
            const hidden = cells[1].lastElementChild.value;
            console.log(hidden);
            document.getElementById('primaryId').value = hidden;
            
            const userId = 'testuser';
            const dietName = cells[2].textContent;
            const priod = cells[3].textContent.split(" ").join("");
            const date = priod.split('～');
            console.log(date);

            let data =new URLSearchParams();
            data.append('userId',userId);
            data.append('dietName',dietName);
            data.append('startDate',date[0]);
            data.append('finishDate',date[1]);


          const res = await fetch(`/api/userSelect/${hidden}`,{
            method:'POST',
            body:data,
          });
          if (res.status === 400) {
            console.log('error');
          } else {
            const dataJson = await res.json();
            
            console.log(dataJson);

            const action = document.getElementById('editAction');
            const startDate = document.getElementById('editStartDate');
            const finishDate = document.getElementById('editFinishDate');

            action.value = dataJson.action;
            startDate.value = dataJson.startDate.split("-").join("/");
            finishDate.value = dataJson.finishDate.split("-").join("/");
          }
        } catch (error) {
          console.log('エラーが発生しました', error);
        }
    }


    const deleteBtn = document.getElementById('deleteBtn');

    deleteBtn.addEventListener('click', () => {
        deleteDietSelect();
        
        editCloseModal();
        
    });





    const editBtn = document.getElementById('editBtn');
    const registerBtn = document.getElementById('registerBtn');

    function isNullOrWhitespace(value) {
        return value === null || value.trim() === "";
    }

    registerBtn.addEventListener('click', function() {
        const form = document.getElementById('registerForm');
        const action = document.getElementById('registerAction').value;
        const startDate = document.getElementById('registerStartDate').value;
        const finishDate = document.getElementById('registerFinishDate').value;

        if(isNullOrWhitespace(action) || 
           isNullOrWhitespace(startDate) ||
           isNullOrWhitespace(finishDate) ){

            const errorMessage = '入力項目に空要素が入っています！';
            document.getElementById('registerErrorText').textContent = errorMessage;
        }else{
            // form.submit();
            insertDietSelect();
            
            insertCloseModal();
            
        }
    });

    editBtn.addEventListener('click', function() {
        const form = document.getElementById('editForm');
        const action = document.getElementById('editAction').value;
        const startDate = document.getElementById('editStartDate').value;
        const finishDate = document.getElementById('editFinishDate').value;

        if(isNullOrWhitespace(action) || 
           isNullOrWhitespace(startDate) ||
           isNullOrWhitespace(finishDate) ){

            const errorMessage = '入力項目に空要素が入っています！';
            document.getElementById('editErrorText').textContent = errorMessage;
        }else{
            // form.submit();
            editDietSelect();
            
            editCloseModal();
            
        }
    });



    async function insertDietSelect() {
      try {
        let data =new URLSearchParams();

        const dietName = document.getElementById('insertDietName-input').value;
        const action = document.getElementById('registerAction').value;
        const startDate = document.getElementById('registerStartDate').value;
        const finishDate = document.getElementById('registerFinishDate').value;

        data.append('dietName',dietName);
        data.append('action',action);
        data.append('startDate',startDate);
        data.append('finishDate',finishDate);

        const res = await fetch(`api/db-register`,{
          method:'POST',
          body:data,
        });

        if (res.status === 400) {
          console.log('error');
        } else {
          const dataJson = await res.json();
          console.log(dataJson);
          getDietList();     
        }
      } catch (error) {
        console.log('エラーが発生しました', error);
      }
    }

    async function editDietSelect() {
      try {
        let data =new URLSearchParams();

        const id = document.getElementById('primaryId').value;
        const dietName = document.getElementById('editDietName-input').value;
        const action = document.getElementById('editAction').value;
        const startDate = document.getElementById('editStartDate').value;
        const finishDate = document.getElementById('editFinishDate').value;

        data.append('id',id);
        data.append('dietName',dietName);
        data.append('action',action);
        data.append('startDate',startDate);
        data.append('finishDate',finishDate);

        const res = await fetch(`api/db-edit`,{
          method:'PUT',
          body:data,
        });

        if (res.status === 400) {
          console.log('error');
        } else {
          const dataJson = await res.json();
          console.log(dataJson);  
          getDietList();   
        }
      } catch (error) {
        console.log('エラーが発生しました', error);
      }
    }

    async function deleteDietSelect() {
      try {
        const id = document.getElementById('primaryId').value;

        const res = await fetch(`api/db-delete/${id}`,{
          method:'DELETE',
        });

        if (res.status === 400) {
          console.log('error');
        } else {
          const dataJson = await res.json();
          console.log(dataJson);     
          getDietList();
        }
      } catch (error) {
        console.log('エラーが発生しました', error);
      }
    }