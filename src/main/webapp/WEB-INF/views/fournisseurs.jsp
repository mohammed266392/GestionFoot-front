<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fournisseur</title>
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
		<h1>liste des fournisseurs</h1>
		<table class="table">
			<thead>
				<th>id</th>
				<th>nom</th>
				<th>prenom</th>
				<th>société</th>
				<th></th>
			</thead>
			<tbody>
				<c:forEach var="p" items="${fournisseurs}">
					<tr>
						<td>${p.id}</td>
						<td>${p.nom}</td>
						<td>${p.prenom}</td>
						<td>${p.societe}</td>
						<td><a href="fournisseurs/delete?id=${p.id}}"
							class="btn btn-outline-danger">supprimer</a></td>
						<td><a href="fournisseurs/edit?id=${p.id}"
							class="btn btn-outline-danger">modifier</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<a href="fournisseurs/add"> nouveau produit </a>
		
		<!-- nouveau produit
		
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
		</form> -->
	</div>
</body>
</html>