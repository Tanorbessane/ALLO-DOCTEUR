<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang=""> 
  <head>
    <meta charset="utf-8"> 
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Allo Docteur</title>

    <link rel="apple-touch-icon" href="apple-touch-icon.png">

    <!-- build:css styles/main.css -->
    <link rel="stylesheet" href="./assets/styles/bootstrap.css"> 
    <link rel="stylesheet" href="./assets/styles/main.css">
    <link rel="stylesheet" href="./assets/styles/rwd.css">
    <!-- endbuild -->
    
  </head>
  <body class="clearfix">
    
    <div class="container-fluid  no-padding">
      <div class="container-fluid clearfix no-padding header-container">
        <div class="container header-content">
          <a href="${pageContext.request.contextPath}/LoginServlet" class="logo">
            <img src="./assets/images/logo.png" alt="">
          </a>
        </div>
      </div>

      <div class="container-fluid clearfix">
        <div class="container content-outer connexion">
          <div class="content-container connexion-container clearfix">
            <div class="content">
              <!-- <h1 class="site-title">
                Bienvenue Mr. Julien sur Compte Allo Docteur
              </h1> -->

               <div class="righ-block">
                 
                 <h3>SE CONNECTER</h3>
                  <form id="login-form" class="login-form" method="post" action="${pageContext.request.contextPath}/LoginServlet">
                   <input name="login" type="text" placeholder="LOGIN" required>
                   <input name="password" type="password" placeholder="PASSWORD" required>
                   <button type="submit">se connecter</button>
                   <div class="clearfix"> </div>
                   <a href="#" class="forgot-pass-link clearfix">Mot de passe oublié?</a>
                 </form>
               </div>
                
            </div>
          </div>
        </div>
      </div>      
    </div>    
    <script src="./assets/scripts/jquery.js"></script>    
    <script src="./assets/scripts/jquery.slicknav.js"></script>
    <script src="./assets/scripts/main.js"></script>
  </body>
</html>
