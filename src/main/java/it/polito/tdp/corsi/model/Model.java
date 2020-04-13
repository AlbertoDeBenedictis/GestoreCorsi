package it.polito.tdp.corsi.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;

public class Model {

	CorsoDAO dao;
	
	

	public Model() {
		dao = new CorsoDAO(); // se te lo scordi di nuovo ti stacco la testa

	}
	
	public Map<String, Integer> getDivisioneCDS(Corso c){
		
		// TENTATIVO COMPLICATO A LIVELLO DI CODICE, VIENE MEGLIO RAGIONARE SU SQL
		/*List<Studente> studenti = dao.getStudentiByCorso(c);
		
		Map<String, Integer> statistiche = new HashMap<>();
		
		for(Studente s: studenti) {
			if(statistiche.containsKey(s.getCds())) {
				statistiche.put(s.getCds(), statistiche.get(s.getCds()+1));
			}else {
				statistiche.put(s.getCds(), 1);
			}
		}
		return statistiche;
	
		 */
		return dao.getDivisioneCDS(c);
		
	}
	

	public boolean esisteCorso(Corso c) {

		return dao.esisteCorso(c);
	}

	public List<Corso> getCorsiByPd(Integer pd) {

		return dao.getCorsiByPd(pd);
	}

	public Map<Corso, Integer> getNumStudenti(Integer pd) {

		return dao.getNumStudenti(pd);
	}

	public List<Studente> getStudentiByCorso(Corso c) {

		return dao.getStudentiByCorso(c);

	}

}
