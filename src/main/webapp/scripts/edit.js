var SUCCESS = 0;
$(function(){
	//console.log(getCookie('userId'));
	
	//网页加载以后立即刷新笔记本列表
	listNotebooks();
	
	$('#notebook-list').on('click','.notebook',loadNotes);
	
	//监听笔记列表中的笔记点击事件,在点击时候加载显示笔记信息
	$('#note-list').on( 'click','.note', loadNote);
	
	//在ready方法中绑定事件打开笔记对话框
	$('#note-list').on('click', '#add_note', showAddNoteDialog);
	
	
	
	//监听对话框中的关闭和取消按钮
	//其中 '.close,.cancel' 是组选择器器, 表示
	//选择 .close 或 .cancel 按钮
	$('#can').on('click','.close,.cancel',closeDialog)
	
	//监听新建笔记对话框中的创建笔记按钮
	$('#can').on('click','.create-note',addNote);
	
});


//添加显示对话框方法
function showAddNoteDialog(){
    var id = $(document).data('notebookId');
    if(id){
        $('#can').load('alert/alert_note.html', function(){
            $('#input_note').focus();
        });
        $('.opacity_bg').show();
        return;
    }
    alert('必须选择笔记本!');
}

//添加关闭事件处理方法
function closeDialog(){
    $('.opacity_bg').hide();
    $('#can').empty();
}       

//添加创建笔记事件方法
function addNote(){
    var url = 'note/add.do';
    var notebookId=$(document).data('notebookId');
    var title = $('#can #input_note').val();

    var data = {userId:getCookie('userId'),
        notebookId:notebookId,
        title:title};
    //console.log(data);

    $.post(url, data, function(result){
        if(result.state==SUCCESS){
            var note=result.data;
            //console.log(note);
            showNote(note);
            //找到显示笔记列表的ul对象
            var ul = $('#note-list ul');
            //创建新新的笔记列表项目 li 
            var li = noteTemplate.replace(
                    '[title]', note.title);
            li = $(li);
            //设置选定效果
            ul.find('a').removeClass('checked');
            li.find('a').addClass('checked');
            //插入到笔记列表的第一个位置
            ul.prepend(li);
            //关闭添加对话框
            closeDialog();   
        }else{
            alert(result.message);
        }
    });
}



//添加笔记点击加载笔记的事件处理方法 loadNote
function loadNote(){
    //获取当前点击的 li 元素
    var li = $(this);
    //获取在显示时候绑定到li中的笔记ID值
    var id = li.data('noteId');

    //设置选中高亮效果
    li.parent().find('a').removeClass('checked');
    li.find('a').addClass('checked');

    var url = 'note/load.do';
    var data= {noteId: id };

    $.getJSON(url, data, function(result){
        //console.log(result);
        if(result.state==SUCCESS){
            var note = result.data;
            showNote(note);
        }else{
            alert(result.message);
        }
    });
}

//添加显示笔记信息方法 showNote
function showNote(note){
    //显示笔记标题
    $('#input_note_title').val(note.title);
    //显示笔记内容
    um.setContent(note.body);
}


function loadNotes(){
	var li = $(this);//当前被点击的对象li

    //在被点击的笔记本li增加选定效果
    li.parent().find('a').removeClass('checked');
    li.find('a').addClass('checked');

    var url = 'note/list.do';

    //li.data('notebookId') 方法可以获取绑定到li
    //元素上的数据notebookId, 这个notebookId在
    //showNotebooks方法中绑定li元素上的!

    var data={notebookId:li.data('notebookId')};
    console.log(data);
    $.getJSON(url, data, function(result){
        if(result.state==SUCCESS){
            var notes = result.data;
            showNotes(notes);
        }else{
            alert(result.message);
        }
    });
  //绑定笔记本ID， 用于添加笔记功能 
    $(document).data('notebookId', li.data('notebookId'))
}

/** 将笔记列表信息显示到屏幕上 */
function showNotes(notes){
    console.log(notes); 
    //将每个笔记对象显示到屏幕的ul区域
    var ul = $('#note-list ul');
    ul.empty();
    for(var i=0; i<notes.length; i++){
        var note = notes[i];
        var li = noteTemplate.replace(
                '[title]', note.title);
        li = $(li);
      //将笔记ID绑定到li, 用在点击笔记时候显示笔记详细信息
        li.data('noteId', note.id);
        ul.append(li);
    }
}

var noteTemplate = '<li class="online note">'+
    '<a>'+
    '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> [title]<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>'+
    '</a>'+
    '<div class="note_menu" tabindex="-1">'+
    '<dl>'+
        '<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>'+
        '<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'+
        '<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>'+
    '</dl>'+
    '</div>'+
    '</li>';


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
		 var li = notebookTemplate.replace(
		            '[name]', notebook.name);
		    li = $(li);
		    //将 notebook.id 绑定到 li
		    li.data('notebookId', notebook.id);
		ul.append(li);
		
	}
}
var notebookTemplate =
	' <li class="online notebook">'+
	'<a><i class="fa fa-book" title="online" rel="tooltip-bottom"></i><span>[name]<span></a>'+
	'</li>';
