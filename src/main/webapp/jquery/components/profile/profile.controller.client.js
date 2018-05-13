(function() {
	var $phoneFld, $emailFld, $roleFld, $dobFld;
	
	$(main);
	
	function main() {
		$phoneFld = $("#phoneFld"); 
		$emailFld = $("#emailFld");
		$roleFld = $("#roleFld");
		$dobFld = $("#dobFld");
		
		$('#dobFld').datepicker();
	}
	
})();