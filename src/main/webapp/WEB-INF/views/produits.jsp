<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
	crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<h1>liste des produits</h1>
		<table class="table">
			<thead>
				<th>id</th>
				<th>libellé</th>
				<th>prix</th>
				<th></th>
			</thead>
			<tbody>
				<c:forEach var="p" items="${produits}">
					<tr>
						<td>${p.id}</td>
						<td>${p.libelle}</td>
						<td>${p.prix}</td>
						<td><a href="produits/delete?id=${p.id}"
							class="btn btn-outline-danger">supprimer</a></td>
						<td>
							modifier
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		nouveau produit
		
		<form action="produits/add" method="post">
		<table class="table">
			<thead>
				<th>libellé</th>
				<th>prix</th>
				<th>Fournisseur</th>
			</thead>
			<tbody>
					<tr>
						<td><input type="text" name="libelle"/></td>
						<td><input type="text" name="prix"/></td>
						<td><input type="text" name="vendeur" value="NULL"/></td>
						<td><input type="submit" value="ajouter" /></td>
						
					</tr>
			</tbody>
		</table>
		</form>
	</div>
	
</body>
</html>
