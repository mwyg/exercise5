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
			
			log.debug("------ (1) ZnajdŸ numery i nazwiska wszystkich osób zarejestrowanych jako studenci. ------");
			log.debug(Queries.selectAllStudents());
			
			log.debug("------ (2) ZnajdŸ numery i nazwiska wszystkich osób, które nie s¹ zapisane na ¿aden przedmiot. ------");
			log.debug(Queries.selectAllStudentsNoAssignedTOClasses());
			
			log.debug("------ (3) ZnajdŸ numery i nazwiska osób p³ci ¿eñskiej ucz¹cych siê o egzystencjaliŸmie w 20 wieku. ------");
			log.debug(Queries.selectWomensTakePartInExistentialismIn20thCentury());
			
			log.debug("------ (4) ZnajdŸ nazwy wszystkich wydzia³ów, na których przedmioty nikt siê nie zapisa³. ------");
			log.debug(Queries.selectFacultiesWithoutStudents());
			
			log.debug("------ (5) ZnajdŸ wiek najstarszej osoby ucz¹cej siê o prawie pracy. ------");
			log.debug(Queries.selectTheOldestStudentOfLaborLaw());
			
			log.debug("------ (6) ZnajdŸ nazwy przedmiotów, na które zapisa³y siê przynajmniej dwie osoby. ------");
			log.debug(Queries.selectClassesWithMoreThanTwoStudents());
						
			log.debug("------ (7) ZnajdŸ poziomy osób studiuj¹cych i œredni wiek osób na ka¿dym poziomie. ------");
			log.debug(Queries.selectLevelsAndAverageAge());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
