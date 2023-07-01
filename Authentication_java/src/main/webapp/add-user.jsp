<!DOCTYPE html>
<html>
<head>
<title>Add User</title>

</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2 align="center">MFCWL</h2>

		</div>
	</div>
	<div id="container">
		<h3>Insert User</h3>
		<form action="UserAuthenticationServlet" method="GET">
			<input type="hidden" name="command" value="ADD" />
			<table border="1">
				<tbody>
					<tr>
						<td><label>User Name:</label></td>
						<td><input type=text name="userName" /></td>
					</tr>
					<tr>
						<td><label>Role Id:</label></td>
						<td><input type=number name="roleId" /></td>
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
						<td><label>Updated By:</label></td>
						<td><input type=text name="updatedBy" /></td>
					</tr>
					<tr>
						<td><label>Insert</label></td>
						<td><input type="submit" value="Confirm" /></td>
					</tr>

				</tbody>
			</table>
		</form>
		<div style="clear: both;"></div>
		<p>
			<a href="UserAuthenticationServlet">Previous Page</a>
	</div>
	</p>