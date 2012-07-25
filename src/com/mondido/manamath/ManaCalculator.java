package com.mondido.manamath;

import java.util.HashMap;
import java.util.Map;

public class ManaCalculator {
	private int LandCount;
	private Map<ManaColor,Long> ColorLandCounts;

	public long getLandCount(ManaColor color){
		try {
			return ColorLandCounts.get(color);
		} catch (NullPointerException e) {
			return 0;
		}
	}

	private void setLandCount(int landCount) {
		LandCount = landCount;
	}

	public ManaCalculator(int landCount) {
		this.setLandCount(landCount);
		ColorLandCounts=new HashMap<ManaColor, Long>();
	}
	
	public void calculateMana(Map<ManaColor,Integer> symbolCounts){
		int totalSymbols=0;
		for (Integer i : symbolCounts.values()) {
			totalSymbols+=i;
		}
		for (ManaColor color : ManaColor.values()) {
			if(symbolCounts.get(color)!=null)
				calculateLandCount(color, symbolCounts.get(color),totalSymbols);
		}
		
	}

	private void calculateLandCount(ManaColor color, int i, int totalSymbols) {
		double landCount=0;
		landCount=(i*LandCount)/totalSymbols;
		ColorLandCounts.put(color, Long.valueOf(Math.round(landCount)));
	}
}

