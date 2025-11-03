<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 

<html> 
<head> 
	<style> 
		.error {color:red} 
	</style> 
</head> 
<body> 
	<h1>Employee Portal</h1>
	<form:form action="welcome" modelAttribute="employee"> 
		<label>First name:</label>
		<form:input path="firstName" />
		<form:errors path="firstName" cssClass="error" /><br><br> 
		
		<label>Last name:</label> 
		<form:input path="lastName" /> 
		<form:errors path="lastName" cssClass="error" /><br><br> 
		
		<label>Emp No:</label>
		<form:input path="empId" /> 
		<form:errors path="empId" cssClass="error" /><br><br> 
		
		<label>Address:</label> 
		<form:textarea path="address" /> 
		<form:errors path="address" cssClass="error" /><br><br> 
	
		<input type="submit" value="Submit" /> 
	</form:form> 
</body> 
</html>
