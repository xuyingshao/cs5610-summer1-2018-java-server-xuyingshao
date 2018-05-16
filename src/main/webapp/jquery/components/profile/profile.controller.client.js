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
		
		var user = userService.populateProfile();
		user.then(renderUsername);
		
		$('#dobFld').datepicker();
		$updateBtn.click(updateProfile);
		$logoutBtn.click(logout);	
	}
	
	function updateProfile() {
		var dob = $dobFld.val() !== ""? new Date($dobFld.val()).toISOString() : null;
		
		var user = {
				username: $usernameFld.val(),   
				phone: $phoneFld.val(),
				email: $emailFld.val(),
				role: $roleFld.val(),
				dateOfBirth: dob
		};
		console.log(user);
		userService.updateProfile(user)
		.then(updateSuccess);	
	}
	
	function logout() {
//		userService.logout();
		url = "../login/login.template.client.html";
		window.location.replace(url);	
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