import beans.Address;
import beans.Anuncio;
import beans.Landlord;
import beans.Rental;
import beans.Renter;
import beans.User;
import dao.DataBaseManager;
import model.business.BusinessRulesManager;

public class Main {
	
	public static void main(String[] args) throws Exception{
		BusinessRulesManager br = new BusinessRulesManager();
		Address address = new Address("Na fabela", "dasd", 10, "hfsaid", "SP", "fafsf", "fsdad");
		//br.registerNewUser("Rainer bd", "vidalokaBandida@gmail.com", address, "555-1565",
			//	"wagem", "Noisesak");
	//	System.out.println("dsajk");
	//	Landlord landlord = new Landlord("wesley santos", "2020-03-10", "acabeleiragad@gmail.com", address, "68179-9299", 0.0, "Bandido Ltda", "noses","sfs");
		//landlord.setLandlordId(4);
		
		//br.registerNewUser(landlord.getName(), landlord.getBirthday(), landlord.getEmail(), address, landlord.getPhone(),
			//				landlord.getCompanyInformation(), landlord.getPassword(), "das");
		
		User user = br.loginUser("acabeleiragad@gmail.com", "noses");
		Landlord ld = br.getLandlordByUserId(user.getUserId());
		System.out.println(ld.getName() + " " +ld.getBirthday() + " " + ld.getEmail() + " " + ld.getPassword() +
				" " + ld.getPhone() + " " + ld.getCompanyInformation() + " " + ld.getLandlordId() + " " +ld.getNumberOperations());
		Renter rt = br.getRenterByUserId(user.getUserId());
		System.out.println(rt.getName() + " " +rt.getBirthday() + " " + rt.getEmail() + " " + rt.getPassword() +
				" " + rt.getPhone() + " " + rt.getUserId() + " " + rt.getRenterId() + " " +rt.getNumberOperations());
		//Anuncio anuncio = new Anuncio(landlord, "ladrao malvadesa", "Bom de lata", 200.0, 1, "Santanao bandidao", 0.0,0);
		//br.insertProduct("carao", "Santanao bom de lata", 20.0, "Ta daora", 
	//			ld,  3);
		//System.out.println(database.newAddress(new Address("fs6a", "dasd", 10, "hfsaid", "dd", "fafsf", "fsdad")));
		//br.finishSolicitation(5);
	//	br.startSolicitation(1,2,4);
		//br.acceptSolicitation(6, "2020-03-10", "2020-03-17" );
		//br.deleteUser(user);
		//br.deleteAnuncio(1);
		//Rental rental = br.getRental(1);
		
	//	System.out.println(rental.getDateStartTransaction());
	}
}
