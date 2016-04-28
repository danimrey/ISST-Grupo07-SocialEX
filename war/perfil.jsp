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
		<title>Perfil</title>
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
						
						<c:if test="${user!=null}">
							<h2><c:out value="${user}" /></h2>
          					</c:if>
          					<p>En esta página puede gestionar su perfil, ver sus transacciones y saldo de divisas</p>
          					</header>
					<section class="wrapper style5">
						<div class="inner">
							<h5>Información de perfil</h5>
							<p><b>Nombre: </b><c:out value="${cliente}" /></br>
							<b>Correo (ID): </b><c:out value="${user}" /></br>
							<b>País: </b><c:out value="${pais}" /></br>
							<b>Nº Tarjeta MassMoney : </b><c:out value="${tarjeta}" /></br>
							<b>Notificaciones: </b><c:out value="${notificaciones}" /></br></p>
							<p><a href="editarPerfil.jsp">Editar pefil</a></p>
							<h5>Movimientos de la cuenta</h5>
								<div class="table-wrapper">
								<c:if test="${not empty transacciones}">
									<table class="alt">
										<thead>
											<tr>
												<th>Fecha</th>
												<th>Importe</th>
												<th>Concepto</th>
												<th>Divisa</th>
												<th>Tipo</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${transacciones}" var="tran">
												<tr>
													<td><c:out value="${tran.fechaCliente}" /></td>
													<td><c:out value="${tran.importe}" /></td>
													<td><c:out value="${tran.concepto}" /></td>
													<td><c:out value="${tran.divisa}" /></td>
													<td><c:out value="${tran.tipo}" /></td>
														
												</tr>
											</c:forEach> 	
										</tbody>
									</table>
									</c:if>
									<c:if test="${ empty transacciones}">
									<p>Aquí aparecerán sus transacciones.</p>
									</c:if>
								</div>
						
								<h5>Saldo de divisas</h5>
								<div class="table-wrapper">
									<table class="alt">
										<thead>
											<tr>
												<th>Divisa</th>
												<th>Saldo</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Euros</td>
												<td><c:out value="${saldoEUR}" /></td>
												<td><a href="recargarCuenta.jsp">Aumentar saldo</a></td>
											</tr>
											<tr>
												<td>Dólares estadounidenses</td>
												<td><c:out value="${saldoDOL}" /></td>
												<td><a href="recargarCuenta.jsp">Aumentar saldo</a></td>
											</tr>
											<tr>
												<td>Libras esterlinas</td>
												<td><c:out value="${saldoGBP}" /></td>
												<td><a href="recargarCuenta.jsp">Aumentar saldo</a></td>
											</tr>
											<tr>
												<td>Yen japonés</td>
												<td><c:out value="${saldoJPY}" /></td>
													<td><a href="recargarCuenta.jsp">Aumentar saldo</a></td>
												</tr>
											</tbody>
										</table>

									</div>

									<h5>Conversor de divisas - Yahoo Finance</h5>
									<div class="table-wrapper">
										<table class="alt">
											<thead>
												<tr>
													<th>Divisa</th>
													<th>Cambio</th>
													<th>Divisa</th>
													<th>Cambio</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>Euro/Euro</td>
													<td>1.0</td>
													<td>Euro/Euro</td>
													<td>1.0</td>
												</tr>
												<tr>
													<td>Euro/Dólar estadounidense</td>
													<td><pa id="EUR/USD"></a></td>
													<td>Dólar estadounidense/Euro</td>
													<td><pa id="USD/EUR"></a></td>
												</tr>
												<tr>
													<td>Euro/Libra esterlina</td>
													<td><pa id="EUR/GBP"></a></td>
													<td>Libra esterlina/Euro</td>
													<td><pa id="GBP/EUR"></a></td>
												</tr>
												<tr>
													<td>Euro/Yen japonés</td>
													<td><pa id="EUR/JPY"></a></td>
													<td>Yen japonés/Euro</td>
													<td><pa id="JPY/EUR"></a></td>
												</tr>
											</tbody>
										</table>		
										<!--https://gist.github.com/henrik/265014 -->
										<!--Mercado actual de divisas -->			
										<script type="text/javascript">
											function getRate(from, to) {
 												var script = document.createElement('script');
  												script.setAttribute('src', "http://query.yahooapis.com/v1/public/yql?q=select%20rate%2Cname%20from%20csv%20where%20url%3D'http%3A%2F%2Fdownload.finance.yahoo.com%2Fd%2Fquotes%3Fs%3D"+from+to+"%253DX%26f%3Dl1n'%20and%20columns%3D'rate%2Cname'&format=json&callback=parseExchangeRate");
  												document.body.appendChild(script);
											}
											function parseExchangeRate(data) {
											  var name = data.query.results.row.name;
											  var rate = parseFloat(data.query.results.row.rate, 10);
											 	if(name == "EUR/USD")
											  	document.getElementById("EUR/USD").innerHTML = rate;
											 	if(name == "USD/EUR")
											  	document.getElementById("USD/EUR").innerHTML = rate;
											 	if(name == "EUR/GBP")
											  	document.getElementById("EUR/GBP").innerHTML = rate;
											 	if(name == "GBP/EUR")
											  	document.getElementById("GBP/EUR").innerHTML = rate;
											 	if(name == "EUR/JPY")
											  	document.getElementById("EUR/JPY").innerHTML = rate;
											 	if(name == "JPY/EUR")
											  	document.getElementById("JPY/EUR").innerHTML = rate;
											  //alert("Exchange rate " + name + " is " + rate);
											}
											getRate("EUR", "USD");
											getRate("USD", "EUR");
											getRate("EUR", "GBP");
											getRate("GBP", "EUR");
											getRate("EUR", "JPY");
											getRate("JPY", "EUR");
										</script>
									</div>
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