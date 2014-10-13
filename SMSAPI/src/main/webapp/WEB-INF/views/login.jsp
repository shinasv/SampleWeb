<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Login Page</title>
<jsp:include page="fragments/headTag.jsp" />
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Please Sign In</h3>
					</div>
					<div class="panel-body">
						<c:url var="url" value="/login-auth.html" />
						<form:form action="${url}" method="POST"
							modelAttribute="userAccount">
							<fieldset>
								<div class="form-group">
									<form:input class="form-control" placeholder="User ID"
										type="text" autofocus="autofocus" required="required"
										path="userName" />
								</div>
								<div class="form-group">
									<form:input class="form-control" placeholder="Password"
										type="password" required="required" path="password" />
								</div>
								<label style="color: #E92B39;">${loginErrorMsg}</label> <input
									type="submit" value="Login"
									class="btn btn-lg btn-success btn-block" />
							</fieldset>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>