/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.servlets;

import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.dao.impl.AdresseDao;
import com.cours.allo.docteur.dao.impl.UtilisateurDao;
import com.cours.allo.docteur.factory.ServiceFactory;
import com.cours.allo.docteur.service.IServiceFacade;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author elhad
 */
// @WebServlet(name = "ManageUsersServlet", urlPatterns = {"/ManageUsersServlet"})
public class ManageUsersServlet extends HttpServlet {

    private static final Log log = LogFactory.getLog(LoginServlet.class);
    private IServiceFacade serviceFacade = null;
    private Object session;

    /**
     * Méthode d'initialisation de la Servlet
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        serviceFacade = ServiceFactory.getDefaultServiceFacade();
    }

    /**
     * Méthode appelée lors d'une requête HTTP GET
     *
     * @param request L'objet requête contenant les informations de la requête
     * http
     * @param response L'objet réponse contenant les informations de la réponse
     * http
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //this.getServletContext().getRequestDispatcher("/pages/user/allUsers.jsp").forward(request, response);
        //this.getServletContext().getRequestDispatcher("/pages/user/user.jsp").forward(request, response);
        String uri = request.getQueryString();
        if (uri != null) {
            if (uri.equals("user")) {
                this.getServletContext().getRequestDispatcher("/pages/user/addUser.jsp").forward(
                        request,
                        response);
            } else if (uri.startsWith("id=")) {
                String param = request.getParameter("id");
                String[] parts = param.split("/");
                UtilisateurDao utilisateurDao = new UtilisateurDao();
                int idUser = Integer.parseInt(parts[0]);
                if (parts[1].equals("e")) {
                    Utilisateur utilisateur = utilisateurDao.findUtilisateurById(idUser);
                    Adresse adresse = utilisateur.getAdressePrincipale();
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

                    RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/user/updateUser.jsp");
                    request.setAttribute("id", idUser);
                    request.setAttribute("firstname", utilisateur.getPrenom());
                    request.setAttribute("lastname", utilisateur.getNom());
                    request.setAttribute("email", utilisateur.getIdentifiant());
                    request.setAttribute("dteNaiss", df.format(utilisateur.getDateNaissance()));

                    if (adresse != null) {
                        request.setAttribute("street", adresse.getRue());
                        request.setAttribute("postal_code", adresse.getCodePostal());
                        request.setAttribute("country", adresse.getPays());
                    } else {
                        request.setAttribute("street", "");
                        request.setAttribute("postal_code", "");
                        request.setAttribute("country", "");
                    }

                    dispatcher.forward(request, response);
                } else {
                    Utilisateur user = new Utilisateur(idUser);
                    utilisateurDao.deleteUtilisateur(user);
                    response.sendRedirect("/maven-quest-allo-docteur-jdbc-web/ManageUsersServlet");
                }
            }
        } else {
            if (this.getServletContext().getRequestDispatcher("/pages/user/allUsers.jsp") == null) {
                System.out.println("servlet est null");
            }
            this.getServletContext().getRequestDispatcher("/pages/user/allUsers.jsp").forward(
                    request,
                    response);
        }
    }
    

    /**
     * Méthode appelée lors d'une requête HTTP POST
     *
     * @param request L'objet requête contenant les informations de la requête
     * http
     * @param response L'objet réponse contenant les informations de la réponse
     * http
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
        if (request.getParameter("_method") != null
                && request.getParameter("_method").equals("put")) {
            doPut(request, response);
            return;
        }

        Utilisateur utilisateur = new Utilisateur();
        UtilisateurDao userDao = new UtilisateurDao();
        Adresse adresse = new Adresse();
        AdresseDao adresseDao = new AdresseDao();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        utilisateur.setPrenom(request.getParameter("firstname"));
        utilisateur.setNom(request.getParameter("lastname"));
        utilisateur.setIdentifiant(request.getParameter("email"));
        utilisateur.setMotPasse(request.getParameter("password"));
        if (request.getParameter("sex").equals("male")) {
            utilisateur.setCivilite("Mr");
        } else {
            utilisateur.setCivilite("Mme");
        }
        try {
            Date date = format.parse(request.getParameter("dteNaiss"));
            utilisateur.setDateNaissance(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        adresse.setRue(request.getParameter("street"));
        adresse.setCodePostal(request.getParameter("postal_code"));
        adresse.setVille(request.getParameter("select-city"));
        adresse.setPays(request.getParameter("country"));
        adresse.setPrincipale(true);

        utilisateur = userDao.createUtilisateur(utilisateur);
        adresse.setIdUtilisateur(utilisateur.getIdUtilisateur());
        adresseDao.createAdresse(adresse);

        response.sendRedirect("/maven-quest-allo-docteur-jdbc-web/ManageUsersServlet");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

   

    /**
     * Méthode appelée lors de la fin de la Servlet
     */
    @Override
    public void destroy() {
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             UtilisateurDao daoUser = new UtilisateurDao();
        AdresseDao daoAddr = new AdresseDao();
        Integer idUser = Integer.parseInt(request.getParameter("id"));
        Utilisateur utilisateur = daoUser.findUtilisateurById(idUser);
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String street = request.getParameter("street");
        String postal_code = request.getParameter("postal_code");
        String city = request.getParameter("select-city");
        String country = request.getParameter("country");
        String dteNaiss = request.getParameter("dteNaiss");
        String civilite = request.getParameter("sex");
        Date creationDate = new Date();
        Date modificationDate = new Date();
        Date birthDate;
        Adresse newAddr;
        Adresse mainAddr;
        Integer idAddr;

        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            birthDate = df.parse(dteNaiss);
        } catch (Exception e) {
            birthDate = new Date();
        }

        utilisateur.setDateNaissance(birthDate);
        utilisateur.setNom(firstname);
        utilisateur.setPrenom(lastname);
        utilisateur.setIdentifiant(email);
        utilisateur.setMotPasse(utilisateur.getMotPasse());
        utilisateur.setDateCreation(creationDate);
        utilisateur.setDateModification(modificationDate);
        utilisateur.setCivilite(civilite);
        utilisateur.setActif(true);
        utilisateur.setMarquerEffacer(false);
        utilisateur.setVersion(1);

        mainAddr = daoUser.updateUtilisateur(utilisateur).getAdressePrincipale();

        if (mainAddr != null) {
            idAddr = mainAddr.getIdAdresse();
        } else {
            idAddr = 1;
        }

        newAddr = new Adresse(idAddr, street, postal_code, city, country, true, 1, idUser);
        daoAddr.updateAdresse(newAddr);

        response.sendRedirect("/maven-quest-allo-docteur-jdbc-web/ManageUsersServlet");
    }
    }

    

