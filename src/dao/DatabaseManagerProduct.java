package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.Ad;
import beans.Item;
import beans.Landlord;
import view.Values;

public class DatabaseManagerProduct extends DatabaseManager {

	private DatabaseManagerUser databaseManagerUser = new DatabaseManagerUser();

	public void insertNewItem(Item item) throws Exception {
		openConnection();
		try {
			commandSQL("insert into produto (Nome, id_locador, categoria)" + "values (?, ?, ?)");
			pstmt.setString(1, item.getName());
			pstmt.setInt(2, item.getLandlord().getLandlordId());
			pstmt.setString(3, item.getCategory());
			pstmt.execute();

			commandSQL("select last_insert_id() as last_id from produto");
			rs = pstmt.executeQuery();
			int itemId = 0;
			if (rs.next()) {
				itemId = rs.getInt("last_id");
			}
			closeConnection();
			Ad ad = (Ad) item;
			insertNewAd(ad, itemId);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			closeConnection();
			throw new Exception(Values.DB_ERROR);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
	}

	private void insertNewAd(Ad ad, int productId) throws Exception {
		try {

			openConnection();
			commandSQL(
					"insert into anuncio (id_prod, titulo, valor, quantidade, quantidade_operacoes, descricao, avaliacao, imagem, periodo)"
							+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, productId);
			pstmt.setString(2, ad.getTitle());
			pstmt.setDouble(3, (ad.getPrice()));
			pstmt.setInt(4, ad.getAvailableAmount());
			pstmt.setInt(5, ad.getOperations());
			pstmt.setString(6, ad.getDescription());
			pstmt.setDouble(7, ad.getRate());
			pstmt.setString(8, ad.getImage());
			pstmt.setString(9, ad.getPeriod());
			pstmt.execute();

			closeConnection();
		} catch (SQLException e) {
			closeConnection();
			throw new Exception(Values.DB_ERROR);
		} catch (Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
	}

	public void deleteAd(int adId, int itemId) throws Exception {
		try {
			openConnection();
			String comando = "delete from anuncio where id_anuncio = '" + adId + "'";
			commandSQL(comando);
			pstmt.execute();
			comando = "delete from produto where id_prod = '" + itemId + "'";
			commandSQL(comando);
			pstmt.execute();
			closeConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			closeConnection();
			throw new Exception(Values.DB_ERROR);
		} catch (Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
	}

	public void deleteAllAds(int landlordId) throws Exception {
		try {
			openConnection();
			String comando = "delete from anuncio, produto where id_locador = '" + landlordId
					+ "' and produto.id_prod = anuncio.id_prod";
			commandSQL(comando);
			pstmt.execute();
			closeConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			closeConnection();
			throw new Exception(Values.DB_ERROR);
		} catch (Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}

	}

	// ************************ Search
	// ******************************************************
	public Ad getAdByIdAd(int adId) throws Exception {
		Ad ad = new Ad();
		ad.setAdId(adId);
		String comando;
		comando = "select id_prod, imagem, titulo, valor, quantidade, quantidade_operacoes, descricao, avaliacao "
				+ "from anuncio where id_anuncio = '" + adId + "'";
		try {
			openConnection();
			commandSQL(comando);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ad.setItemId(rs.getInt(1));
				ad.setImage(rs.getString(2));
				ad.setTitle(rs.getString(3));
				ad.setPrice(rs.getDouble(4));
				ad.setAvailableAmount(rs.getInt(5));
				ad.setOperations(rs.getInt(6));
				ad.setDescription(rs.getString(7));
				ad.setRate(rs.getDouble(8));
			}
			comando = "select id_locador, Nome, categoria from produto where id_prod = '" + ad.getItemId() + "'";
			int landlordId = 0;
			commandSQL(comando);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				landlordId = rs.getInt(1);
				ad.setName(rs.getString(2));
				ad.setCategory(rs.getString(3));
			}
			closeConnection();
			System.out.println(landlordId);
			ad.setLandlord(databaseManagerUser.getLandlordByLandlordId(landlordId));
			return ad;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			closeConnection();
			throw new Exception(Values.DB_ERROR);
		} catch (Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
	}

	public ArrayList<Ad> getAdByLandlordId(int landlordId) throws Exception {

		ArrayList<Ad> arrayList = new ArrayList<Ad>();
		ArrayList<Integer> aux = new ArrayList<Integer>();
		try {
			openConnection();
			Landlord landlord = databaseManagerUser.getLandlordByLandlordId(landlordId);
			String comando;
			comando = "select produto.id_prod, Nome, categoria, id_anuncio, imagem, titulo, valor, periodo, quantidade,"
					+ " quantidade_operacoes, descricao, avaliacao from produto, anuncio "
					+ "where produto.id_prod = anuncio.id_prod and produto.id_locador = '" + landlordId + "'";
			commandSQL(comando);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Ad ad = new Ad();
				ad.setLandlord(landlord);
				ad.setItemId(rs.getInt(1));
				ad.setName(rs.getString(2));
				ad.setCategory(rs.getString(3));
				ad.setAdId(rs.getInt(4));
				ad.setImage(rs.getString(5));
				ad.setTitle(rs.getString(6));
				ad.setPrice(rs.getDouble(7));
				ad.setPeriod(rs.getString(8));
				ad.setAvailableAmount(rs.getInt(9));
				ad.setOperations(rs.getInt(10));
				ad.setDescription(rs.getString(11));
				ad.setRate(rs.getDouble(12));
				arrayList.add(ad);
			}

			closeConnection();
			return arrayList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			closeConnection();
			throw new Exception(Values.DB_ERROR);
		} catch (Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
	}

	public Ad getAdByIdItem(int itemId) throws Exception {
		Ad ad = new Ad();
		String comando;
		comando = "select id_anuncio, imagem, titulo, valor, quantidade, quantidade_operacoes, descricao, avaliacao "
				+ "from anuncio where id_prod = '" + itemId + "'";
		try {
			openConnection();
			commandSQL(comando);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ad.setAdId(rs.getInt(1));
				ad.setImage(rs.getString(2));
				ad.setTitle(rs.getString(3));
				ad.setPrice(rs.getDouble(4));
				ad.setAvailableAmount(rs.getInt(5));
				ad.setOperations(rs.getInt(6));
				ad.setDescription(rs.getString(7));
				ad.setRate(rs.getDouble(8));
			}
			comando = "select id_locador from produto where id_prod = '" + ad.getItemId() + "'";
			int landlordId = 0;
			commandSQL(comando);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				landlordId = rs.getInt(1);
			}
			closeConnection();
			ad.setLandlord(databaseManagerUser.getLandlordByLandlordId(landlordId));
			return ad;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			closeConnection();
			throw new Exception(Values.DB_ERROR);
		} catch (Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
	}

	public void updateAvailableAmount(int adId, int newAmount) throws Exception {
		openConnection();
		try {
			String comando = "update anuncio set quantidade = '" + newAmount + "' where id_anuncio='" + adId + "'";
			commandSQL(comando);
			pstmt.execute();
			closeConnection();
		} catch (SQLException e) {
			// System.out.println(e.getMessage());
			closeConnection();
			throw new Exception(Values.DB_ERROR);
		} catch (Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
	}

	public void updateAd(Ad ad) throws Exception {
		try {
			openConnection();
			String comando = "update anuncio, produto set titulo = '" + ad.getTitle() + "', descricao = '" + ad.getDescription()
					+ "', quantidade = '" + ad.getAvailableAmount() + "', valor = '" + ad.getPrice()
					+ "', periodo ='" +ad.getPeriod()+"', imagem = '"+ ad.getImage() +"', Nome = '"+ad.getName()+"',"
							+ "categoria = '"+ad.getCategory()+"' where id_anuncio = '" + ad.getAdId() + "' and "
									+ "anuncio.id_prod = produto.id_prod";
			commandSQL(comando);
			pstmt.execute();
			closeConnection();
		} catch (SQLException e) {
			 System.out.println(e.getMessage());
			closeConnection();
			throw new Exception(Values.DB_ERROR);
		} catch (Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
	}

	public void setRateAd(Double rate, int adId) throws Exception {
		try {
			openConnection();
			String comando = "update anuncio set avaliacao = '" + rate + "' where id_anuncio = '" + adId + "'";
			commandSQL(comando);
			pstmt.execute();
			closeConnection();
		} catch (SQLException e) {
			// System.out.println(e.getMessage());
			closeConnection();
			throw new Exception(Values.DB_ERROR);
		} catch (Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
	}

	public void setQuantifyOperationAd(int amount, int adId) throws Exception {
		try {
			openConnection();
			String comando = "update anuncio set quantidade_operacoes = '" + amount + "' where id_anuncio = '" + adId
					+ "'";
			commandSQL(comando);
			pstmt.execute();
			closeConnection();
		} catch (SQLException e) {
			// System.out.println(e.getMessage());
			closeConnection();
			throw new Exception(Values.DB_ERROR);
		} catch (Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
	}

}
