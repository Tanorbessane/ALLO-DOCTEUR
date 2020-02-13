<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Allo Docteur</title>
        <!-- CSS files -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800|Oswald:300,400,500,600,700" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="./assets/css/master.css"> 
        <link rel="icon" href="./assets/images/favicon.png" type="image/x-icon">
    </head>
    <body>
        <div class="outer">
            <div class="header-outer" id="header-outer">
                <!-- Header -->
                <header id="header"  class="header">
                    <div class="header padd-top">
                        <a href="index.html" title="Allo Docteur" class="logo"> 
                            <img src="./assets/images/logo/logo.png" alt="Allo Docteur"> 
                        </a>
                    </div>
                </header>
            </div>
            <!-- Section -->
            <section>
                <div class="content">
                    <div class="User quest">
                        <h1>Editer un utilisateur</h1>
                        <form method="post" id="customer-info-form" class="no-gutters"  action="allUsers.html">
                            <div class="account-container row">

                                <fieldset class=" common-form-controls col-md-4">
                                    <div >
                                        <p>Informations Personnelles</p>

                                        <div class="input-wrapper">
                                            <label for="firstname">Prénom</label>
                                            <input value="John"  name="firstname" id="firstname" type="text">
                                        </div>
                                        <div class="input-wrapper">
                                            <label for="lastname">Nom</label>
                                            <input value="Doe"  name="lastname" id="lastname" type="text">
                                        </div>
                                        <div class="input-wrapper">
                                            <label for="email">Identifiant</label>
                                            <input name="email" value="john@mail.com" id="email" type="email">
                                        </div>
                                        <div class="input-wrapper">
                                            <label>Civilité</label>
                                            <div class="gender">
                                                <input type="radio" id="male" name="sex" checked="checked"/>
                                                <label for="male">
                                                    <i class="fa fa-male" aria-hidden="true"></i>
                                                </label>
                                                <input type="radio" id="female" name="sex"/>
                                                <label for="female">
                                                    <i class="fa fa-female" aria-hidden="true"></i>
                                                </label>
                                            </div>
                                        </div>
                                        <div class="input-wrapper">
                                            <label style="display: block;"> Date de naissance</label>
                                            <input id="dteNaiss" name="dteNaiss" style="width:auto;" data-toggle="datepicker" type="text" value="15/05/1990" name="naissance">
                                        </div>
                                        
                                </fieldset>
                                <div class="offset-md-2"></div>
                                <fieldset class="col-md-4">
                                    
                                    <p> Adresse Principale :</p>
                                    <div class="input-wrapper">
                                        <label>Rue :</label>
                                         <input type="text" value="Rue la victoire"  name="street">
                                     </div>
                                    <div class="input-wrapper">
                                        <label>Code postal :</label>
                                        <input type="text" value="75000"  name="postal_code">
                                    </div>
                                    <div class="input-wrapper">
                                        <p>Selectionner la ville</p>
                                        <div class="sel-container">
                                            <div class="sel">
                                                <select  class="required" name="select-city" id="select-city"> 
                                                    <option value="Ville" disabled>Ville</option>
                                                    <option value="Laval " selected>Laval </option>
                                                    <option value="Paris ">Paris </option>
                                                    <option value="Lyon ">Lyon </option>
                                                    <option value="Nante ">Nante </option>
                                                    <option value="Montpelier ">Montpelier </option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="input-wrapper">
                                        <label>Pays :</label>
                                        <input type="text" value="France"  name="country">
                                    </div>
                                </fieldset>
                            </div>
                        <div class="row">
                        <div class="col-md-3"></div>
                        <div class="actions col-md-5">
                            <button type="submit">Enregistrer</button>
                        </div>
                        </div>
                        </form>

                        
                    </div> 
                </div>
            </section>
            <!-- Footer -->
            <footer>
                <div class="footer-container ">
                    <div class="footer">
                        <div class="footer-middle">
                            <div class="footer-container_">
                                <div class="row no-gutters"> 
                                    <div class="col-sm-6 col-md-3">
                                        <div class="block">
                                            <div class="block-title"><label><span>Contactez Nous</span>:</label></div>
                                            <div class="block-content">
                                                <ul class="contact-info">
                                                    <li><i class="icon-location">&nbsp;</i><p><b>Addresse:</b><br>123 Rue la victoire, 75000 Paris, France</p></li>
                                                    <li><i class="icon-phone">&nbsp;</i><p><b>Tél:</b><br>(+33) 00 11 00 11 00</p></li>
                                                    <li><i class="icon-mail">&nbsp;</i><p><b>Email:</b><br><a href="mailto:mail@example.com">mail@example.com</a></p></li>
                                                    <li><i class="icon-clock">&nbsp;</i><p><b>Horaire : </b><br>Lundi au Samedi</p></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6 col-md-3"><div class="block">
                                            <div class="block-title"><label><span>Mon compte</span>:</label></div>
                                            <div class="block-content">
                                                <ul class="links">
                                                    <li><i class="icon-right-dir theme-color"></i><a href="#" title="A propos de nous">Mon compte</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>                
                                    <div class="col-sm-6 col-md-3"><div class="block">
                                            <div class="block-title"><label><span>Information</span>:</label></div>
                                            <div class="block-content">
                                                <ul class="features">
                                                    <li><i class="icon-ok theme-color"></i><a href="#">Les informations</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6 col-md-3">
                                        <div class="block">
                                            <div class="block-title"><label><span>Nos Services</span>:</label></div>
                                            <div class="block-content">
                                                <ul class="features">
                                                    <li><i class="icon-ok  theme-color"></i><a href="#">Service Client</a></li>
                                                    <li><i class="icon-ok  theme-color"></i><a href="#">Politique d'Utilisation</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>              
                                </div>
                            </div>
                        </div>
                        <div class="footer-bottom">
                            <div class="footer-container_">              
                                <address>© Allo Docteur. 2018. Tous droit réservé</address>
                            </div>
                        </div>
                    </div>
                </div>   
            </footer>
        </div>
        <!-- JS files -->
        <script src="./assets/js/bower.js" type="text/javascript"></script>
        <script src="./assets/js/application.js" type="text/javascript"></script>
    </body>
</html>
