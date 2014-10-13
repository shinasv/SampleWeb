function getResellerCredit(id) {
	$.ajax({
		type : 'GET',
		url : 'getResellerByID.html',
		data : ({
			id : id,

		}),
		success : function(reseller) {
			$('#hiddenAddid').val(reseller['id']);
			$('#addQuantityWindow').modal('show');
		}
	});
}

function saveResellerCredit() {
	var status = true;
	if ($('#quantity').val() == "") {
		$("#errQuantity").html("Enter any qauntity..");
		$('#quantity').val().focus();
		status = false
		return (status);
	}
	if (isNaN($('#quantity').val())) {

		$("#errQuantity").html("Only numbers are allowed..");
		$('#quantity').val().focus();
		status = false
		return (status);
	}
	if ($('#quantity').val() == 0) {
		$("#errQuantity").html("Enter any qauntity..");
		$('#quantity').val().focus();
		status = false
		return (status);
	}

	$.ajax({
		type : 'POST',
		url : 'saveOrUpdateResellerCredit.html',
		data : ({
			id : $('#hiddenAddid').val(),
			quantity : $('#quantity').val()

		}),
		success : function() {
			$('#saveResellerCredit').modal('data-dismiss');
			$('#addQuantityWindow').hide();
			$('#successWindowResellerCredit').modal('show');

		}

	});
}

function success() {

	location.replace("manageSMSCredit.html");

}
