<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
	
<html>
	<head>
		<title>SE325_A01</title>
	</head>
	
	<body>
		<h1>
			Welcome to the library management system!
		</h1>
		
		<h2>Books:</h2>
		<table>
		   	<tr>
		        <td>ISBN:</td>
		        <td>|</td>
		        <td>Title:</td> 
		        <td>|</td>
		        <td>Current owner:</td>  
		    </tr>
			<c:forEach items="${allBooks}" var="book">
			    <tr>
			        <td><c:out value="${book.isbn}"/></td>
			        <td>|</td>
			        <td><a href="book/${book.id}"><c:out value="${book.title}"/></a></td> 
			        <td>|</td>
			        <td><c:out value="${book.owner.name}"/></td>  
			    </tr>
			</c:forEach>
		</table>
		
		<form action="/library/book/add">
    		<input type="submit" value="Add new book">
		</form>
		
		<h2>Staff members:</h2>
		<table>
		   	<tr>
		        <td>Name:</td>
		        <td>|</td>
		        <td>Email address:</td> 
		        <td>|</td>
		        <td>Position:</td>  
		    </tr>
			<c:forEach items="${allStaffMembers}" var="staffMember">
			    <tr>
			        <td><a href="staffmember/${staffMember.id}"><c:out value="${staffMember.name}"/></a></td>
			        <td>|</td>
			        <td><c:out value="${staffMember.email}"/></td> 
			        <td>|</td>
			        <td><c:out value="${staffMember.position}"/></td>  
			    </tr>
			</c:forEach>
		</table>
		
		<h2>Customers:</h2>
		<table>
		   	<tr>
		        <td>Name:</td>
		        <td>|</td>
		        <td>Email address:</td> 
		        <td>|</td>
		        <td>Address:</td>  
		    </tr>
			<c:forEach items="${allCustomers}" var="customer">
			    <tr>
			        <td><a href="customer/${customer.id}"><c:out value="${customer.name}"/></a></td>
			        <td>|</td>
			        <td><c:out value="${customer.email}"/></td> 
			        <td>|</td>
			        <td><c:out value="${customer.address}"/></td>  
			    </tr>
			</c:forEach>
		</table>
		
	</body>
</html>
