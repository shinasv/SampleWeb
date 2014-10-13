<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="utf-8" />
<title>Sample | Registration</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- Core CSS - Include with every page -->
<link rel="stylesheet" th:href="@{/resources/css/bootstrap.min.css}" />
<link rel="stylesheet"
	th:href="@{/resources/css/font-awesome/font-awesome.css}" />

<script type="text/javascript"
	th:src="@{/resources/js/ui/jquery-1.10.2.js}"></script>
<script type="text/javascript"
	th:src="@{/resources/js/ui/bootstrap.min.js}"></script>
</head>
<body>
	<div class="container">
		<div class="page-header">
			<h1 align="center">SMSAPI</h1>
		</div>
		<div class="panel panel-primary">
			<div class="panel-heading" style="font-size: large;">Sample
				Registration Form</div>
			<div class="panel-body">
				<!-- Registration form - START -->
				<div class="row">
					<form id="frmReseller" role="form" method="post">
						<div class="col-lg-6">
							<div class="form-group">
								<label for="txtName">Name</label><label id="errName"
									class="error-msg"></label> <input type="text"
									class="form-control" name="txtName" maxlength="30" id="txtName"
									placeholder="Reseller Name" required="required" />
							</div>
							<div class="form-group">
								<label for="txtPhone">Phone</label><label id="errPhone"
									class="error-msg"></label> <input type="text"
									class="form-control" id="txtPhone" maxlength="15"
									name="txtPhone" placeholder="Phone" required="required"
									onkeyup="numericFilter(this);" />
							</div>
							<div class="form-group">
								<label for="txtSecPhone">Secondary Phone</label><label
									id="errSecPhone" class="error-msg"></label> <input type="text"
									class="form-control" id="txtSecPhone" maxlength="15"
									name="txtSecPhone" placeholder="Secondary Phone"
									required="required" onkeyup="numericFilter(this);" />
							</div>
							<div class="form-group">
								<label for="txtaAddress">Address</label>
								<textarea name="txtaAddress" id="txtaAddress" maxlength="300"
									class="form-control" rows="5"></textarea>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label for="txtEmail">Email</label><label id="errEmail"
									class="error-msg"></label> <input type="email"
									class="form-control" name="txtEmail" maxlength="40"
									id="txtEmail" placeholder="Email" required="required" />
							</div>
							<div class="form-group">
								<label for="txtWebsite">Website</label> <label id="errWebsite"
									class="error-msg"></label> <input type="text"
									class="form-control" id="txtWebsite" maxlength="30"
									name="txtWebsite" placeholder="Website" required="required" />
							</div>
							<div class="form-group">
								<label for="txtUserID">User ID</label> <label id="errUserID"
									class="error-msg"></label> <input type="text"
									class="form-control" id="txtUserID" maxlength="30"
									name="txtUserID" placeholder="User ID" required="required" />
							</div>
							<div class="form-group">
								<label for="txtPassword">Password</label> <label
									id="errPassword" class="error-msg"></label> <input
									type="password" class="form-control" id="txtPassword"
									maxlength="20" name="txtPassword" placeholder="Password"
									required="required" />
							</div>
							<div class="form-group">
								<label for="txtConfPassword">Verify Password</label> <input
									type="password" class="form-control" id="txtConfPassword"
									name="txtConfPassword" maxlength="20"
									placeholder="Confirm Password" required="required" />
							</div>
						</div>
					</form>
				</div>
				<!-- Registration form - END -->
			</div>
			<div class="panel-footer" align="right">
				<button type="reset" class="btn btn-default">Reset</button>
				&nbsp;&nbsp; <input type="button" name="submit" id="submit"
					value="Submit" class="btn btn-info pull-right"
					onclick="$('#successBox').show();" />
			</div>
		</div>
		<div id="successBox" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<!-- dialog body -->
					<div class="modal-body">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h3 align="center" style="color: rgb(40, 180, 40);">Registered
							Successfully</h3>
					</div>
					<!-- dialog buttons -->
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">OK</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>