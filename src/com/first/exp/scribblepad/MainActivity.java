package com.first.exp.scribblepad;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.support.v4.app.NavUtils;


public class MainActivity extends Activity{
	
	DrawView drawView;
	
	
	ImageButton eraser,red,blu,blk,gry,ylo,grn;
	SeekBar seekbar;
	TextView size;
	
	static float width = 5;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);     
        
        setContentView(R.layout.activity_main);

        red=(ImageButton)findViewById(R.id.red); 
        blu=(ImageButton)findViewById(R.id.blue);
        blk=(ImageButton)findViewById(R.id.black);
        gry=(ImageButton)findViewById(R.id.grey);
        ylo=(ImageButton)findViewById(R.id.yellow);
        grn=(ImageButton)findViewById(R.id.green);
        eraser=(ImageButton)findViewById(R.id.eraser);
        
        seekbar=(SeekBar)findViewById(R.id.seekBar1);
        size=(TextView)findViewById(R.id.textview1);
        
        
        
        seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

        	public void onProgressChanged(SeekBar seekBar, int progress,
        			boolean fromUser) {
        		// TODO Auto-generated method stub
        		
        		width = progress/5;
        		size.setText(""+width);
        		
        		
        	}

        	public void onStartTrackingTouch(SeekBar seekBar) {
        		// TODO Auto-generated method stub
        		
        	}

        	public void onStopTrackingTouch(SeekBar seekBar) {
        		// TODO Auto-generated method stub
        		
        	}
        	
        });
        
        
        red.setOnClickListener(new ColorListener());
        blu.setOnClickListener(new ColorListener());
        blk.setOnClickListener(new ColorListener());
        ylo.setOnClickListener(new ColorListener());
        gry.setOnClickListener(new ColorListener());
        grn.setOnClickListener(new ColorListener());
        eraser.setOnClickListener(new ColorListener());
        
        drawView = new DrawView(this);
    }
}
