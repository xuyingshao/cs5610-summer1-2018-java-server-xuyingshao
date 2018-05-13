function UserServiceClient() {
	this.createUser = createUser;
	this.findAllUsers = findAllUsers;
	this.findUserById = findUserById;
	this.deleteUser = deleteUser;
	this.updateUser = updateUser;
	this.register = register;
	this.login = login;
	this.url = "http://localhost:8080/api/user";
	this.registerUrl = "http://localhost:8080/api/register";
	this.loginUrl = "http://localhost:8080/api/login";
	var self = this;
	
	function createUser(user) {
		return fetch(self.url, {
			method: "post",
			body: JSON.stringify(user),
			headers: {
				"content-type": "application/json"
			}
		});
	}
	
	function findAllUsers() {
		return fetch(self.url)
		.then(function(response) {
			return response.json();
		});
	} 
	
	function findUserById(userId) {
		return fetch(self.url + "/" + userId)
		.then(function(response) {
			return response.json();
		});	
	}
	
	function deleteUser(userId) {
		return fetch(self.url + "/" + userId, {
			method: "delete"
		});
	}
	
	function updateUser(userId, newUser) {
		return fetch(self.url + "/" + userId, {
			method: "put",
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
			body: JSON.stringify(user),
			headers: {
				"content-type": "application/json"
			}
		})
		.then(function(response) {
			if (!response.bodyUsed) {
				return null;
			}
			else {
				return response.json();
			}	
        });
//		debugger;
//
//		let json = response.json();
//		if (response.status >= 200 && response.status < 300) {
//	    return json;
//		  } else {
//		    return json.then(Promise.reject.bind(Promise));
//		  }
//		
//        }).catch(function(err) {
//            console.log('Fetch Error :-S', err);
//        });
	}
	
	function login(user) {
		return fetch(self.loginUrl, {
			method: "post",
			body: JSON.stringify(user),
			headers: {
				"content-type": "application/json"
			}
		})
		.then(function(response){  		
			if (!response.bodyUsed) {
				return null;
			}
			else {
				return response.json();
			}
        });
	}
}