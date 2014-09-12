<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
    
<html>
	<head>
		<title>${addedStaffMember.name} added</title>
	</head>
	
	<body>
		Staff member ${addedStaffMember.toString()} was added to the library system!
		
		<form action="/library" method="get">
			<button name="return" type="submit">Return</button>
		</form>
	</body>
</html>
