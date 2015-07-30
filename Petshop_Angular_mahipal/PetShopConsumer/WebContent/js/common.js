// This used to pass search field 	
function customNavigate(msg) {
	if (msg == null || msg.trim() == "") {
		alert("Please enter proper details");
	} else {
		window.location.href = "#/search/" + msg;
	}
}

// Checking validation for quantity text box
function isNumberKey(evt) {
	var charCode = (evt.which) ? evt.which : event.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	} else {
		return true;
	}
}
