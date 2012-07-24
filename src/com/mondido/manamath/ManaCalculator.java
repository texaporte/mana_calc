package com.mondido.manamath;

public class ManaCalculator {
	private int LandCount;
	private long[] ColorLandCounts=new long[5];

	public long getWhiteLandCount() {
		return ColorLandCounts[0];
	}

	public long getBlackLandCount() {
		return ColorLandCounts[1];
	}

	public long getBlueLandCount() {
		return ColorLandCounts[2];
	}

	public long getGreenLandCount() {
		return ColorLandCounts[3];
	}

	public long getRedLandCount() {
		return ColorLandCounts[4];
	}

	private void setLandCount(int landCount) {
		LandCount = landCount;
	}

	public ManaCalculator(int landCount) {
		this.setLandCount(landCount);
	}
	
	public void calculateMana(int[] symbolCounts){
		int totalSymbols=0;
		for (int i : symbolCounts) {
			totalSymbols+=i;
		}
		for(int i=0;i<symbolCounts.length;i++){
			ColorLandCounts[i]=calculateLandCount(symbolCounts[i],totalSymbols);
		}
	}

	private long calculateLandCount(int i, int totalSymbols) {
		double landCount=0;
		landCount=(i*LandCount)/totalSymbols;
		return Math.round(landCount);
	}
}

