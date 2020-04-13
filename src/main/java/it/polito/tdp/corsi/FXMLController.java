/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.corsi;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Model;
import it.polito.tdp.corsi.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	Model model;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtPeriodo"
	private TextField txtPeriodo; // Value injected by FXMLLoader

	@FXML // fx:id="txtCorso"
	private TextField txtCorso; // Value injected by FXMLLoader

	@FXML // fx:id="btnCorsiPerPeriodo"
	private Button btnCorsiPerPeriodo; // Value injected by FXMLLoader

	@FXML // fx:id="btnStudenti"
	private Button btnStudenti; // Value injected by FXMLLoader

	@FXML // fx:id="btnNumStudenti"
	private Button btnNumStudenti; // Value injected by FXMLLoader

	@FXML // fx:id="divisioneStudenti"
	private Button divisioneStudenti; // Value injected by FXMLLoader

	@FXML // fx:id="txtRisultato"
	private TextArea txtRisultato; // Value injected by FXMLLoader

	@FXML
	void corsiPerPeriodo(ActionEvent event) {
		txtRisultato.clear();

		String pdString = txtPeriodo.getText();

		// 1. CONTROLLO DELL'INPUT
		Integer pd;

		// 1.1. E' UN NUMERO?
		try {
			pd = Integer.parseInt(pdString);
		} catch (NumberFormatException nfe) {
			txtRisultato.setText("Devi inserire un numero! (1 o 2)");
			return;
		}
		// 1.2. E' UN NUMERO COMPRESO TRA 1 E 2?
		if (!pd.equals(1) && !pd.equals(2)) {
			txtRisultato.setText("Devi inserire un numero! (1 o 2)");
			return;
		}

		// 2. INPUT CORRETTO VERIFICATO, LO METTO COME PARAMETRO NELLA FUNZIONE DEL
		// MODEL
		List<Corso> corsi = this.model.getCorsiByPd(pd);

		for (Corso c : corsi) {
			txtRisultato.appendText(c.toString() + "\n");
		}

	}

	@FXML
	void stampaDivisione(ActionEvent event) {

		// Dato un corso, ci aspettiamo una divisione di questo tipo:
		// Informatici 28
		// Gestionali 145

		txtRisultato.clear();

		String codins = txtCorso.getText();

		// Posso controllare se codins è il codice di un corso
		Corso c = new Corso(codins, null, null, null);

		if (!this.model.esisteCorso(c)) {
			txtRisultato.appendText("Il corso non esiste!");
			return;
		}

		Map<String, Integer> stat = this.model.getDivisioneCDS(new Corso(codins, null, null, null));

		for(String s: stat.keySet()) {
			txtRisultato.appendText(s+" "+ stat.get(s)+ "\n");
		}
		
	}

	@FXML
	void stampaNumStudenti(ActionEvent event) {

		txtRisultato.clear();

		String pdString = txtPeriodo.getText();

		// 1. CONTROLLO DELL'INPUT
		Integer pd;

		// 1.1. E' UN NUMERO?
		try {
			pd = Integer.parseInt(pdString);
		} catch (NumberFormatException nfe) {
			txtRisultato.setText("Devi inserire un numero! (1 o 2)");
			return;
		}
		// 1.2. E' UN NUMERO COMPRESO TRA 1 E 2?
		if (!pd.equals(1) && !pd.equals(2)) {
			txtRisultato.setText("Devi inserire un numero! (1 o 2)");
			return;
		}

		// 2. INPUT CORRETTO VERIFICATO, LO METTO COME PARAMETRO NELLA FUNZIONE DEL
		// MODEL

		Map<Corso, Integer> statistiche = this.model.getNumStudenti(pd);

		for (Corso c : statistiche.keySet()) {
			txtRisultato.appendText(c.getNome() + " - " + statistiche.get(c) + "\n");
		}

	}

	@FXML
	void stampaStudenti(ActionEvent event) {
		txtRisultato.clear();

		String codins = txtCorso.getText();

		// Posso controllare se codins è il codice di un corso
		Corso c = new Corso(codins, null, null, null);

		if (!this.model.esisteCorso(c)) {
			txtRisultato.appendText("Il corso non esiste!");
			return;
		}

		List<Studente> studenti = this.model.getStudentiByCorso(c);

		if (studenti.size() == 0) {
			txtRisultato.setText("Il corso non ha studenti iscritti");
		}

		for (Studente s : studenti) {
			txtRisultato.appendText(s.toString() + "\n");
		}

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert txtPeriodo != null : "fx:id=\"txtPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtCorso != null : "fx:id=\"txtCorso\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnCorsiPerPeriodo != null : "fx:id=\"btnCorsiPerPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnStudenti != null : "fx:id=\"btnStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnNumStudenti != null : "fx:id=\"btnNumStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
		assert divisioneStudenti != null : "fx:id=\"divisioneStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

	}

	public void setModel(Model model) {

		this.model = model;

	}
}
