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
	<acme:form-textbox code="sponsor.commercialBanner.form.label.picture" path="picture"/>
	<acme:form-textbox code="sponsor.commercialBanner.form.label.slogan" path="slogan"/>
	<acme:form-url code="sponsor.commercialBanner.form.label.url" path="URL"/>
	<acme:form-textbox code="sponsor.commercialBanner.form.label.holder" path="holder"/>
	<acme:form-integer code="sponsor.commercialBanner.form.label.expirationMonth" path="expirationMonth"/>
	<acme:form-integer code="sponsor.commercialBanner.form.label.expirationYear" path="expirationYear"/>
	<acme:form-textbox code="sponsor.commercialBanner.form.label.creditCardNumber" path="creditCardNumber"/>	
	<acme:form-textbox code="sponsor.commercialBanner.form.label.brand" path="brand"/>	
	<acme:form-textbox code="sponsor.commercialBanner.form.label.CVV" path="CVV"/>	
		
	<acme:form-return code="sponsor.commercialBanner.form.button.return"/>
</acme:form>