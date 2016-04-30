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
		<title>Nueva tarjeta</title>
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
						<h1><a href="index.html">MassMoney</a></h1>
						<nav id="nav">
							<ul>
								<li class="special">
									<a href="#menu" class="menuToggle"><span>Menu</span></a>
									<div id="menu">
										<ul>
											<li><a href="index.html">Inicio</a></li>
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
							<h2>Solicitar tarjeta MassMoney</h2>
						</header>
						<section class="wrapper style5">
							<div class="inner">
								<h4>Nueva Tarjeta</h4>
								<p>Ha accedido con su cuenta de Google. Para usar los servicios de MassMoney es necesario que solicte una tarjeta.</p>
									<form method="post" action="/nuevoCliente">
										<p></br><strong>Información de cliente</strong></p>
										<div class="row uniform">
											<div class="6u 12u$(xsmall)">
												<input type="text" name="nombre" id="nombre" value="" required placeholder="Nombre" />
											</div>
											<div class="6u 12u$(xsmall)">
												<input type="text" name="apellidos" id="apellidos" value="" required placeholder="Apellidos" />
											</div>
											<div class="6u$ 12u$(xsmall)">
												<input type="text" name="pais" id="pais" value="" required placeholder="Pais" />
											</div>
											<div class="6u 12u$(small)">
												<input type="checkbox" id="notificaciones" name="notificaciones">
												<label for="notificaciones">Enviar notificaciones al correo</label>
											</div>
											<div class="12u$">
											<p></br><strong>Añadir una tarjeta</strong></p>
											<p>Dirección de faturación</p>
											</div>
											<div class="12u 12u$(xsmall)">
												<input type="text" name="titularTarjeta" id="titularTarjeta" value="" required placeholder="Titular de la tarjeta" />
											</div>
											<div class="6u 12u$(xsmall)">
												<input type="text" name="direccion" id="direccion" value="" required placeholder="Dirección" />
											</div>
											<div class="6u 12u$(xsmall)">
												<input type="text" name="ciudad" id="ciudad" value="" required placeholder="Ciudad" />
											</div>
											<div class="6u 12u$(xsmall)">
												<input type="text" name="provincia" id="provincia" value="" required placeholder="Provincia" />
											</div>
											<div class="6u 12u$(xsmall)">
												<input type="text" name="codigoPostal" id="codigoPostal" value="" required placeholder="Código postal" />
											</div>
											<div class="6u 12u$(xsmall)">
												<input type="text" name="paisTarjeta" id="paisTarjeta" value="" required placeholder="Pais de la tarjeta" />
											</div>
											<div class="6u 12u$(xsmall)">
												<input type="text" name="divisaTarjeta" id="divisaTarjeta" value="" required placeholder="Divisa de tarjeta" />
											</div>
											<div class="12u$">
											<p>Detalles de la tarjeta</p>
											</div>
											<div class="6u 12u$(xsmall)">
												<input type="text" name="tarjeta" id="tarjeta" value="" required placeholder="Nº de tarjeta" />
											</div>
								
											<div class="6u 12u$(xsmall)">
												<input type="text" name="caducidadTarjeta" id="caducidadTarjeta" value="" required placeholder="Fecha de caducidad" />
											</div>
											<div class="6u 12u$(xsmall)">
												<input type="password" name="codigoSecreto" id="codigoSecreto" value="" maxlength="3" required placeholder="Código secreto" />
											</div>
											<div class="12u$">
												<ul class="actions">
													<li><input type="submit" value="Solicitar tarjeta" class="special" /></li>
													<li><input type="reset" value="Reset" /></li>
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