package beans;

public class User {
	private int userId;
	private String name;
	private String email;
	private String phone;
	private String password;
	private String birthday;
	private Address address;
	private String image;
	
	public User(int userId, String name, String email,Address address, String phone, String password, 
			String birthday, String image) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.password = password;
		this.birthday = birthday;
		this.image = image;
	}
	public User(){
		
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
