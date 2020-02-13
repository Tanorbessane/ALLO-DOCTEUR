<!doctype html>
<%@page import="com.cours.allo.docteur.dao.entities.Medecin"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                        <li><a href="creation-compte.html">Création compte</a></li>
                        <li><a href="allo-doctor-compte-patient.html">Connexion</a></li>
                    </ul>
                    <ul class="nav">
                        <li><a href="patients.html">Patient</a></li>
                        <li><a class="active" href="rendez-vous.html">Prendre rendez vous</a></li>
                        <li><a href="modification-informations-patient.html">Informations patient</a></li>
                        <li><a href="selection-creneau.html ">Selection créneau</a></li>
                        <li><a href="futurs-rendez-vous.html ">Futurs rendez-vous</a></li>
                        <li><a href="recherche-medecin.html ">Recherche médecin</a></li>
                    </ul>
                </div>
            </div>

            <div class="container-fluid clearfix">
                <div class="container content-outer rendez-vous">
                    <div class="content-container clearfix">
                        <div class="content">
                            <h1 class="site-title">
                                PRISE DE RENDEZ VOUS
                            </h1>
                            <div class="formulaire rendez-vous-form">
                                <form action="${pageContext.request.contextPath}/ManageCreneauServlet" method="post">

                                    <p>
                                        <select name="ditance">
                                            <option value="Médecins Ã  moins de 5 km" selected>Médecins Ã  moins de 5 km</option>
                                            <option value="Médecins Ã  moins de 10 km " >Médecins Ã  moins de 10 km </option>
                                            <option value="Médecins Ã  moins de 20 km" >Médecins Ã  moins de 20 km</option>
                                            <option value=" Médecins Ã  moins de 50 km" > Médecins Ã  moins de 50 km</option>
                                            <option value="Médecins Ã  moins de 100 km" >Médecins Ã  moins de 100 km</option>
                                        </select>
                                    </p>
                                    <p>
                                        <select name="city">
                                            <option value="ville" selected>Ville</option>
                                            <option value="Paris" >Paris</option>
                                            <option value="lavale" >Laval</option>
                                            <option value="Lille" >Lille</option>
                                            <option value="Lion" >Lyon</option>
                                        </select>
                                    </p>
                                    <p>
                                        <select name="medecin">
                                            <%
                                                List<Medecin> medecins = (List<Medecin>) request.getAttribute("medecins");
                                                for (Medecin medecin : medecins) {
                                                    out.println("<option value=\"" + medecin.getIdUtilisateur().getIdUtilisateur() + "\">" + medecin.getIdUtilisateur().getPrenom() + " " + medecin.getIdUtilisateur().getNom() + "</option>");
                                                }
                                            %>
                                        </select>
                                    </p>
                                    <p>
                                        <input autocomplete="off" data-toggle="datepicker" type="text" name="date" placeholder="Date du rendez vous">
                                    </p>
                                    <p>
                                    <p>
                                        <button type="submit">Valider</button>
                                        <button type="submit">Effacer</button>
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

        <script src="./assets/scripts/jquery.slicknav.js"></script>
        <script src="https://fengyuanchen.github.io/datepicker/js/datepicker.js"></script>

        <!-- build:js scripts/main.js -->
        <script src="./assets/scripts/main.js"></script>
        <!-- endbuild -->
    </body>
</html>
