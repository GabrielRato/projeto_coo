package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.Landlord;
import beans.Rental;
import beans.Renter;
import beans.Request;
import model.business.BusinessRulesManager;
import view.Values;

public class DatabaseManagerTransaction extends DatabaseManager{
	private DatabaseManagerUser databaseManagerUser = new DatabaseManagerUser();
	private DatabaseManagerProduct databaseManagerProduct = new DatabaseManagerProduct();

	// *************************** Solicitation *******************************************************
	public void startRequest(int adId, int renterId, int landlordId, String dateStart, String dateEnd) throws Exception,SQLException {
		try {
			openConnection();
			commandSQL("insert into solicitacao (id_anuncio, id_locador, id_locatario, data_retirada, data_devolucao)" +
							"values (?, ?, ?, ?, ?)");
			pstmt.setInt(1, adId);
			pstmt.setInt(2, landlordId);
			pstmt.setInt(3, renterId);
			pstmt.setString(4, dateStart);
			pstmt.setString(5, dateEnd);
			pstmt.execute();
			closeConnection();
		}  catch (SQLException e) {
			System.out.println(e.getMessage());
			closeConnection();
			throw new Exception(Values.DB_ERROR); 
		} catch(Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
	}
	
	public void finishRequest(int requestId) throws Exception {
		try {
			openConnection();
			commandSQL("delete from solicitacao where id_solicitacao = '" + requestId + "'") ;
			
			pstmt.execute();
			closeConnection(); 
		} catch (SQLException e) {
			closeConnection();
			throw new Exception(Values.DB_ERROR); 
		} catch(Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
	}
	
	public void finishAllRequestsByAnuncio(int adId)throws Exception {
		try {
			openConnection();
			commandSQL("delete from solicitacao where id_anuncio = '" + adId + "'") ;
			pstmt.execute();
			closeConnection(); 
		} catch (SQLException e) {
			closeConnection();
			throw new Exception(Values.DB_ERROR); 
		} catch(Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
	}
	
	// **************************************** Rental**********************************************************
	public Rental getRentalById(int rentalId) throws Exception {
		Rental rental = new Rental();
		rental.setRentalId(rentalId);
		int renterId = 0;
		int landlordId = 0;
		String comando;
		comando = "select id_anuncio, id_locador, id_locatario, data_devolucao, data_retirada, processo_status, valor_emprestimo, "
				+ "titulo, finalizado_locador, finalizado_locatario, avaliacao_locador, avaliacao_locatario, avaliacao_produto"
				+ " from empresta where id_emprestimo = '" +rentalId+ "'";
		try {
			openConnection();
			commandSQL(comando);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				rental.setAdId(rs.getInt(1));
				landlordId = rs.getInt(2);
				renterId = rs.getInt(3);
				rental.setDateFinishTransaction(rs.getString(4));
				rental.setDateStartTransaction(rs.getString(5));
				rental.setStatus(rs.getString(6));
				rental.setPrice(rs.getDouble(7));
				rental.setTitle(rs.getString(8));
				rental.setFinishedByLandlord(rs.getBoolean(9));
				rental.setFinishedByRenter(rs.getBoolean(10));
				rental.setLandlordRating(rs.getDouble(11));
				rental.setRenterRating(rs.getDouble(12));
				rental.setProductRating(rs.getDouble(13));
			}
			closeConnection();
			rental.setLandlord(databaseManagerUser.getLandlordByLandlordId(landlordId));
			rental.setRenter(databaseManagerUser.getRenterByRenterId(renterId));
			return rental;
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			closeConnection();
			throw new Exception(Values.DB_ERROR);
		} catch (Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
	}
	public ArrayList<Rental> findRentalByRenter(int renterId) throws Exception {
		ArrayList<Rental> arrayList = new ArrayList<Rental>();
		Renter renter = databaseManagerUser.getRenterByRenterId(renterId);
		int landlordId = 0;
		try {
			openConnection();
			String comando = "select id_anuncio, id_emprestimo, id_locador, data_devolucao, data_retirada, processo_status, valor_emprestimo, "
					+ "titulo, finalizado_locador, finalizado_locatario, avaliacao_locador, avaliacao_locatario, avaliacao_produto"
					+ " from empresta where id_locatario = '" +renterId+ "'";
			commandSQL(comando);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Rental rental = new Rental();
				rental.setAdId(rs.getInt(1));
				rental.setRentalId(rs.getInt(2));
				landlordId = rs.getInt(3);
				rental.setDateFinishTransaction(rs.getString(4));
				rental.setDateStartTransaction(rs.getString(5));
				rental.setStatus(rs.getString(6));
				rental.setPrice(rs.getDouble(7));
				rental.setTitle(rs.getString(8));
				rental.setFinishedByRenter(rs.getBoolean(9));
				rental.setFinishedByLandlord(rs.getBoolean(10));
				rental.setLandlordRating(rs.getDouble(11));
				rental.setRenterRating(rs.getDouble(12));
				rental.setProductRating(rs.getDouble(13));
				rental.setRenter(renter);
				rental.setLandlord(databaseManagerUser.getLandlordByLandlordId(landlordId));
				arrayList.add(rental);			
			}
			closeConnection();
		}catch(SQLException e) {
			closeConnection();
			throw new Exception(Values.DB_ERROR);
		} catch (Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
		return arrayList;
	}
	// Igual o caso de cima
	// Listar todas as operacoes de aluguel onde o usuario é o locador
	public ArrayList<Rental> findRentalByLandlord(int landlordId) throws Exception {
		ArrayList<Rental> arrayList = new ArrayList<Rental>();
		Landlord landlord = databaseManagerUser.getLandlordByLandlordId(landlordId);
		int renterId = 0;
		try {
			openConnection();
			String comando = "select id_anuncio, id_emprestimo, id_locatario, data_devolucao, data_retirada, processo_status, valor_emprestimo, "
					+ "titulo, finalizado_locador, finalizado_locatario, avaliacao_locador, avaliacao_locatario, avaliacao_produto"
					+ " from empresta where id_locador = '" +landlordId+ "'";
			commandSQL(comando);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Rental rental = new Rental();
				rental.setAdId(rs.getInt(1));
				rental.setRentalId(rs.getInt(2));
				renterId = rs.getInt(3);
				rental.setDateFinishTransaction(rs.getString(4));
				rental.setDateStartTransaction(rs.getString(5));
				rental.setStatus(rs.getString(6));
				rental.setPrice(rs.getDouble(7));
				rental.setTitle(rs.getString(8));
				rental.setFinishedByRenter(rs.getBoolean(9));
				rental.setFinishedByLandlord(rs.getBoolean(10));
				rental.setLandlordRating(rs.getDouble(11));
				rental.setRenterRating(rs.getDouble(12));
				rental.setProductRating(rs.getDouble(13));
				rental.setRenter(databaseManagerUser.getRenterByRenterId(renterId));
				rental.setLandlord(landlord);
				arrayList.add(rental);			
			}
			closeConnection();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			closeConnection();
			throw new Exception(Values.DB_ERROR);
		} catch (Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
		return arrayList;
	}
	
	//// ********************************** Request ***********************************************************
	
	
	public ArrayList<Request> getAllRequestsByLandlord(int landlordId) throws Exception {
		ArrayList<Request> arrayList = new ArrayList<Request>();
		String comando;
		//System.out.println(search.length);
		//(Landlord landlord, String name, String description, int price, int availableAmount, String title,int rate) 
		comando ="select id_solicitacao, id_locatario, data_retirada,"
				+ " data_devolucao, id_anuncio from "
				+ "solicitacao where id_locador = '" + landlordId + "'";
		try {
			openConnection();
			commandSQL(comando);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Request request = new Request();
				request.setRequestId(rs.getInt(1));
				request.setRenter(databaseManagerUser.getRenterByRenterId(rs.getInt(2)));
				request.setDateStart(rs.getString(3));
				request.setDateEnd(rs.getString(4));
				request.setAd(databaseManagerProduct.getAdByIdAd(rs.getInt(5)));
				arrayList.add(request);
			}
			closeConnection();
			return arrayList;
		} catch (SQLException e) {
			closeConnection();
			throw new Exception(Values.DB_ERROR); 
		} catch(Exception e) {
			closeConnection();
			System.out.println(e.getMessage());
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
	}
	public Request getRequest(int requestId) throws Exception {
		Request request = new Request();

		int adId = 0;
		int landlordId = 0;
		int renterId = 0;
		String dateStart = "";
		String dateEnd ="";
		try {
			openConnection();
			
			commandSQL("select id_locador, id_anuncio, id_locatario, data_retirada, data_devolucao from "
					+ "solicitacao where id_solicitacao = '" + requestId + "'");
			rs = pstmt.executeQuery();
			if (rs.next()) {				
				landlordId = (rs.getInt(1));
				adId = (rs.getInt(2));
				renterId = (rs.getInt(3));
				dateStart = rs.getString(4);
				dateEnd = rs.getString(5);
			}
			closeConnection();
			request.setRequestId(requestId);
			//System.out.println("chegou aqui3");
			request.setAd(databaseManagerProduct.getAdByIdAd(adId));
			request.setRenter(databaseManagerUser.getRenterByRenterId(renterId));
			request.setDateStart(dateStart);
			request.setDateEnd(dateEnd);
			
		}  catch (SQLException e) {
			closeConnection();
			throw new Exception(Values.DB_ERROR); 
		} catch(Exception e) {
			closeConnection();
			System.out.println(e.getMessage());
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
		return request;
	}

	
	
	//******************************* ***************************************************************************
	
	
	public void startTransaction(int renterId, int landlordId, int adId, String dateStart, String dateEnd) throws Exception{
		try {
			openConnection();
			commandSQL("select valor, titulo from anuncio where id_anuncio = '" + adId + "'");
			rs = pstmt.executeQuery();
			Double price = 0.0;
			String title= "";
			if (rs.next()) {
				price = rs.getDouble(1);
				title = rs.getString(2);
			}
			
			commandSQL("insert into empresta (id_anuncio, id_locador, id_locatario, data_retirada, data_devolucao, "
					+ "valor_emprestimo, finalizado_locador, finalizado_locatario, processo_status, titulo)" +
							"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)") ;
			pstmt.setInt(1, adId);
			pstmt.setInt(2, landlordId);
			pstmt.setInt(3, renterId);
			pstmt.setString(4, dateStart);
			pstmt.setString(5, dateEnd);
			pstmt.setDouble(6, price);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			pstmt.setString(9, Values.RENTAL_IN_PROGRESS);
			pstmt.setString(10, title);
			pstmt.execute();
			closeConnection();
		}  catch (SQLException e) {
			System.out.println(e.getMessage());
			closeConnection();
			throw new Exception(Values.DB_ERROR); 
		} catch(Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
	}
	
	public void updateTransactionByRenter(int transactionId, int status, Double landlordRate, Double productRate) throws Exception {
		openConnection();
		try{
			String comando="update empresta set finalizado_locatario='"+status+"', avaliacao_locador='"+landlordRate+
					"', avaliacao_produto='"+productRate+"' where id_emprestimo='"+transactionId+"'";
			commandSQL(comando);
			pstmt.execute();
			closeConnection();
		}  catch (SQLException e) {
			//System.out.println(e.getMessage());
			closeConnection();
			throw new Exception(Values.DB_ERROR); 
		} catch(Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}

	}
    
	public void updateTransactionByLandlord(int transactionId, int status, Double rate) throws Exception {
		try{
			openConnection();
			String comando="update empresta set finalizado_locador='"+status+"', avaliacao_locatario='"+rate+"' where id_emprestimo='"+transactionId+"'";
			commandSQL(comando);
			pstmt.execute();
			closeConnection();
		}  catch (SQLException e) {
			System.out.println(e.getMessage());
			closeConnection();
			throw new Exception(Values.DB_ERROR); 
		} catch(Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
	}
	
	// ********* update the status of the solicitation *****************
	public void finishTransaction(int rentalId) throws Exception {
		
		try{
			openConnection();
			String comando="update empresta set processo_status = '" + Values.RENTAL_FINALIZED +"' where id_emprestimo = '"+rentalId+"'";
			commandSQL(comando);
			pstmt.execute();
			closeConnection();
		}  catch (SQLException e) {
			//System.out.println(e.getMessage());
			closeConnection();
			throw new Exception(Values.DB_ERROR); 
		} catch(Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
	}
	

}
