package it.prova.gestioneproprietari.test;

import java.text.SimpleDateFormat;

import it.prova.gestioneproprietari.dao.EntityManagerUtil;
import it.prova.gestioneproprietari.model.Automobile;
import it.prova.gestioneproprietari.model.Proprietario;
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

			System.out.println("\n");
			testInsertEListProprietario(proprietarioService);

			System.out.println("\n");
			testDeleteAutomobile(automobileService, proprietarioService);

			System.out.println("\n");
			testCountByProprietariWhereAnnoImmatricolazioneFrom(automobileService, proprietarioService);

			System.out.println("\n");
			testFindAllByCodiceFiscaleIniziaCon(proprietarioService, automobileService);
			
			System.out.println("\n");
			testFindAllByError(proprietarioService, automobileService);

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}

	}

	private static void testInsertEListAutomobile(AutomobileService automobileService) throws Exception {
		System.out.println(".......testInsertEListAutomobile inizio.............");

		Automobile nuovaAutomobile = new Automobile("Honda", "Civic", "AB123CD", 2000);
		if (nuovaAutomobile.getId() != null)
			throw new RuntimeException("testInsertEListAutomobile failed: record gia' inserito.");
		automobileService.insert(nuovaAutomobile);
		if (automobileService.list().size() == 0)
			throw new RuntimeException("testInsertEListAutomobile failed: record non inserito correttamente.");

		automobileService.delete(nuovaAutomobile);

		System.out.println(".......testInsertEListAutomobile passed..............");
	}

	private static void testInsertEListProprietario(ProprietarioService proprietarioService) throws Exception {
		System.out.println(".......testInsertEListProprietario inizio.............");
		Proprietario nuovoProprietario = new Proprietario("Pinco", "Pallino", "PNCPALL123",
				new SimpleDateFormat("dd-MM-yyyy").parse("01-07-1990"));
		if (nuovoProprietario.getId() != null)
			throw new RuntimeException("testInsertEListProprietario failed: record gia' inserito.");
		proprietarioService.insert(nuovoProprietario);
		if (proprietarioService.list().size() == 0)
			throw new RuntimeException("testInsertEListProprietario failed: record non inserito correttamente.");

		proprietarioService.delete(nuovoProprietario);

		System.out.println(".......testInsertEListProprietario passed..............");
	}

	private static void testDeleteAutomobile(AutomobileService automobileService,
			ProprietarioService proprietarioService) throws Exception {
		System.out.println(".......testDeleteAutomobile inizio.............");
		Automobile nuovaAutomobile = new Automobile("Honda", "Civic", "AB123CD", 2000);
		Proprietario nuovoProprietario = new Proprietario("Pinco", "Pallino", "PNCPALL123",
				new SimpleDateFormat("dd-MM-yyyy").parse("01-07-1990"));
		proprietarioService.insert(nuovoProprietario);
		nuovaAutomobile.setProprietario(nuovoProprietario);

		automobileService.insert(nuovaAutomobile);

		Long idDeleted = nuovaAutomobile.getId();

		automobileService.delete(nuovaAutomobile);
		proprietarioService.delete(nuovoProprietario);

		if (automobileService.get(idDeleted) != null)
			throw new RuntimeException("testDeleteAutomobile failed: eliminazione non eseguita correttamente.");

		System.out.println(".......testDeleteAutomobile passed..............");
	}

	private static void testCountByProprietariWhereAnnoImmatricolazioneFrom(AutomobileService automobileService,
			ProprietarioService proprietarioService) throws Exception {

		System.out.println(".......testCountByProprietariWhereAnnoImmatricolazioneFrom inizio.............");
		Automobile nuovaAutomobile1 = new Automobile("Honda", "Civic", "BNM123UIO", 2015);
		Automobile nuovaAutomobile2 = new Automobile("Honda", "Civic", "BNM123UIO", 2018);

		Proprietario nuovoProprietario1 = new Proprietario("Pinco", "Pallino", "PNCPALL123",
				new SimpleDateFormat("dd-MM-yyyy").parse("01-07-1990"));
		proprietarioService.insert(nuovoProprietario1);

		nuovaAutomobile1.setProprietario(nuovoProprietario1);

		automobileService.insert(nuovaAutomobile1);

		Proprietario nuovoProprietario2 = new Proprietario("Pinco", "Palletto", "PNCPALL903",
				new SimpleDateFormat("dd-MM-yyyy").parse("01-07-1990"));
		proprietarioService.insert(nuovoProprietario2);
		nuovaAutomobile2.setProprietario(nuovoProprietario2);

		automobileService.insert(nuovaAutomobile2);

		int annoInput = 2000;

		if (proprietarioService.countByProprietariWhereAnnoImmatricolazioneFrom(annoInput) != 2)
			throw new RuntimeException(
					"testCountByProprietariWhereAnnoImmatricolazioneFrom failed: risultato inatteso.");

		automobileService.delete(nuovaAutomobile2);
		automobileService.delete(nuovaAutomobile1);
		proprietarioService.delete(nuovoProprietario2);
		proprietarioService.delete(nuovoProprietario1);

		System.out.println(".......testCountByProprietariWhereAnnoImmatricolazioneFrom passed..............");

	}

	private static void testFindAllByCodiceFiscaleIniziaCon(ProprietarioService proprietarioService,
			AutomobileService automobileService) throws Exception {

		System.out.println(".......testFindAllByCodiceFiscaleIniziaCon inizio.............");

		Automobile nuovaAutomobile1 = new Automobile("Honda", "Civic", "BNM123UIO", 2015);
		Automobile nuovaAutomobile2 = new Automobile("Honda", "Civic", "BNM123UIO", 2018);

		Proprietario nuovoProprietario1 = new Proprietario("Pinco", "Pallino", "PNCPALL123",
				new SimpleDateFormat("dd-MM-yyyy").parse("01-07-1990"));
		proprietarioService.insert(nuovoProprietario1);

		nuovaAutomobile1.setProprietario(nuovoProprietario1);

		automobileService.insert(nuovaAutomobile1);

		Proprietario nuovoProprietario2 = new Proprietario("Pinco", "Palletto", "PNCPALL903",
				new SimpleDateFormat("dd-MM-yyyy").parse("01-07-1990"));
		proprietarioService.insert(nuovoProprietario2);
		nuovaAutomobile2.setProprietario(nuovoProprietario2);

		automobileService.insert(nuovaAutomobile2);

		String iniziale = "pn";

		if (automobileService.cercaTutteConProprietarioCodiceFiscaleIniziaCon(iniziale).size() != 2)
			throw new RuntimeException("testFindAllByCodiceFiscaleIniziaCon failed: risultato inatteso.");

		automobileService.delete(nuovaAutomobile2);
		automobileService.delete(nuovaAutomobile1);
		proprietarioService.delete(nuovoProprietario2);
		proprietarioService.delete(nuovoProprietario1);

		System.out.println(".......testFindAllByCodiceFiscaleIniziaCon passed..............");

	}

	private static void testFindAllByError(ProprietarioService proprietarioService, AutomobileService automobileService)
			throws Exception {

		
		System.out.println(".......testFindAllByError inizio.............");

		Automobile nuovaAutomobile1 = new Automobile("Honda", "Civic", "BNM123UIO", 2015);
		Automobile nuovaAutomobile2 = new Automobile("Honda", "Civic", "BNM123UIO", 2018);

		Proprietario nuovoProprietario1 = new Proprietario("Pinco", "Pallino", "PNCPALL123",
				new SimpleDateFormat("dd-MM-yyyy").parse("01-07-2010"));
		proprietarioService.insert(nuovoProprietario1);

		nuovaAutomobile1.setProprietario(nuovoProprietario1);

		automobileService.insert(nuovaAutomobile1);

		Proprietario nuovoProprietario2 = new Proprietario("Pinco", "Palletto", "PNCPALL903",
				new SimpleDateFormat("dd-MM-yyyy").parse("01-07-2010"));
		proprietarioService.insert(nuovoProprietario2);
		nuovaAutomobile2.setProprietario(nuovoProprietario2);

		automobileService.insert(nuovaAutomobile2);

		String iniziale = "pn";

		if (automobileService.cercaTutteConErrore().size() != 2)
			throw new RuntimeException("testFindAllByError failed: risultato inatteso.");

		automobileService.delete(nuovaAutomobile2);
		automobileService.delete(nuovaAutomobile1);
		proprietarioService.delete(nuovoProprietario2);
		proprietarioService.delete(nuovoProprietario1);

		System.out.println(".......testFindAllByError passed..............");
		
	}

}
