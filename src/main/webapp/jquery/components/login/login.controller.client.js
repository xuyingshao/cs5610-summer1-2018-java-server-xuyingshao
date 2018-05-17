(function() {
	var $usernameFld, $passwordFld;
	var $loginBtn, $changeBtn, $updateBtn;
	var $loginDiv, $changeDiv;
	var $newUsernameFld, $newPasswordFld, $verifyPasswordFld;
	
	var userService = new UserServiceClient();
	
	$(main);
	
	function main() {
		$usernameFld = $("#usernameFld");
		$passwordFld = $("#passwordFld");
		$loginBtn = $("#loginBtn");
		$changeBtn = $("#changeBtn");
		$updateBtn = $("#updateBtn");
		
		$loginDiv = $("#login");
		$changeDiv = $("#changepassword");
		
		$newUsernameFld = $("#newUsernameFld");
		$newPasswordFld = $("#newPasswordFld");
		$verifyPasswordFld = $("#verifyPasswordFld");
		
		$loginBtn.click(login);
		$changeBtn.click(showChangePage);
		$updateBtn.click(changePassword);
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
			clearLoginForm();
			return;
		}
		else {
			url = "../profile/profile.template.client.html";
			window.location.replace(url);
		}
	}	
	
	function showChangePage() {
		$loginDiv.hide();
		$changeDiv.show();
	}
	
	function changePassword() {
		if ($newUsernameFld.val() === null || $newUsernameFld.val() === "") {
			alert("username cannot be empty!");
			return;
		}
		if ($newPasswordFld.val() === null || $newPasswordFld.val() === "") {
			alert("password cannot be empty!");
			return;
		}
		if ($verifyPasswordFld.val() === null || $verifyPasswordFld.val() === "") {
			alert("please verify your password!");
			return;
		}
		if ($newPasswordFld.val() !== $verifyPasswordFld.val()) {
			alert("password does not match!");
			return;
		}
		else {
			var user = {
					username: $newUsernameFld.val(),
					password: $newPasswordFld.val()
			}
			userService.changePassword(user)
			.then(changePasswordSuccess);
		}
	}
	
	function changePasswordSuccess(response) {
		if (response === null) {
			alert("user does not exist!");
			clearChangeForm();
		}
		else {
			url = "../profile/profile.template.client.html";
			window.location.replace(url);
		}
	}
	
	function renderUsername(user) {
		var username = user.username;
		$usernameFld.val(username);
	}
	
	function clearLoginForm() {
		$usernameFld.val("");
		$passwordFld.val("");
	}
	
	function clearChangeForm() {
		$newUsernameFld.val("");
		$newPasswordFld.val("");
		$verifyPasswordFld.val("");
	}
})();