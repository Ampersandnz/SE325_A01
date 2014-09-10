<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
	<head>
		<title>${staffMember.name}</title>
	</head>
	
	<body>
		Name: ${staffMember.name}<br />
		Email: ${staffMember.email}<br />
		Position: ${staffMember.position}<br />
	</body>
</html>
