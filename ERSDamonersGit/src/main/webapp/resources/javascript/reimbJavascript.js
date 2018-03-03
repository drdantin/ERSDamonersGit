/**
 * 
 */
window.onload = function(){

}

$(function () {
	getLoginPage();
	
	function getLoginPage(){
		var xhr = new XMLHttpRequest();
		xhr.open("GET", "welcome.html", true);
		xhr.send();

		xhr.onreadystatechange = function(){
			if (xhr.readyState == 4 && xhr.status == 200){
				document.getElementById('view').innerHTML = xhr.responseText;
				getCssLogin();
				$('#submitForLogin').on('click',login);
			}
		}
	}

	function login(){
		console.log("in login");
		var username = $('#username').val();
		var password = $('#password').val();

		var toSend = [username, password];
		var json = JSON.stringify(toSend);

		var xhr = new XMLHttpRequest();
		console.log(xhr.readyState);
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status==200){
				console.log("in xhr callback" + xhr.responseText);

				var loginMessage = JSON.parse(xhr.responseText);
				if(loginMessage == 'Success'){
					alert("Employee Login Success");
					gettoAddReimb();
				}else if(loginMessage == "man"){
					alert("Manager Login Success");
					viewInitEmpReimb();
				}else{
					alert("Login Failed");
					getLoginPage();
				}
			}
		};

		xhr.open("POST","Login.do", true);
		console.log(xhr.readyState);
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		console.log("AFTER HEADER " + xhr.readyState);
		xhr.send(json);
	}

	function logout(){
		console.log("In logout **********");
		var xhr = new XMLHttpRequest();
		xhr.open("POST","Logout.do", true);
		console.log(xhr.readyState);
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		console.log("AFTER HEADER " + xhr.readyState);
		xhr.send();

		console.log(xhr.readyState);
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status==200){
				console.log("in xhr callback" + xhr.responseText);
				document.getElementById('view').innerHTML = xhr.responseText;
				alert("You are logged out");
				getLoginPage();
			}
		}
	}
	
	function viewInitEmpReimb(){
		var xhr = new XMLHttpRequest();
		xhr.open("POST","viewAllReimbController.do", true);
		console.log(xhr.readyState);
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		console.log("AFTER HEADER " + xhr.readyState);
		xhr.send();

		console.log(xhr.readyState);
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status==200){
				console.log("in xhr callback" + xhr.responseText);
				document.getElementById('view').innerHTML = xhr.responseText;
				getInitEmpReimb();
				
			}
		}
	}
	
	function getInitEmpReimb(){
		var xhr = new XMLHttpRequest();
		xhr.open("POST","GetDetailViewController.do", true);
		console.log(xhr.readyState);
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		console.log("AFTER HEADER " + xhr.readyState);
		xhr.send();

		console.log(xhr.readyState);
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status==200){
				console.log("in xhr callback" + xhr.responseText);
				var data = JSON.parse(xhr.responseText);
				man_viewReimbDataTable(data);
				
			}
		}
	}
	
	function man_viewReimbDataTable(getData){
		for(var i = 0;i < getData.length;i++){
			a = new Date(getData[i].submitted);
			b = new Date(getData[i].resolved);
			getData[i].submitted = a;
			getData[i].resolved = b;
		}
		
		$(document).ready(function(){
			var table = $('#bustamoveView').DataTable({
				data: getData,
				
				columns: [
					{ data: "author" },	            
					{ data: "email" },
					{ data: "submitted" },
					{ data: "description" },
					{ data: "status" },	            
					{ data: "resolved" },
					{ data: "resolver" },
					{ data: "reimbId" },
					{ data: null}
					  ],
					"columnDefs": [{
						"targets": -1,
						 "data": null,
						"defaultContent": "<button id ='approve' type='button' class='approveButton'>Approve</button>"  +
                         "<button id = 'deny' type ='button' class='denyButton'>Deny</button>"
					}]
			  });
			$('#bustamoveView tbody').on('click', '.approveButton',function(){
				var data = table.row($(this).parents('tr')).data();
				var index = table.row($(this).parents('tr')).index();
				approve(data,index);
			});
			$('#bustamoveView tbody').on('click', '.denyButton',function(){
				var data = table.row($(this).parents('tr')).data();
				var index = table.row($(this).parents('tr')).index();
				deny(data,index);
			});
			
		});
		$('#mout').on('click',function(){
			
			logout();
		});
	}
	
	function approve(data,index){
		
		var accept = {reimbid:data.reimbId};
		var json = JSON.stringify(accept);
		var xhr = new XMLHttpRequest();
		
		var xhr = new XMLHttpRequest();
		xhr.open("POST","accept.do", true);
		console.log(xhr.readyState);
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		console.log("AFTER HEADER " + xhr.readyState);
		xhr.send(json);

		console.log(xhr.readyState);
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status==200){
				console.log("in xhr callback" + xhr.responseText);
				var messageFromAddReimb = JSON.parse(xhr.responseText);//***************error
				alert(messageFromAddReimb);
			}
		};
	}
	function deny(data,index){

		var deny = {reimbid:data.reimbId};
		var json = JSON.stringify(deny);
		var xhr = new XMLHttpRequest();
		
		var xhr = new XMLHttpRequest();
		xhr.open("POST","Deny.do", true);
		console.log(xhr.readyState);
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		console.log("AFTER HEADER " + xhr.readyState);
		xhr.send(json);

		console.log(xhr.readyState);
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status==200){
				console.log("in xhr callback" + xhr.responseText);
				var messageFromAddReimb = JSON.parse(xhr.responseText);//***************error
				alert(messageFromAddReimb);
			}
		};
	}
	
	function getCssLogin(){
		$('#login-form-link').click(function(e) {
			$("#login-form").delay(100).fadeIn(100);
			$("#register-form").fadeOut(100);
			$('#register-form-link').removeClass('active');
			$(this).addClass('active');
			e.preventDefault();
		});
		$('#register-form-link').click(function(e) {
			$("#register-form").delay(100).fadeIn(100);
			$("#login-form").fadeOut(100);
			$('#login-form-link').removeClass('active');
			$(this).addClass('active');
			e.preventDefault();
		});
	}

	function gettoAddReimb(){
		var xhr = new XMLHttpRequest();
		xhr.open("GET","employeePartialHTML/addReimbursement.html", true);
		console.log(xhr.readyState);
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		console.log("AFTER HEADER " + xhr.readyState);
		xhr.send();

		console.log(xhr.readyState);
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status==200){
				console.log("after ready state");
				console.log("in xhr callback" + xhr.responseText);
				document.getElementById('view').innerHTML = xhr.responseText;
	
				$('#buttonid').on('click', function(){
					addReimb();
				});
				$('#goView').on('click', function(){
					viewReimb();
				});
				$('#logout').on('click', function(){
					logout();
				});

			};
		}
	}

	function addReimb(){
		console.log("in addreimb");
		var typeresult ="";
		
		var amount = $("#amountid").val();
		var description = $("#descriptionid").val();
		
		if(document.getElementById('lodgingid').checked) {
			typeresult = $('#lodgingid').val();
		}
		if(document.getElementById('travelid').checked) {
			typeresult = $('#travelid').val();
		}
		if(document.getElementById('foodid').checked) {
			typeresult = $('#foodid').val();
		}
		if(document.getElementById('otherid').checked) {
			typeresult = $('#otherid').val();
		}
		console.log("in addReimb and description is " + description);
		
		addReimbGoToSuccess(typeresult,amount,description);
	}

	function addReimbGoToSuccess(typeresult,amountresult,description){
		if(typeresult != false && amountresult != false){
			var reimb_amount = amountresult;
			var reimb_type = typeresult;
			var reimb_description = description;

			var addReimb = {amount:reimb_amount,type:reimb_type,description: reimb_description};
			var json = JSON.stringify(addReimb);
			var xhr = new XMLHttpRequest();

			xhr.open("POST","addReimbursement.do", true);
			console.log(xhr.readyState);
			xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			console.log("AFTER HEADER " + xhr.readyState);
			xhr.send(json);

			console.log(xhr.readyState);
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4 && xhr.status==200){
					console.log("in xhr callback" + xhr.responseText);
					var messageFromAddReimb = JSON.parse(xhr.responseText);//***************error
					alert(messageFromAddReimb);
				}
			};
		}
	}

	function viewReimb(){
		var xhr = new XMLHttpRequest();
		xhr.open("GET","employeePartialHTML/viewReimbHtml.html", true);
		console.log(xhr.readyState);
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		console.log("AFTER HEADER " + xhr.readyState);
		xhr.send();

		console.log(xhr.readyState);
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status==200){
				console.log("after ready state");
				console.log("in xhr callback" + xhr.responseText);
				document.getElementById('view').innerHTML = xhr.responseText;
				getViewData();		//ViewReimb html

			}
		};
	}

	function getViewData(){
		var xhr = new XMLHttpRequest();
		xhr.open("POST","WriterEmpView.do", true);
		console.log(xhr.readyState);
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		console.log("AFTER HEADER " + xhr.readyState);
		xhr.send();

		console.log(xhr.readyState);
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status==200){
				console.log("after ready state");
				var getData = JSON.parse(xhr.responseText);//getting data for viewReimb html
				populateEmpView(getData);
				$('#goAdd').on('click', function(){
					gettoAddReimb();
				});
				$('#vout').on('click', function(){
					logout();
				});
			}
		};
	}

	function populateEmpView(getData){
		for(var i = 0;i < getData.length;i++){
			if(getData[i].subDate != null){
			a = new Date(getData[i].subDate);
			getData[i].subDate = a;
			}
			else if(getData[i].resDate != null){
			b = new Date(getData[i].resDate);
			getData[i].resDate = b;
			}
			
			
		}
		$(document).ready(function(){
			$('#bustamoveView').DataTable({
				data: getData,
				columns: [
					{ data: "reimb_amount" },	            
					{ data: "subDate" },
					{ data: "resDate" },
					{ data: "description" },
					{ data: "riembStatus"},
					],
					"columnDefs": [ {
						"targets": -1,
						 "data": null,
					} ]
			});
		});
	}
});

