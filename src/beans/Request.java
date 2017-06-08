package beans;

import view.Values;

public class Request extends Notification {
	private Ad ad;
	private Renter renter;
	private int requestId;
	private String dateStart;
	private String dateEnd;
	
	public Request(String title, Ad ad, Renter renter, String dateStart, String dateEnd) {
		super(title);
		this.ad = ad;
		this.renter = renter;
		this.dateEnd = dateEnd;
		this.dateStart = dateStart;
	}
	
	public String getTitle() {
		return Values.NEW_REQUEST_MESSAGE;
	}
	public String getDateStart() {
		return dateStart;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	public Request() {
	}
	
	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}

	public Renter getRenter() {
		return renter;
	}

	public void setRenter(Renter renter) {
		this.renter = renter;
	}
	
}
