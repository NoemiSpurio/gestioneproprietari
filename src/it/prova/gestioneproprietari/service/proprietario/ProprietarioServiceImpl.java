package it.prova.gestioneproprietari.service.proprietario;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneproprietari.dao.EntityManagerUtil;
import it.prova.gestioneproprietari.dao.proprietario.ProprietarioDAO;
import it.prova.gestioneproprietari.model.Proprietario;

public class ProprietarioServiceImpl implements ProprietarioService {
	
	private ProprietarioDAO proprietarioDAO;

	@Override
	public List<Proprietario> list() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			
			proprietarioDAO.setEntityManager(entityManager);
			return proprietarioDAO.list();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw e;
			
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Proprietario get(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			
			proprietarioDAO.setEntityManager(entityManager);
			return proprietarioDAO.get(id);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw e;
			
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void update(Proprietario proprietarioInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			
			entityManager.getTransaction().begin();

			proprietarioDAO.setEntityManager(entityManager);

			proprietarioDAO.update(proprietarioInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void insert(Proprietario proprietarioInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			
			entityManager.getTransaction().begin();

			proprietarioDAO.setEntityManager(entityManager);

			proprietarioDAO.insert(proprietarioInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void delete(Proprietario proprietarioInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			
			entityManager.getTransaction().begin();

			proprietarioDAO.setEntityManager(entityManager);

			proprietarioDAO.delete(proprietarioInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void setProprietarioDAO(ProprietarioDAO proprietarioDAO) {
		this.proprietarioDAO = proprietarioDAO;
	}

	@Override
	public int countByProprietariWhereAnnoImmatricolazioneFrom(int input) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			proprietarioDAO.setEntityManager(entityManager);
			return proprietarioDAO.countByProprietariWhereAnnoImmatricolazioneFrom(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

}
