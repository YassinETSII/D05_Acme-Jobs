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

<div class="jumbotron">
	<acme:message code="anonymous.rodriguezBulletin.form.initialMessage"/>
</div>

<acme:form>
	<acme:form-textbox code="anonymous.rodriguezBulletin.form.label.informer" path="informer"/>
	<acme:form-textbox code="anonymous.rodriguezBulletin.form.label.type" path="type"/>
	
	<acme:form-submit code="anonymous.rodriguezBulletin.form.button.create" action="/anonymous/rodriguez-bulletin/create"/>
	<acme:form-return code="anonymous.rodriguezBulletin.form.button.return"/>
</acme:form>