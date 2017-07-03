//login.js

var SUCCESS=0;
var ERROR=1;

$(function(){
	//console.log('Hello world!');
	
	$('#login').click(loginAction);
	$('#count').blur(checkName);
	$('#password').blur(checkPassword);
	
});

function checkName(){
	var name = $('#count').val();
	var rule = /^\w{4,10}$/;
	if(rule.test(name)){
		$('#count').next().empty();
		return true;
	}
	$('#count').next().html('4~10个字符');
	return false;
}
function checkPassword(){
	var pwd = $('#count').val();
	var rule = /^\w{4,10}$/;
	if(rule.test(pwd)){
		$('#password').next().empty();
		return true;
	}
	$('#password').next().html('4~10个字符');
	return false;
}
function loginAction(){
	//获取用户名和密码 
	//提交到服务 等待服务器返回结果
	
	var n = checkName() + checkPassword();
	
	if(n!=2){
		return;
	}
	
	
	var name = $('#count').val();
	var password = $('#password').val();
	
	var url='user/login.do';
	var data={name:name, password:password};
	$.post(url,data,function(result){
		console.log(result);//new JsonResult()
		if(result.state==SUCCESS){
			var user = result.data;
			console.log(user);
			//location.href='edit.html';
		}else if(result.state==2){
			$('#count').next().html(result.message);
		}else if(result.state==3){
			$('#password').next().html(result.message);
		}
		else{
			alert(result.message);
		}
		
	});
}