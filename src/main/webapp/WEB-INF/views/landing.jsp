<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
	<meta charset="utf-8">
	<title>${restaurant.name} - ${restaurant.slogan}</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="${restaurant.name} - ${restaurant.slogan}">
	<meta name="author" content="icardapio">

	<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="resources/css/main.css" rel="stylesheet">
    <link href="resources/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">

	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
		<script src="resources/js/html5shiv.js"></script>
	<![endif]-->
</head>

<body>

<div class="container">

	<div class="jumbotron">
		<h1>${restaurant.name}</h1>
		<p class="lead">${restaurant.slogan}</p>
		<address>
			${restaurant.address}<br>
			${restaurant.city}<br>
			<abbr title="Telefone">Tel:</abbr> ${restaurant.phone}
		</address>        
	</div>

	<c:if test="${message != null}">	
		<div class="alert alert-success">
	  		<button type="button" class="close" data-dismiss="alert">&times;</button>
			${message}
		</div>	
	</c:if>
	
	<div>
		<f:form modelAttribute="tenant" method="post" action="addRestaurant">
			<fieldset>
				<legend>Cadastre seu restaurante e seu cardápio agora mesmo!</legend>
				
				<f:label path="name">Nome</f:label>					
				<f:input path="name" type="text" class="input-block-level" placeholder="Ex: Dona Maria Pizzaria" autofocus="autofocus" />
				
				<f:label path="slogan">Slogan</f:label>					
				<f:input path="slogan" type="text" class="input-block-level" placeholder="Ex: A melhor pizza do Ipiranga" />
				
				<f:label path="subdomain">Subdominio</f:label>					
				<f:input path="subdomain" type="text" class="input-block-level" placeholder="Ex: donamaria" />
	
				<f:label path="phone">Telefone</f:label>					
				<f:input path="phone" type="text" class="input-block-level" placeholder="Ex: (11) 3115-2345" />
				
				<f:label path="address">Address</f:label>					
				<f:input path="address" type="text" class="input-block-level" placeholder="Ex: Av Dr Gentil de Moura, 850" />
				
				<f:label path="city">Cidade</f:label>					
				<f:input path="city" type="text" class="input-block-level" placeholder="Ex: São Paulo/SP" />
			</fieldset>
			
			<f:button class="btn btn-primary pull-right">Criar</f:button>
		</f:form>
	</div>
          
	<hr>

	<div class="footer">
		<p>&copy; iCardapio 2013</p>
	</div>
	
</div>

<!-- Placed at the end of the document so the pages load faster -->
<script src="resources/js/jquery.min.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/js/main.js"></script>

</body>
</html>