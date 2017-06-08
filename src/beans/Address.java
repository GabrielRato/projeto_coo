package beans;

public class Address {
	private int addressId;

	private String streetName;
	private String cep;
	private int number;
	private String city;
	private String state;
	private String neighborhood;
	private String complement;
	
	public Address() {
		
	}

	public Address(String streetName, String cep, int number, String city, String state, String neighborhood,
			String complement) {
		this.streetName = streetName;
		this.cep = cep;
		this.number = number;
		this.city = city;
		this.state = state;
		this.neighborhood = neighborhood;
		this.complement = complement;
	}
	
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}
	
}
