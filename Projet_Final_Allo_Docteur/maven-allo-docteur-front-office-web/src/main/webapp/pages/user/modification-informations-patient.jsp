<!doctype html>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.cours.allo.docteur.dao.entities.Patient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    Patient patient = (Patient) request.getAttribute("patient");
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String dateNaissance = dateFormat.format(patient.getIdUtilisateur().getDateNaissance());

%>
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
                        <li><a href="${pageContext.request.contextPath}/ManagePatientServlet">Patient</a></li>
                        <li><a href="rendez-vous.html">Prendre rendez vous</a></li>
                        <li><a class="active" href="modification-informations-patient.html">Informations patient</a></li>
                        <li><a href="selection-creneau.html ">Selection créneau</a></li>
                        <li><a href="futurs-rendez-vous.html ">Futurs rendez-vous</a></li>
                        <li><a href="recherche-medecin.html ">Recherche médecin</a></li>

                    </ul>
                </div>
            </div>

            <div class="container-fluid clearfix">
                <div class="container content-outer personal-information">
                    <div class="content-container clearfix">
                        <div class="content">
                            <h1 class="site-title">
                                INFORMATIONS PERSONNELLES DU PATIENT
                            </h1>
                            <div class="formulaire">
                                <form action="${pageContext.request.contextPath}/UpdatePatientServlet" method="post">
                                    <p>
                                        <select name="civility">
                                            <option value="<%= request.getAttribute("civility")%>" selected><%= request.getAttribute("civility")%></option>
                                            <option value="Homme">Homme</option>
                                            <option value="Femme">Femme</option>
                                        </select>
                                    </p>
                                    <p>
                                        <input type="text" name="firstName" placeholder="Prénom" value="<% out.println(patient.getIdUtilisateur().getPrenom()); %>" required>
                                    </p>
                                    <p>
                                        <input type="text" name="name" placeholder="Nom" value="<% out.println(patient.getIdUtilisateur().getNom()); %>">
                                    </p>
                                    <p>
                                        <input type="email" name="identifier" placeholder="Identifiant" value="<% out.println(patient.getIdUtilisateur().getIdentifiant()); %>">
                                    </p>
                                    <p>
                                        <input type="password" name="password" placeholder="Mot de passe" value="<% out.println(patient.getIdUtilisateur().getMotPasse()); %>">
                                    </p>
                                    <p>
                                        <input type="password" name="cnfpassword" placeholder="Confirmation mot de passe" value="<% out.println(patient.getIdUtilisateur().getMotPasse()); %>">
                                    </p>
                                    <p>
                                        <input autocomplete="off" data-toggle="datepicker" type="text" name="naissance" placeholder="Date de naissance" value="<% out.print(dateNaissance);%>">
                                    </p>
                                    <p>
                                        <input type="text" name="tel" placeholder="Numéro de Téléphone" value="<%= request.getAttribute("tel")%>" >
                                    </p>
                                    <p>
                                        <input type="text" name="rue" placeholder="Rue" value="<%= request.getAttribute("rue")%>">
                                    </p>
                                    <p>
                                        <input type="text" name="codePostal" placeholder="Code postale" value="<%= request.getAttribute("codePostal")%>">
                                    </p>
                                    </p>
                                    <p>
                                       <select name="country">
                                           <option value="<%= request.getAttribute("country")%>" selected><%= request.getAttribute("country")%></option>
                                            <option>Paris</option>
                                            <option>lavale</option>
                                            <option>Lille</option>
                                            <option>Lion</option>
                                        </select>
                                    </p>
                                    <p>
                                        <input type="text" name="pays" value="<%= request.getAttribute("pays")%>" placeholder="Pays">
                                    </p>

                                    <p>
                                        <button type="submit">Modifier</button>
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
