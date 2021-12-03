package com.bsoft.Ecalculator;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.*;
import android.graphics.*;
import android.view.View.*;
import com.bsoft.Ecalculator.Analysis.*;

public class MainActivity extends Activity {
	private TextView screen, preview;
	private double memory;
	private boolean isSolved;
	private Button delButton;
	
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		Typeface font=Typeface.createFromAsset(getAssets(),"pocket.ttf");
		screen = (TextView)findViewById(R.id.screen);
		screen.setTypeface(font);
		
		preview = (TextView)findViewById(R.id.preview);
		preview.setTypeface(font);
		
		delButton = (Button)findViewById(R.id.del_button);
		delButton.setOnLongClickListener(new OnLongClickListener(){
			@Override
			public boolean onLongClick(View view){
				screen.setText("0");
				preview.setText("");
				isSolved = false;
				return true;
			}
		});
		
		this.memory = 0;
		this.isSolved = false;
    }
	
	public void click(View view){
		if( view.getId() != R.id.negative && view.getId() != R.id.dot){
			try{
				double init;
				if(isSolved){
					init = Double.parseDouble(((Button)view).getText().toString());
					preview.setText("");
					isSolved = false;
				}else{
					init = Double.parseDouble( screen.getText().toString() + ((Button)view).getText());
				}
				this.setScreen(init);
			}catch(Exception ex){}
		}else if(view.getId() == R.id.negative){
			try{
				double init = -1 * Double.parseDouble(screen.getText().toString());
				this.setScreen(init);
			}catch(Exception ex){}
		}else if(view.getId() == R.id.dot){
			screen.setText( screen.getText().toString() + ((Button)view).getText());
		}
	}
	
	public void sign(View view){
		String sign = ((Button)view).getText().toString();
		if(isSolved){
			preview.setText("Ans " + sign);
			isSolved = false;
		}else{
			preview.setText(preview.getText().toString() + " " + screen.getText() + " " + sign);
		}
		screen.setText("0");
	}
	
	public void solve(View view){
		if(!isSolved){
			setScreen( Double.parseDouble(screen.getText().toString()));
			preview.setText( preview.getText().toString() + " " + screen.getText().toString());
		}
		this.memory = new Solver(this.preview.getText().toString(), this.memory).getAns();
		this.setScreen(this.memory);
		this.isSolved = true;
	}
	
	private void setScreen(double value){
		String init = Double.toString(value);
		screen.setText( init.endsWith(".0") ? init.substring(0, init.length()-2) : init);
	}
	
	public void delete(View view){
		if(screen.getText().length() >= 1){
			int last = screen.getText().length() - 1;
			screen.setText(screen.getText().subSequence(0, last));
		}else if(preview.getText().length() >= 1){
			String[] init = preview.getText().toString().split(" ");
			try{
				Double.parseDouble(init[init.length - 1]);
				screen.setText(init[init.length - 1]);
			}catch(NumberFormatException ex){}
			preview.setText(preview.getText().subSequence(0, preview.getText().length() - screen.getText().length() - 1));
		}
	}
	
	public void exit(View view){
		finish();
	}
}
