(function() {
	var $usernameFld, $phoneFld, $emailFld, $roleFld, $dobFld;
	var $updateBtn, $logoutBtn;
	var url;
	var uid;
	var userService = new UserServiceClient();
	
	$(main);
	
	function main() {
		$usernameFld = $("#usernameFld");
		$phoneFld = $("#phoneFld"); 
		$emailFld = $("#emailFld");
		$roleFld = $("#roleFld");
		$dobFld = $("#dobFld");
		
		$updateBtn = $("#updateBtn"); 
		$logoutBtn = $("#logoutBtn");
		
		url = window.location.href;
		var query = url.substring(url.indexOf('?') + 1);
		uid = query.substring(query.indexOf("=") + 1);
		
		userService.findUserById(uid).
		then(renderUsername);
		
		$('#dobFld').datepicker();
		$updateBtn.click(updateProfile); 
		$logoutBtn.click(logout);	
	}
	
	function updateProfile() {
		var dob;
		if ($dobFld.val() !== "") {
			dob = new Date($dobFld.val()).toISOString();
		}
		var user = {
				username: $usernameFld.val(),   
				phone: $phoneFld.val(),
				email: $emailFld.val(),
				role: $roleFld.val(),
				dateOfBirth: dob
		};
		
		userService.updateProfile(uid, user)
		.then(updateSuccess);	
	}
	
	function logout() {
		url = "../login/login.template.client.html";
		window.location.href = url;	
	}
	
	function updateSuccess(response) {
		if (response === null) {
			$("#errorAlert").show();
		}
		else {
			$("#successAlert").show();
		}
	}
	
	function renderUsername(user) {
		var username = user.username;
		$usernameFld.val(username);
	}
})();