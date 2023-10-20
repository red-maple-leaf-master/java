// 获取选座的外部容器
const container = document.querySelector('.container');
// 获取未选择的作为容器
const seats = document.querySelectorAll('.row .seat:not(.occupied)');
// 获取显示座位数量的容器
const count = document.getElementById('count');
// 获取显示钱数的容器
const total = document.getElementById('total');
// 获取当前选择 的电影的价格容器
const movieSelect = document.getElementById('movie');

// 更新选择ui状态
populateUI();
// 票价
let ticketPrice = +movieSelect.value;

//保存选择的电影索引和价格
function setMovieData(movieIndex, moviePrice) {
    localStorage.setItem('selectedMovieIndex', movieIndex);
    localStorage.setItem('selectedMoviePrice', moviePrice);
}
// 更新总价和数量
function updateSelectedCount(){
    const selectedSeats = document.querySelectorAll('.row .seat.selected');
    const seatsIndex = [...selectedSeats].map(seat => [...seats].indexOf(seat));

    localStorage.setItem('selectedSeats', JSON.stringify(seatsIndex));

    const selectedSeatsCount = selectedSeats.length;


    count.innerText = selectedSeatsCount;
    total.innerText = selectedSeatsCount * ticketPrice;
    setMovieData(movieSelect.selectedIndex, movieSelect.value);
}
// 从 localStorage 获取数据 并更新ui 让选座的样式变化
function populateUI() {
    const selectedSeats = JSON.parse(localStorage.getItem('selectedSeats'));
    if(selectedSeats !== null && selectedSeats.length > 0){
        seats.forEach((seat,index) => {
            if(selectedSeats.indexOf(index) > -1){
                seat.classList.add('selected')
            }
        })
    }

    const selectedMovieIndex = localStorage.getItem('selectedMovieIndex');

    if (selectedMovieIndex !== null) {
        movieSelect.selectedIndex = selectedMovieIndex;
    }
}

// 电影选择事件
movieSelect.addEventListener('change', e=>{
    ticketPrice = +e.target.value;
    setMovieData(e.target.selectedIndex, e.target.value);
    updateSelectedCount();
});

// 作为选择事件
container.addEventListener('click', e => {
    if (
        e.target.classList.contains('seat') &&
        !e.target.classList.contains('occupied')
    ) {
        e.target.classList.toggle('selected');

        updateSelectedCount();
    }
});

// Initial count and total set
updateSelectedCount();
