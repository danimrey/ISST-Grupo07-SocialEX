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
		<title>Recargar cuenta</title>
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
												<li><a href="#">Ingresar dinero</a></li>
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

				<!-- Main -->
					<article id="main">
						<header>
							<h2>Recarga de saldo</h2>
							<p>En esta página podrá ingresar dinero en su cuenta </p>
						</header>
						<section class="wrapper style5">
							<div class="inner">
								<h4>Recarga de saldo</h4>
								<p>Ingrese dinero en su divisa predeterminada. 
								Para otras divisas solocite cambio de divisas.</p>
									<form method="post" action="/recargaCuenta">
										<div class="row uniform">
											<div class="6u 12u$(xsmall)">
												<input type="text" name="cantidadRecarga" id="cantidadRecarga" value="" placeholder="Cantidad a recargar" min="1" />
												<input type="hidden" name="localTime" id="localTime" value=""/>
												<input type="hidden" name="numeroCuenta" id="numeroCuenta" value=<c:out value="${cuenta.id}"/> />
												
                                    				<script type="text/javascript">
                                      					 var now = new Date(); 
                                      					 var weekday = new Array(7);
                                      					 weekday[0]=  "Domingo";
                                      					 weekday[1] = "Lunes";
                                      					 weekday[2] = "Martes";
                                      					 weekday[3] = "Miercoles";
                                      					 weekday[4] = "Jueves";
                                      					 weekday[5] = "Viernes";
                                      					 weekday[6] = "Sábado";
                                      					 
                                      					 var nowFormatted = weekday[now.getUTCDay()]+"  "+now.getDate() + "/" +(now.getMonth() + 1) + "/" + now.getFullYear() +"  "+ now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
                                       					 document.getElementById("localTime").value = nowFormatted;
                                   					</script>
											</div>
											<div class="6u">
												<div class="select-wrapper">
						
													<select name="divisas" id="divisa">
													<c:if test="${cuenta.divisaPredeterminada=='EUR'}">
														<option value="EUR">Euro € (EUR)</option>
													</c:if>
													<c:if test="${cuenta.divisaPredeterminada=='USD'}">
														<option value="USD">Dólar estadounidense $ (USD)</option>
													</c:if>
													<c:if test="${cuenta.divisaPredeterminada=='GBP'}">
														<option value="GBP">Libra esterlina £ (GBP)</option>
													</c:if>
													<c:if test="${cuenta.divisaPredeterminada=='JPY'}">
														<option value="JPY">Yen japonés ¥ (JPY)</option>
													</c:if>
														<%-- 
														<option value="USD">Dólar estadounidense $ (USD)</option>
														<option value="GBP">Libra esterlina £ (GBP)</option>
														<option value="JPY">Yen japonés ¥ (JPY)</option>
														--%>
													</select>
												</div>
											</div>
											<div class="12u">
												<p>Seleccione tarjeta:</p>
											</div>
											<div class="6u">
												<div class="select-wrapper">
												<c:if test="${not empty tarjetas}">
													<select name="tarjetas" id="tarjeta">
														<c:forEach items="${tarjetas}" var="tarjetai">
															<option value=<c:out value="${tarjetai.id}"/>><c:out value="${tarjetai.numeroTarjeta}"/></option>
														</c:forEach>
													</select>
												</c:if>
												</div>
											</div>
											<div class="12u$">
												<ul class="actions">
													<li><input type="submit" value="Recargar" class="special" /></li>
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