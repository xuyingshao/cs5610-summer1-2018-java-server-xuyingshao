function UserServiceClient() {
	this.createUser = createUser;
	this.findAllUsers = findAllUsers;
	this.findUserById = findUserById;
	this.deleteUser = deleteUser;
	this.updateUser = updateUser;
	this.register = register;
	this.login = login;
	this.logout = logout;
	this.populateProfile = populateProfile;
	this.updateProfile = updateProfile;
	this.changePassword = changePassword;
	this.url = "/api/user";
	this.registerUrl = "/api/register";
	this.loginUrl = "/api/login";
	this.profileUrl = "/api/profile";
	this.logoutUrl = "/api/logout";
	this.passwordUrl = "/api/password";
	var self = this;
	
	function createUser(user) {
		return fetch(self.url, {
			method: "post",
			credentials: "same-origin",
			body: JSON.stringify(user),
			headers: {
				"content-type": "application/json"
			}
		});
	}
	
	function findAllUsers() {
		return fetch(self.url, {
			credentials: "same-origin"
		})
		.then(function(response) {
			return response.json();
		});
	} 
	
	function findUserById(userId) {
		return fetch(self.url + "/" + userId, {
			credentials: "same-origin"
		})
		.then(function(response) {
			return response.json();
		});	
	}
	
	function deleteUser(userId) {
		return fetch(self.url + "/" + userId, {
			method: "delete",
			credentials: "same-origin",
		});
	}
	
	function updateUser(userId, newUser) {
		return fetch(self.url + "/" + userId, {
			method: "put",
			credentials: "same-origin",
			body: JSON.stringify(newUser),
			headers: {
				"content-type": "application/json"
			}
		})
		.then(function(response) {
			if (response === null) {
				return null;
			}
			else {
				return response.json();
			}
		});
	}
	
	function register(user) {
		return fetch(self.registerUrl, {
			method: "post",
			credentials: "same-origin",
			body: JSON.stringify(user),
			headers: {
				"content-type": "application/json"
			}
		})
		.then(function(response) {
			if (response.status === 409) {
				return null;
			}
			else {
				return response.json();
			}	
        });
	}
	
	function login(user) {
		return fetch(self.loginUrl, {
			method: "put",
			credentials: "same-origin",
			body: JSON.stringify(user),
			headers: {
				"content-type": "application/json"
			}
		})
		.then(function(response){  		
			if (response.status === 409) {
				return null;
			}
			else {
				return response.json();
			}
        });
	}
	
	function populateProfile() {
		return fetch(self.profileUrl, {
			credentials: "same-origin"
		})
		.then(function(response) {
			if (response.status === 409) {
				alert("something went wrong!");
				return null;
			}
			else {
				return response.json();
			}
		});
	}
	
	function updateProfile(user) {
		return fetch(self.profileUrl, {
			method: "put",
			credentials: "same-origin",
			body: JSON.stringify(user),
			headers: {
				"content-type": "application/json"
			}
		})
		.then(function(response){  		
			if (response.status === 409) {
				return null;
			}
			else {
				return response.json();
			}
        });
	}
	
	function logout() {
		return fetch(self.logoutUrl, {
			method: "post",
			credentials: "same-origin"
		})	
	}
	
	function changePassword(user) {
		return fetch(self.passwordUrl, {
			method: "post",
			credentials: "same-origin",
			body: JSON.stringify(user),
			headers: {
				"content-type": "application/json"
			}
		})
		.then(function(response) {
			if (response.status === 409) {
				return null;
			}
			else {
				return response.json();
			}
		});
	}
}