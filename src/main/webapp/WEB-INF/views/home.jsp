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

	<sec:authorize access="isAuthenticated() and hasRole('admin')">
		<div class="row-fluid actions">
			<div class="span12">
				<div class="btn-group pull-right">
				  <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
				    Ação
				    <span class="caret"></span>
				  </a>
				  <ul class="dropdown-menu">
				    <li>
				    	<a href="#addProductModal" role="button" data-toggle="modal">
				    		<i class="icon-plus"></i> Adicionar Produto
				    	</a>
				    </li>
				    <li class="divider"></li>
				    <li>
				    	<a href="j_spring_security_logout">
				    		<i class="icon-off"></i> Sair
				    	</a>
				    </li>
				  </ul>
				</div>
			</div>
		</div>
	</sec:authorize>
	
	<sec:authorize access="not isAuthenticated()">
		<a href="spring_security_login" class="btn brn-primary pull-right">Entrar</a>
	</sec:authorize>
	
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

	<div class="menu">
		<c:if test="${not empty categories}">
			<c:forEach items="${categories}" var="category">
				<div class="row-fluid category">
					<div class="span12">
						<header>
							<h2>${category.name}</h2>
							<hr>
						</header>

						<c:forEach items="${category.products}" var="product">
							<div class="row-fluid">
								<div class="span12 product">
									<header>
										<h3>${product.name}</h3>
										<span class="badge badge-success pull-right">
											R$ ${product.price}
											<a href="removeProduct?categoryId=${category.id}&productId=${product.id}" class="removeProduct">
												<i class="icon-trash icon-white"></i>
											</a>
										</span>
									</header>
									
									<p>${product.description}</p>
								</div>
							</div>
						</c:forEach>
						
						<c:if test="${empty category.products}">
							<div class="alert">
								<strong>Atenção:</strong> Nenhuma produto nessa categoria.
							</div>
						</c:if>
					</div>
				</div>
			</c:forEach>			
		</c:if>
		<c:if test="${empty categories}">
			<div class="alert">
				<strong>Atenção:</strong> Nenhuma categoria de produtos cadastada.
			</div>
		</c:if>
	</div>
          
	<hr>

	<div class="footer">
		<p>&copy; iCardapio 2013</p>
	</div>
	
	<div id="addProductModal" class="modal hide fade" tabIndex="-1" role="dialog" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h3>Adicionar Produto</h3>
		</div>
		<div class="modal-body">
			<f:form modelAttribute="product" method="post" action="addProduct">
				<f:label path="category.id">Categoria</f:label>
				<f:select path="category.id" class="input-block-level" autofocus="autofocus">
					<f:option value="-1">Selecione</f:option>
					<f:options items="${categories}" itemLabel="name" itemValue="id" />
				</f:select>
			
				<f:label path="name">Nome</f:label>					
				<f:input path="name" type="text" class="input-block-level" placeholder="Ex: Marguerita" />
				
				<f:label path="description">Descrição</f:label>
				<f:textarea path="description" rows="5" class="input-block-level" placeholder="Ex: Marguerita" ></f:textarea>
				
				<f:label path="price">Preço</f:label>					
				<f:input path="price" type="text" class="input-block-level" placeholder="Ex: 25,00" />
			</f:form>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn" data-dismiss="modal" aria-hidden="true">Cancelar</a>
			<a href="#" class="btn btn-primary addProduct">Adicionar</a>
		</div>
	</div>	

</div>

<!-- Placed at the end of the document so the pages load faster -->
<script src="resources/js/jquery.min.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/js/main.js"></script>

</body>
</html>