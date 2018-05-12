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
		
		$removeBtn = $(".wbdv-removeBtn");
		$editBtn = $(".wbdv-edit"); 
		$createBtn = $(".wbdv-createBtn");
		
		$userRowTemplate = $(".wbdv-template");
		$tbody = $(".wbdv-tbody");
		
		$createBtn.click(createUser);
	
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
		userService.createUser(user)
		.then(findAllUsers);
	}
	
	function findAllUsers() {
		userService.findAllUsers()
		.then(renderUsers);
	}
	
	function findUserById() {
		
	}
	
	function deleteUser(event) {
		$removeBtn = $(event.currentTarget);
		var userId = $removeBtn.parent().parent().parent().attr("id");
		
		userService.deleteUser(userId)
		.then(findAllUsers);
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
			
			$clone.find(".wbdv-removeBtn").click(deleteUser);
			
			$clone.find(".wbdv-username").html(user.username);
			$clone.find(".wbdv-first-name").html(user.firstName);
			$clone.find(".wbdv-last-name").html(user.lastName);
			$clone.find(".wbdv-role").html(user.role);
			$tbody.append($clone);
		}
	}
})();