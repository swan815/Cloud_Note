//login.js

var SUCCESS=0;
var ERROR=1;

$(function(){
	//console.log('Hello world!');
	
	$('#login').click(loginAction);
	$('#count').focus().blur(checkName);
	$('#password').blur(checkPassword);
	
	//绑定事件
	
	$('#regist_button').click(registAction);
	
});

function registAction(){
	
	var name = $('#regist_username').val();
	var nick = $('#nickname').val();
	var password = $('#regist_password').val();
	var confirm=$('#confirm_password').val();
	
	var url = 'user/regist.do';
	var data = {name:name, nick:nick, password:password, confirm:confirm};
	console.log(data);
	$.post(url,data,function(result){
		if(result.state==SUCCESS){
			var user = result.data;
			console.log(user);
			
			
			$('#back').click();
			$('#count').val(user.name);
			$('#password').focus();
			
			$('#regist_username').val('');
			$('#nickname').val('');
			$('#regist_password').val('');
			$('#confirm_password').val('');
		}else{
			alert(result.message);
		}
	});
}

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
			
			addCookie('userId',user.id);
			location.href='edit.html';
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