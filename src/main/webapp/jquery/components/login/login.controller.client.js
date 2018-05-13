(function() {
	var $usernameFld, $passwordFld;
	var $loginBtn;
	
	var userService = new UserServiceClient();
	
	$(main);
	
	function main() {
		$usernameFld = $("#usernameFld");
		$passwordFld = $("#passwordFld");
		$loginBtn = $("#loginBtn");
		
		$loginBtn.click(login);
	}
	
	function login() {
		if ($usernameFld.val() === null || $usernameFld.val() === "") {
			alert("username cannot be empty!");
			return;
		}
		if ($passwordFld.val() === null || $passwordFld.val() === "") {
			alert("password cannot be empty!");
			return;
		}
		else {
			var user = {
					username: $usernameFld.val(),
					password: $passwordFld.val()
			};
		}
		userService.login(user)
		.then(loginSuccess);
	}
	
	function loginSuccess(response) {
		if (response === null) {
			alert("user not exist OR password and username don't match!");
			clearForm();
			return;
		}
		else {
			url = "../profile/profile.template.client.html";
			window.location.href = url;	
		}
	}
	
	function clearForm() {
		$usernameFld.val("");
		$passwordFld.val("");
	}
})();