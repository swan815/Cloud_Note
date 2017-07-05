var SUCCESS = 0;
$(function(){
	//console.log(getCookie('userId'));
	
	//网页加载以后立即刷新笔记本列表
	listNotebooks();
	
});

function listNotebooks(){
	var url = 'notebook/list.do';
	var data = {userId:getCookie('userId')};
	
	$.getJSON(url,data,function(result){
		console.log(result);
		if(result.state==SUCCESS){
			var notebooks = result.data;
			showNotebooks(notebooks);
		}else{
			alert(result.message);
		}
	});
} 

function showNotebooks(notebooks){
	var ul = $('#notebook-list ul');
	ul.empty();
	for(var i=0; i<notebooks.length;i++){
		var notebook=notebooks[i];
		var li = $(notebookTemplate);
		li.find('span').html(notebook.name);
		ul.append(li);
	}
}
var notebookTemplate =
	' <li class="online">'+
	'<a><i class="fa fa-book" title="online" rel="tooltip-bottom"></i><span>[name]<span></a>'+
	'</li>';
