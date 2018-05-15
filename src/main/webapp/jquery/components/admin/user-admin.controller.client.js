(function() {
	var $usernameFld, $passwordFld, $roleFld;
	var $firstNameFld, $lastNameFld;
	var $removeBtn, $editBtn, $createBtn, $updateBtn;
	var $userRowTemplate, $tbody, $inputForm;
	
	var userService = new UserServiceClient();
	
	$(main);
	
	function main() {
		$usernameFld = $("#usernameFld");
		$passwordFld = $("#passwordFld");
		$roleFld = $("#roleFld");
		$firstNameFld = $("#firstNameFld");
		$lastNameFld = $("#lastNameFld");
		
		$removeBtn = $(".wbdv-removeBtn");
		$editBtn = $(".wbdv-editBtn"); 
		$createBtn = $(".wbdv-createBtn");
		$updateBtn = $(".wbdv-updateBtn");
		
		$userRowTemplate = $(".wbdv-template");
		$tbody = $(".wbdv-tbody");
		$inputForm = $(".wbdv-form");
		
		$createBtn.click(createUser);
		$updateBtn.click(updateUser);
	
		findAllUsers();
	}
	
	function createUser() {
		var user = {
				username: $usernameFld.val(),
				password: $passwordFld.val(),
				firstName: $firstNameFld.val(),
				lastName: $lastNameFld.val(),
				role: $roleFld.val()
		};
		clearInputForm();
		userService.createUser(user)
		.then(findAllUsers);	
	}
	
	function findAllUsers() {
		userService.findAllUsers()
		.then(renderUsers);
	}
	
	function findUserById() {
		$editBtn = $(event.currentTarget);
		var userId = $editBtn.parent().parent().parent().attr("id");
		$inputForm.attr("id", userId);
		
		userService.findUserById(userId)
		.then(renderUser);
	}
	
	function deleteUser(event) {
		$removeBtn = $(event.currentTarget);
		var userId = $removeBtn.parent().parent().parent().attr("id");
	
		userService.deleteUser(userId)
		.then(findAllUsers);
	}
	
	function updateUser(event) {
		var userId = $inputForm.attr("id");
		if (userId === undefined) {
			return;
		}
//		var newUser = {
//				username: $usernameFld.val(),
//				password: $passwordFld.val(),
//				firstName: $firstNameFld.val(),
//				lastName: $lastNameFld.val(),
//				role: $roleFld.val()	
//		};
		var newUser = new User($usernameFld.val(), 
				$passwordFld.val(),
				$firstNameFld.val(),
				$lastNameFld.val(),
				$roleFld.val());
		clearInputForm();
		userService.updateUser(userId, newUser)
		.then(findAllUsers);
	}
	
	function renderUser(user) {
		$usernameFld.val(user.username);
		$passwordFld.val(user.password);
		$roleFld.val(user.role);
		$firstNameFld.val(user.firstName);
		$lastNameFld.val(user.lastName);
	}
	
	function renderUsers(users) {
		$tbody.empty();
		for (var i = 0; i < users.length; i++) {
			var user = users[i];
			var $clone = $userRowTemplate.clone();
			$clone.attr("id", user.id);
			
			$clone.find(".wbdv-removeBtn").click(deleteUser);
			$clone.find(".wbdv-editBtn").click(findUserById);
			
			$clone.find(".wbdv-username").html(user.username);
			$clone.find(".wbdv-first-name").html(user.firstName);
			$clone.find(".wbdv-last-name").html(user.lastName);
			$clone.find(".wbdv-role").html(user.role);
			$tbody.append($clone);
		}
	}
	
	function clearInputForm() {
		$inputForm.find("#usernameFld").val("");
		$inputForm.find("#passwordFld").val("");
		$inputForm.find("#firstNameFld").val("");
		$inputForm.find("#lastNameFld").val("");
	}
})();