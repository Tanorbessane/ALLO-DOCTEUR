package com.cours.allo.docteur.servlets;

import com.cours.allo.docteur.dao.entities.Medecin;
import com.cours.allo.docteur.factory.ServiceFactory;
import com.cours.allo.docteur.service.IServiceFacade;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/***
 * 
 * @author sigt_sf
 *
 */
public class HomeMedecinServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LoginServlet.class);
    private IServiceFacade serviceFacade = null;

    @Override
    public void init() throws ServletException {
        serviceFacade = ServiceFactory.getDefaultServiceFacade();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int medecinId = (Integer) session.getAttribute("medecinId");
        Medecin medecin = serviceFacade.getMedecinDao().findMedecinById(medecinId);
        request.setAttribute("medecin", medecin);
        this.getServletContext().getRequestDispatcher("/pages/home.jsp").forward(request, response);
        log.debug("Affichage de la page home");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    public void destroy() {
    }

}
