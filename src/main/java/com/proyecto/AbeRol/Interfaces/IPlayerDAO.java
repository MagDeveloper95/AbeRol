package com.proyecto.AbeRol.Interfaces;

public interface IPlayerDAO {
	/**
	* Save a new Player in the base
	* @return 0 if successfully saved
	**/
	public int SavePlayer();
	/**
	* Delete a Player starting from the id
	* @return true if successfully removed
	**/
	public int deletePlayer();
	
}
