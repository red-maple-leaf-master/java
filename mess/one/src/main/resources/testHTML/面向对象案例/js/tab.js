var that;

class Tab {
    constructor(id) {
        that = this;
        // 获取父类的dom元素
        this.main = document.querySelector(id);
        this.add = this.main.querySelector('.tabadd');
        this.ul = this.main.querySelector('.fisrstnav ul:first-child');
        this.fsection = this.main.querySelector('.tabscon');
        this.init();
    }

    init() {
        // 初始化 获取一些详细的操作dom
        this.updateNode();
        this.add.onclick = this.addTab;
        for (var i = 0; i < this.lis.length; i++) {
            // 初始化  加上每个tab对应的索引
            this.lis[i].index = i;
            // 注册事件
            this.lis[i].onclick = this.toggletab;
            this.remove[i].onclick = this.removeTab;
            this.spans[i].ondblclick = this.editTab; // ondblclick 双击事件
            this.sections[i].ondblclick = this.editTab;
        }
    }

    updateNode() {
        this.lis = this.main.querySelectorAll('li');
        this.sections = this.main.querySelectorAll('section');
        this.remove = this.main.querySelectorAll('.icon-guanbi');
        this.spans = this.main.querySelectorAll('.fisrstnav li span:first-child');
    }

    addTab() {
        that.clearClass();
        var random = Math.random();
        var li = '<li class="liactive"><span>新选项卡</span><span class="iconfont icon-guanbi"></span></li>';
        var section = '<section class="conactive">测试 ' + random + '</section>';
        that.ul.insertAdjacentHTML('beforeend',li);
        that.fsection.insertAdjacentHTML('beforeend',section);
        that.init();
    }

    toggletab() {
        that.clearClass();
        this.className ='liactive';
        that.sections[this.index].className = 'conactive';
    }
    // 清除所有的 li 和 section的 类
    clearClass(){
        for(var i=0; i < this.lis.length;i++){
            this.lis[i].className = '';
            this.sections[i].className = '';
        }
    }

    removeTab(e) {
        e.stopPropagation(); // 阻止冒泡 防止触发li 的切换点击事件
        var index = this.parentNode.index;
        console.log(index);
        that.lis[index].remove();
        that.sections[index].remove();
        that.init();
        // 删除当前选项卡,需要自动切换到前一个选项卡
        if(document.querySelector('.liactive')) return;
        index--;
        // 手动调用点击事件
        that.lis[index] && that.lis[index].click();
    }

    editTab() {
        var str = this.innerHTML;
        // 双击禁止选定文字
        window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
        this.innerHTML = '<input type = "text" />';
        var input = this.children[0];
        input.value = str;
        input.select(); // 文本框里面的文字处于选定状态
        input.onblur = function () {
            this.parentNode.innerHTML = this.value;
        };
        input.onkeyup = function(e){
            if(e.keyCode === 13){
                // 手动调用表单失去焦点事件  不需要鼠标离开操作
                this.blur();
            }
        }
    }
}
new Tab('#tab');

