<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<title>SMS API- Upload</title>
<jsp:include page="template/headTag.jsp" />
<spring:url value="/resources/js/admin/uploadFile.js" var="uploadFileJS" />
<script src="${uploadFileJS}"></script>
<spring:url value="/resources/js/libs/jquery.form.js" var="ajaxForm" />
<script src="${ajaxForm}"></script>
<body>

	<div id="wrapper">
		<jsp:include page="template/bodyHeader.jsp" />
		<div id="page-wrapper">
			<div class="row" align="center">
				<div class="col-lg-12">
					<h1 class="page-header">Reseller File Upload</h1>
				</div>
				<c:url var="url" value="/admin/upload.html"></c:url>
				<form:form action="upload.html" id="fileUploadForm"
					enctype="multipart/form-data">

					<p>Select file to upload.</p>
					<input name="files" type="file" id="files" />
					<label id="errFiles" class="error-msg"></label>
					<br />
					<input type="submit" value="Upload">


				</form:form>
				<div id="message"></div>
			</div>
		</div>
	</div>
</body>
</html>