package it.prova.gestioneproprietari.service.proprietario;

import java.util.List;

import it.prova.gestioneproprietari.dao.proprietario.ProprietarioDAO;
import it.prova.gestioneproprietari.model.Proprietario;

public interface ProprietarioService {

	public List<Proprietario> list() throws Exception;
	
	public Proprietario get(Long id) throws Exception;
	
	public void update(Proprietario o) throws Exception;
	
	public void insert(Proprietario o) throws Exception;
	
	public void delete(Proprietario o) throws Exception;
	
	public void setProprietarioDAO(ProprietarioDAO proprietarioDAO);
}
