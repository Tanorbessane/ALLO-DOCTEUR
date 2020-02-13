/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.allo.docteur.dao.IUtilisateurDao;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.factory.ServiceFactory;
import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.service.ServiceFacade;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author elhad
 */
// @WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final Log log = LogFactory.getLog(LoginServlet.class);
    private IServiceFacade serviceFacade = null;

    @Override
    public void init() throws ServletException {
        serviceFacade =ServiceFactory.getDefaultServiceFacade();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String mail = request.getParameter("email");
        String password = request.getParameter("password");
        IUtilisateurDao dao = serviceFacade.getUtilisateurDao();
        List<Utilisateur> utilisateurs2 =dao.findAllUtilisateurs();
        List<Utilisateur> utilisateurs = serviceFacade.getUtilisateurDao().findAllUtilisateurs();
        boolean result = false;
        for (Utilisateur utilisateur : utilisateurs) {
            if(utilisateur.getIdentifiant().equals(mail)&&utilisateur.getMotPasse().equals(password)){
                result = true;
               session.setAttribute("IdUtilisateur", utilisateur.getIdUtilisateur());
                break;
            } else {
                result = false;
            }
        }
        if (!result) {
            System.out.println("wrong");
            response.sendRedirect(this.getServletContext().getContextPath() + "/pages/login/login.jsp");
        } else {
            System.out.println("good");
            response.sendRedirect(this.getServletContext().getContextPath() + "/ManageUsersServlet");
        }
    }

    /**
     * Méthode appelée lors de la fin de la Servlet
     */
    @Override
    public void destroy() {
    }
}
