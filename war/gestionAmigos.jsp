<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<!--
	Spectral by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>Mercado de divisas</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="assets/css/main.css" />
<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
</head>
<body>

	<!-- Page Wrapper -->
	<div id="page-wrapper">

		<!-- Header -->
		<header id="header">
			<h1>
				<a href="index.jsp">MassMoney</a>
			</h1>
			<nav id="nav">
				<ul>
					<li class="special"><a href="#menu" class="menuToggle"><span>Menu</span></a>
						<div id="menu">
							<ul>
								<li><a href="index.jsp">Inicio</a></li>
								<c:if test="${not empty user}">
									<li><a href="isst_grupo07_socialex">Perfil</a></li>
									<li><a href="recargarCuenta.jsp">Ingresar dinero</a></li>
									<li><a href="solicitarCambioDivisas.jsp">Solicitar cambio de divisas</a></li>
									<li><a href="#">Gestion de amigos</a></li>
									<li><a href="mercadoDivisas">Mercado divisas</a></li>
								</c:if>
								<li><a href="<c:url value="${url}"/>"><c:out value="${urlLinktext}" /></a></li>
							</ul>
						</div></li>
				</ul>
			</nav>
		</header>

		<!-- Main -->
		<article id="main">
			<header>
				<h2>Gestión de amigos</h2>

				<p>
					En esta página puede ver su lista de amigos, sus solicitudes de
					amistad y<br> puede añadir nuevo amigos.
				</p>
			</header>
			<section class="wrapper style5">
				<div class="inner">

					<h5>Listado de amigos</h5>
					<div class="table-wrapper">
						<c:if test="${not empty amigos}">
							<table class="alt">
								<thead>
									<tr>
										<th>Correo</th>
										<th>Acción</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${amigos}" var="a">
										<tr>
											<td><c:out value="${a}" /></td>
											<td>
												<form method="post" action="/borrarAmigo">
													<div class="row uniform">
														<div class="12u$">
															<ul class="actions">
															<%--Usuario al que quiere eliminar de su lista de amigos --%>
																<input type="hidden" name="aBorrar" id="aBorrar" value=<c:out value="${a}"/> /> 
																<li><input type="submit" name="action" value="Borrar" class="special"></li>
															</ul>
														</div>
													</div>
												</form>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:if>
						<c:if test="${empty amigos}">
							<p>
								No tiene amigos añadidos. Añada alguno para poder ver sus solicitudes de cambio<br>
								y poder elegir alguna.
							</p>
						</c:if>
					</div>
					
					<h5>Solicitudes de amistad pendientes</h5>
					<div class="table-wrapper">
						<c:if test="${not empty solicitudes}">
							<table class="alt">
								<thead>
									<tr>
										<th>Correo</th>
										<th></th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${solicitudes}" var="sol">
										<tr>
											<td><c:out value="${sol}" /></td>
											<td>
												<form method="post" action="/aceptarAmigo">
													<div class="row uniform">
														<div class="12u$">
															<ul class="actions">
															<%--Usuario al que quiere aceptar y añadir de su lista de amigos --%>
																<input type="hidden" name="aceptarA" id="aceptarA" value=<c:out value="${sol}"/> /> 
																<li><input type="submit" name="action" value="Aceptar" class="special"></li>
															</ul>
														</div>
													</div>
												</form>
											</td>
											<td>
												<form method="post" action="/rechazarAmigo">
													<div class="row uniform">
														<div class="12u$">
															<ul class="actions">
															<%--Usuario al que quiere rechazar y no añadir de su lista de amigos --%>
																<input type="hidden" name="rechazarA" id="rechazarA" value=<c:out value="${sol}"/> /> 
																<li><input type="submit" name="action" value="Rechazar" class="special"></li>
															</ul>
														</div>
													</div>
												</form>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:if>
						<c:if test="${empty solicitudes}">
							<p>
								No tiene solicitudes pendientes.
							</p>
						</c:if>
					</div>
					
					<h5>Añadir amigos</h5>
					<form method="post" action="/nuevoAmigo">
						<%--Usuario a añadir como amigo --%>
						<div class="row uniform">
							<div class="6u 12u$(xsmall)">
								<input type="text" name="correo" id="correo" value="" required
									placeholder="Correo" />
							</div>
							<div class="12u$">
								<ul class="actions">
									<li><input type="submit" name="action" value="Enviar" class="special"></li>
								</ul>
							</div>
						</div>
					</form>
				</div>
			</section>
		</article>

		<!-- Footer -->
		<footer id="footer">
			<ul class="icons">
				<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
				<li><a href="#" class="icon fa-facebook"><span
						class="label">Facebook</span></a></li>
				<li><a href="#" class="icon fa-instagram"><span
						class="label">Instagram</span></a></li>
				<li><a href="#" class="icon fa-dribbble"><span
						class="label">Dribbble</span></a></li>
				<li><a href="#" class="icon fa-envelope-o"><span
						class="label">Email</span></a></li>
			</ul>
			<ul class="copyright">
				<li>&copy; MassMoney 2016</li>
				<li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
			</ul>
		</footer>

	</div>

	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.scrollex.min.js"></script>
	<script src="assets/js/jquery.scrolly.min.js"></script>
	<script src="assets/js/skel.min.js"></script>
	<script src="assets/js/util.js"></script>
	<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
	<script src="assets/js/main.js"></script>

</body>
</html>