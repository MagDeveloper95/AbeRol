package com.proyecto.AbeRol.Model;

/**
 * Clase donde guardaremos los datos de los personajes para no
 * hacer que un player tenga muchos datos y sea un clase tan ámplia.
 * @author Abepuoh
 *
 */
public class Character {
	
	protected String name; //nombre
	protected int level; // nivel
	protected int strength; // fuerza
	protected int dexerity; // destreza
	protected int intelligence; //inteligencia
	protected String information; //información
	protected int height; //altura
	protected int weight; //peso

	public Character(String name, int level, int strenght, int dexerity, int intelligence, String information,
			int height, int weight) {
		super();
		this.name = name;
		this.level = level;
		this.strength = strenght;
		this.dexerity = dexerity;
		this.intelligence = intelligence;
		this.information = information;
		this.height = height;
		this.weight = weight;
	}
	public Character() {
		super();
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strenght) {
		this.strength = strenght;
	}

	public int getDexerity() {
		return dexerity;
	}

	public void setDexerity(int dexterity) {
		this.dexerity = dexterity;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	

}

