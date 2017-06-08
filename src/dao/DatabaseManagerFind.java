package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.Ad;
import beans.Rental;
import beans.Request;
import view.Values;

public class DatabaseManagerFind extends DatabaseManager{
	private DatabaseManagerUser dbUser = new DatabaseManagerUser();
	public ArrayList<Ad> findByKeyWord(String search[], int first, int last) throws Exception {
		ArrayList<Ad> arrayList = new ArrayList<Ad>();
		String comando;
		//System.out.println(search.length);
		//(Landlord landlord, String name, String description, int price, int availableAmount, String title,int rate) 
		comando = "select id_prod, imagem, titulo, valor, avaliacao, quantidade,id_anuncio "
				+ "from anuncio "
				+ "where titulo like '%"+search[0]+"%' ";
		for(int i = 1; i < search.length; i++){
			comando = comando.concat("and titulo like '%"+search[i]+"%' ");
		}
		comando = comando.concat("limit " + (last) +" offset " + first);
		//System.out.println(comando);
		openConnection();
		commandSQL(comando);
		rs = pstmt.executeQuery();
		while(rs.next()){
			Ad anun = new Ad();
			anun.setItemId(rs.getInt(1));
			anun.setImage(rs.getString(2));
			anun.setTitle(rs.getString(3));
			anun.setPrice(rs.getDouble(4));
			anun.setRate(rs.getDouble(5));
			anun.setAvailableAmount(rs.getInt(6));
			anun.setAdId(rs.getInt(7));
			arrayList.add(anun);
		}
		closeConnection();
		return arrayList;
	}
	

}
