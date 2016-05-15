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
		<title>MassMoney</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="assets/css/main.css" />
		<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
		<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
	</head>
	<body class="landing">

		<!-- Page Wrapper -->
			<div id="page-wrapper">

				<!-- Header -->
					<header id="header" class="alt">
						<h1><a href="#">MassMoney</a></h1>
						<nav id="nav">
							<ul>
								<li class="special">
									<a href="#menu" class="menuToggle"><span>Menu</span></a>
									<div id="menu">
										<ul>
											<li><a href="isst_grupo07_socialex">Inicio</a></li>
											<c:if test="${not empty user}">
												<li><a href="isst_grupo07_socialex">Perfil</a></li>
												<li><a href="recargarCuenta.jsp">Ingresar dinero</a></li>
												<li><a href="solicitarCambioDivisas.jsp">Solicitar cambio de divisas</a></li>
           										<li><a href="gestionAmigos">Gestion de amigos</a></li>
           										<li><a href="mercadoDivisas">Mercado divisas</a></li>
           									</c:if>
											<li><a href= "<c:url value="${url}"/>"><c:out value="${urlLinktext}"/></a></li>			
										</ul>
									</div>
								</li>
							</ul>
						</nav>
					</header>

				<!-- Banner -->
					<section id="banner">
						<div class="inner">
							<h2>MassMoney</h2>
							<p>Tu servicio de cambio de divisas<br />
							</p>
							<ul class="actions">
								<li><a href="<c:url value="${url}"/>" class="button special">
								<c:if test="${not empty user}">
									Salir
								</c:if>
								<c:if test="${empty user}">
									Accede con tu cuenta de Google
								</c:if>
								</a></li>
							</ul>
						</div>
						<a href="#one" class="more scrolly">Descrubre</a>
					</section>

				<!-- One -->
					<section id="one" class="wrapper style1 special">
						<div class="inner">
							<header class="major">
								<h2>Presentación</h2>
								<p>MassMoney ha nacido pensando en la gente que viaja con frecuencia entre paises con diferente moneda o que realiza compras en mercados con moneda distinta la local.
								Si quiere evitar las elevadas comisiones que conlleva el cambio de divisas esta es su aplicación.</p>
							</header>
							<ul class="icons major">
								<li><span class="icon fa-diamond major style1"><span class="label">Lorem</span></span></li>
								<li><span class="icon fa-heart-o major style2"><span class="label">Ipsum</span></span></li>
								<li><span class="icon fa-code major style3"><span class="label">Dolor</span></span></li>
							</ul>
						</div>
					</section>

				<!-- Two -->
					<section id="two" class="wrapper alt style2">
						<section class="spotlight">
							<div class="image"><img src="images/ahorro.jpg" alt="" /></div><div class="content">
								<h2>Ahorra comisiones<br />
								</h2>
								<p>Olvidese de las elevadas comisiones que suele llevar el cambio de divisas. En MassMoney se le aplicará una comisión reducida que hará rentable el cambio de divisas.</p>
							</div>
						</section>
						<section class="spotlight">
							<div class="image"><img src="images/amigos.jpg" alt="" /></div><div class="content">
								<h2>Solicita cambio de divisas a amigos<br />
								</h2>
								<p>Ahorre aún más dinero cambiando divisas con amigos o conocidos dentro de MassMoney.</p>
							</div>
						</section>
						<section class="spotlight">
							<div class="image"><img src="images/divisas.jpg" alt="" /></div><div class="content">
								<h2>Administra diferentes divisas desde una única tarjeta<br />
								</h2>
								<p>Con el registro en MassMoney se le porpociona un tarjeta multidivisa. Podrá pagar direntamente con la divisa local, evitando los costes del cambio de divisas convencional.</p>
							</div>
						</section>
					</section>

				<!-- Three -->
					<section id="three" class="wrapper style3 special">
						<div class="inner">
							<header class="major">
								<h2>Ventajas al usar MassMoney</h2>
								<p>MassMoney es una aplicación web con muchas ventajas.</p>
							</header>
							<ul class="features">
							<li class="icon fa-heart-o">
									<h3>Ahorra dinero</h3>
									<p>MassMoney aplica comisiones reducidas durante el cambio de divisas. </br>Ahorrarás mucho dinero.</p>
								</li>
								<li class="icon fa-paper-plane-o">
									<h3>Red social</h3>
									<p>Añade amigos para realizar cambio de divisas con comisiones más bajas.</p>
								</li>
								<li class="icon fa-laptop">
									<h3>Portabilidad</h3>
									<p>MassMoney es una aplicación web con diseño adaptativo. Se adapta a tu dispositivo automaticamente.</p>
								</li>
								<li class="icon fa-headphones">
									<h3>Acceso seguro</h3>
									<p>Regístrate facilmente utilizando tu cuenta de Google.</p>
								</li>
								
								<li class="icon fa-flag-o">
									<h3>Nuevas divisas</h3>
									<p>Inicialmente se ofrecen cuatro divisas: euro, dolar estadounidense, libra esterlina y yen japonés.
									</br>El sistema está diseñado para incorporar nuevas divisas en el futuro.</p>
								</li>
								<li class="icon fa-code">
									<h3>Tecnología</h3>
									<p>Despliegue sobre Google App Engine. </br>Uso de tecnologías HTLM5, CC3, JavaScript y Java EE.</p>
								</li>
							</ul>
						</div>
					</section>

				<!-- CTA -->
					<section id="cta" class="wrapper style4">
						<div class="inner">
							<header>
								<h2>Accede a MassMoney con tu cuenta de Google</h2>
								<p>Sólo necesitas tu cuenta de Google para acceder a los servicios de MassMoney y solictar tu tarjeta multidivisa</p>
							</header>
							<ul class="actions vertical">
								<li><a href="<c:url value="${url}"/>" class="button fit special">
								<c:if test="${not empty user}">
									Salir
								</c:if>
								<c:if test="${empty user}">
									Iniciar sesión
								</c:if>
								</a></li>
								<li><a href="#" class="button fit">Sube</a></li>
							</ul>
						</div>
					</section>

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

