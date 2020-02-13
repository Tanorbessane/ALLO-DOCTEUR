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
      <p class="browserupgrade">Vous utilisez un <strong>obsolète</strong> navigateur. Merci de se <a href="http://browsehappy.com/">mettre à jour</a> pour am�liorer votre experience.</p>
    <![endif]-->
    
    <div class="container-fluid  no-padding ">
      <div class="container-fluid clearfix no-padding header-container ">
        <div class="container header-content">
          <a href="./index.html" class="logo">
            <img src="./assets/images/logo.png" alt="">
          </a>
          <ul class="loginMenu">
            <li><a href="creation-compte.html">Cr�ation compte</a></li>
            <li><a href="allo-doctor-compte-patient.html">Connexion</a></li>
          </ul>
          <ul class="nav">
            <li><a class="active" href="patients.html">Patient</a></li>
            <li><a href="rendez-vous.html">Prendre rendez vous</a></li>
            <li><a href="modification-informations-patient.html">Informations patient</a></li>
            <li><a href="selection-creneau.html ">Selection cr�neau</a></li>
            <li><a href="futurs-rendez-vous.html ">Futurs rendez-vous</a></li>
            <li><a href="recherche-medecin.html ">Recherche m�decin</a></li>
          </ul>
        </div>
      </div>

      <div class="container-fluid clearfix">
        <div class="container content-outer patient-home">
          <div class="content-container clearfix">
            <div class="content">
              <h1 class="site-title">
                ACCUEIL DES PATIENTS
              </h1>
              <div class="home-links">
                <p>Bonjour Mr Nicolas Care, votre prochain rendez-vous est pour le 25/10/2018 à 09h00
avec le docteur Jacques Dupont dont le cabinet se situe au 5 rue du paradis, 53 000 Laval,
T�l�phone XX XX XX XX XX.</p>
                <div class="links">
                  <a href="./modification-informations-patient.html">Modifier mes informations personnelles</a>
                  <a href="./rendez-vous.html">Prendre un rendez-vous</a>
                  <a href="./futurs-rendez-vous.html">Voir mes futur rendez-vous</a>
                  <a href="./recherche-medecin.html">Rechercher medecin</a>
                  <a href="#">Exporter les m�decins au format PDF.</a>
                  <a href="#">Exporter les m�decins au format CSV.</a>
                  <a href="#">Exporter mes futurs rendez-vous au format PDF.</a>
                  <a href="#">Exporter tous mes rendez-vous au format CSV.</a>
                  <a href="#">Exporter tous mes rendez-vous au format JSON.</a>
                  <a href="#">Exporter tous mes rendez-vous au format XML.</a>
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
    
    <script src="./assets/scripts/jquery.slicknav.js"></script> 
    <!-- build:js scripts/main.js -->
    <script src="./assets/scripts/main.js"></script>
    <!-- endbuild -->
  </body>
</html>

