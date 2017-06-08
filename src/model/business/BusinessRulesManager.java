package model.business;

import dao.DatabaseManagerFind;
import dao.DatabaseManagerProduct;
import dao.DatabaseManagerTransaction;
import dao.DatabaseManagerUser;
import view.Values;

import java.util.ArrayList;

import beans.Address;
import beans.Ad;
import beans.Landlord;
import beans.Rental;
import beans.Renter;
import beans.Request;
import beans.User;

public class BusinessRulesManager {
	
	//private DataBaseManagerGeral database = new DataBaseManagerGeral();
	private DatabaseManagerUser databaseUser = new DatabaseManagerUser();
	private DatabaseManagerProduct databaseProduct = new DatabaseManagerProduct();
	private DatabaseManagerFind databaseFind = new DatabaseManagerFind();
	private DatabaseManagerTransaction databaseTransaction = new DatabaseManagerTransaction();
	// Usuario 
	// Esta feito: Inserir o usuario, renter e landlord
	// Precisa fazer: deletar usu
	public User getUserByLandlordId(int landlordId) throws Exception {
        try {
            return databaseUser.getUserByLandlordId(landlordId);
        } catch(Exception e) {
            throw e;
        }
    }

	private boolean containsString(String file) {
		return(file.contains("\"") || file.contains("\'") || file.contains(";"));
	}
	public void registerNewUser(String name, String birthday, String email, Address address, String phone,
						String companyInformation, String password, String image) throws Exception  {
		if(containsString(name)) throw new Exception(Values.INVALID_CHARACTERS);
		if(containsString(email)) throw new Exception(Values.INVALID_CHARACTERS);
		if(containsString(phone))throw new Exception(Values.INVALID_CHARACTERS);
		if(containsString(companyInformation))throw new Exception(Values.INVALID_CHARACTERS);
		if(containsString(password))throw new Exception(Values.INVALID_CHARACTERS);
		
		
		if(databaseUser.isRegistered(email)) {
			throw new Exception(Values.EMAIL_IS_USED);
		}
		Landlord landlord = new Landlord(name, birthday, email, address, phone, 0.0, companyInformation, password, image);
		Renter renter = new Renter(name, birthday, 0.0, email, address, phone, password, image);
		
		
		databaseUser.newUser(renter, landlord);
		
	}
	
	
	public User loginUser(String email, String password) throws Exception{
		
		if(containsString(email))throw new Exception(Values.INVALID_FIELD);
		if(containsString(password))throw new Exception(Values.INVALID_FIELD);
		try {
			User user = databaseUser.login(email, password);
			return user;
			
		} catch(Exception e) {
			throw e;
		}
	}
	
	public void deleteUser(int userId) throws Exception {
		try {
			Landlord landlord = databaseUser.getLandlordByUserId(userId);
			databaseProduct.deleteAllAds(landlord.getLandlordId());
			databaseUser.deleteUser(userId);
		} catch (Exception e) {
			throw e;
		}
	}
	//*******************************************************************************************
	

	
	public void insertProduct(String name, String image, String category, String title, Double price, String period, 
			String description, Landlord landlord,  int avaliableAmount) throws Exception {
		if(containsString(name)) throw new Exception(Values.INVALID_CHARACTERS);
		if(containsString(title)) throw new Exception(Values.INVALID_CHARACTERS);
		if(containsString(description)) throw new Exception(Values.INVALID_CHARACTERS);
		
		Ad ad = new Ad(landlord, name, price, period, title, 0.0, 0, image, description, avaliableAmount, 0, category);
		try {
			databaseProduct.insertNewItem(ad);
		} catch(Exception e) {
			throw e;
		}
	}

