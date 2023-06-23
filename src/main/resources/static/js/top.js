'use strict'

        function createCalendar(year, month) {
            const start = new Date(year, month, 1);     // 月初
            const last = new Date(year, month + 1, 0);  // 月末
            const startDate = start.getDate();          // 月初
            const lastDate = last.getDate();            // 月末
            const startDay = start.getDay();            // 月初の曜日
            const lastDay = last.getDay();              // 月末の曜日

            let days = [];
            let weekDay = [];
            let dayCount = 0; // 曜日カウント用
            for (let i = startDate; i <= lastDate; i++) {
                if (i === startDate) {
                    for (let j = 0; j < startDay; j++) {
                        weekDay.push('');
                        dayCount++;
                    }
                }
                weekDay.push(i);
                dayCount++;
                if (dayCount === 7) {
                    days.push(weekDay);
                    dayCount = 0;
                    weekDay = [];
                }
            }
            for (let i = lastDay; i < 6; i++) {
                weekDay.push('');
            }
            days.push(weekDay);

            let cal = `<tr>
                <th class="weekend"><h2>日</h2></th>
                <th class="weekday"><h2>月</h2></th>
                <th class="weekday"><h2>火</h2></th>
                <th class="weekday"><h2>水</h2></th>
                <th class="weekday"><h2>木</h2></th>
                <th class="weekday"><h2>金</h2></th>
                <th class="weekday"><h2>土</h2></th>
            </tr>`;
            for (const week of days) {
                cal += '<tr>';
                for (const day of week) {
                    cal += '<td class="weekday" onclick="openDate(this)"><h3>' + day + '</h3></td>';
                }
                cal += '</tr>';
            }
            document.getElementById('cal').innerHTML = cal;
            document.getElementById('year').textContent = year;
            document.getElementById('month').textContent = month + 1;

        }

        document.getElementById('last-month').addEventListener('click', e => {
            e.preventDefault();
            let year = Number(document.getElementById('year').textContent);
            let month = Number(document.getElementById('month').textContent);
            year = month === 1 ? year - 1 : year;
            month = month === 1 ? 12 : month - 1;
            createCalendar(year, month - 1);
            getDay();
        });

        document.getElementById('next-month').addEventListener('click', e => {
            e.preventDefault();
            let year = Number(document.getElementById('year').textContent);
            let month = Number(document.getElementById('month').textContent);
            year = month === 12 ? year + 1 : year;
            month = month === 12 ? 1 : month + 1;
            createCalendar(year, month - 1);
            getDay();
        });

        const today = new Date(); // 現在の日時
        createCalendar(today.getFullYear(), today.getMonth());
        getDay();

        function openDate(day){
            const year = document.getElementById('year').textContent;
            const month = document.getElementById('month').textContent;
            const date = day.textContent;
            
            console.log(date);
            console.log(`/calendar/${year}-${month}-${date}`);
            const dayStyle = day.style.backgroundColor;
            if(date !== '' && (dayStyle === 'red' || dayStyle === 'green')){
                const month2 = month.padStart(2, '0');
                const date2 = date.padStart(2, '0');
                location.href = `/calendar/${year}-${month2}-${date2}`;
            }
        }

        async function getDay() {
            try {
                const year = document.getElementById('year').textContent;
                const month = document.getElementById('month').textContent;

              const res = await fetch(`/api/calenderResult/${year}/${month}`);
              if (res.status === 400) {
                console.log('error');
              } else {
                const data = await res.json();
                const trueDates = [];
                const falseDates = [];
                console.log(data);
                for(let i=0;i<data.length;i++){
                    const dateString = data[i].date.split('-');
                    console.log(data[i].date);
                    if(data[i].result === true){
                        trueDates.push(Number(dateString[2]).toString());
                    }else{
                        falseDates.push(Number(dateString[2]).toString());
                    }
                }

                console.log(trueDates);
                console.log(falseDates);
                const cells = document.getElementsByTagName('td');
                for (let i = 0; i < cells.length; i++) {
                    const cellDate = cells[i].textContent;
                    if (trueDates.includes(cellDate)) {
                        cells[i].style.backgroundColor = 'green';
                        cells[i].classList.add('pointer');
                    }
                    if(falseDates.includes(cellDate)){
                        cells[i].style.backgroundColor = 'red';
                        cells[i].classList.add('pointer');
                    }
                    
                }
              }
            } catch (error) {
              console.log('エラーが発生しました', error);
            }
        }