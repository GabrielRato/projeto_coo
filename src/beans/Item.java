package beans;

public class Item {
	private Landlord landlord;
	private String name;
	private int itemId;
	private String category;

	public Item() {
		
	}
	public Item(Landlord landlord, String name, String category) {
		this.landlord = landlord;
		this.name = name;
		this.category = category;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	public Landlord getLandlord() {
		return landlord;
	}


	public void setLandlord(Landlord landlord) {
		this.landlord = landlord;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
}
