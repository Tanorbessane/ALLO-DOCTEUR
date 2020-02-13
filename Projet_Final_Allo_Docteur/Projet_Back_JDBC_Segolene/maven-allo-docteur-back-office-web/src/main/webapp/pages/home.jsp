<%@page contentType="text/html" pageEncoding="UTF-8"%>                        
<!doctype html>
<html class="no-js" lang="">   
  <head>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Allo Docteur</title>

    <link rel="apple-touch-icon" href="apple-touch-icon.png">
    <!-- Place favicon.ico in the root directory -->

    <!-- build:css styles/vendor.css -->
    <!-- bower:css -->
    <!-- endbower -->
    <!-- endbuild -->

    <!-- build:css styles/main.css -->
    <link rel="stylesheet" href="./assets/styles/bootstrap.css"> 
    <link rel="stylesheet" href="./assets/styles/main.css">
    <link rel="stylesheet" href="./assets/styles/rwd.css">
    <!-- endbuild -->
    
    <!-- build:js scripts/vendor/modernizr.js -->
    <!-- endbuild -->
  </head>
  <body class="clearfix">
    <!--[if IE]>
      <p class="browserupgrade">Vous utilisez un <strong>obsolète</strong> navigateur. Merci de se <a href="http://browsehappy.com/">mettre à jour</a> pour améliorer votre experience.</p>
    <![endif]-->
    
    <div class="container-fluid  no-padding ">
      <div class="container-fluid clearfix no-padding header-container ">
        <div class="container header-content">
         <a href="${pageContext.request.contextPath}/HomeMedecinServlet" class="logo">
                        <img src="./assets/images/logo.png" alt="">
                    </a>
        
          <ul class="nav">
                <li><a class="active" href="${pageContext.request.contextPath}/HomeMedecinServlet">Médecin</a></li>
				<li><a href="${pageContext.request.contextPath}/InfoMedecinServlet">Informations médecins</a></li>
                <li><a href="${pageContext.request.contextPath}/FindPatientServlet">Recherche patient</a></li>
                <li><a href="${pageContext.request.contextPath}/RdvMedecinServlet">Présence rendez-vous</a></li>
            	<li><a href="${pageContext.request.contextPath}/ReferMedecinServlet">Parrainer un médecin</a></li>            	
            	<li><a href="${pageContext.request.contextPath}/RdvServlet">Rendez-vous journée</a></li>
          </ul>
        </div>
      </div>

      <div class="container-fluid clearfix">
        <div class="container content-outer medecin-home">
          <div class="content-container clearfix">
            <div class="content">
              <h1 class="site-title">
                ACCUEIL DES MÉDECIN
              </h1>
              <div class="home-links">
                <p>Bonjour Mr le docteur ### , votre chiffre d’affaire du mois est de ### euros.</p>
                <div class="links">
                  <a href="./modification-informations-medecin.html">Modifier mes informations personnelles</a>
                  <a href="./rendez-vous-journee.html">Voir les rendez-vous de la journée</a>
                  <a href="./recherche-patient.html">Recherche patients</a>
                  <a href="./recherche-rendez-vous.html">Rechercher mes rendez-vous</a>
                  <a href="./parrainer-medecin.html">Parrainer un médecin</a>
                  <a href="./presence-rendez-vous.html ">Présence au rendez-vous</a>
                  <a href="#">Exporter mes futurs rendez-vous au format CSV</a>
                  <a href="#">Exporter mes futurs rendez-vous au format XML</a>
                  <a href="#">Exporter mes futurs rendez-vous au format Json</a>
                  <a href="#">Exporter tous mes rendez-vous au format CSV</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      
    </div>
    
    

    <!-- build:js scripts/vendor.js -->
    <!-- bower:js -->
    <script src="./assets/scripts/jquery.js"></script>
    <!-- endbower -->
    <!-- endbuild -->
    
    <!-- build:js scripts/main.js -->
    <script src="./assets/scripts/jquery.slicknav.js"></script>
    <script src="./assets/scripts/main.js"></script>
    <!-- endbuild -->
  </body>
</html>

                        