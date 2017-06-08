package beans;

public class Rental {
	private int rentalId;
	private int adId;
	private String title;
	private String status;
	private String dateStartTransaction;
	private String dateFinishTransaction;
	private Landlord landlord;
	private Renter renter;
	private Double price;
	private boolean finishedByRenter;
	private boolean finishedByLandlord;
	private Double landlordRating;
	private Double renterRating;
	private Double productRating;

	public Rental(int rentalId, int adId, Double price, String title, String status, String dateStartTransaction, String dateFinishTransaction, Landlord landlord,
			Renter renter, Double landlordRating, Double renterRating, Double productRating) {
		this.rentalId = rentalId;
		this.adId = adId;
		this.status = status;
		this.price = price;
		this.title = title;
		this.dateStartTransaction = dateStartTransaction;
		this.dateFinishTransaction = dateFinishTransaction;
		this.landlord = landlord;
		this.renter = renter;
		this.landlordRating = landlordRating;
		this.renterRating = renterRating;
		this.productRating = productRating;
	}
	
	
	public Double getLandlordRating() {
		return landlordRating;
	}

	public void setLandlordRating(Double landlordRating) {
		this.landlordRating = landlordRating;
	}

	public Double getRenterRating() {
		return renterRating;
	}

	public void setRenterRating(Double renterRating) {
		this.renterRating = renterRating;
	}

	public Double getProductRating() {
		return productRating;
	}

	public void setProductRating(Double productRating) {
		this.productRating = productRating;
	}

	public boolean isFinishedByRenter() {
		return finishedByRenter;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setFinishedByRenter(boolean finishedByRenter) {
		this.finishedByRenter = finishedByRenter;
	}
	public boolean isFinishedByLandlord() {
		return finishedByLandlord;
	}
	public void setFinishedByLandlord(boolean finishedByLandlord) {
		this.finishedByLandlord = finishedByLandlord;
	}
	public void setAdId(int adId) {
		this.adId = adId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Rental() {
		// TODO Auto-generated constructor stub
	}
	public int getRentalId() {
		return rentalId;
	}
	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}
	public int getAdId() {
		return adId;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDateStartTransaction() {
		return dateStartTransaction;
	}
	public void setDateStartTransaction(String dateStartTransaction) {
		this.dateStartTransaction = dateStartTransaction;
	}
	public String getDateFinishTransaction() {
		return dateFinishTransaction;
	}
	public void setDateFinishTransaction(String dateFinishTransaction) {
		this.dateFinishTransaction = dateFinishTransaction;
	}
	public Landlord getLandlord() {
		return landlord;
	}
	public void setLandlord(Landlord landlord) {
		this.landlord = landlord;
	}
	public Renter getRenter() {
		return renter;
	}
	public void setRenter(Renter renter) {
		this.renter = renter;
	}
	
	
}
