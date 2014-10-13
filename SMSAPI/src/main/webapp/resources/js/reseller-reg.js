function registerReseller() {

	var status = validateResellerRegForm();
	if (status == false) {
		return;
	}
	$
	.ajax({
		type : 'POST',
		url : 'resellerRegistration.html',
		data : ({
			name : $('#txtName').val(),
			phone : $('#txtPhone').val(),
			secPhone : $('#txtSecPhone').val(),
			address : $('#txtaAddress').val(),
			email : $('#txtEmail').val(),
			website : $('#txtWebsite').val(),
			userID : $('#txtUserID').val(),
			password : $('#txtPassword').val()
		}),
		success : function(responseData) {
			if (responseData == 0) {
				 $('#successBox').modal('show');
				 resetForm();
			} else if(responseData == -1){
				alert("failed...");
			}else if(responseData == 1){
				alert("phone already exist...");
			}else if(responseData == 2){
				alert("email already exist...");
			}else if(responseData == 3){
				alert("user id already exist...");
			}
		}
	});
}

function resetForm(){
	$('#frmReseller')[0].reset();
	$( ".error-msg" ).html("");
}

function validateResellerRegForm(){
	var status = true;
	if ($("#txtName").val() == "") {
		$("#errName").html("Please enter name");
		status = false;
	} else {
		$("#errName").html("");
	}
	if ($("#txtPhone").val() == "") {
		$("#errPhone").html("Please enter phone");
		status = false;
	} else if (($("#txtPhone").val()).length < 10) {
		$("#errPhone").html("Invalid phone number");
		status = false;
	} else {
		$("#errPhone").html("");
	}
	if ($("#txtSecPhone").val() != "" && ($("#txtSecPhone").val()).length < 10) {
		$("#errSecPhone").html("Invalid phone number");
		status = false;
	} else {
		$("#errSecPhone").html("");
	}
	if ($("#txtWebsite").val() != "") {
		status = validateWebsite(status);
	} else {
		 $("#errWebsite").html("");
	}
	if ($("#txtEmail").val() == "") {
		$("#errEmail").html("Please enter email");
		status = false;
	} else {
		status = validateEmail(status);
	}
	if ($("#txtUserID").val() == "") {
		$("#errUserID").html("Please enter user ID");
		status = false;
	} else if (($("#txtUserID").val()).length < 6) {
		$("#errUserID").text("User ID should be atleast 6 symbol long");
		status = false;
	} else {
		$("#errUserID").html("");
	}
	if ($("#txtPassword").val() == "") {
		$("#errPassword").html("Please enter password");
		status = false;
	} else {
		status = validatePassword(status);
	}
return status;
}

function validateEmail(status) {
	var email = $("#txtEmail").val();
	var EMAIL_REGEX = new RegExp(
			"^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$");
	if (!email.match(EMAIL_REGEX)) {
		$("#errEmail").text("Invalid email address");
		status = false;
	} else {
		$("#errEmail").text("");
	}
	return status;
}

function validateWebsite(status) {
	var strValue = $("#txtWebsite").val();
	var WEB_REGEX = new RegExp(/[-a-zA-Z0-9@:%_\+.~#?&//=]{2,256}\.[a-z]{2,4}\b(\/[-a-zA-Z0-9@:%_\+.~#?&// =]*)?/gi);
	if (!strValue.match(WEB_REGEX)) {
		$("#errWebsite").text("Invalid website address");
		status = false;
	} else {
		$("#errWebsite").text("");
	}
	return status;
}

function validatePassword(status) {
	if (($("#txtPassword").val()).length < 6) {
		$("#errPassword").text("Password should be atleast 6 symbol long");
		status = false;
	} else if ($("#txtConfPassword").val() == "") {
		$("#errPassword").text("Please verify your password");
		status = false;
	} else if ($("#txtPassword").val() != $("#txtConfPassword").val()) {
		$("#errPassword").text("Your password must be verified correctly");
		status = false;
	} else {
		$("#errPassword").text("");
	}
	return status;
}

function numericFilter(txb) {
	txb.value = txb.value.replace(/[^\0-9]/ig, "");
}