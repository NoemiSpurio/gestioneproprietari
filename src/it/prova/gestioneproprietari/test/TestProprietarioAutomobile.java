package it.prova.gestioneproprietari.test;

import java.text.SimpleDateFormat;

import it.prova.gestioneproprietari.dao.EntityManagerUtil;
import it.prova.gestioneproprietari.model.Automobile;
import it.prova.gestioneproprietari.service.MyServiceFactory;
import it.prova.gestioneproprietari.service.automobile.AutomobileService;
import it.prova.gestioneproprietari.service.proprietario.ProprietarioService;

public class TestProprietarioAutomobile {

	public static void main(String[] args) {

		ProprietarioService proprietarioService = MyServiceFactory.getProprietarioServiceInstance();
		AutomobileService automobileService = MyServiceFactory.getAutomobileServiceInstance();

		try {
			
			System.out.println("\n");
			testInsertEListAutomobile(automobileService);

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}

	}

	private static void testInsertEListAutomobile(AutomobileService automobileService) throws Exception {
		System.out.println(".......testInsertEListAutomobile inizio.............");

		Automobile nuovaAutomobile = new Automobile("Honda", "Civic", "AB123CD",
				new SimpleDateFormat("dd-MM-yyyy").parse("01-07-2010"));
		if(nuovaAutomobile.getId() != null)
			throw new RuntimeException("testInsertEListAutomobile failed: record gia' inserito.");
		automobileService.insert(nuovaAutomobile);
		if(automobileService.list().size() == 0)
			throw new RuntimeException("testInsertEListAutomobile failed: record non inserito correttamente.");
		System.out.println("testInsertEListAutomobile passed.");
	}

}
