

// function Start(name,age){
//     this.name = name;
//     this.age=age;
// }
//
// class goods{
//
//     constructor(name,age){
//         this.name=name;
//         this.age=age;
//     }
//
// }
//
// var one = new Start("张学友",12);
// var two = new goods("火腿肠",13);
// console.log(one.proto);
// console.log(two);
//
// Array.prototype.num = function () {
//     var sum = 0;
//     for (let i = 0; i <this.length ; i++) {
//         sum += this[i];
//     }
//     return sum;
// };
//
// var arr = [12, 66, 4, 88, 3, 7];
// // arr.forEach((value,index,array) => {
// //     console.log(value);
// //     console.log(index);
// //     console.log(array);
// // })
// var newArr = arr.filter(function(value, index,array) {
//     //参数一是:数组元素
//     //参数二是:数组元素的索引
//     //参数三是:当前的数组
//     return value >= 20;
// });
// console.log(newArr);//[66,88] //返回值是一个新数组
// var arr = [10, 30, 4];
// var flag = arr.some(function(value,index,array) {
//     //参数一是:数组元素
//     //参数二是:数组元素的索引
//     //参数三是:当前的数组
//     return value < 3;
// });
// console.log(flag);//false返回值是布尔值,只要查找到满足条件的一个元素就立马终止循环


// var o = {
//     name: 'andy'
// };
// function fn(a, b) {
//     console.log(this);
//     console.log(a+b);
// }
// fn(1,2);// 此时的this指向的是window 运行结果为3
// fn.call(o,1,2);//此时的this指向的是对象o,参数使用逗号隔开,运行结果为3

// function fn1(){
//     var num = 10;
//     function fn2(){
//         console.log(num);
//     }
//     fn2();
// }
// fn1();

// var car = (function() {
//     var start = 13; // 起步价  局部变量
//     var total = 0; // 总价  局部变量
//     return {
//         // 正常的总价
//         price: function(n) {
//             if (n <= 3) {
//                 total = start;
//             } else {
//                 total = start + (n - 3) * 5
//             }
//             return total;
//         },
//         // 拥堵之后的费用
//         yd: function(flag) {
//             return flag ? total + 10 : total;
//         }
//     }
// })();
// // 闭包 在外部函数结束之后 内部的函数被调用依旧可以访问外部函数的变量的内容 并作出操作
// console.log(car.price(5)); // 23
// console.log(car.yd(true)); // 33
// console.log(car.price(8)); // 23
// console.log(car.yd(true)); // 43
//
// // 我们想要做输入id号,就可以返回的数据对象
// var data = [{
//     id: 1,
//     name: '家电',
//     goods: [{
//         id: 11,
//         gname: '冰箱',
//         goods: [{
//             id: 111,
//             gname: '海尔'
//         }, {
//             id: 112,
//             gname: '美的'
//         }]
//
//     }, {
//         id: 12,
//         gname: '洗衣机'
//     }]
// }, {
//     id: 2,
//     name: '服饰'
// }];
// //1.利用 forEach 去遍历里面的每一个对象
// function getID(json, id) {
//     var o = {};
//     json.forEach(function(item) {
//         // console.log(item); // 2个数组元素
//         if (item.id == id) {
//             // console.log(item);
//             o = item;
//             return o;
//             // 2. 我们想要得里层的数据 11 12 可以利用递归函数
//             // 里面应该有goods这个数组并且数组的长度不为 0
//         } else if (item.goods && item.goods.length > 0) {
//             o = getID(item.goods, id);
//         }
//     });
//     return o;
// }
// var id = getID(data,112);
// console.log(id);
//
// var str = 'andy和red';
// // var newStr = str.replace('andy','baby');
// var newStr = str.replace(/andy/,'baby');
// console.log(newStr);
let arr = [];
for (let i = 0; i < 2; i++) {
    arr[i] = function () {
        console.log(i);
    }
}
arr[0]();
arr[1]();

var arr1 = [];
for (var i = 0; i < 2; i++) {
    arr1[i] = function () {
        console.log(i);
    }
}
arr1[0]();
arr1[1]();
