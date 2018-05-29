// Programmierung verteilter und mobiler Anwendungen
// (Programming Distributed and Mobile Applications)
// Prof. Dr. Carsten Vogt
// Cologne University of Applied Sciences
// Institute of Communications Engineering
// 11.5.2015

package de.fhkoeln.cvogt.android.bouncingball;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

// activity to display the view with the animation

public class BouncingBallActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new BouncingBallView(this,null,0));


	}
}

// view with the animation

class BouncingBallView extends SurfaceView implements SurfaceHolder.Callback {
	
	private BouncingBallAnimationThread bbThread = null; // thread to control the animation (see class definition 'BouncingBallView' below)
	private int xPosition = 100; // xPosition of the ball
	private int yPosition = 100; // yPosition of the ball (initial position: somewhere close to the upper-left corner)
	private int xDirection = 20;  // xDirection of ball movement
	private int yDirection = 40;  // yDirection of ball movement
	private static int radius = 50; // radius of the ball
	private static int ballColor = Color.RED; // color of the ball

	private int xPosition_2 = 200; // xPosition of the ball
	private int yPosition_2 = 200; // yPosition of the ball (initial position: somewhere close to the upper-left corner)
	private int xDirection_2 = 70;  // xDirection of ball movement
	private int yDirection_2 = 30;  // yDirection of ball movement
	private static int[]radius_2=new int[]{20,22,24,26,28,30,32,34,36,38,40,42,44,46,48,50,52,54,56,58,60};
	private static int ballColor_2 = Color.GREEN; // color of the ball

    // constructor: registers the callback methods defined in this class (surfaceCreated/changed/destroyed) to be called when the view appears / changes / disappears
	public BouncingBallView(Context ctx, AttributeSet attrs, int defStyle) {
		super(ctx, attrs, defStyle);
		getHolder().addCallback(this);
	}

    // method called by the control thread to draw the next step of the animation
    @Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint paint = new Paint();
        // clear the screen
		paint.setColor(Color.BLACK);
		canvas.drawRect(0,0,getWidth(),getHeight(), paint);
        // draw the ball at its current position
		paint.setColor(ballColor);
		canvas.drawCircle(xPosition, yPosition, radius, paint);
		paint.setColor(ballColor_2);
		canvas.drawCircle(xPosition_2,yPosition_2,radius_2[bbThread.Geti()],paint);
		Log.v("DEMO","onDraw() "+xPosition+" "+yPosition);
	}

    // method to alter direction and speed of the ball movement when the user touches the display
	public boolean onTouchEvent(MotionEvent event) {
		 if (event.getAction()!=MotionEvent.ACTION_DOWN) return true;
         // stop the ball when it is currently in motion
		 if (xDirection!=0 || yDirection!=0)
		    xDirection = yDirection = 0;
		  else {
            // put the ball into motion again (direction determined by the position of the user's touch)
		    xDirection = (int) event.getX() - xPosition;
		    yDirection = (int) event.getY() - yPosition;
		  }

		//Ball 2

		if (xDirection_2!=0 || yDirection_2!=0)
			xDirection_2 = yDirection_2 = 0;
		else {
			// put the ball into motion again (direction determined by the position of the user's touch)
			xDirection_2 = (int) event.getX() - xPosition_2;
			yDirection_2 = (int) event.getY() - yPosition_2;
		}
		 return true;
		}

    // callback method to create an start the thread when the view appears on the display
	public void surfaceCreated(SurfaceHolder holder) {
		Log.v("DEMO","surfaceCreated");
		if (bbThread!=null) return;
		bbThread = new BouncingBallAnimationThread(getHolder());
		bbThread.start();
	}

    // callback method called when the display is modified: does nothing here
	public void surfaceChanged(SurfaceHolder holder,
			int format, int width, int height) {  }

    // callback method to stop the thread when the view disappears from the screen
	public void surfaceDestroyed(SurfaceHolder holder){
		bbThread.stop = true;
	}
	
    // Thread to control the animation
	private class BouncingBallAnimationThread extends Thread {
		private int i=0;
		public boolean stop = false; // boolean variable to repeat or leave the run loop
		private SurfaceHolder surfaceHolder;

        // constructor
		public BouncingBallAnimationThread(SurfaceHolder surfaceHolder) {
			this.surfaceHolder = surfaceHolder;
		}

		int Geti(){
			return i;
		}

        // method to be executed by the thread: controls the animation
        @SuppressLint("WrongCall")  // annotation needed for the onDraw() call - otherwise the 'lint' static code analyzer will not let the control thread call onDraw() because normally only the runtime system should call this method
        public void run() {
			boolean runter = false;

			while (!stop) {
				xPosition += xDirection; // move the ball into the given direction (= update xPosition ...
				yPosition += yDirection; // ... and yPosition)

				//Ball 2

				xPosition_2 += xDirection_2; // move the ball into the given direction (= update xPosition ...
				yPosition_2 += yDirection_2; // ... and yPosition)
                // check if the ball has reached a border of the display and, if so, let it rebounce
				if (xPosition<radius) {
					xDirection = -xDirection;
					xPosition = radius; }
				if (xPosition>getWidth()-radius) {
					xDirection = -xDirection;
					xPosition = getWidth()-radius; }
				if (yPosition<radius) {
					yDirection = -yDirection;
					yPosition = radius; }
				if (yPosition>getHeight()-radius) {
					yDirection = -yDirection;
					yPosition = getHeight()-radius-1; }

				//Ball 2

				if (xPosition_2<radius_2[i]) {
					xDirection_2 = -xDirection_2;
					xPosition_2 = radius_2[i]; }
				if (xPosition_2>getWidth()-radius_2[i]) {
					xDirection_2 = -xDirection_2;
					xPosition_2 = getWidth()-radius_2[i]; }
				if (yPosition_2<radius_2[i]) {
					yDirection_2 = -yDirection_2;
					yPosition_2 = radius_2[i]; }
				if (yPosition_2>getHeight()-radius_2[i]) {
					yDirection_2 = -yDirection_2;
					yPosition_2 = getHeight()-radius_2[i]-1; }
				Canvas c = null;
				try {
					c = surfaceHolder.lockCanvas(null);
					synchronized (surfaceHolder) {
                        if (c!=null)
                         // redraw the view = show the ball at its new position
						 onDraw(c);
					}
				} finally {
					if (c != null) surfaceHolder.unlockCanvasAndPost(c);

				}
				if(i<20 && runter==false){
					i++;
				}else if(i == 19){
					runter = true;
					i++;
				}else if (i>0 && runter==true){
					i--;
				}else{
					i--;
					runter=false;
				}

			}
		}
	}

}