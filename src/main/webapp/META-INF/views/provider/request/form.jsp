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
	<acme:form-textbox code="provider.request.label.title" path="title"/>
	<acme:form-moment code="provider.request.label.deadline" path="deadline"/>
	<acme:form-textarea code="provider.request.label.text" path="text"/>
	<acme:form-money code="provider.request.label.reward" path="reward"/>
	<acme:form-textbox code="provider.request.label.ticker" path="ticker" placeholder="RXXXX-99999"/>
	 	
	 <acme:form-checkbox code="provider.request.label.accept" path="accept"/>
	 	
	<acme:form-submit code="provider.request.button.create" action="/provider/request/create"/>
  	<acme:form-return code="provider.request.button.return"/>
</acme:form>
