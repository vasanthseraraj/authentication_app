<!DOCTYPE html>
<html>
<head>
<title>Update Student</title>
<!-- <link type="text/css" rel="stylesheet" href="css/style.css"> -->
<!-- <link type="text/css" rel="stylesheet" href="css/add-student-style.css"> -->
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2 align="center">MFCWL</h2>

		</div>
	</div>
	<div id="container">
		<h3>Edit User</h3>
		<form action="UserAuthenticationServlet" method="GET">
			<input type="hidden" name="command" value="EDIT" /> <input
				type="hidden" name="userId" value="${THE_USER.userId}" />
			<table>
				<tbody>
					<tr>
						<td><label>User Name:</label></td>
						<td><input type=text name="userName"
							value="${THE_USER.userName}" /></td>
					</tr>
					<tr>
						<td><label>Role Id:</label></td>
						<td><input type=number name="roleId"
							value="${THE_USER.roleId}" /></td>
					</tr>
					<tr>
						<td><label>Role Name:</label></td>
						<td><select name="roleName">
								<option>ADMIN</option>
								<option>DEVELOPER</option>
								<option>USER</option>

						</select></td>
					</tr>
					<tr>
						<td><label>Updated BY:</label></td>
						<td><input type=text name="updatedBy"
							value="${THE_USER.updatedBy}" /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="Submit" value="Save" /></td>
					</tr>

				</tbody>
			</table>
		</form>
		<p>
		<div style="clear: both;"></div>

		<a href="UserAuthenticationServlet">Previous Page</a>
	</div>
	</p>