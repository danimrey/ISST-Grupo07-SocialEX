<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML>
<!--
	Spectral by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>Editar perfil</title>
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
						<h1><a href="index.jsp">MassMoney</a></h1>
						<nav id="nav">
							<ul>
								<li class="special">
									<a href="#menu" class="menuToggle"><span>Menu</span></a>
									<div id="menu">
										<ul>
											<li><a href="index.jsp">Inicio</a></li>
											<c:if test="${not empty user}">
												<li><a href="isst_grupo07_socialex">Perfil</a></li>
												<li><a href="recargarCuenta.jsp">Ingresar dinero</a></li>
												<li><a href="#">Solicitar cambio de divisas</a></li>
           									</c:if>
											<li><a href= "<c:url value="${url}"/>"><c:out value="${urlLinktext}"/></a></li>
										</ul>
									</div>
								</li>
							</ul>
						</nav>
					</header>

				<!-- Main -->
					<article id="main">
						<header>
							<h2>Editar perfil</h2>
							<p>Cambie sus datos de usuario</p>
						</header>
						<section class="wrapper style5">
							<div class="inner">
								<h4>Introduce los datos que quieres cambiar</h4>
									<form method="post" action="/editarPerfil">
										<div class="row uniform">
											<div class="6u 12u$(xsmall)">
												<input type="text" name="nombreNuevo" id="nombreNuevo" value="" placeholder="Nombre y apellidos"/>
											</div>
											<div class="6u 12u$(xsmall)">
												<input type="text" name="paisNuevo" id="paisNuevo" value="" placeholder="País" />
											</div>
											<div class="6u 12u$(small)">
												<input type="checkbox" id="notificaciones" name="notificaciones">
												<label for="notificaciones">Enviar notificaciones al correo</label>
											</div>
											<div class="12u$">
												<ul class="actions">
													<li><input type="submit" value="Guardar Cambios" class="special" /></li>
													<li><input type="reset" value="Reset" /></li>
													<li><a href="isst_grupo07_socialex" class="button">Cancelar</a></li>
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
							<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
							<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
							<li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
							<li><a href="#" class="icon fa-envelope-o"><span class="label">Email</span></a></li>
						</ul>
						<ul class="copyright">
							<li>&copy; MassMoney 2016</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
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