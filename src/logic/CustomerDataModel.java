package logic;

public class CustomerDataModel
{
	private String name;
	private String address;
	private String city;
	private String zipCode;
	private String phoneNumber;
	private String Email;
	private String CPR;
	
	public CustomerDataModel(String name, String address, String city, String zipCode, String phoneNumber, String email,
			String cPR) {
		this.name = name;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
		this.phoneNumber = phoneNumber;
		Email = email;
		CPR = cPR;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return Email;
	}

	public String getCPR() {
		return CPR;
	}





}
