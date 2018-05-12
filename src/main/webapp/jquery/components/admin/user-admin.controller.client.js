(function() {
	var $usernameFld, $passwordFld, $roleFld;
	var $firstNameFld, $lastNameFld;
	var $removeBtn, $editBtn, $createBtn;
	var $userRowTemplate, $tbody;
	
	var userService = new UserServiceClient();
	
	$(main);
	
	function main() {
		$usernameFld = $("#usernameFld");
		$passwordFld = $("#passwordFld");
		$roleFld = $("#roleFld");
		$firstNameFld = $("#firstNameFld");
		$lastNameFld = $("#lastNameFld");
		
		$removeBtn = $(".wbdv-remove");
		$editBtn = $(".wbdv-edit"); 
		$createBtn = $(".wbdv-createBtn");
	
		
		
		$userRowTemplate = $(".wbdv-template");
		$tbody = $(".wbdv-tbody");
		
		
		
		
		$createBtn.click(createUser);
	
		findAllUsers();
	}
	
	function createUser() {
		
		var username = $usernameFld.val();
		var password = $passwordFld.val();
		var firstName = $firstNameFld.val();
		var lastName = $lastNameFld.val();
		var role = $roleFld.val();
		
		var user = {
				username: username,
				password: password,
				firstName: firstName,
				lastName: lastName,
				role: role
		};
		userService.createUser(user)
		.then(findAllUsers);
	}
	
	function findAllUsers() {
		userService.findAllUsers()
		.then(renderUsers);
	}
	
	function findUserById() {
		
	}
	
	function deleteUser() {
		
	}
	
	function selectUser() {
		
	}
	
	function updateUser() {
		
	}
	
	function renderUser(user) {
		
	}
	
	function renderUsers(users) {
		$tbody.empty();
		for (var i = 0; i < users.length; i++) {
			var user = users[i];
			var $clone = $userRowTemplate.clone();
			$clone.attr("id", user.id);
			$clone.find(".wbdv-username").html(user.username);
			$clone.find(".wbdv-first-name").html(user.firstName);
			$clone.find(".wbdv-last-name").html(user.lastName);
			$clone.find(".wbdv-role").html(user.role);
			$tbody.append($clone);
		}
	}
})();