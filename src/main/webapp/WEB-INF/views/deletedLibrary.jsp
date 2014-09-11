<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
    
<html>
	<head>
		<title>Warning!</title>
	</head>
	
	<body>
		Library user cannot be deleted!
		
		<form action="/library" method="get">
			<button name="return" type="submit">Return</button>
		</form>
	</body>
</html>
