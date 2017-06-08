package dao;

import java.sql.SQLException;

import beans.Address;
import beans.Landlord;
import beans.Renter;
import beans.User;
import view.Values;

public class DatabaseManagerUser extends DatabaseManager{
	
	
	
	// **************** Register new address, User, landlord and renter ****************************************
	
	public void newUser(Renter renter, Landlord landlord) throws Exception {
		try {
			openConnection();
			commandSQL("insert into usuario (Nome, telefone, Email, senha, data_nascimento, foto) "
					+ "values (?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, renter.getName());
			pstmt.setString(2, renter.getPhone());
			pstmt.setString(3, renter.getEmail());
			pstmt.setString(4, renter.getPassword());
			pstmt.setString(5, renter.getBirthday());
			pstmt.setString(6, renter.getImage());
			pstmt.execute();
		    commandSQL("select id_usu from usuario where Nome = '" + renter.getName() +
		    											"' and Email = '" + renter.getEmail() +
		    											"' and senha = '" + renter.getPassword() + "'");
		    rs = pstmt.executeQuery();
			int userId = 0;
			if (rs.next()) {
				userId = rs.getInt(1);
			}
			closeConnection();
			//System.out.println(id_usu);
			newAddress(renter.getAddress(), userId);
			renter.setUserId(userId);
			renter.setRenterId(newRenter(renter, userId));
			landlord.setLandlordId(newLandlord(landlord, userId));
		} catch (SQLException e) {
			closeConnection();
			throw new Exception(Values.DB_ERROR); 
		} catch(Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
	}
	private void newAddress(Address address, int userId) throws Exception {
		try {
			openConnection();
			commandSQL("insert into endereco (rua, bairro, cidade, complemento, estado, cep, numero, id_usu) "
							+ "values (?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, address.getStreetName());
			pstmt.setString(2, address.getNeighborhood());
			pstmt.setString(3, address.getCity());
			pstmt.setString(4, address.getComplement());
			pstmt.setString(5, address.getState());
			pstmt.setString(6, address.getCep());
			pstmt.setInt(7, address.getNumber());
			pstmt.setInt(8, userId);
			pstmt.execute();
			
			closeConnection();
			//System.out.println(id_endereco);
		}  catch (SQLException e) {
			closeConnection();
			throw new Exception(Values.DB_ERROR); 
		} catch(Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
	}
	private int newRenter(Renter renter, int id) throws Exception {
		openConnection();
		int renterId = 0;
		commandSQL("insert into locatario (id_usu, avaliacao, numero_de_operacoes) values (?, ?, ?)");
		try {
			pstmt.setInt(1, id);
			pstmt.setDouble(2, 0.0);
			pstmt.setDouble(3, 0);
			pstmt.execute();
		
			commandSQL("select id_locador from locador where id_usu = '" + id +"'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				renterId = rs.getInt(1);
			}
			closeConnection();
			return renterId;
		} catch (SQLException e) {
			closeConnection();
			throw new Exception(Values.DB_ERROR); 
		} catch(Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
	}

	private int newLandlord(Landlord landlord, int id) throws Exception {
		openConnection();
		commandSQL("insert into locador (id_usu, nome_da_empresa, avaliacao,"
				+ " numero_de_operacoes) values (?, ?, ?, ?)");
		int landlordId = 0;
		try {
			pstmt.setInt(1, id);
			pstmt.setString(2, landlord.getCompanyInformation());
			pstmt.setDouble(3, 0.0);
			pstmt.setInt(4, 0);
			pstmt.execute();
			commandSQL("select id_locador from locador where id_usu = '" + id +"'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				landlordId = rs.getInt(1);
			}
			closeConnection();
			return landlordId;
		}  catch (SQLException e) {
			closeConnection();
			throw new Exception(Values.DB_ERROR); 
		} catch(Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
	}
	
	public boolean isRegistered(String email) throws Exception {
		try {
			openConnection();
			commandSQL("select id_usu from usuario where Email = '" + email+"'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if(rs.getInt(1) > 0) {
					System.out.println("Esta cadastrado");
					return true;
				}
			} else {
				return false;
			}
		} catch (SQLException e) {
			closeConnection();
			throw new Exception(Values.DB_ERROR); 
		} catch(Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
		return false;
	}
	
	
	// **************************** Login **********************************************
	public User login(String email, String password) throws Exception{
		User user = new User();
		Address address = new Address();
		int addressId = 0;
		try {
			openConnection();
			commandSQL("select id_usu, telefone, Nome, data_nascimento from usuario where Email = '" + email+ "' and senha = '" + password + "'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user.setUserId(rs.getInt(1));
				user.setPhone(rs.getString(2));
				user.setName(rs.getString(3));
				user.setBirthday(rs.getString(4));
			} else {
				closeConnection();
				throw new Exception(Values.INVALID_FIELD);
			}
			commandSQL("select rua, bairro, cidade, complemento, estado, cep, numero from endereco where id_usu = '" + user.getUserId() + "'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				address.setStreetName(rs.getString(1));
				address.setNeighborhood(rs.getString(2));
				address.setCity(rs.getString(3));
				address.setComplement(rs.getString(4));
				address.setState(rs.getString(5));
				address.setCep(rs.getString(6));
				address.setNumber(rs.getInt(7));
				address.setAddressId(addressId);
			}
			user.setAddress(address);
			user.setEmail(email);
			user.setPassword(password);
			closeConnection();
			return user;
		}  catch (SQLException e) {

			closeConnection();
			throw new Exception(Values.DB_ERROR); 
		} catch(Exception e) {
			closeConnection();
			throw new Exception(Values.INVALID_FIELD);
		}
	}
	
	// *************************** Delete User **********************************************************
	
	public void deleteUser(int userId) throws Exception {
		try {
			openConnection();
			commandSQL("update usuario set email = '\'invalid\'', senha = '\'invalid\'', nome = '" + Values.DELETED_USER+ "', "
					+ "telefone = '" +Values.DELETED_USER_INFORMATIONS+ "' where id_usu='"+userId+"'");
			pstmt.execute();
			closeConnection();
		}  catch (SQLException e) {
			closeConnection();
			throw new Exception(Values.DB_ERROR); 
		} catch(Exception e) {
			closeConnection();
			throw new Exception("Não foi Possível Remover o Usuário");
		}
	}
	
	public User getUserByLandlordId(int landlordId) throws Exception {
        User user = new User();
        int userId = 0;
        try {
            openConnection();
            commandSQL("select id_usu from locador where id_locador ='"+ landlordId+"'");
            rs = pstmt.executeQuery();
            if (rs.next()) {                
                userId = rs.getInt(1);
            }
            user.setUserId(userId);
            commandSQL("select telefone, Nome, data_nascimento, Email from usuario where id_usu = '" + userId + "'");
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user.setPhone(rs.getString(1));
                user.setName(rs.getString(2));
                user.setBirthday(rs.getString(3));
                user.setEmail(rs.getString(4));
            } else {
                closeConnection();
                throw new Exception(Values.INVALID_FIELD);
            }
            Address address = new Address();
            commandSQL("select rua, bairro, cidade, complemento, estado, cep, numero from endereco where id_usu = '" + userId + "'");
            rs = pstmt.executeQuery();
            if (rs.next()) {
                address.setStreetName(rs.getString(1));
                address.setNeighborhood(rs.getString(2));
                address.setCity(rs.getString(3));
                address.setComplement(rs.getString(4));
                address.setState(rs.getString(5));
                address.setCep(rs.getString(6));
                address.setNumber(rs.getInt(7));
            }
            user.setAddress(address);
            closeConnection();
        }  catch (SQLException e) {
            closeConnection();
            throw new Exception(Values.DB_ERROR);
        } catch(Exception e) {
            closeConnection();
            throw new Exception(Values.ITEM_NOT_FOUND);
        }
        return user;
    }
	// ***************************** Renter and Landlord by the own id***********************************************
	public Landlord getLandlordByLandlordId(int landlordId) throws Exception {
		Landlord landlord = new Landlord();
		try {
			openConnection();
			commandSQL("select numero_de_operacoes, usuario.id_usu, nome_da_empresa, avaliacao, Nome, telefone, foto, data_nascimento, Email "
					+ "from locador, usuario where locador.id_locador = '" + landlordId + "' and usuario.id_usu = locador.id_usu");
			rs = pstmt.executeQuery();
			if (rs.next()) {				
				landlord.setNumberOperations(rs.getInt(1));
				landlord.setUserId(rs.getInt(2));
				landlord.setCompanyInformation(rs.getString(3));
				landlord.setRateLandlord(rs.getDouble(4));
				landlord.setName(rs.getString(5));
				landlord.setPhone(rs.getString(6));
				landlord.setImage(rs.getString(7));
				landlord.setBirthday(rs.getString(8));
				landlord.setEmail(rs.getString(9));
			}
			landlord.setLandlordId(landlordId);
			Address address = new Address();
			commandSQL("select rua, bairro, cidade, complemento, estado, cep, numero, id_endereco from endereco where id_usu = '" + landlord.getUserId() + "'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				address.setStreetName(rs.getString(1));
				address.setNeighborhood(rs.getString(2));
				address.setCity(rs.getString(3));
				address.setComplement(rs.getString(4));
				address.setState(rs.getString(5));
				address.setCep(rs.getString(6));
				address.setNumber(rs.getInt(7));
				address.setAddressId(rs.getInt(8));
			}
			landlord.setAddress(address);
			closeConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			closeConnection();
			throw new Exception(Values.DB_ERROR); 
		} catch(Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
		return landlord;
	}
	
	public Renter getRenterByRenterId(int renterId) throws Exception {
		Renter renter = new Renter();
		try {
			openConnection();
			commandSQL("select numero_de_operacoes, usuario.id_usu, avaliacao, Nome, telefone, foto, data_nascimento, Email "
					+ "from locatario, usuario where locatario.id_locatario = '" + renterId + "' and usuario.id_usu = locatario.id_usu");
			rs = pstmt.executeQuery();
			if (rs.next()) {				
				renter.setNumberOperations(rs.getInt(1));
				renter.setUserId(rs.getInt(2));
				renter.setRateRenter(rs.getDouble(3));
				renter.setName(rs.getString(4));
				renter.setPhone(rs.getString(5));
				renter.setImage(rs.getString(6));
				renter.setBirthday(rs.getString(7));
				renter.setEmail(rs.getString(8));
			}
			renter.setRenterId(renterId);
			Address address = new Address();
			commandSQL("select rua, bairro, cidade, complemento, estado, cep, numero, id_endereco from endereco where id_usu = '" + renter.getUserId() + "'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				address.setStreetName(rs.getString(1));
				address.setNeighborhood(rs.getString(2));
				address.setCity(rs.getString(3));
				address.setComplement(rs.getString(4));
				address.setState(rs.getString(5));
				address.setCep(rs.getString(6));
				address.setNumber(rs.getInt(7));
				address.setAddressId(rs.getInt(8));
			}
			renter.setAddress(address);
			closeConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			closeConnection();
			throw new Exception(Values.DB_ERROR); 
		} catch(Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
		return renter;
	}
	
	// ***************** renter e landlord by user id **********************************************
	public Renter getRenterByUserId(int userId) throws Exception {
		Renter renter = new Renter();
		try {
			openConnection();
			commandSQL("select id_locatario, numero_de_operacoes, avaliacao, Nome, telefone, foto, data_nascimento, Email "
					+ "from locatario, usuario where locatario.id_usu = '" + userId + "' and usuario.id_usu = '" + userId+ "'");
			rs = pstmt.executeQuery();
			if (rs.next()) {	
				renter.setRenterId(rs.getInt(1));
				renter.setNumberOperations(rs.getInt(2));
				renter.setRateRenter(rs.getDouble(3));
				renter.setName(rs.getString(4));
				renter.setPhone(rs.getString(5));
				renter.setImage(rs.getString(6));
				renter.setBirthday(rs.getString(7));
				renter.setEmail(rs.getString(8));
			}
			renter.setUserId(userId);
			Address address = new Address();
			commandSQL("select rua, bairro, cidade, complemento, estado, cep, numero, id_endereco from endereco where id_usu = '" + userId + "'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				address.setStreetName(rs.getString(1));
				address.setNeighborhood(rs.getString(2));
				address.setCity(rs.getString(3));
				address.setComplement(rs.getString(4));
				address.setState(rs.getString(5));
				address.setCep(rs.getString(6));
				address.setNumber(rs.getInt(7));
				address.setAddressId(rs.getInt(8));
			}
			renter.setAddress(address);
			closeConnection();
		} catch (SQLException e) {
			closeConnection();
			throw new Exception(Values.DB_ERROR); 
		} catch(Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
		
		return renter;
	}

	public Landlord getLandlordByUserId(int userId) throws Exception {
		Landlord landlord = new Landlord();
		try {
			openConnection();
			commandSQL("select numero_de_operacoes, id_locador, nome_da_empresa, avaliacao, Nome, telefone, foto, data_nascimento, Email "
					+ "from locador, usuario where locador.id_usu = '" + userId + "' and usuario.id_usu = '" +userId+ "'");
			rs = pstmt.executeQuery();
			if (rs.next()) {				
				landlord.setNumberOperations(rs.getInt(1));
				landlord.setLandlordId(rs.getInt(2));
				landlord.setCompanyInformation(rs.getString(3));
				landlord.setRateLandlord(rs.getDouble(4));
				landlord.setName(rs.getString(5));
				landlord.setPhone(rs.getString(6));
				landlord.setImage(rs.getString(7));
				landlord.setBirthday(rs.getString(8));
				landlord.setEmail(rs.getString(9));
			}
			landlord.setUserId(userId);
			Address address = new Address();
			commandSQL("select rua, bairro, cidade, complemento, estado, cep, numero, id_endereco from endereco where id_usu = '" + userId + "'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				address.setStreetName(rs.getString(1));
				address.setNeighborhood(rs.getString(2));
				address.setCity(rs.getString(3));
				address.setComplement(rs.getString(4));
				address.setState(rs.getString(5));
				address.setCep(rs.getString(6));
				address.setNumber(rs.getInt(7));
				address.setAddressId(rs.getInt(8));
			}
			landlord.setAddress(address);
			closeConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			closeConnection();
			throw new Exception(Values.DB_ERROR); 
		} catch(Exception e) {
			closeConnection();
			throw new Exception(Values.ITEM_NOT_FOUND);
		}
		return landlord;
	}
	// ************************************* Alter User data ****************************************************************
	public void setRateLandlord(Double rate, int ladlordId) throws Exception {
		try{
			openConnection();
			String comando="update locador set avaliacao = '"+rate+"' where id_locador = '"+ladlordId+"'";
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

	public void setQuantifyOperationLandlord(int amount, int landlordId) throws Exception {
		try{
			openConnection();
			String comando="update locador set numero_de_operacoes = '"+amount+"' where id_locador = '"+landlordId+"'";
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
	
	
	public void setRateRenter(Double rate, int renterId) throws Exception {
		try{
			openConnection();
			String comando="update locatario set avaliacao = '"+rate+"' where id_locatario = '"+renterId+"'";
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
	
	public void setQuantifyOperationRenter(int amount, int renterId) throws Exception {
		try{
			openConnection();
			String comando="update locatario set numero_de_operacoes = '"+amount+"' where id_locatario = '"+renterId+"'";
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
