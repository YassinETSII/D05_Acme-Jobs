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

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.companyRecord.form.label.company" path="company"/>
	<acme:form-textbox code="authenticated.companyRecord.form.label.sector" path="sector"/>
	<acme:form-textbox code="authenticated.companyRecord.form.label.ceo" path="CEO"/>
	<acme:form-textarea code="authenticated.companyRecord.form.label.description" path="description"/>
	<acme:form-url code="authenticated.companyRecord.form.label.web" path="web"/>
	<acme:form-textbox code="authenticated.companyRecord.form.label.phone" path="phone"/>
	<acme:form-textbox code="authenticated.companyRecord.form.label.email" path="email"/>
	<acme:form-integer code="authenticated.companyRecord.form.label.stars" path="stars" placeholder="3"/>
	
	<acme:form-return code="authenticated.companyRecord.form.button.return"/>
</acme:form>