package it.prova.gestioneproprietari.service.automobile;

import java.util.List;

import it.prova.gestioneproprietari.dao.automobile.AutomobileDAO;
import it.prova.gestioneproprietari.model.Automobile;

public interface AutomobileService {

	public List<Automobile> list() throws Exception;
	
	public Automobile get(Long id) throws Exception;
	
	public void update(Automobile o) throws Exception;
	
	public void insert(Automobile o) throws Exception;
	
	public void delete(Automobile o) throws Exception;
	
	public void setAutomobileDAO(AutomobileDAO automobileDAO);
	
	public List<Automobile> cercaTutteConProprietarioCodiceFiscaleIniziaCon(String iniziale) throws Exception;
	
	public List<Automobile> cercaTutteConErrore() throws Exception;
}
