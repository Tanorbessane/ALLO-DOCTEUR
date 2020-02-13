/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.servlets;

import com.cours.allo.docteur.dao.entities.Medecin;
import com.cours.allo.docteur.dao.entities.RendezVous;
import com.cours.allo.docteur.factory.AbstractStatisticSingleton;
import com.cours.allo.docteur.factory.SingletonFactory;
import static com.cours.allo.docteur.factory.SingletonFactory.FactorySingletonType.XML_SINGLETON_FACTORY;
import com.cours.allo.docteur.service.IServiceFacade;
import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author TA013BE
 */
public class DownloadExportServlet extends HttpServlet {
    private static final Log log = LogFactory.getLog(LoginServlet.class);
    private IServiceFacade serviceFacade = null;

    @Override
    public void init() throws ServletException {
        WebApplicationContext context
                = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        serviceFacade = (IServiceFacade) context.getBean("serviceFacade");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String action = request.getParameter("action");
        switch (action) {case "medecinPdf":
                try {
                    this.createMedecinPdf(request);
                } catch (DocumentException ex) {
                    Logger.getLogger(DownloadExportServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.tpFile("Medecins.pdf", response, "application/pdf");
                break;
            case "rdvJson":
                this.createRendezVousJson(request);
                this.tpFile("RendezVous.json", response, "application/json");
                break;
                case "rdvXml":
                this.createRendezVousXML(request);
                this.tpFile("apptsXML.xml", response, "text/xml");
                break;
            case "medecinCsv":
                this.createMedecinCsv(request);
                this.tpFile("Medecins.csv", response, "text/csv");
                break;
            case "rdvCsv":
                this.createRendezVousCsv(request);
                this.tpFile("RendezVous.csv", response, "text/csv");
                break;
            
            case "rdvPdf":
                try {
                    this.createRendezVousPdf(request);
                } catch (DocumentException ex) {
                    Logger.getLogger(DownloadExportServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.tpFile("RendezVous.pdf", response, "application/pdf");
                break;

    }
    }
 private void tpFile(String file, HttpServletResponse response, String ct) throws IOException {
        response.setContentType(ct);
        response.setHeader("Content-Disposition",
                "attachment;filename=" + file);
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
   private void createMedecinPdf(HttpServletRequest request) throws DocumentException, FileNotFoundException {
        try {
            HttpSession session = request.getSession();
            int idPatient = (Integer) session.getAttribute("patientId");
            List<Medecin> medecins = serviceFacade.getMedecinDao().findAll();
           String exportName = "Medecin";
            Document document = new Document();
          //  ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, new FileOutputStream("Medecins.pdf"));
           //  PdfWriter.getInstance(document, baos);
            document.open();
            document.add(new Paragraph("civilite,prenom,nom,identifiant,numeroAccreditation,numeroTelephone,adresse"));
            for (Medecin medecin : medecins) {
                document.add(new Paragraph(medecin.getIdUtilisateur().getCivilite() + ","
                        + medecin.getIdUtilisateur().getPrenom() + ","
                        + medecin.getIdUtilisateur().getNom() + ","
                        + medecin.getIdUtilisateur().getIdentifiant() + ","
                        + medecin.getNumeroAccreditation() + ","
                        + medecin.getIdUtilisateur() + ","
                        + medecin.getIdUtilisateur().getAdressePrincipale().getRue() + medecin.getIdUtilisateur().getAdressePrincipale().getCodePostal() + medecin.getIdUtilisateur().getAdressePrincipale().getVille() + medecin.getIdUtilisateur().getAdressePrincipale().getPays()));
            }
            document.close();
        } catch (com.itextpdf.text.DocumentException ex) {
            Logger.getLogger(DownloadExportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    private void createRendezVousCsv(HttpServletRequest request) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("RendezVous.csv"));
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            StringBuilder sb = new StringBuilder();
            HttpSession session = request.getSession();
            int idPatient = (Integer) session.getAttribute("patientId");
            List<RendezVous> rendezVous = serviceFacade.getRendezVousDao().findAll();//findAllRendezVousByPatient(idPatient);
            sb.append("civilite");
            sb.append(",");
            sb.append("prenom");
            sb.append(",");
            sb.append("nom");
            sb.append(",");
            sb.append("identifiant");
            sb.append(",");
            sb.append("numeroAccreditation");
            sb.append(",");
            sb.append("numeroTelephone");
            sb.append(",");
            sb.append("adresse");
            sb.append(",");
            sb.append("Creneau");
            sb.append(",");
            sb.append("jour");
            sb.append("\r\n");
            for (RendezVous rdv : rendezVous) {
                String jour = dateFormat.format(rdv.getJour());
                sb.append(rdv.getCreneau().getIdMedecin().getIdUtilisateur().getCivilite());
                sb.append(",");
                sb.append(rdv.getCreneau().getIdMedecin().getIdUtilisateur().getPrenom());
                sb.append(",");
                sb.append(rdv.getCreneau().getIdMedecin().getIdUtilisateur().getNom());
                sb.append(",");
                sb.append(rdv.getCreneau().getIdMedecin().getIdUtilisateur().getIdentifiant());
                sb.append(",");
                sb.append(rdv.getCreneau().getIdMedecin().getNumeroAccreditation());
                sb.append(",");
                sb.append(rdv.getCreneau().getIdMedecin().getIdUtilisateur().getNumeroTelephone());
                sb.append(",");
                sb.append(rdv.getCreneau().getIdMedecin().getIdUtilisateur().getAdressePrincipale().getRue()).append(rdv.getCreneau().getIdMedecin().getIdUtilisateur().getAdressePrincipale().getCodePostal()).append(rdv.getCreneau().getIdMedecin().getIdUtilisateur().getAdressePrincipale().getVille()).append(rdv.getCreneau().getIdMedecin().getIdUtilisateur().getAdressePrincipale().getPays());
                sb.append(",");
                sb.append(rdv.getCreneau().getHeureDebut()).append("h").append(rdv.getCreneau().getMinuteDebut()).append("-").append(rdv.getCreneau().getHeureFin()).append("h").append(rdv.getCreneau().getMinuteFin());
                sb.append(",");
                sb.append(jour);
                sb.append("\r\n");
            }
            writer.write(sb.toString());
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ManagePatientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     private void createRendezVousJson(HttpServletRequest request){
         BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("RendezVous.json"));
        } catch (IOException ex) {
            Logger.getLogger(ManagePatientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        HttpSession session = request.getSession();
        int idPatient = (Integer) session.getAttribute("patientId");
        List<RendezVous> rendezVous = serviceFacade.getRendezVousDao().findAll();//findAllRendezVousByPatient(idPatient);
        String jsonUser = new Gson().toJson(rendezVous);
        try {
            writer.write(jsonUser);
        } catch (IOException ex) {
            Logger.getLogger(ManagePatientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ManagePatientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      private void createRendezVousXML(HttpServletRequest request) {
          	AbstractStatisticSingleton mySingleton = SingletonFactory.getFactory(XML_SINGLETON_FACTORY);
                File xmlFile;
                List<RendezVous> lsRendezVous;
                
                lsRendezVous = serviceFacade.getRendezVousDao().findAll();
                xmlFile = mySingleton.toXml(lsRendezVous);
                              
    }
         private void createMedecinCsv(HttpServletRequest request) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("Medecins.csv"));
            StringBuilder sb = new StringBuilder();
            List<Medecin> medecins = serviceFacade.getMedecinDao().findAll();
            sb.append("civilite");
            sb.append(",");
            sb.append("prenom");
            sb.append(",");
            sb.append("nom");
            sb.append(",");
            sb.append("identifiant");
            sb.append(",");
            sb.append("numeroAccreditation");
            sb.append(",");
            sb.append("numeroTelephone");
            sb.append(",");
            sb.append("adresse");
            sb.append("\r\n");
            for (Medecin medecin : medecins) {
                sb.append(medecin.getIdUtilisateur().getCivilite());
                sb.append(",");
                sb.append(medecin.getIdUtilisateur().getPrenom());
                sb.append(",");
                sb.append(medecin.getIdUtilisateur().getNom());
                sb.append(",");
                sb.append(medecin.getIdUtilisateur().getIdentifiant());
                sb.append(",");
                sb.append(medecin.getNumeroAccreditation());
                sb.append(",");
                sb.append(medecin.getIdUtilisateur());
                sb.append(",");
                sb.append(medecin.getIdUtilisateur().getAdressePrincipale().getRue()).append(medecin.getIdUtilisateur().getAdressePrincipale().getCodePostal()).append(medecin.getIdUtilisateur().getAdressePrincipale().getVille()).append(medecin.getIdUtilisateur().getAdressePrincipale().getPays());
                sb.append("\r\n");
            }
            writer.write(sb.toString());
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ManagePatientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
             private void createRendezVousPdf(HttpServletRequest request) throws DocumentException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            HttpSession session = request.getSession();
            int idPatient = (Integer) session.getAttribute("patientId");
            List<RendezVous> rendezVous = serviceFacade.getRendezVousDao().findAll(); //findAllRendezVousByPatient(idPatient);
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("RendezVous.pdf"));
            document.open();
            document.add(new Paragraph("civilite,prenom,nom,identifiant,numeroAccreditation,numeroTelephone,adresse,Creneau,jour"));
            for (RendezVous rdv : rendezVous) {
                String jour = dateFormat.format(rdv.getJour());
                document.add(new Paragraph(rdv.getCreneau().getIdMedecin().getIdUtilisateur().getCivilite() + ","
                        + rdv.getCreneau().getIdMedecin().getIdUtilisateur().getPrenom() + ","
                        + rdv.getCreneau().getIdMedecin().getIdUtilisateur().getNom() + ","
                        + rdv.getCreneau().getIdMedecin().getIdUtilisateur().getIdentifiant() + ","
                        + rdv.getCreneau().getIdMedecin().getNumeroAccreditation() + ","
                        + rdv.getCreneau().getIdMedecin().getIdUtilisateur().getNumeroTelephone() + ","
                        + rdv.getCreneau().getIdMedecin().getIdUtilisateur().getAdressePrincipale().getRue() + rdv.getCreneau().getIdMedecin().getIdUtilisateur().getAdressePrincipale().getCodePostal() + rdv.getCreneau().getIdMedecin().getIdUtilisateur().getAdressePrincipale().getVille() + rdv.getCreneau().getIdMedecin().getIdUtilisateur().getAdressePrincipale().getPays()+ ","
                        + rdv.getCreneau().getHeureDebut() + "h" + rdv.getCreneau().getMinuteDebut() + "-" + rdv.getCreneau().getHeureFin() + "h" + rdv.getCreneau().getMinuteFin() + ","
                        + jour));
            }
            document.close();
        } catch (IOException ex) {
            Logger.getLogger(ManagePatientServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (com.itextpdf.text.DocumentException ex) {
            Logger.getLogger(DownloadExportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
    public void destroy() {
    }
}
