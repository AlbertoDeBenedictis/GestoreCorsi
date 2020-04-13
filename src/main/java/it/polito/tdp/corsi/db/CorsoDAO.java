package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Studente;

public class CorsoDAO {
	
	public boolean esisteCorso(Corso corso) {
		String sql = "select * from corso where codins = ?";
		
		try {
			Connection conn = DBConnect.getConnection(); // ottengo la connesione
			PreparedStatement st = conn.prepareStatement(sql); // preparo lo statement
			
			st.setString(1, corso.getCodins());
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				conn.close();
				return true;
			}else {
			conn.close();
			return false;
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Map<String, Integer> getDivisioneCDS(Corso c){
			
		String sql = "SELECT s.CDS, COUNT(*) as tot\n" + 
				"from studente as s, iscrizione as i\n" + 
				"where s.matricola = i.matricola AND s.cds <> \"\" AND i.codins = ?\n" + 
				"group by s.cds";
		
		Map<String, Integer> divisione = new HashMap<>();
		
		try {
			Connection conn = DBConnect.getConnection(); // ottengo la connesione
			PreparedStatement st = conn.prepareStatement(sql); // preparo lo statement
			
			st.setString(1, c.getCodins());
			ResultSet rs = st.executeQuery();
			
			
			while(rs.next()) {
				
				divisione.put(rs.getString("CDS"), rs.getInt("tot"));

			}
			conn.close();
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return divisione;
	}
	
	

	public List<Corso> getCorsiByPd(Integer pd) {

		List<Corso> risultato = new ArrayList<>();

		String sql = "SELECT * FROM corso WHERE pd = ?";

		try {
			Connection conn = DBConnect.getConnection(); // ottengo la connesione
			PreparedStatement st = conn.prepareStatement(sql); // preparo lo statement

			st.setInt(1, pd); // imposto il parametro

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"),
						rs.getInt("pd"));

				risultato.add(c);
			}

			conn.close(); // IMPORTANTISSIMO!!!

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return risultato;
	}

	public Map<Corso, Integer> getNumStudenti(Integer pd) {

		Map<Corso, Integer> risultato = new HashMap<>();

		String sql = "SELECT c.codins, crediti, nome, pd, COUNT(*) as totaleIscritti\n"
				+ "FROM corso as c, iscrizione as i\n" + "WHERE pd = ? AND c.codins = i.codins  \n"
				+ "GROUP BY c.codins, crediti, nome";

		try {
			Connection conn = DBConnect.getConnection(); // ottengo la connesione
			PreparedStatement st = conn.prepareStatement(sql); // preparo lo statement

			st.setInt(1, pd); // imposto il parametro

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"),
						rs.getInt("pd"));

				Integer numIscritti = rs.getInt("totaleIscritti");

				risultato.put(c, numIscritti);
			}

			conn.close(); // IMPORTANTISSIMO!!!

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return risultato;
	}

	public List<Studente> getStudentiByCorso(Corso corso) { // passiamo gli oggetti!!!

		List<Studente> risultato = new LinkedList<>();

		String sql = "select s.matricola, s.nome, s.cognome, s.CDS " + 
				"from studente as s, iscrizione as i " + 
				"where s.matricola = i.matricola and i.codins = ?";

		try {

			Connection conn = DBConnect.getConnection(); // ottengo la connesione
			PreparedStatement st = conn.prepareStatement(sql); // preparo lo statement

			st.setString(1, corso.getCodins());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Studente s = new Studente(rs.getInt("matricola"), rs.getString("nome"), rs.getString("cognome"),
						rs.getString("CDS"));
				
				risultato.add(s);
			}
			conn.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return risultato;
	}

}
