<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="add" method="post">
		<table class="table">
			<thead>
				<th>nom</th>
				<th>prenom</th>
				<th>société</th>
			</thead>
			<tbody>
					<tr>
						<td><input type="text" name="nom" placeholder="fournisseur.id"/></td>
						<td><input type="text" name="prenom" placeholder=""/></td>
						<td><input type="text" name="compagny" placeholder=""/></td>
						
						
					</tr>
			</tbody>
		</table>
		<input type="submit" value="envoyer"/>
		</form>
		
		<div class="container">
		<h1>edition fournisseur</h1>
		<form:form action="add" method="post"
			modelAttribute="fournisseur">
			<div class="form-group">
				<form:label path="">id:</form:label>
				<form:input path="id" cssClass="form-control" readonly="true"
					placeholder="generation automatique" />
			</div>
			<div class="form-group">
				<form:label path="prenom">prenom:</form:label>
				<form:input path="prenom" cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="nom">nom:</form:label>
				<form:input path="nom" cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="company">company:</form:label>
				<form:input path="company" cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="adresse.numero">numero:</form:label>
				<form:input path="adresse.numero" cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="adresse.voie">rue:</form:label>
				<form:input path="adresse.voie" cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="adresse.cp">code postal:</form:label>
				<form:input path="adresse.cp" cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="adresse.ville">ville:</form:label>
				<form:input path="adresse.ville" cssClass="form-control" />
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-outline-success">enregistrer</button>
				<a href="../fournisseurs" class="btn btn-outline-warning">annuler</a>
			</div>
		</form:form>
	</div>


</body>
</html>