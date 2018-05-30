package database;

import logic.SellerDataModel;

public interface SellerDAO 
{
	public SellerDataModel checkLogin(String username, String password);
}
