<!DOCTYPE html>
<%@page import="com.aitrich.smsapi.model.Reseller"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<title>SMS API-Manage Reseller</title>
<jsp:include page="template/headTag.jsp" />
<spring:url value="/resources/js/admin/manageReseller.js"
	var="ManageResellerJS" />
<script src="${ManageResellerJS}"></script>

<head>
<style>
.error {
	color: red;
}
</style>
</head>
<body>


	<div id="wrapper">
		<jsp:include page="template/bodyHeader.jsp" />
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Manage Reseller</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading" align="right">
							<label><a href="downloadExcel">Download Excel Document</a></label>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="table-responsive">

								<table class="table table-striped table-bordered table-hover"
									id="dataTables-ManageReseller">
									<thead>
										<tr>
											<th>SLNo</th>
											<th>Name</th>
											<th>Phone</th>
											<th>Email ID</th>
											<th>View</th>
											<th>Edit</th>
											<th>Delete</th>
										</tr>
									</thead>
									<c:set var="count" value="0" scope="page" />
									<tbody>
										<c:forEach items="${resellerList }" var="reseller">


											<tr>

												<td align="center"><c:set var="count"
														value="${count+1}" scope="page" />${count}</td>
												<td align="center">${reseller.name}</td>
												<td align="center">${reseller.phone}</td>
												<td align="center">${reseller.email}</td>

												<td><img
													src="<spring:url value="/resources/img/icons/view.png" htmlEscape="true"/>"
													onclick="viewResellerPopup(${reseller.id})" title="View" />
												</td>
												<td><img
													src="<spring:url value="/resources/img/icons/pencil.png" htmlEscape="true"/>"
													onclick="editResellerPopup(${reseller.id})" title="Edit" />
												</td>
												<td><img
													src="<spring:url value="/resources/img/icons/cross.png" htmlEscape="true"/>"
													onclick="deleteResellerPopup(${reseller.id})"
													title="Delete" /></td>


											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<!-- /.table-responsive -->
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- col la -->

				<div>
					<!-- popupwindow -->


					<div class="modal fade" id="viewResellerPopupWindow">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title" id="myModalLabel">View Reseller</h4>
								</div>

								<form:form action="${url}" modelAttribute="reseller">

									<fieldset>

										<div class="form-group">

											<label style="color: #E92B39;">Name<output
													id="txtname"></output></label>


										</div>
										<div class="form-group">

											<label style="color: #E92B39;">Address<output
													id="txtaddress"></output></label>

										</div>

										<div class="form-group">
											<label style="color: #E92B39;">Email Id<output
													id="txtemail"></output></label>


										</div>

										<div class="form-group">
											<label style="color: #E92B39;">Phone No<output
													id="txtphone"></output></label>


										</div>
										<div class="form-group">
											<label style="color: #E92B39;">Secondary Phone<output
													id="txtsecondaryPhone"></output></label>

										</div>
										<div class="form-group">
											<label style="color: #E92B39;">Website<output
													id="txtwebsite"></output></label>

										</div>
										<div class="modal-footer">
											<label style="color: #E92B39;"></label>
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Close</button>

										</div>
									</fieldset>
								</form:form>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<!-- /.modal -->
					<div class="modal fade" id="editResellerPopupWindow">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title" id="myModalLabel">Edit Reseller</h4>
								</div>

								<div class="modal-body">
									<%-- <form> --%>
									<table>
										<tr>
											<td><input type="hidden" id="hiddenSaveid" /></td>
										</tr>
										<tr>
											<td>
												<div class="form-group">
													<label>Name</label> <input class="form-control" type="text"
														autofocus="autofocus" required="required" id="name" /> <label
														id="errName" class="error-msg"></label>
												</div>
											</td>
										</tr>
										<tr>
											<td>

												<div class="form-group">
													<label>Address</label> <input class="form-control"
														type="text" autofocus="autofocus" required="required"
														id="address" /><label id="errAddress" class="error-msg"></label>

												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="form-group">
													<label>Email ID</label> <input class="form-control"
														type="text" autofocus="autofocus" required="required"
														id="email" /> <label id="errEmail" class="error-msg"></label>

												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="form-group">
													<label>Phone no.</label> <input class="form-control"
														type="text" autofocus="autofocus" required="required"
														id="phone" /> <label id="errPhone" class="error-msg"></label>


												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="form-group">
													<label>Secondary Phone</label> <input class="form-control"
														type="text" autofocus="autofocus" required="required"
														id="secondaryPhone" /> <label id="errSecPhone"
														class="error-msg"></label>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="form-group">
													<label>Website</label> <input class="form-control"
														type="text" autofocus="autofocus" required="required"
														id="website" /><label id="errWebsite" class="error-msg"></label>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="modal-footer">

													<label style="color: #E92B39;"></label>
													<button type="button" onclick="saveReseller()"
														class="btn btn-primary">Save</button>
													<label style="color: #E92B39;"></label>
													<button type="button" class="btn btn-default"
														data-dismiss="modal">Close</button>
												</div>
											</td>
										</tr>
									</table>
									<%-- </form> --%>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<!-- mymodel end -->



				</div>
				<!-- popupwindowend -->

				<!-- /.row -->
				<!-- Modal -->

				<div class="modal fade" id="deleteResellerPopupWindow">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<!-- <button type="button" class="close" data-dismiss="modal">&times;</button>
								 -->
							</div>

							<input type="hidden" id="hiddenDeleteid">

							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<div class="form-group">
								<h3 align="center" style="color: rgb(40, 180, 40);">Do you
									want to delete?</h3>
							</div>
							<div class="modal-footer">
								<button type="submit" onclick="deleteReseller()"
									class="btn btn-primary">YES</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">NO</button>

							</div>





						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->

				<div class="modal fade" id="successWindowReseller">
					<div class="modal-dialog">
						<div class="modal-content">

							<div class="modal-header"></div>
							<div class="modal-body">

								<div class="form-group">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h3 align="center" style="color: rgb(20, 100, 20);">
										Success</h3>
								</div>
								<div class="modal-footer">
									<label style="color: #E92B39;"></label>
									<button type="submit" onclick=" successWindowReseller()"
										class="btn btn-primary" data-dismiss="modal">OK</button>
								</div>

							</div>


						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- mymodel end -->


			</div>
			<!--   raw -->


		</div>
		<!-- /#page-wrapper -->


	</div>


	<!-- /#wrapper -->
	<!-- Page-Level Plugin Scripts - Tables -->
	<spring:url
		value="/resources/js/ui/plugins/dataTables/jquery.dataTables.js"
		var="dataTableJS" />
	<script src="${dataTableJS}"></script>
	<spring:url
		value="/resources/js/ui/plugins/dataTables/dataTables.bootstrap.js"
		var="dataTableBoostrapJS" />
	<script src="${dataTableBoostrapJS}"></script>

	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<script>
		$(document).ready(function() {
			$('#dataTables-ManageReseller').dataTable();
		});
	</script>

</body>
</html>



