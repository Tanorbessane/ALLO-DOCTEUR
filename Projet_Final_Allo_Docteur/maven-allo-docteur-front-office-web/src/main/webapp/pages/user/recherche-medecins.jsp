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
            <li><a href="patients.html">Patient</a></li>
            <li><a href="rendez-vous.html">Prendre rendez vous</a></li>
            <li><a href="modification-informations-patient.html">Informations patient</a></li>
            <li><a href="selection-creneau.html ">Selection cr�neau</a></li>
            <li><a href="futurs-rendez-vous.html ">Futurs rendez-vous</a></li>
            <li><a class="active" href="recherche-medecin.html ">Recherche m�decin</a></li>
          </ul>
        </div>
      </div>

      <div class="container-fluid clearfix">
        <div class="container content-outer search-patient">
          <div class="content-container clearfix">
            <div class="content">
              <h1 class="site-title">
                RECHERCHE Medecin
              </h1>
                <form class="search-form" action="" method="get">
                  <select name="city">
                    <option value="ville" selected>entrez Critère de recherche</option>
                    <option value="ville" >Pr�nom</option>
                    <option value="ville" >Nom</option>
                    <option value="ville" >Ville</option>
                    <option value="ville" >Code postale</option>
                  </select>
                  <input type="text" name="searchName" placeholder="Entrez la valeur">
                  <button type="submit">Rechercher</button>
                </form>
              
              <table>
                <thead>
                  <tr>
                    <th title="Pr�nom et Nom">Pr�nom et Nom</th>
                    <th>civilit�</th>
                    <th>Identifiant</th>
                    <th>Num�ro accr�ditation</th>
                    <th>Num�ro de t�l�phone</th>
                    <th>Adresse</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td title="Pr�nom et Nom">Faris CORONA</td>
                    <td title="Num�ro de s�curit� sociale">Mr</td>
                    <td title="Identifiant">Mich@el2001</td>
                    <td title="Num�ro de s�curit� sociale">235146587452698</td>
                    <td title="Num�ro de t�l�phone">03365221144</td>
                    <td title="Adresse">Avenue la r�sistance, n°2, 75000, Paris France </td>
                  </tr>
                  <tr>
                    <td title="Pr�nom et Nom">Marie Pierre</td>
                    <td title="Num�ro de s�curit� sociale">Mme</td>
                    <td title="Identifiant">Mich@el2001</td>
                    <td title="Num�ro de s�curit� sociale">235146587452698</td>
                    <td title="Num�ro de t�l�phone">03365221144</td>
                    <td title="Adresse">Avenue la r�sistance, n°2, 75000, Paris France </td>
                  </tr>
                  <tr>
                    <td title="Pr�nom et Nom">Xavina Collet</td>
                    <td title="Num�ro de s�curit� sociale">Mme</td>
                    <td title="Identifiant">Mich@el2001</td>
                    <td title="Num�ro de s�curit� sociale">235146587452698</td>
                    <td title="Num�ro de t�l�phone">03365221144</td>
                    <td title="Adresse">Avenue la r�sistance, n°2, 75000, Paris France </td>
                  </tr>
                  <tr>
                    <td title="Pr�nom et Nom">Max Marois</td>
                    <td title="Num�ro de s�curit� sociale">Mr</td>
                    <td title="Identifiant">Mich@el2001</td>
                    <td title="Num�ro de s�curit� sociale">235146587452698</td>
                    <td title="Num�ro de t�l�phone">03365221144</td>
                    <td title="Adresse">Avenue la r�sistance, n°2, 75000, Paris France </td>
                  </tr>
                  
                  

                </tbody>
              </table>

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
