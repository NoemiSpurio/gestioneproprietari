package it.prova.gestioneproprietari.dao;

import it.prova.gestioneproprietari.dao.automobile.AutomobileDAO;
import it.prova.gestioneproprietari.dao.automobile.AutomobileDAOImpl;
import it.prova.gestioneproprietari.dao.proprietario.ProprietarioDAO;
import it.prova.gestioneproprietari.dao.proprietario.ProprietarioDAOImpl;

public class MyDaoFactory {

	private static ProprietarioDAO proprietarioDAOInstance = null;
	private static AutomobileDAO automobileDAOInstance = null;
	
	public static ProprietarioDAO getProprietarioDaoInstance() {
		if(proprietarioDAOInstance == null)
			proprietarioDAOInstance = new ProprietarioDAOImpl();
		return proprietarioDAOInstance;
	}
	
	public static AutomobileDAO getAutomobileDaoInstance() {
		if(automobileDAOInstance == null)
			automobileDAOInstance = new AutomobileDAOImpl();
		return automobileDAOInstance;
	}
}
