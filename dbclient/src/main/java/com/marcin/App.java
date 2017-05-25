package com.marcin;

import java.sql.SQLException;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
	
	private static final Logger log = LoggerFactory.getLogger(App.class);
	public static void main(String[] args) {
		BasicConfigurator.configure();
		
		try {
			PrepareData.createTables();
			PrepareData.insertData();
			
			log.debug("------ (1) Znajd� numery i nazwiska wszystkich os�b zarejestrowanych jako studenci. ------");
			log.debug(Queries.selectAllStudents());
			
			log.debug("------ (2) Znajd� numery i nazwiska wszystkich os�b, kt�re nie s� zapisane na �aden przedmiot. ------");
			log.debug(Queries.selectAllStudentsNoAssignedTOClasses());
			
			log.debug("------ (3) Znajd� numery i nazwiska os�b p�ci �e�skiej ucz�cych si� o egzystencjali�mie w 20 wieku. ------");
			log.debug(Queries.selectWomensTakePartInExistentialismIn20thCentury());
			
			log.debug("------ (4) Znajd� nazwy wszystkich wydzia��w, na kt�rych przedmioty nikt si� nie zapisa�. ------");
			log.debug(Queries.selectFacultiesWithoutStudents());
			
			log.debug("------ (5) Znajd� wiek najstarszej osoby ucz�cej si� o prawie pracy. ------");
			log.debug(Queries.selectTheOldestStudentOfLaborLaw());
			
			log.debug("------ (6) Znajd� nazwy przedmiot�w, na kt�re zapisa�y si� przynajmniej dwie osoby. ------");
			log.debug(Queries.selectClassesWithMoreThanTwoStudents());
						
			log.debug("------ (7) Znajd� poziomy os�b studiuj�cych i �redni wiek os�b na ka�dym poziomie. ------");
			log.debug(Queries.selectLevelsAndAverageAge());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
