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
		var username = $('#username').val();
		var password = $('#password').val();

		var toSend = [username, password];
		var json = JSON.stringify(toSend);

		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status==200){
				console.log("in xhr callback" + xhr.responseText);

				var loginMessage = JSON.parse(xhr.responseText);
				if(loginMessage == 'Success'){
					alert("Employee Login Success");
					getAddReimbPage();
				}else if(loginMessage == "man"){
					alert("Manager Login Success");
					getAllEmpReimbPage();//****************
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
	
	function getAllEmpReimbPage(){
		var xhr = new XMLHttpRequest();
		xhr.open("POST","GetAllReimbPageController.do", true);
		console.log(xhr.readyState);
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		console.log("AFTER HEADER " + xhr.readyState);
		xhr.send();

		console.log(xhr.readyState);
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status==200){
				console.log("in xhr callback" + xhr.responseText);
				document.getElementById('view').innerHTML = xhr.responseText;
				getAllEmpReimb();
				
			}
		}
	}
	
	function getAllEmpReimb(){
		var xhr = new XMLHttpRequest();
		xhr.open("POST","GetAllEmpReimbController.do", true);
		console.log(xhr.readyState);
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		console.log("AFTER HEADER " + xhr.readyState);
		xhr.send();

		console.log(xhr.readyState);
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status==200){
				console.log("in xhr callback" + xhr.responseText);
				var data = JSON.parse(xhr.responseText);
				viewAllEmpReimbDataTable(data);
				
			}
		}
	}
	
	function viewAllEmpReimbDataTable(getData){
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
				approveReimb(data,index);
			});
			$('#bustamoveView tbody').on('click', '.denyButton',function(){
				var data = table.row($(this).parents('tr')).data();
				var index = table.row($(this).parents('tr')).index();
				denyReimb(data,index);
			});
			
		});
		$('#mout').on('click',function(){
			
			logout();
		});
	}
	
	//ApproveReimb is when a manager approves a reimbursement for an employee. Data provides the 
	//user information. ******took out index -useless
	function approveReimb(data){
		
		var aprove = {reimbid:data.reimbId};
		var json = JSON.stringify(aprove);
		var xhr = new XMLHttpRequest();
		
		var xhr = new XMLHttpRequest();
		xhr.open("POST","AproveReimb.do", true);
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
	
	//denyReimb is when a manager approves a reimbursement for an employee. Data provides the 
	//user information. ******took out index -useless
	function denyReimb(data){

		var deny = {reimbid:data.reimbId};
		var json = JSON.stringify(deny);
		var xhr = new XMLHttpRequest();
		
		var xhr = new XMLHttpRequest();
		xhr.open("POST","DenyReimb.do", true);
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

	function getAddReimbPage(){
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
					viewReimbPage();
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
		
		addReimbData(typeresult,amount,description);
	}

	function addReimbData(typeresult,amountresult,description){
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

	function viewReimbPage(){
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
//Friday May 25th
	function getViewData(){
		var xhr = new XMLHttpRequest();
		xhr.open("POST","writerEmpView.do", true);
		console.log(xhr.readyState);
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xhr.send();

		console.log(xhr.readyState);
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status==200){
				console.log("after ready state");
				var getData = JSON.parse(xhr.responseText);
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
			if(getData[i].submitted != null){
			a = new Date(getData[i].submitted);
			getData[i].submitted = a;
			}
			else if(getData[i].resolved != null){
			b = new Date(getData[i].resolved);
			getData[i].resolved = b;
			}
			
			
		}
		$(document).ready(function(){
			$('#bustamoveView').DataTable({
				data: getData,
				columns: [
					{ data: "amount" },	            
					{ data: "submitted" },
					{ data: "resolved" },
					{ data: "description" },
					{ data: "status"},
					],
					"columnDefs": [ {
						"targets": -1,
						 "data": null,
					} ]
			});
		});
	}
});

