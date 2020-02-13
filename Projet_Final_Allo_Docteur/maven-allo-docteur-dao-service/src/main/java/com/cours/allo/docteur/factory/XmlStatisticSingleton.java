/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.factory;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

import com.cours.allo.docteur.dao.entities.RendezVous;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 *
 * @author elhad
 */
public class XmlStatisticSingleton extends AbstractStatisticSingleton {
    public static String className = XmlStatisticSingleton.class.getName();
    private static final Log log = LogFactory.getLog(XmlStatisticSingleton.class);
    final String personnesXmlPathFile = "personnesXml.xml";
    final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
     private XmlStatisticSingleton() {
         toXml(rdv);
    }
  private static class Singleton {

        private final static XmlStatisticSingleton instance = new XmlStatisticSingleton();
    }

    public static XmlStatisticSingleton getInstance() {
        return Singleton.instance;
    }
  

    @Override
    public File toXml(List<RendezVous> rdv) {
        Document doc;
		Element root;
		Iterator<RendezVous> it;
		RendezVous curRdv;
		Element curRdvElem;
		File ret;
		String xmlStr;
		Writer writer;

		it = rdv.iterator();
		ret = new File("RendezVous.xml");

		try {
			doc = (Document) DocumentHelper.createDocument();
			root = doc.addElement("rendezvous");

			while (it.hasNext()) {
				curRdv = it.next();
				curRdvElem = root.addElement("rdv");
				curRdvElem.addAttribute("date", curRdv.getJour().toString());
				curRdvElem.addElement("civilite").addText(
					curRdv.getPatientRdv().getUserPatient().getCivilite());
				curRdvElem.addElement("nom").addText(
					curRdv.getPatientRdv().getUserPatient().getNom());
				curRdvElem.addElement("prenom").addText(
					curRdv.getPatientRdv().getUserPatient().getPrenom());
				curRdvElem.addElement("naissance")
				.addText(curRdv.getPatientRdv().getUserPatient().getDateNaissance().toString());
			}

			xmlStr = doc.asXML();
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ret), "UTF-8"));
			writer.write(xmlStr);
		} catch (Exception e) {
			log.error(e.getMessage());
			ret = null;
		}

		return ret;

    }



}
