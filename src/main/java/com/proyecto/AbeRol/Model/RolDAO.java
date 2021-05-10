package com.proyecto.AbeRol.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.proyecto.AbeRol.UIUtils.EnumBBDD;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RolDAO extends Rol {

	public RolDAO(int id, String name, String description, Master masterRol, List<Player> players) {
		super(id, name, description, masterRol, players);

	}
	public RolDAO(String name, String description) {
		super(name,description);
	}
	public RolDAO() {
		super();

	}

	public RolDAO(Rol aux) {
		this.id = aux.id;
		this.name = aux.name;
		this.description = aux.description;
		this.masterRol = aux.masterRol;
		this.players = aux.players;
	}

	public RolDAO(int id) {
		Connection con = ConnectionDB.getConexion();
		if (con != null) {
			try {
				PreparedStatement query = con.prepareStatement(EnumBBDD.GETROLBYNAME.getString());
				query.setInt(1, id);
				ResultSet rs = query.executeQuery();
				while (rs.next()) {
					this.id = rs.getInt("id");
					this.name = rs.getString("name");
					this.description = rs.getString("description");
					this.masterRol = new MasterDAO(rs.getInt("id_Master"));
				}
				this.players = PlayerDAO.getPlayerByRol(this.id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int saveRol() {
		int saveResult = 0;
		Connection con = ConnectionDB.getConexion();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(EnumBBDD.INTERTUPDATEROL.getString());
				q.setString(1, this.name);
				q.setString(2, this.description);
				q.setString(3, this.name);
				q.setString(4, this.description);
				saveResult = q.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return saveResult;
	}

	public static int deleteRol(String name) {
		int deleteMasterResult = 0;
		Connection con = ConnectionDB.getConexion();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(EnumBBDD.DELETEROL.getString());
				q.setString(1, name);
				deleteMasterResult = q.executeUpdate();
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return deleteMasterResult;
	}

	public static ObservableList<Rol> getRolByMaster(int id) {
		ObservableList<Rol> rolList = FXCollections.observableArrayList();
		Connection con = ConnectionDB.getConexion();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(EnumBBDD.GETROLBYMASTER.getString());
				q.setInt(1, id);
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					// cada row
					Rol auxR = new Rol();
					auxR.setId(rs.getInt("id"));
					auxR.setName(rs.getString("name"));
					auxR.setDescription(rs.getString("description"));
					rolList.add(auxR);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rolList;
	}
	 public static List<String> getRols() {
	        List<String> getNombres = new ArrayList<>();
	        Connection con = ConnectionDB.getConexion();
	        if (con != null) {
	          try {
	        	PreparedStatement q = con.prepareStatement(EnumBBDD.SELECTROL.getString());
	            ResultSet rs = q.executeQuery();
	            while (rs.next()) {
	              getNombres.add(rs.getString("name"));
	            }
	          } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	          }
	        }
	        return getNombres;
	      }
}
