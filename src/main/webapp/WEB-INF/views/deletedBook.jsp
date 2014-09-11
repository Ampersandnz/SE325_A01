<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
    
<html>
	<head>
		<title>${deletedBook.title} deleted</title>
	</head>
	
	<body>
		Book ${deletedBook.toString()} successfully deleted!
		
		<form action="/library" method="get">
			<button name="return" type="submit">Return</button>
		</form>
	</body>
</html>
