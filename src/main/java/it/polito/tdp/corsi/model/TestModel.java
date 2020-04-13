package it.polito.tdp.corsi.model;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Model model = new Model();
		
		System.out.println(model.getCorsiByPd(1));

		System.out.println(model.getNumStudenti(1));
		
		Corso c = new Corso("01KSUPG",null,null,null);
		
		System.out.println(model.getStudentiByCorso(c));
	}

}
