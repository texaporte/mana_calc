package com.mondido.manamath;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TableLayout.LayoutParams;

public class ManaCalcActivity extends Activity {
	
	private Map<ManaColor, EditText> landCountEditViews;
	private EditText totalLandCountEditView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        landCountEditViews=new HashMap<ManaColor, EditText>();
        landCountEditViews.put(ManaColor.WHITE,(EditText)findViewById(R.id.white_count));
        landCountEditViews.put(ManaColor.BLACK,(EditText)findViewById(R.id.black_count));
        landCountEditViews.put(ManaColor.RED,(EditText)findViewById(R.id.red_count));
        landCountEditViews.put(ManaColor.BLUE,(EditText)findViewById(R.id.blue_count));
        landCountEditViews.put(ManaColor.GREEN,(EditText)findViewById(R.id.green_count));
        totalLandCountEditView=(EditText)findViewById(R.id.land_count);   
    }
    /**
     * OnClick Event to invoke the calculation of the Mana spread
     * @param view
     */
    public void onSwaggit(View view){
    	cleanAnswerTextViews();
    	this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    	String totalLandCount;
    	if(!(totalLandCount=totalLandCountEditView.getText().toString()).isEmpty()){
    		ManaCalculator calculator=new ManaCalculator(Integer.parseInt(totalLandCount));
    		
    		Map<ManaColor,Integer>symbolCounts=new HashMap<ManaColor, Integer>();
    		
    		getManaCounts(symbolCounts);
    		
    		calculator.calculateMana(symbolCounts);
    		
    		makeAnswerTextViews(calculator);
    	}
    }
    
    public void reset(View v){
    	cleanAnswerTextViews();
    	for (EditText view : landCountEditViews.values()) {
			view.setText("");
		}
    	totalLandCountEditView.setText("");
    }
    
    private void cleanAnswerTextViews() {
		LinearLayout rel = getAnswerLayout();
    	
    	rel.removeAllViews();
		
	}
	private LinearLayout getAnswerLayout() {
		LinearLayout rel=(LinearLayout)findViewById(R.id.answer_layout);
		return rel;
	}
	/**
     * Makes the TextViews that are needed to be made based on which values from the ManaCalculator calc comes back with.
     * These TextViews are then added to the main layout.
     * @param calc
     */
    private void makeAnswerTextViews(ManaCalculator calc){
    	LinearLayout rel = getAnswerLayout();
    	for (ManaColor color : ManaColor.values()) {
    		Long count;
			if((count=calc.getLandCount(color))>0){
				rel.addView(makeView(count,color.toString()),0);
			}
		}
    }
    
    /**
     * Factory method to facilitate the creation of TextViews
     * @param count
     * @param name
     * @return
     */
    private TextView makeView(long count,String name){
    	
    	final TextView resultView=new TextView(this);
    	resultView.setTextSize(25);
    	resultView.setText(count+" "+name);
    	ViewGroup.LayoutParams p = new LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
    	resultView.setLayoutParams(p);
    	return resultView;
    }

	private void getManaCounts(Map<ManaColor, Integer> symbolCounts) {
		for (ManaColor color : ManaColor.values()) {
			String text;
			if(!(text=landCountEditViews.get(color).getText().toString()).isEmpty()){
				symbolCounts.put(color, Integer.valueOf(text));
			}
		}
	}
}