	public void deleteAd(Ad ad) throws Exception {
		try {
			databaseTransaction.finishAllRequestsByAnuncio(ad.getAdId());
			databaseProduct.deleteAd(ad.getAdId(), ad.getItemId());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void updateAd(int adId, String title, String description, Double price, int available, 
			String category, String name, String period, String image) throws Exception{
		Ad ad = new Ad();
		ad.setTitle(title);
		ad.setImage(image);
		ad.setName(name);
		ad.setCategory(category);
		ad.setDescription(description);
		ad.setPrice(price);
		ad.setPeriod(period);
		ad.setAvailableAmount(available);
		ad.setAdId(adId);
		try {
			databaseProduct.updateAd(ad);
		}catch(Exception e) {
			throw e;
		}
	}
	//*********************************************************************************************
	
	// Busca
	
	public ArrayList<Ad> findByKeyWord(String search, int first, int last) throws Exception {
		try {
			if(containsString(search)) throw new Exception(Values.INVALID_CHARACTERS);
			String item[] = search.split(" ");
			return databaseFind.findByKeyWord(item, first, last);
			
		} catch (Exception e) {
			throw e;
		}
	}
	public ArrayList<Rental> getRentalByLandlordId(int idLandLord) throws Exception{
		try {
			return databaseTransaction.findRentalByLandlord(idLandLord);
			
		} catch (Exception e) {
			throw e;
		}
	}
	public ArrayList<Rental> getRentalByRenterId(int idRenter) throws Exception{
		try {
			return databaseTransaction.findRentalByRenter(idRenter);
			
		} catch (Exception e) {
			throw e;
		}
	}
	public ArrayList<Ad> getAdByLandlord(int landlordId) throws Exception {
		try {
			return databaseProduct.getAdByLandlordId(landlordId);
			
		} catch (Exception e) {
			throw e;
		}
	}
	//**********************************************************************************************
	
	
	// *******************************Transação***********************************************
	
	public void startRequest(int adId, int renterId, int landlordId, String dateStart, String dateEnd) throws Exception {
		try {
			databaseTransaction.startRequest(adId, renterId, landlordId, dateStart, dateEnd);
		} catch (Exception e) {
			throw e;
		}
	}
	public void finishRequest(int requestId) throws Exception {
		try {
			databaseTransaction.finishRequest(requestId);
		} catch (Exception e) {

			throw e;
		}
	}
	public void acceptRequest(int requestId) throws Exception{
		try {
			Request request = databaseTransaction.getRequest(requestId);
			databaseTransaction.finishRequest(requestId);
			startTransaction(request.getRenter().getRenterId(), request.getAd().getLandlord().getLandlordId(), request.getAd().getItemId(), request.getDateStart(), request.getDateEnd());
		} catch(Exception e) {
			throw e;
		}
		
	}

	private void startTransaction(int renterId, int landlordId, int adId, String dateStart, String dateEnd) throws Exception {
		try {
			Ad anuncio = new Ad();
			anuncio = databaseProduct.getAdByIdAd(adId);
			anuncio.setAdId(adId);
			if(anuncio.getAvailableAmount() == 0) throw new Exception(Values.ITEM_NOT_FOUND);
			databaseTransaction.startTransaction(renterId, landlordId, adId, dateStart, dateEnd);
			databaseProduct.updateAvailableAmount(anuncio.getAdId(), (anuncio.getAvailableAmount()-1));
		} catch(Exception e) {
			throw e;
		}
	}
	
	public Ad getAdByIdAd(int adId) throws Exception {
		try {
			return databaseProduct.getAdByIdAd(adId);
		} catch(Exception e) {
			throw e;
		}
	}
	private void finishTransaction(Rental rental) throws Exception {
		try {
			int itemId = rental.getAdId();
			Ad ad = databaseProduct.getAdByIdItem(itemId);
			databaseTransaction.finishTransaction(rental.getRentalId());
			databaseProduct.updateAvailableAmount(ad.getAdId(), ad.getAvailableAmount()+1);
		} catch(Exception e) {
			throw e;
		}
	}
	
	public void updateTransactionByRenter(Rental rental, int renter, Double productRate, Double landlordRate) throws Exception {
		try {
			if(rental.isFinishedByRenter() && renter == 1) return;
			Landlord landlord = rental.getLandlord();
			int itemId =rental.getAdId();
			Ad ad = databaseProduct.getAdByIdItem(itemId);
			databaseTransaction.updateTransactionByRenter(rental.getRentalId(), renter, landlordRate, productRate);
			rateLandlord(landlord, landlordRate);
			rateProduct(ad, productRate);
			if(rental.isFinishedByLandlord()) finishTransaction(rental);
		} catch(Exception e) {
			throw e;
		}
	}
	
	public void updateTransactionByLandlord(Rental rental, int landlord, Double renterRate) throws Exception {
		try {
			if(rental.isFinishedByLandlord() && landlord == 1) return;
			Renter renter = rental.getRenter();
			databaseTransaction.updateTransactionByLandlord(rental.getRentalId(), landlord, renterRate);
			rateRenter(renter, renterRate);
			if(rental.isFinishedByRenter()) finishTransaction(rental);
		} catch(Exception e) {
			throw e;
		}
	}
	
	// ********************************* Get objects *****************************************************
	
	public ArrayList<Request> getAllRequestsByLandlord(int landlordId) throws Exception {
		ArrayList<Request> array = new ArrayList<>();
		try {
			array = databaseTransaction.getAllRequestsByLandlord(landlordId);
		} catch (Exception e) {
			throw e;
		}
		return array;
	}
	
	public Rental getRental(int rentalId) throws Exception {
		Rental rental;
		try {
			rental = databaseTransaction.getRentalById(rentalId);
		} catch(Exception e) {
			throw e;
		}
		return rental;
	}
	
	public Landlord getLandlordByUserId(int userId) throws Exception {
		Landlord landlord = null;
		try {
			landlord = databaseUser.getLandlordByUserId(userId);
		} catch(Exception e) {
			throw e;
		}
		return landlord;
	}
	
	public Renter getRenterByUserId(int userId) throws Exception {
		Renter renter = null;
		try {
			renter = databaseUser.getRenterByUserId(userId);
		} catch(Exception e) {
			throw e;
		}
		return renter;
	}
	
	public Landlord getLandlordById(int landlordId) throws Exception {
		Landlord landlord = null;
		try {
			landlord = databaseUser.getLandlordByLandlordId(landlordId);
		} catch(Exception e) {
			throw e;
		}
		return landlord;
	}
	
	public Renter getRenterById(int renterId) throws Exception {
		Renter renter = null;
		try {
			renter = databaseUser.getRenterByRenterId(renterId);
		} catch(Exception e) {
			throw e;
		}
		return renter;
	}
	
	// ***************************** Change rate of Landlord, Renter or Product *****************************
	private void rateRenter(Renter renter, Double rate) throws Exception {
		try {
			renter.rateRenter(rate);
			databaseUser.setQuantifyOperationRenter(renter.getNumberOperations(), renter.getRenterId());
			databaseUser.setRateRenter(renter.getRateRenter(), renter.getRenterId());
		}catch(Exception e) {
			throw e;
		}
	}
	
	private void rateLandlord(Landlord landlord, Double rate) throws Exception {
		
		try {
			landlord.rateLandlord(rate);
			
			databaseUser.setQuantifyOperationLandlord(landlord.getNumberOperations(), landlord.getLandlordId());
			databaseUser.setRateLandlord(landlord.getRateLandlord(), landlord.getLandlordId());
			
		}catch(Exception e) {
			throw e;
		}
	}
	
	private void rateProduct(Ad ad, Double rate) throws Exception {
		try {
			ad.rateProduct(rate);
			databaseProduct.setQuantifyOperationAd(ad.getOperations(), ad.getAdId());
			databaseProduct.setRateAd(ad.getRate(), ad.getAdId());
		} catch (Exception e) {
			throw e;

		}
	}
	
	//**********************************************************************************************
	
}
