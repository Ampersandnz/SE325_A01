<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
	<head>
		<title>Editing ${staffMember.name}</title>
	</head>
	
	<body>
		<form action="/library/staffMember/${staffMember.id}" method=post>
			<table>
				<tr>
					<td><b>Name: </b></td>
					<td><input type="text" name="name" value="${staffMember.name}"><br></td>
				</tr>
				<tr>
					<td><b>Email address: </b></td>
					<td><input type="text" name="email" value="${staffMember.email}"><br></td>
				</tr>
				<tr>
					<td><b>Address: </b></td>
					<td><input type="text" name="position" value="${staffMember.position}"></td>
				</tr>
			</table>
			<a href="/library/staffMember/${staffMember.id}">
				<input type="button" value="Cancel" />
			</a>
			<input type="submit" value="Save">
		</form>
	</body>
</html>
