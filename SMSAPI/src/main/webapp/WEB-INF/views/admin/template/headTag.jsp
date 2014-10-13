<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>SMS API-Administrator</title>
<meta name="description" content="HTML 5 CSS 3 Spring Form" />
<meta name="viewport" content="width=device-width,initial-scale=1" />

<!-- Core CSS - Include with every page -->
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />


<spring:url value="/resources/css/font-awesome/font-awesome.css"
	var="fontAwesomeCss" />
<link href="${fontAwesomeCss}" rel="stylesheet" />

<!-- SB Admin CSS - Include with every page -->
<spring:url value="/resources/css/sb-admin.css" var="sbAdminCss" />
<link href="${sbAdminCss}" rel="stylesheet" />


<!-- Core Scripts - Include with every page -->
<spring:url value="/resources/js/ui/jquery-1.10.2.js" var="jqueryJS" />
<script src="${jqueryJS}"></script>

<spring:url value="/resources/js/ui/bootstrap.min.js" var="bootstrapJS" />
<script src="${bootstrapJS}"></script>

<spring:url
	value="/resources/js/ui/plugins/metisMenu/jquery.metisMenu.js"
	var="metisMenuJS" />
<script src="${metisMenuJS}"></script>

<!-- SB Admin Scripts - Include with every page -->
<spring:url value="/resources/js/ui/sb-admin.js" var="sbAdminJS" />
<script src="${sbAdminJS}"></script>

</head>


