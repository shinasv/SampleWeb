<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<title>SMS API-Reseller Registration</title>
<jsp:include page="template/headTag.jsp" />
<spring:url value="/resources/js/admin/resellerRegistration.js"
	var="ResellerRegistrationJS" />
<script src="${ResellerRegistrationJS}"></script>
<spring:url value="/resources/js/libs/jquery.form.js" var="ajaxForm" />
<script src="${ajaxForm}"></script>

<body>
	<jsp:include page="template/bodyHeader.jsp" />
	<div class="container">

		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Reseller Registration</h3>
					</div>
					<div class="panel-body">
						<c:url var="url" value="/admin/resellerRegistrationSubmit.html"></c:url>
						<form:form id="resellerRegistrationForm" action="${url}"
							modelAttribute="reseller">

							<fieldset>
								<div class="form-group">


									<form:input class="form-control" placeholder="Name" type="text"
										autofocus="autofocus" required="required" path="name"
										id="name" />
									<label id="errName" class="error-msg"></label>

								</div>
								<div class="form-group">

									<form:textarea class="form-control" placeholder="Address"
										type="text" autofocus="autofocus" required="required"
										path="address" id="address" />



								</div>

								<div class="form-group">

									<form:input class="form-control" placeholder="Email ID"
										type="text" autofocus="autofocus" required="required"
										path="email" id="email" />
									<label id="errEmail" class="error-msg"></label>

								</div>

								<div class="form-group">
									<form:input class="form-control" placeholder="Phone No."
										type="text" autofocus="autofocus" required="required"
										path="phone" id="phone" />
									<label id="errPhone" class="error-msg"></label>


								</div>
								<div class="form-group">
									<form:input class="form-control" placeholder="Secondary Phone"
										type="text" autofocus="autofocus" required="required"
										path="secondaryPhone" id="secondaryPhone" />
									<label id="errSecPhone" class="error-msg"></label>
								</div>
								<div class="form-group">
									<form:input class="form-control" placeholder="Website"
										type="text" autofocus="autofocus" required="required"
										path="website" id="website" />
									<label id="errWebsite" class="error-msg"></label>
								</div>
								<div class="form-group">
									<form:input class="form-control" placeholder="User Name"
										type="text" autofocus="autofocus" required="required"
										path="userAccount.userName" id="userName" />
									<label id="errUserName" class="error-msg"></label>

								</div>

								<div class="form-group">
									<form:input class="form-control" placeholder="Password"
										type="password" required="required"
										path="userAccount.password" id="password" />
									<label id="errPassword" class="error-msg"></label>
								</div>
								<label style="color: #E92B39;"></label>
								<button type="button" onclick="validateForm()"
									class="btn btn-lg btn-success btn-block">Register</button>
								<label style="color: #E92B39;"></label>
								<button type="reset" value="cancel"
									class="btn btn-lg btn-success btn-block">Reset</button>

							</fieldset>
						</form:form>
					</div>
				</div>
			</div>
			<!-- "col-md-4 col-md-offset-4 -->

			<div id="successPopup" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<!-- dialog body -->
						<div class="modal-body">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h3 align="center" style="color: rgb(20, 80, 20);">Registered
								Successfully</h3>
						</div>
						<!-- dialog buttons -->
						<div class="modal-footer">
							<button type="button" onclick=" successResellerRegistration()"
								class="btn btn-primary" data-dismiss="modal">OK</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- raw -->
	</div>
</body>
</html>