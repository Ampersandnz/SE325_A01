<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
	<head>
		<title>Editing ${customer.name}</title>
	</head>
	
	<body>
		<form action="/library/customer/${customer.id}" method=post>
			<table>
				<tr>
					<td><b>Name: </b></td>
					<td><input type="text" name="name" value="${customer.name}"><br></td>
				</tr>
				<tr>
					<td><b>Email address: </b></td>
					<td><input type="text" name="email" value="${customer.email}"><br></td>
				</tr>
				<tr>
					<td><b>Address: </b></td>
					<td><input type="text" name="address" value="${customer.address}"></td>
				</tr>
			</table>
			<a href="/library/customer/${customer.id}">
				<input type="button" value="Cancel" />
			</a>
			<input type="submit" value="Save">
		</form>
	</body>
</html>
