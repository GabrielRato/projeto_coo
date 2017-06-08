package beans;
public class Landlord extends User{
	private Double rateLandlord;
	private String companyInformation;
	private int landlordId;

	private int numberOperations;

	public Landlord(String name, String birthday, String email,  Address address, String phone, 
			Double rateLandlord, String companyInformation, String password, String image) {
		super(0, name, email, address, phone, password, birthday, image);
		this.companyInformation = companyInformation;
		this.rateLandlord = rateLandlord;
	}
	
	public String status() {
		if(numberOperations < 10) return "Esse usuário não possui avaliações suficiente";
		if(rateLandlord <= 3.0 ) return "Usuário pouco confiável";
		return "Usuário confiável";
	}
	
	public void rateLandlord(Double rate) {
		rateLandlord = (rateLandlord*numberOperations + rate)/(numberOperations+1);
		numberOperations++;
	}
	
	public Landlord() {
		// TODO Auto-generated constructor stub
	}
	public int getNumberOperations() {
		return numberOperations;
	}
	public void setNumberOperations(int numberOperations) {
		this.numberOperations = numberOperations;
	}

	public int getLandlordId() {
		return landlordId;
	}
	public void setLandlordId(int landlordId) {
		this.landlordId = landlordId;
	}
	public Double getRateLandlord() {
		return rateLandlord;
	}
	public void setRateLandlord(Double rateLandlord) {
		this.rateLandlord = rateLandlord;
	}
	public String getCompanyInformation() {
		return companyInformation;
	}
	public void setCompanyInformation(String companyInformation) {
		this.companyInformation = companyInformation;
	}
}
