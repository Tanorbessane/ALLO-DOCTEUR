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
          <p class="browserupgrade">Vous utilisez un <strong>obsolÃ¨te</strong> navigateur. Merci de se <a href="http://browsehappy.com/">mettre Ã  jour</a> pour améliorer votre experience.</p>
        <![endif]-->

        <div class="container-fluid  no-padding ">
            <div class="container-fluid clearfix no-padding header-container ">
                <div class="container header-content">
                    <a href="./index.html" class="logo">
                        <img src="./assets/images/logo.png" alt="">
                    </a>
                    <ul class="loginMenu">
                        <li><a href="${pageContext.request.contextPath}/CreatePatientServlet">Création compte</a></li>
                        <li><a href="${pageContext.request.contextPath}/LoginServlet">Connexion</a></li>
                    </ul>
                    <ul class="nav">
                        <li><a href="patients.html">Patient</a></li>
                        <li><a href="rendez-vous.html">Prendre rendez vous</a></li>
                        <li><a href="modification-informations-patient.html">Informations patient</a></li>
                        <li><a href="selection-creneau.html ">Selection créneau</a></li>
                        <li><a href="futurs-rendez-vous.html ">Futurs rendez-vous</a></li>
                        <li><a href="recherche-medecin.html ">Recherche médecin</a></li>

                    </ul>
                </div>
            </div>

            <div class="container-fluid clearfix">
                <div class="container content-outer creation-compte">
                    <div class="content-container clearfix">
                        <div class="content">
                            <h1 class="site-title">
                                CREATION COMPTE
                            </h1>
                            <div class="formulaire">
                                <form action="#" method="post">
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
                                        <input type="text" name="numeroSS" placeholder="Numéro Sécurité Social">
                                    </p>
                                    <p>
                                        <input id="email" type="email" name="identifier" placeholder="Identifiant">
                                    </p>
                                    <p>
                                        <input id="password" type="password" name="password" placeholder="Mot de passe">
                                    </p>
                                    <p>
                                        <input id="password" type="password" name="cnfpassword" placeholder="Confirmation mot de passe">
                                    </p>
                                    <p>
                                        <input autocomplete="off" data-toggle="datepicker" type="text" name="naissance" placeholder="Date de naissance">
                                    </p>
                                    <p>
                                        <input type="text" name="tel" placeholder="Numéro de téléphone">
                                    </p>
                                    <p>
                                        <input type="text" name="street" placeholder="Rue">
                                    </p>
                                    <p>
                                        <input type="text" name="postalCode" placeholder="Code postale">
                                    </p>
                                    </p>
                                    <p>
                                        <select  class="required" name="city" id="select-city"> 
                                            <option value="Ville" disabled>Ville</option>
                                            <option value="Laval " selected>Laval </option>
                                            <option value="Paris ">Paris </option>
                                            <option value="Lyon ">Lyon </option>
                                            <option value="Nante ">Nante </option>
                                            <option value="Montpelier ">Montpelier </option>
                                        </select>
                                    </p>
                                    <p>
                                        <input type="text" name="Pays" placeholder="Pays">
                                    </p>

                                    <p>
                                        <button type="submit">Envoyer</button>
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


