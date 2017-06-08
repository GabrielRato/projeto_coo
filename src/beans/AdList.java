package beans;

public class AdList extends Item{
	private Double price;
	private String title;
	private Double rate;
	private int adId;
	private String image;
	private String period;
	public AdList(Landlord landlord, String name, Double price, String period, String title, Double rate, int adId, String image, String category) {
		super(landlord, name, category);
		this.price = price;
		this.title = title;
		this.rate = rate;
		this.adId = adId;
		this.image = image;
		this.period = period;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public AdList() {
		
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public int getAdId() {
		return adId;
	}
	public void setAdId(int adId) {
		this.adId = adId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
