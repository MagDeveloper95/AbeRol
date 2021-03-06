package com.proyecto.AbeRol.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.proyecto.AbeRol.Interfaces.IPlayerDAO;
import com.proyecto.AbeRol.UIUtils.ConnectionDB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlayerDAO extends Player implements IPlayerDAO {
	
	private final static String GETPLAYERBYID = "SELECT id, name, level, strength, dexerity, intelligence, information, height, "
			+ "weight, class, age, id_Rol FROM player WHERE id = ?";
	private final static String GETPLAYERBYNAME = "SELECT id, name, level, strength, dexerity, intelligence, information, height, "
			+ "weight, class, age, id_Rol FROM player WHERE name = ?";
	private final static String GETPLAYERSBYROL = "SELECT id, name, level, strength, dexerity, intelligence, information, height, "
			+ "weight, class, age, id_Rol FROM player WHERE id_Rol = ?";
	private final static String SELECTPLAYERS = "SELECT name FROM player";
	
	public static PlayerDAO MPlayer;

	public PlayerDAO(int id, String name, int level, int strength, int dexerity, int intelligence, String information,
			int height, int weight, String classRol, int age, Rol contains) {
		super(id, name, level, strength, dexerity, intelligence, information, height, weight, classRol, age, contains);
	}

	public PlayerDAO() {
		super();
	}

	public PlayerDAO(Player dummy) {
		this.id = dummy.id;
		this.classRol = dummy.classRol;
		this.age = dummy.age;
		this.contains = dummy.contains;
		this.name = dummy.name;
		this.level = dummy.level;
		this.strength = dummy.strength;
		this.dexerity = dummy.dexerity;
		this.intelligence = dummy.intelligence;
		this.information = dummy.information;
		this.height = dummy.height;
		this.weight = dummy.weight;
	}

	public PlayerDAO(int id) {
		super();
		Connection con = ConnectionDB.getConexion();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(GETPLAYERBYID);
				q.setInt(1, id);
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					this.id = rs.getInt("id");
					this.name = rs.getString("name");
					this.level = rs.getInt("level");
					this.strength = rs.getInt("strength");
					this.dexerity = rs.getInt("dexerity");
					this.intelligence = rs.getInt("intelligence");
					this.information = rs.getString("information");
					this.height = rs.getInt("height");
					this.weight = rs.getInt("weight");
					this.classRol = rs.getString("class");
					this.age = rs.getInt("age");
					this.contains = new RolDAO(rs.getInt("id_rol"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public PlayerDAO(String name) {
		super();
		Connection con = ConnectionDB.getConexion();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(GETPLAYERBYNAME);
				q.setString(1, name);
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					this.id = rs.getInt("id");
					this.name = rs.getString("name");
					this.level = rs.getInt("level");
					this.strength = rs.getInt("strength");
					this.dexerity = rs.getInt("dexerity");
					this.intelligence = rs.getInt("intelligence");
					this.information = rs.getString("information");
					this.height = rs.getInt("height");
					this.weight = rs.getInt("weight");
					this.classRol = rs.getString("class");
					this.age = rs.getInt("age");
					this.contains = new RolDAO(rs.getInt("id_rol"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * method that returns an observable list of players
	 * 
	 * @param id rol identifier
	 * @return returns the players of each rol
	 */
	public static ObservableList<Player> getPlayerByRol(int id) {

		ObservableList<Player> listPlayers = FXCollections.observableArrayList();
		Connection con = ConnectionDB.getConexion();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(GETPLAYERSBYROL);
				q.setInt(1, id);
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					Player auxP = new Player();
					auxP.setId(rs.getInt("id"));
					auxP.setName(rs.getString("name"));
					auxP.setLevel(rs.getInt("level"));
					auxP.setStrength(rs.getInt("strength"));
					auxP.setDexerity(rs.getInt("dexerity"));
					auxP.setIntelligence(rs.getInt("intelligence"));
					auxP.setInformation(rs.getString("information"));
					auxP.setHeight(rs.getInt("height"));
					auxP.setWeight(rs.getInt("weight"));
					auxP.setClassRol(rs.getString("class"));
					auxP.setAge(rs.getInt("age"));
					listPlayers.add(auxP);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listPlayers;
	}

	/**
	 * method that returns a list of players' names
	 * 
	 * @return method that returns a list of players' names
	 */
	public static List<String> getPlayers() {
		List<String> getNombres = new ArrayList<>();
		Connection con = ConnectionDB.getConexion();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(SELECTPLAYERS);
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

	@Override
	public String toString() {
		return name;
	}

	///////////////////// UPDATE NOT FINISHED /////////////////////
	
	private final static String DELETEPLAYER = "DELETE FROM Player WHERE name = ?";
	private final static String MODIFYPLAYER = "INSERT INTO Player (name, level, strength, dexerity, intelligence, information, height, weight, classRol, age, contains)"
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE name=?,level=?,strength=?,dexerity=?,intelligence=?"
			+ ",information=?,height=?,weight=?,classRol=?,age=?,contains=?";
	
	public int deletePlayer() {
		int rs = 0;
		Connection con = ConnectionDB.getConexion();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(DELETEPLAYER);
				q.setInt(1, this.id);
				rs = q.executeUpdate();
				this.id = -1;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rs;
	}

	public int SavePlayer() {
		int save = 0;
		Connection con = ConnectionDB.getConexion();
		if (this.contains == null) {
			this.contains = new Rol();
		}
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(MODIFYPLAYER);
				q.setString(1, this.name);
				q.setInt(2, this.level);
				q.setInt(3, this.strength);
				q.setInt(4, this.dexerity);
				q.setInt(5, this.intelligence);
				q.setString(6, this.information);
				q.setInt(7, this.height);
				q.setInt(8, this.weight);
				q.setString(9, this.classRol);
				q.setInt(10, this.age);
				q.setInt(11, this.contains!=null?this.contains.id:-1);
				
				q.setString(12, this.name);
				q.setInt(13, this.level);
				q.setInt(14, this.strength);
				q.setInt(15, this.dexerity);
				q.setInt(16, this.intelligence);
				q.setString(17, this.information);
				q.setInt(18, this.height);
				q.setInt(19, this.weight);
				q.setString(20, this.classRol);
				q.setInt(21, this.age);
				q.setInt(11, this.contains!=null?this.contains.id:-1);
				
				save = q.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return save;
	}

}
