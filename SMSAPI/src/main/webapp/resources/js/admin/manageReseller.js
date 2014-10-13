function viewResellerPopup(id) {
	$.ajax({
		type : 'GET',
		url : 'getResellerByID.html',
		data : ({
			id : id
		}),
		success : function(reseller) {
			$('#txtid').val(reseller['id']);
			$('#txtname').val(reseller['name']);
			$('#txtaddress').val(reseller['address']);
			$('#txtemail').val(reseller['email']);
			$('#txtphone').val(reseller['phone']);
			$('#txtsecondaryPhone').val(reseller['secondaryPhone']);
			$('#txtwebsite').val(reseller['website']);
			$('#viewResellerPopupWindow').modal('show');
		}
	});
}

function editResellerPopup(id) {

	$.ajax({
		type : 'GET',
		url : 'getResellerByID.html',
		data : ({
			id : id
		}),
		success : function(reseller) {
			$('#hiddenSaveid').val(reseller['id']);
			$('#name').val(reseller['name']);
			$('#address').val(reseller['address']);
			$('#email').val(reseller['email']);
			$('#phone').val(reseller['phone']);
			$('#secondaryPhone').val(reseller['secondaryPhone']);
			$('#website').val(reseller['website']);
			$('#editResellerPopupWindow').modal('show');
		}
	});
}

function saveReseller() {

	var check_name = /^[A-Za-z0-9 ]{3,20}$/;
	var check_email = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	var check_website=/(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
	var status = true;

	if (!check_name.test($('#name').val()) || $('#name').val() == "") {

		$("#errName").html("Enter a valid Name.." + "[A-Za-z0-9 ]{3,20}");

		status = false;
		return (status);
	} else
		$("#errName").html("");

	if ($('#address').val() == "") {

		$("#errAddress").html("Enter the address");

		status = false;
		return (status);
	} else
		$("#errAddress").html("");

	if (!check_email.test($('#email').val()) || $('#email').val() == "") {

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

	if ((($("#phone").val()).length != 10) || $('#phone').val() == "") {
		$("#errPhone").html("Should be 10 digits");
		status = false;
		return (status);

	} else
		$("#errPhone").html("");

	if ((($("#secondaryPhone").val()).length != 10)
			|| $('#secondaryPhone').val() == "") {
		$("#errSecPhone").html(" Should be 10 digits");
		status = false;
		return (status);
	} else
		$("#errSecPhone").html("");
	if ($('#website').val() == "") {

		$("#errWebsite").html("Enter the website");

		status = false;
		return (status);
	} else
		$("#errWebsite").html("");

	$.ajax({

		type : 'POST',
		url : 'saveOrUpdateResellerInAdmin.html',
		data : ({

			id : $('#hiddenSaveid').val(),
			name : $('#name').val(),
			address : $('#address').val(),
			email : $('#email').val(),
			phone : $('#phone').val(),
			secondaryPhone : $('#secondaryPhone').val(),
			website : $('#website').val()

		}),
		success : function(returnStatus) {

			if (returnStatus == 0) {
				$('#saveReseller').modal('data-dismiss');
				$('#editResellerPopup').hide();
				$('#successWindowReseller').modal('show');
			}
			if (returnStatus == 1) {
				$('#errEmail').html("Email already exist");
			} else
				$('#errEmail').html("");
			if (returnStatus == 2) {
				$('#errPhone').html("phone already exist");
			} else
				$('#errPhone').html("");

		}

	});
}

function deleteResellerPopup(id) {

	$.ajax({
		type : 'GET',
		url : 'getResellerByID.html',
		data : ({
			id : id
		}),
		success : function(reseller) {
			$('#hiddenDeleteid').val(reseller['id']);
			$('#deleteResellerPopupWindow').modal('show');
		}
	});
}
function deleteReseller(id) {

	$.ajax({
		type : 'POST',
		url : 'deleteReseller.html',
		data : ({

			id : $('#hiddenDeleteid').val(),

		}),
		success : function() {

			$('#deleteResellerPopup').hide();
			location.replace("manageResellers.html");

		}

	});

}

function successWindowReseller() {

	location.replace("manageResellers.html");

}