package com.example.justdot;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private GameView gameView;
    private boolean isMoving = false; // Tracks whether the dot is currently moving
    private int moveX = 0, moveY = 0; // Movement direction variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the custom GameView and set it as the content view
        gameView = new GameView(this);
        setContentView(gameView);

        // Add a layout with control buttons
        View controls = View.inflate(this, R.layout.activity_main, null);
        addContentView(controls, new android.widget.RelativeLayout.LayoutParams(
                android.widget.RelativeLayout.LayoutParams.MATCH_PARENT,
                android.widget.RelativeLayout.LayoutParams.MATCH_PARENT
        ));

        // Button references
        Button btnUp = findViewById(R.id.btnUp);
        Button btnDown = findViewById(R.id.btnDown);
        Button btnLeft = findViewById(R.id.btnLeft);
        Button btnRight = findViewById(R.id.btnRight);

        // Set OnTouchListeners for continuous movement
        setMovementListener(btnUp, 0, -50); // Move Up
        setMovementListener(btnDown, 0, 50); // Move Down
        setMovementListener(btnLeft, -50, 0); // Move Left
        setMovementListener(btnRight, 50, 0); // Move Right);
    }

    // Method to set up a continuous movement listener for buttons
    private void setMovementListener(Button button, int dx, int dy) {
        button.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: // Button pressed
                    moveX = dx;
                    moveY = dy;
                    isMoving = true;
                    startMovement();
                    return true;

                case MotionEvent.ACTION_UP: // Button released
                    isMoving = false;
                    return true;
            }
            return false;
        });
    }

    // Starts the continuous movement of the dot
    private void startMovement() {
        new Thread(() -> {
            while (isMoving) {
                gameView.moveDot(moveX, moveY);
                try {
                    Thread.sleep(50); // Control the speed of movement (50ms delay)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
