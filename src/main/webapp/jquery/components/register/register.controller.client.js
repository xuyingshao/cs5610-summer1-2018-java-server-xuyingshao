(function() {
	var $usernameFld, $passwordFld, $verifyPasswordFld;
	var $registerBtn;
	var userService = new UserServiceClient();
	
	$(main);
	
	function main() {
		$usernameFld = $("#usernameFld");
		$passwordFld = $("#passwordFld");
		$verifyPasswordFld = $("#verifyPasswordFld");
		$registerBtn = $("#registerBtn");
		
		$registerBtn.click(register);
	}
	
	function register() {
		console.log($usernameFld.val());
		if ($usernameFld.val() === null || $usernameFld.val() === "") {
			alert("username cannot be empty!");
			return;
		}
		if ($passwordFld.val() === null || $passwordFld.val() === "") {
			alert("password cannot be empty!");
			return;
		}
		if ($verifyPasswordFld.val() === null || $verifyPasswordFld.val() === "") {
			alert("please verify your password!");
			return;
		}
		if ($passwordFld.val() !== $verifyPasswordFld.val()) {
			alert("password does not match!");
			return;
		}
		else {
			var user = {
					username: $usernameFld.val(),
					password: $passwordFld.val()
			}
			userService.register(user)
			.then(registerSuccess);
		}
	}
	
	function registerSuccess(response) {
//		if (response === null) {
//			alert("username already taken");
//		 // clear form
//		}
//		else {	
			url = "../profile/profile.template.client.html";
			console.log(url);
			window.location.href = url;		
//			$(location).attr("href", url);
//	
//		}
	}
})();
