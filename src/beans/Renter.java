package beans;
public class Renter extends User{
	private Double rateRenter;
	private int renterId;
	private int numberOperations;
	public Renter(String name, String birthday, Double rateRenter, String email, Address address, 
					String phone, String password, String image) {
		super(0, name, email, address, phone, password, birthday, image);
		this.rateRenter = rateRenter;
	}
	public String status() {
		if(numberOperations < 10) return "Esse usuário não possui avaliações suficiente";
		if(rateRenter <= 3.0 ) return "Usuário pouco confiável";
		return "Usuário confiável";
	}
	
	public void rateRenter(Double rate) {
		rateRenter = (rateRenter*numberOperations + rate)/(numberOperations+1);
		numberOperations++;
	}
	public Renter() {
		// TODO Auto-generated constructor stub
	}
	public int getNumberOperations() {
		return numberOperations;
	}
	public void setNumberOperations(int numberOperations) {
		this.numberOperations = numberOperations;
	}
	public int getRenterId() {
		return renterId;
	}
	public void setRenterId(int renterId) {
		this.renterId = renterId;
	}
	public Double getRateRenter() {
		return rateRenter;
	}
	public void setRateRenter(Double rateRenter) {
		this.rateRenter = rateRenter;
	}
	
}
