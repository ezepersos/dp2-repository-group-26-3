<%--
- form.jsp
-
- Copyright (C) 2012-2021 Rafael Corchuelo.
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
	<acme:form-textbox code="anonymous.shout.form.label.author" path="author"/>
	<acme:form-textarea code="anonymous.shout.form.label.text" path="text"/>
	<acme:form-textbox code="anonymous.shout.form.label.info" path="info"/>
	
		<acme:form-textbox code="anonymous.shout.form.label.informationSheet.identifier" path="informationSheet.identifier" placeholder="XXXXXX:ddmmyy"/>
	<acme:form-moment code="anonymous.shout.form.label.informationSheet.deadline"	path="informationSheet.deadline" placeholder="YYYY/MM/DD"/>
	<acme:form-textbox code="anonymous.shout.form.label.informationSheet.amount"	path="informationSheet.budget" placeholder="XXX.XX"/>
	<acme:form-select code="anonymous.shout.form.label.informationSheet.currency" path="informationSheet.currencyType">
		<acme:form-option code="anonymous.shout.form.label.informationSheet.currency.EUR" value="EUR" />
		<acme:form-option code="anonymous.shout.form.label.informationSheet.currency.DOLAR"	value="DOLAR" />
	</acme:form-select>
	<acme:form-checkbox code="anonymous.shout.form.label.informationSheet.important" path="informationSheet.important"/>
	
	
	
	
	<acme:form-submit code="anonymous.shout.form.button.create" action="/anonymous/shout/create"/>
  	<acme:form-return code="anonymous.shout.form.button.return"/>
</acme:form>
