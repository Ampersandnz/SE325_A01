<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
	<head>
		<title>Adding staff member</title>
	</head>
	
	<body>
		<form action="/library/staffMember/added" method=post>
			<table>
				<tr>
					<td><b>Name: </b></td>
					<td><input type="text" name="name" value=""><br></td>
				</tr>
				<tr>
					<td><b>Email: </b></td>
					<td><input type="text" name="email" value=""><br></td>
				</tr>
				<tr>
					<td><b>Position: </b></td>
					<td><input type="text" name="position" value=""></td>
				</tr>
			</table>
				
			<a href="/library">
				<input type="button" value="Cancel" />
			</a>
			
			<input type="submit" value="Save">
		</form>
	</body>
</html>
