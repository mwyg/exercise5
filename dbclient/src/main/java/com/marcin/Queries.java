package com.marcin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Queries {

	private static HsqlConnect hsqlConnect = HsqlConnect.getDbCon();

	public static String selectAllStudents() throws SQLException {

		final String QUERY = "SELECT id, name from STUDENT";
		ResultSet result = hsqlConnect.query(QUERY);
		StringBuilder sb = new StringBuilder();

		while (result.next()) {
			sb.append("\n" + result.getInt("id") + " " + result.getString("name"));
		}
		return sb.toString();
	}

	public static String selectAllStudentsNoAssignedTOClasses() throws SQLException {

		final String QUERY = "SELECT id, name from STUDENT WHERE id not in (select DISTINCT fkey_student from ENROLLMENT)";
		ResultSet result = hsqlConnect.query(QUERY);
		StringBuilder sb = new StringBuilder();

		while (result.next()) {
			sb.append("\n" + result.getInt("id") + " " + result.getString("name"));
		}

		return sb.toString();
	}
	
	public static String selectWomensTakePartInExistentialismIn20thCentury() throws SQLException {

		final String QUERY = "SELECT id, name from STUDENT WHERE sex='female' and id IN (SELECT DISTINCT e.FKEY_STUDENT FROM ENROLLMENT e JOIN CLASS c ON e.FKEY_CLASS=c.ID and NAME = 'Existentialism in 20th century')";
		ResultSet result = hsqlConnect.query(QUERY);
		StringBuilder sb = new StringBuilder();

		while (result.next()) {
			sb.append("\n" + result.getInt("id") + " " + result.getString("name"));
		}

		return sb.toString();
	}
	
	public static String selectFacultiesWithoutStudents() throws SQLException {

		final String QUERY = "SELECT name FROM FACULTY where ID in(SELECT fkey_faculty FROM CLASS WHERE ID not in (SELECT DISTINCT fkey_class from ENROLLMENT))";
		ResultSet result = hsqlConnect.query(QUERY);
		StringBuilder sb = new StringBuilder();

		while (result.next()) {
			sb.append("\n" + result.getString("name"));
		}

		return sb.toString();
	}
	
	public static String selectTheOldestStudentOfLaborLaw() throws SQLException {
		final String QUERY = "SELECT max(AGE) as age from STUDENT where id in (SELECT FKEY_STUDENT from ENROLLMENT where FKEY_CLASS = (SELECT id from CLASS where NAME='Introduction to labour law'))";
		ResultSet result = hsqlConnect.query(QUERY);
		StringBuilder sb = new StringBuilder();

		while (result.next()) {
			sb.append("\n" + result.getInt("age"));
		}

		return sb.toString();
	}
	
	public static String selectClassesWithMoreThanTwoStudents() throws SQLException {
		final String QUERY = "SELECT NAME FROM CLASS WHERE id in (SELECT FKEY_CLASS FROM ENROLLMENT GROUP BY FKEY_CLASS HAVING count(FKEY_CLASS) >= 2)";
		ResultSet result = hsqlConnect.query(QUERY);
		StringBuilder sb = new StringBuilder();

		while (result.next()) {
			sb.append("\n" + result.getString("name"));
		}

		return sb.toString();
	}
	
	public static String selectLevelsAndAverageAge() throws SQLException {
		final String QUERY = "SELECT LEVEL, avg(AGE) as a FROM STUDENT where LEVEL in (SELECT DISTINCT LEVEL FROM STUDENT) GROUP BY LEVEL";
		ResultSet result = hsqlConnect.query(QUERY);
		StringBuilder sb = new StringBuilder();

		while (result.next()) {
			sb.append("\n" + result.getString("level") + " " + result.getInt("a"));
		}

		return sb.toString();
	}

}
