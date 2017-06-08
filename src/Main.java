import java.util.ArrayList;

import beans.Address;
import beans.Ad;
import beans.Landlord;
import beans.Rental;
import beans.User;
import dao.DatabaseManagerTransaction;
import model.business.BusinessRulesManager;

public class Main {
	
	public static void main(String[] args) throws Exception{
		BusinessRulesManager br = new BusinessRulesManager();
		DatabaseManagerTransaction databaseTransaction = new DatabaseManagerTransaction();
		Address address = new Address("Na fabela", "dasd", 10, "hfsaid", "SP", "fafsf", "fsdad");
//		br.registerNewUser("Rainer bd", "vidalokaBandida@gmail.com", address, "555-1565",
//				"wagem", "Noisesak");
	//	System.out.println("dsajk");
		Landlord landlord = new Landlord("wesley", "2010-03-10", "ws@gmail.com", address, "6923-9299", 0.0, "Bandido Ltda", "eita","sfs");
		landlord.setLandlordId(4);
		br.registerNewUser(landlord.getName(), landlord.getBirthday(), landlord.getEmail(), address, landlord.getPhone(),
							landlord.getCompanyInformation(), landlord.getPassword(), "das");
		User user = br.loginUser("ws@gmail.com", "eita");
		//System.out.println(user.getName() + " " + user.getBirthday() + " " + user.getEmail() + " " + user.getPhone());
		//Landlord landlord = br.getLandlordByUserId(user.getUserId());
		//Anuncio anuncio = new Anuncio(landlord, "Santana 1990 branco", "Bom de lata, com tudo em cima", 200.0, 1, "Santanao dahora ", 0.0,2);
	///	br.insertProduct("Santana Casa", "AQUI", "Santanao de ladrao carro de Casa", 20.0, "Bom de lata", 
		//		landlord,  2);
		//ArrayList<Anuncio> anuncio = br.findByKeyWord("de black" , 0, 10);
 		//for(int i = 0; i < anuncio.size();i++) {
 			//System.out.println(anuncio.get(i).getTitle() + " " + anuncio.get(i).getPrice() + " " + anuncio.get(i).getRate());
 		//}
		
		//Rental rental = br.getRental(2);
		//System.out.println(rental.getRentalId() + " " + rental.getDateFinishTransaction() + " " +rental.getStatus());
		//br.updateTransactionByLandlord(rental, 1, 4.1);
		//br.updateTransactionByRenter(rental, 1, 3.5, 2.4);
		//System.out.println(database.newAddress(new Address("fs6a", "dasd", 10, "hfsaid", "dd", "fafsf", "fsdad")));
		//br.finishSolicitation(1);
		//br.startSolicitation(1,1,2, "2020-03-10", "2010-03-17");
		//br.acceptSolicitation(3);
		
		//br.deleteUser(user);
		//br.deleteAnuncio(1);
		//Rental rental = br.getRental(1);
		
	//	System.out.println(rental.getDateStartTransaction());
		
		
	}
}
