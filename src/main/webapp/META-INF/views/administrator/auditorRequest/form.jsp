<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.auditorRequest.form.label.firm" path="firm" readonly="true"/>
	<acme:form-textarea code="administrator.auditorRequest.form.label.responsibilityStatement" path="responsibilityStatement" readonly="true"/>
	<acme:form-textbox code="administrator.auditorRequest.form.label.userName" path="user.identity.fullName" readonly="true"/>
	<acme:form-select code="administrator.auditorRequest.form.label.status" path="status">
		<acme:form-option code="administrator.auditorRequest.form.label.status.accepted" value="accepted" selected="true"/>
		<acme:form-option code="administrator.auditorRequest.form.label.status.rejected" value="rejected"/>		
	</acme:form-select>	
	
	<acme:form-submit code="administrator.auditorRequest.form.button.update" action="/administrator/auditor-request/update"/>
	<acme:form-return code="administrator.auditorRequest.form.button.return"/>
</acme:form>