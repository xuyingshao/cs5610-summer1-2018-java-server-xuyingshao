function User(username, password, firstName, lastName, role) {
	this.username = username;
	this.password = password;
	this.firstName = firstName;
	this.lastName = lastName;
	this.role = role;
	
	this.setUserName = setUserName;
	this.getUserName = getUserName;
	this.setPassword = setPassword;
	this.getPassword = getPassword;
	this.setFirstName = setFirstName;
	this.getFirstName = getFirstName;
	this.setLastName = setLastName;
	this.getLastName = getLastName;
	this.setRole = setRole;
	this.getRole = getRole;
	
	var self = this;	
}