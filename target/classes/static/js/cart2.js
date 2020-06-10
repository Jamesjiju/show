window.onload = function() {
	// 确定购物车表格
	var table = document.getElementById("store");
	// 得到tbody里面的每一行，table里面有thead和tbody两部分，下标为1的是tbody
	var tr = table.children[1].rows;
	// 得到所有的勾选框
	var selectInputs = document.getElementsByClassName('check');
	console.log("selectInputs" + selectInputs);
	// 全选框
	var selectAllInput = document.getElementsByClassName('ckall');
	// 已选商品的总数目
	var selectTotal = document.getElementById("selectCount");
	// 总计
	var priceTotal = document.getElementById("selectTotal");
	// 已选择的商品
	var selected = document.getElementById("selected");
	// 如果全选框选中的话，下面的复选框都要选中
	for (var i = 0; i < selectInputs.length; i++) {
		selectInputs[i].onclick = function() {
			// 如果选中的是全选的话
			if (this.className.indexOf('ckall') >= 0) {
				for (var j = 0; j < selectInputs.length; j++) {
					selectInputs[j].checked = this.checked;
				}
			}
			// 如果有一个没有选中，则取消全选
			if (!this.checked) {
				for (var i = 0; i < selectAllInput.length; i++) {
					selectAllInput[i].checked = false;
				}
			}
			// 选完以后更新总计
//			getTotal();
		}
	}
	// 更新总计和总数
	function getTotal() {
		// 已选商品
		var selected = 0;
		// 价格
		var price = 0;
		// 遍历每一行
		for (var i = 0; i < tr.length; i++) {
			if (tr[i].getElementsByTagName('input')[0].checked) {
				tr[i].className = "on";
				selected += parseInt(tr[i].getElementsByTagName('input')[2].value);
				// 已选商品的价格进行累加
				var spanPrice = parseFloat(tr[i].getElementsByTagName('span')[0].innerHTML);
				console.log("spanPrice" + spanPrice);
				var spanTotal = tr[i].getElementsByTagName('span')[1].innerHTML;
				var count = parseInt(tr[i].getElementsByTagName('input')[2].value);
				console.log("count" + count);
				var countPrice = spanPrice * count;
				console.log("countPrice" + countPrice);
				tr[i].getElementsByTagName('span')[1].innerHTML = countPrice;
				price += countPrice;
			} else {
				tr[i].className = "";
			}
			selectTotal.innerHTML = selected;
			// 后面的toFixed函数表示保留两位小数
			priceTotal.innerHTML = price.toFixed(2);
		}
		// 为每行元素添加点击事件
		for (var i = 0; i < tr.length; i++) {
			tr[i].onclick = function() {
				var e = e || window.event;
				// 通过事件对象的target属性来触发元素
				var el = e.target;
				var els = el.className;
				// 得到加号对应的input
				var countInput = this.getElementsByTagName('input')[2];
				var value = parseInt(countInput.value);
				// 判断点击的是什么
				switch (els) {
				case "add":
					countInput.value = value + 1;
					// 计算每行的价格
					getTotal();
					break;
				case "reduce":
					countInput.value = value - 1;
					// 计算每行的价格
					getTotal();
					break;
				}
			}
		}
	}
}