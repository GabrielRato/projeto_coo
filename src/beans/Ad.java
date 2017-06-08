package beans;

public class Ad extends AdList {
	
	private String description;
	private int availableAmount;
	private int operations;
	
	public Ad() {
		super();
	}
	public Ad(Landlord landlord, String name, Double price, String period, String title, Double rate, int adId, 
			String image, String description, int avaliableAmount, int operations, String category) {
		super(landlord, name, price, period, title, rate, adId, image, category);
		this.availableAmount = avaliableAmount;
		this.operations = operations;
		this.description = description;	
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAvailableAmount() {
		return availableAmount;
	}

	public void setAvailableAmount(int availableAmount) {
		this.availableAmount = availableAmount;
	}

	public int getOperations() {
		return operations;
	}

	public void setOperations(int operations) {
		this.operations = operations;
	}

	public void rateProduct(Double rate) {
		Double aux = getRate();
		setRate(((aux*operations)+rate)/(operations+1));
	}
	
}
