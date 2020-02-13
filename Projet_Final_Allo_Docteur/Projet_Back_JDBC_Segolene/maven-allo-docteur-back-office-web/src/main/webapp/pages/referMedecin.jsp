
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
        <div class="container content-outer personal-information">
          <div class="content-container clearfix">
            <div class="content">
              <h1 class="site-title">
                Parrainer un médecin
              </h1>
              <div class="formulaire">
                <form action="${pageContext.request.contextPath}/ReferMedecinServlet" method="post">
                  <p>
                  <select name="civility">
                    <option selected value="Civilité">Civilité</option>
                    <option value="Homme">Homme</option>
                    <option value="Femme">Femme</option>
                  </select>
                </p>
                  <p>
                  <input type="text" name="firstName" placeholder="Prénom">
                  </p>
                  <p>
                  <input type="text" name="name" placeholder="Nom">
                  </p>
                  <p>
                  <input type="text" name="identifier" placeholder="Identifiant">
                  </p>
                  <p>
                  <input type="text" name="password" placeholder="Mot de passe">
                  </p>
                  <p>
                  <input type="text" name="cnfpassword" placeholder="Confirmation mot de passe">
                  </p>
                  <p>
                    <input autocomplete="off" data-toggle="datepicker" type="text" name="naissance" placeholder="Date de naissance">
                  </p>
                  <p>
                  <input type="text" name="tel" placeholder="Numéro de Téléphone">
                  </p>
                  <p>
                  <input type="text" name="numAcreditation" placeholder="numéro d’accréditation">
                  </p>
                  <p>
                  <input type="text" name="street" placeholder="Rue">
                  </p>
                  <p>
                  <input type="text" name="postaCode" placeholder="Code postale">
                  </p>
                  
                  <p>
                  <select name="city">
                    <option value="ville" selected>Ville</option>
                    <option value="ville" >Paris</option>
                    <option value="ville" >lavale</option>
                    <option value="ville" >Lille</option>
                    <option value="ville" >Lion</option>
                  </select>
                  </p>
                  <p>
                  <input type="text" name="pays" placeholder="Pays">
                  </p>
                  <p>
                  <button type="submit">Créer</button>
                  </p>
                  <p>
                </form>
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
