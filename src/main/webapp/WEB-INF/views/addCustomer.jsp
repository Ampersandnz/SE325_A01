<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
	<head>
		<title>Adding customer</title>
	</head>
	
	<body>
		<form action="/library/customer/added" method=post>
			<table>
				<tr>
					<td><b>Name: </b></td>
					<td><input type="text" name="name" value=""><br></td>
				</tr>
				<tr>
					<td><b>Email address: </b></td>
					<td><input type="text" name="email" value=""><br></td>
				</tr>
				<tr>
					<td><b>Address: </b></td>
					<td><input type="text" name="address" value=""></td>
				</tr>
			</table>			
			<a href="/library">
				<input type="button" value="Cancel" />
			</a>
			<input type="submit" value="Save">
		</form>
	</body>
</html>
