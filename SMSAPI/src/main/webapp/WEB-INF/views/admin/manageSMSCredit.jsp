<!DOCTYPE html>
<%@page import="com.aitrich.smsapi.model.Reseller"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<jsp:include page="template/headTag.jsp" />
<spring:url value="/resources/js/admin/manageSMSCredit.js"
	var="ManageSMSCreditJS" />
<script src="${ManageSMSCreditJS}"></script>

<body>
	<div id="wrapper">
		<jsp:include page="template/bodyHeader.jsp" />
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Manage SMS Credit</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading" align="right">
							<label style="color: #E92B39;">Manage SMS Credit</label>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="table-responsive">

								<table class="table table-striped table-bordered table-hover"
									id="dataTables-manageSMSCredit">
									<thead>
										<tr>
											<th>SLNo</th>
											<th>Reseller</th>
											<th>Total Purchase</th>
											<th>Credit Used</th>
											<th>Balance</th>
											<th>Add</th>

										</tr>
									</thead>
									<c:set var="count" value="0" scope="page" />

									<tbody>
										<c:forEach items="${resellerCreditList}" var="resellerCredit">


											<tr>

												<td align="center"><c:set var="count"
														value="${count+1}" scope="page" />${count}</td>
												<td align="center">${resellerCredit.reseller.name}</td>
												<td align="center">${resellerCredit.totalCredits}</td>
												<td align="center">${resellerCredit.creditUsed}</td>

												<td align="center"><c:set var="balance"
														value="${resellerCredit.totalCredits - resellerCredit.creditUsed}"
														scope="page" />${balance}</td>

												<td><img
													src="<spring:url value="/resources/img/icons/timetable.png" htmlEscape="true"/>"
													onclick="getResellerCredit(${resellerCredit.reseller.id})"
													title="Add" /></td>


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
					<!-- popup-->
					<div class="modal fade" id="addQuantityWindow">
						<div class="modal-dialog">
							<div class="modal-content">

								<div class="modal-header"></div>
								<div class="modal-body">


									<c:set var="quantity" value="0" />

									<input type="hidden" id="hiddenAddid">
									<div class="form-group">
										<label align="center" style="color: rgb(40, 180, 40);">Quantity
											: </label> <label id="errQuantity" class="error-msg"></label> <input
											type="text" path="quantity" id="quantity" />

									</div>
									<div class="modal-footer">
										<label style="color: #E92B39;"></label>
										<button type="submit" onclick="saveResellerCredit()"
											class="btn btn-primary">Save</button>
										<label style="color: #E92B39;"></label>
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Close</button>
									</div>
								</div>


							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<!-- mymodel end -->

					<div class="modal fade" id="successWindowResellerCredit">
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
										<button type="submit" onclick="success()"
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

			</div>
			<!-- raw -->

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
			$('#dataTables-manageSMSCredit').dataTable();
		});
	</script>

</body>
</html>



