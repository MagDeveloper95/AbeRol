package com.proyecto.AbeRol.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.proyecto.AbeRol.Interfaces.IMasterDAO;
import com.proyecto.AbeRol.UIUtils.ConnectionDB;

import javafx.collections.ObservableList;

public class MasterDAO extends Master implements IMasterDAO {
	
	private final static String LOGINMENU = "SELECT name,password FROM Master WHERE name LIKE ? and password LIKE ?";
	private final static String GETMASTERBYID ="SELECT id,name,password,email FROM Master WHERE id = ?";
	private final static String GETMASTERBYNAMEPASS = "SELECT id,name,password,email FROM Master WHERE name = ? AND password =?";
	private final static String GETMASTERBYEMAIL = "SELECT id,name,password FROM Master WHERE email = ?";
	private final static String INSERTUPDATEMASTER = "INSERT INTO Master (name, email, password) VALUES (?,?,?) ON DUPLICATE KEY UPDATE name= ?, "
			+ "email = ?, password= ?";
	private final static String DELETEMASTER = "DELETE FROM Master WHERE name = ?";
	private final static String UPDATEMASTER = "UPDATE Master SET name= ?,email=?,password =? Where id = ?";
	private final static String SELECTMASTERS = "SELECT name FROM Master";
	
	public MasterDAO(int id, String name, String email, String password, ObservableList<Rol> rol) {
		super(id, name, email, password, rol);
	}

	public MasterDAO(String name, String email, String password) {
		super(name, email, password);
	}

	public MasterDAO() {
		super();
	}

	public MasterDAO(Master aux) {
		this.id = aux.id;
		this.name = aux.name;
		this.email = aux.email;
		this.password = aux.password;
		this.rol = aux.rol;
	}

	public MasterDAO(int id) {
		Connection con = ConnectionDB.getConexion();
		if (con != null) {
			try {
				PreparedStatement query = con.prepareStatement(GETMASTERBYID);
				query.setInt(1, id);
				ResultSet rs = query.executeQuery();
				while (rs.next()) {
					this.id = rs.getInt("id");
					this.name = rs.getString("Name");
					this.email = rs.getString("Email");
					this.password = rs.getString("Password");
					this.rol = RolDAO.getRolByMaster(id);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public MasterDAO(String name, String pass) {
		Connection con = ConnectionDB.getConexion();
		if (con != null) {
			try {
				PreparedStatement query = con.prepareStatement(GETMASTERBYNAMEPASS);
				query.setString(1, name);
				query.setString(2, pass);
				ResultSet rs = query.executeQuery();
				while (rs.next()) {
					this.id = rs.getInt("id");
					this.name = rs.getString("name");
					this.email = rs.getString("email");
					this.password = rs.getString("password");
					this.rol = RolDAO.getRolByMaster(id);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public MasterDAO(String email) {
		Connection con = ConnectionDB.getConexion();
		if (con != null) {
			try {
				PreparedStatement query = con.prepareStatement(GETMASTERBYEMAIL);
				query.setString(1, email);
				ResultSet rs = query.executeQuery();
				while (rs.next()) {
					this.id = rs.getInt("id");
					this.name = rs.getString("name");
					this.password = rs.getString("password");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int SaveMaster() {
		int saveResult = 0;
		Connection con = ConnectionDB.getConexion();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(INSERTUPDATEMASTER);
				q.setString(1, this.name);
				q.setString(2, this.email);
				q.setString(3, this.password);
				q.setString(4, this.name);
				q.setString(5, this.email);
				q.setString(6, this.password);
				saveResult = q.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return saveResult;
	}
	/**
	 * method to update an existing master without creating a new one
	 * @param id of the master
	 * @return an update
	 */
	public int updateMaster(int id) {
		int saveResult = 0;
		Connection con = ConnectionDB.getConexion();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(UPDATEMASTER);
				q.setString(1, this.name);
				q.setString(2, this.email);
				q.setString(3, this.password);
				q.setInt(4, id);
				saveResult = q.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return saveResult;
	}

	public int deleteMaster(String name) {
		int deleteMasterResult = 0;
		Connection con = ConnectionDB.getConexion();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(DELETEMASTER);
				q.setString(1, name);
				deleteMasterResult = q.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return deleteMasterResult;
	}
	/**
	 * boolean method to log in with name and password
	 * @param name of the master
	 * @param password of the master
	 * @return true if the login was successful
	 */
	public boolean logIn(String name, String password) {
		boolean logResult = false;
		try {
			Connection con = ConnectionDB.getConexion();
			PreparedStatement q = con.prepareStatement(LOGINMENU);
			q.setString(1, name);
			q.setString(2, password);
			ResultSet rs = q.executeQuery();
			if (!rs.next()) {
				logResult = false;
			} else {
				logResult = true;
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return logResult;
	}
	/**
	 * method to obtain a list with the names of the masters
	 * @return a list of existing masters
	 */
	public static List<String> getMasters() {
		List<String> getNombres = new ArrayList<>();
		Connection con = ConnectionDB.getConexion();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(SELECTMASTERS);
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					getNombres.add(rs.getString("name"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return getNombres;
	}

}