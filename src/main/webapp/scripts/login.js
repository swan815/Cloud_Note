//login.js

var SUCCESS=0;
var ERROR=1;

$(function(){
	//console.log('Hello world!');
	
	$('#login').click(loginAction);
	
});

function loginAction(){
	//获取用户名和密码
	//提交到服务 等待服务器返回结果
	
	var name = $('#count').val();
	var password = $('#password').val();
	
	var url='user/login.do';
	var data={name:name, password:password};
	$.post(url,data,function(result){
		console.log(result);//new JsonResult()
		if(result.state==SUCCESS){
			var user = result.data;
			console.log(user);
		}else{
			alert(result.message);
		}
		
	});
}