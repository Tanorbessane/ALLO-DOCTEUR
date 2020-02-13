/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.servlets;

import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.factory.ServiceFactory;
import com.cours.allo.docteur.service.IServiceFacade;
import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author TA013BE
 */
public class JsonServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final Log log = LogFactory.getLog(LoginServlet.class);
    private IServiceFacade serviceFacade = null;
  @Override
    public void init() throws ServletException {
        serviceFacade = ServiceFactory.getDefaultServiceFacade();
    }
private void createJson(HttpServletRequest request) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("ListUser.json"));
        } catch (IOException ex) {
            Logger.getLogger(ManageUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        HttpSession session = request.getSession();
        int idUtilisateur = (Integer) session.getAttribute("IdUtilisateur");
        List<Utilisateur> utilisateurs = serviceFacade.getUtilisateurDao().findAllUtilisateurs();
        String jsonUser = new Gson().toJson(utilisateurs);
        try {
            writer.write(jsonUser);
        } catch (IOException ex) {
            Logger.getLogger(ManageUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ManageUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

private void File(String file, HttpServletResponse response, String ct) throws IOException {
        response.setContentType(ct);
        response.setHeader("Content-Disposition","attachment;filename=" + file);
        ServletContext ctx = getServletContext();
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
        out.close();
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String action = request.getParameter("action");
         switch (action) {
            case "listUserJson":
         this.createJson(request);
                this.File("ListUser.json", response, "application/json");
                break;
                //Le Reste des case poour les autres exports à faire 
    }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        this.doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
