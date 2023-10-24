/**
 * 目标：封装_简易axios函数_获取省份列表
 *  1. 定义myAxios函数，接收配置对象，返回Promise对象
 *  2. 发起XHR请求，默认请求方法为GET
 *  3. 调用成功/失败的处理程序
 *  4. 使用myAxios函数，获取省份列表展示
 */
// 1. 定义myAxios函数，接收配置对象，返回Promise对象
function myAxios(config){
    return new Promise(((resolve, reject) => {
        const xhr = new XMLHttpRequest();
        if(config.params){
            // 2. 使用URLSearchParams转换，并携带到url上
            const paramsObj = new URLSearchParams(config.params)
            const queryString = paramsObj.toString()
            // 把查询参数字符串，拼接在url？后面
            config.url += `?${queryString}`
        }
        xhr.open(config.methods || 'GET',config.url)
        xhr.addEventListener('loadend',()=>{
            if(xhr.status >=200 && xhr.status < 300){
                resolve(JSON.parse(xhr.response))
            } else{
                reject(new Error(xhr.response))
            }
        });
        xhr.send();
    }))
}

const params = {
    name:'zhangs',
    age:1
}
const paramsObj = new URLSearchParams(params);
console.log(paramsObj)
console.log(paramsObj.toString())
// URLSearchParams { 'name' => 'zhangs', 'age' => '1' }
// name=zhangs&age=1


const fs = require('fs')

