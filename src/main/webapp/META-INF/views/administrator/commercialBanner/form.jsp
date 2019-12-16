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
	<acme:form-url code="administrator.commercialBanner.form.label.picture" path="picture"/>
	<acme:form-textbox code="administrator.commercialBanner.form.label.slogan" path="slogan"/>
	<acme:form-url code="administrator.commercialBanner.form.label.url" path="URL"/>
	<acme:form-textbox code="administrator.commercialBanner.form.label.holder" path="creditCard.holder"/>
	<acme:form-integer code="administrator.commercialBanner.form.label.expirationMonth" path="creditCard.expirationMonth"/>
	<acme:form-integer code="administrator.commercialBanner.form.label.expirationYear" path="creditCard.expirationYear"/>
	<acme:form-textbox code="administrator.commercialBanner.form.label.creditCardNumber" path="creditCard.creditCardNumber"/>
	<acme:form-textbox code="administrator.commercialBanner.form.label.brand" path="creditCard.brand"/>
	<acme:form-textbox code="administrator.commercialBanner.form.label.CVV" path="creditCard.CVV"/>
	
	<acme:form-return code="administrator.commercialBanner.form.button.return"/>
</acme:form>
