<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
	<head>
		<title>${customer.name}</title>
	</head>
	
	<body>
		Name: ${customer.name}<br />
		Email: ${customer.email}<br />
		Address: ${customer.address}<br />
	</body>
</html>
