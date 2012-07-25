package com.mondido.manamath;

public enum ManaColor {
	BLACK("Swamp"),
	BLUE("Island"),
	GREEN("Forest"),
	RED("Mountain"),
	WHITE("Plains");
	
	private String landName;
	
	public String getLandName() {
		return landName;
	}
	private void setLandName(String landName) {
		this.landName = landName;
	}
	private ManaColor(String name){
		setLandName(name);
	}
	
	@Override
	public String toString() {
		return getLandName();
	}
}
