function validateForm() {

	var check_name = /^[A-Za-z0-9 ]{3,20}$/;
	var check_email = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	var check_username = /^[A-Za-z0-9_]{1,20}$/;
	var check_password = /^[A-Za-z0-9!@#$%^&*()_]{6,20}$/;
	var status;

	if (!check_name.test($('#name').val())) {

		$("#errName").html("Enter a valid Name..");

		status = false;
		return (status);
	} else
		$("#errName").html("");

	if (!check_email.test($('#email').val())) {

		$("#errEmail").html("Enter a valid Email.." + "eg:name@email.com");

		status = false;
		return (status);
	} else
		$("#errEmail").html("");

	if ($('#email').val().indexOf("@", 0) < 0) {
		$("#errEmail").html("Enter a valid Email.." + "eg:name@email.com");

		status = false;
		return (status);
	} else
		$("#errEmail").html("");

	if ($('#email').val().indexOf(".", 0) < 0) {
		$("#errEmail").html("Enter a valid Email.." + "eg:name@email.com");

		status = false;
		return (status);
	} else
		$("#errEmail").html("");

	if ((($("#phone").val()).length < 10) || ($("#phone").val()).length > 12) {
		$("#errPhone").html("Should be 10 digits");
		status = false;
		return (status);

	} else
		$("#errPhone").html("");

	if ((($("#secondaryPhone").val()).length < 10)
			|| ($("#secondaryPhone").val()).length > 12) {
		$("#errSecPhone").html(" Should be 10 digits");
		status = false;
		return (status);
	} else
		$("#errSecPhone").html("");
	status = true;

	if (!check_username.test($('#userAccount.userName').val())) {

		$("#errUserName").html(
				"you must enter a valid Name.." + "/^[A-Za-z0-9_]{1,20}$/");

		status = false;
		return (status);
	} else
		$("#errUserName").html("");
	if (!check_password.test($('#userAccount.password').val())) {

		$("#errPassword").html(
				"you must enter a valid Name.." + "/^[A-Za-z0-9_]{1,20}$/");

		status = false;
		return (status);
	} else
		$("#errPassword").html("");

	$('#resellerRegistrationForm').ajaxForm({

		success : function(returnStatus) {

			if (returnStatus == 0) {

				$('#successPopup').modal('show');

			}
			if (returnStatus == 1) {
				$('#errEmail').html("Email already exist");
			}
			if (returnStatus == 2) {
				$('#errPhone').html("phone already exist");
			}
			if (returnStatus == 3) {
				$('#errUserName').html("Name already exist");
			}

		},
		dataType : 'json'
	}).submit();
}

function successResellerRegistration() {

	location.replace("resellerRegistration.html");

}