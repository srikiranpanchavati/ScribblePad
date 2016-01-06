package com.first.exp.scribblepad;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;

import android.util.AttributeSet;
import android.util.Log;
import android.view.InputEvent;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.view.View.*;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;



public class DrawView extends View implements OnTouchListener {


    ArrayList<Path> pathList= new ArrayList<Path>();
    ArrayList<Paint> paintList = new ArrayList<Paint>();
    Path path = new Path();
    Path path1 = new Path();    
    TextView size;
    Paint paint; 
    Bitmap mbitmap;
    String pathName; 
    
    
   public DrawView(Context context){
    	super(context);
    }


    public DrawView(Context context,AttributeSet attr) {
        super(context,null);
        
        
        setFocusable(true);
        setFocusableInTouchMode(true);
        setBackgroundColor(Color.WHITE);    
        
        this.setOnTouchListener(this);  
        
        pathName = "/mnt/sdcard/asr/pic.jpg";
    	mbitmap = BitmapFactory.decodeFile(pathName); 
    	paint = new Paint();
              
    }
       
     @Override
    public void onDraw(Canvas canvas) {      
     
    	
    	//canvas.drawBitmap(mbitmap, 0, 0, paint);
    	 int iIndex = 0;
    			 
                 Paint currentPaintObject;
                 

                 for (Path path : pathList) {
   
                      currentPaintObject = paintList.get(iIndex);
   
                      canvas.drawPath(path, currentPaintObject);
   
                      iIndex++;
   
                 }

    }
     
     
     
    public boolean onTouch(View view, MotionEvent event) {
    	
    	if(event.getAction() == MotionEvent.ACTION_DOWN){
    		
            path = new Path();

            path.moveTo(event.getX(), event.getY());
            //path.lineTo(event.getX(), event.getY());
           

           // path.addCircle(event.getX(), event.getY(), MainActivity.width/2, Direction.CW);

    }
    	
    	
    	else if(event.getAction() == MotionEvent.ACTION_MOVE){
			
            getColor(); // create Paint object and add to the paintlist
            float x,y;
            if((event.getPointerCount()-1)/2 > 0 && (event.getHistorySize())/2 > 0)
            {
            	x = event.getHistoricalX( (event.getPointerCount()-1)/2 , event.getHistorySize()/2);
                 y = event.getHistoricalY( (event.getPointerCount()-1)/2 , event.getHistorySize()/2);
            }
            else
            {
            	x = event.getX() -1 ;
            	y = event.getY() - 1;
            }
            
            path.quadTo(x,y ,event.getX(), event.getY());
            //path.lineTo(x, y);
            pathList.add(path);
           // MainActivity.width = (event.getPointerCount()-1)/2;

          

            path.moveTo(event.getX(), event.getY());
            
        
    }
    	
    	else if (event.getAction() == MotionEvent.ACTION_UP) {
    		path = new Path();
    		getColor();
           

            path.moveTo(event.getX(), event.getY());
           // path.lineTo(event.getX(), event.getY());

            path.lineTo(event.getX() + 1,  event.getY() + 1);
            pathList.add(path);
           
		}
    
  
    	invalidate();
    	return true;
    	
    }
    

    
    public void getColor(){
    	Paint temp=new Paint();
    	temp.setStyle(Paint.Style.STROKE);
 		temp.setStrokeJoin(Paint.Join.ROUND);
 		temp.setStrokeCap(Paint.Cap.ROUND);
 		
 		temp.setStrokeWidth(MainActivity.width);

 		switch(ColorListener.color){
 		case 0: temp.setColor(Color.BLUE);
 		break;
 		
 		case 1: temp.setColor(Color.RED);
 		break;
 		
 		case 2: temp.setColor(Color.BLACK);
 		break;
 		
 		case 3: temp.setColor(Color.YELLOW);
 		break;
 		
 		case 4: temp.setColor(Color.GREEN);
 		break;
 		
 		case 5: temp.setColor(Color.GRAY);
 		break;
 		
 		case 6: temp.setColor(Color.WHITE);
 		break;
 		
 		}
 		
 		
 		paintList.add(temp);
	 	
    }

}


class ColorListener implements OnClickListener{
	static int color;
	public void onClick(View v) {
		
		switch(v.getId()){
		
		case R.id.blue: color=0;
		break;
		
		case R.id.red: color=1;
		break;
		
		case R.id.black: color=2;
		break;
		
		case R.id.yellow: color=3;
		break;
		
		case R.id.green: color=4;
		break;
		
		case R.id.grey: color=5;
		break;
		
		case R.id.eraser: color= 6;
		break;
						
						
		}

		
	}
	
}

