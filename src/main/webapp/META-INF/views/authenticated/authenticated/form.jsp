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
	<acme:form-hidden path="${messageThreadId}"/>
	<acme:form-textbox code="authenticated.authenticated.form.label.userName" path="identity.fullName"/>
	<acme:form-submit test="${toAdd == true}"
	 code="authenticated.authenticated.form.button.addToThread" action="/authenticated/authenticated/add-to-thread?messageThreadId=${messageThreadId}"/>
	 <acme:form-submit test="${toAdd == false}"
	 code="authenticated.authenticated.form.button.removeFromThread" action="/authenticated/authenticated/remove-from-thread?messageThreadId=${messageThreadId}"/>
	<acme:form-return code="authenticated.authenticated.form.button.return"/>
</acme:form>