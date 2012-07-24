package com.mondido.manamath;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ManaCalcActivity extends Activity {
	private EditText whiteCountEditView;
	private EditText greenCountEditView;
	private EditText blueCountEditView;
	private EditText redCountEditView;
	private EditText blackCountEditView;
	private EditText landCountEditView;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        whiteCountEditView=(EditText)findViewById(R.id.white_count);
        blackCountEditView=(EditText)findViewById(R.id.black_count);
        redCountEditView=(EditText)findViewById(R.id.red_count);
        blueCountEditView=(EditText)findViewById(R.id.blue_count);
        greenCountEditView=(EditText)findViewById(R.id.green_count);
        landCountEditView=(EditText)findViewById(R.id.land_count);   
    }
    
    public void onSwaggit(View v){
    	this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    	ManaCalculator calculator=new ManaCalculator(Integer.parseInt(landCountEditView.getText().toString()));
    	
    	int[] symbolCounts=new int[5];
    	
    	getManaCounts(symbolCounts);
    	
    	calculator.calculateMana(symbolCounts);
    	
    	setAnswerViews(calculator);
    	
    	
    }
    
    private void setAnswerViews(ManaCalculator calc){
    	LinearLayout rel=(LinearLayout)findViewById(R.id.main_layout);
    	if(calc.getWhiteLandCount()>0)
    		rel.addView(makeView(calc.getWhiteLandCount(),"Plains"));
    	if(calc.getBlackLandCount()>0)
    		rel.addView(makeView(calc.getBlackLandCount(),"Swamps"));
    	if(calc.getRedLandCount()>0)
    		rel.addView(makeView(calc.getRedLandCount(),"Mountains"));
    	if(calc.getBlueLandCount()>0)
    		rel.addView(makeView(calc.getBlueLandCount(),"Islands"));
    	if(calc.getGreenLandCount()>0)
    		rel.addView(makeView(calc.getGreenLandCount(),"Forests"));
    }
    
 private TextView makeView(long count,String name){
    	
    	TextView resultView=new TextView(this);
    	resultView.setText(count+" "+name);
    	LayoutParams p = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    	resultView.setLayoutParams(p);
    	return resultView;
    }

	private void getManaCounts(int[] symbolCounts) {
		if(!whiteCountEditView.getText().toString().isEmpty())
			symbolCounts[0]=Integer.parseInt((String) whiteCountEditView.getText().toString());
		if(!blackCountEditView.getText().toString().isEmpty())
			symbolCounts[1]=Integer.parseInt((String) blackCountEditView.getText().toString());
    	if(!blueCountEditView.getText().toString().isEmpty())
    		symbolCounts[2]=Integer.parseInt((String) blueCountEditView.getText().toString());
    	if(!greenCountEditView.getText().toString().isEmpty())
    		symbolCounts[3]=Integer.parseInt((String) greenCountEditView.getText().toString());
    	if(!redCountEditView.getText().toString().isEmpty())
    		symbolCounts[4]=Integer.parseInt((String) redCountEditView.getText().toString());
	}
}