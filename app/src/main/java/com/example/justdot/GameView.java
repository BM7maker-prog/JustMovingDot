package com.example.justdot;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class GameView extends View {

    private int dotX = 500; // Initial X position
    private int dotY = 500; // Initial Y position
    private int dotRadius = 30; // Radius of the dot
    private Paint paint;

    public GameView(Context context) {
        super(context);

        // Initialize the Paint object for the dot
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Clear the canvas with a background color
        canvas.drawColor(Color.WHITE);

        // Draw the dot
        canvas.drawCircle(dotX, dotY, dotRadius, paint);
    }

    // Method to move the dot
    public void moveDot(int dx, int dy) {
        dotX += dx;
        dotY += dy;

        // Prevent the dot from moving off-screen
        if (dotX < dotRadius) dotX = dotRadius; // Left boundary
        if (dotY < dotRadius) dotY = dotRadius; // Top boundary
        if (dotX > getWidth() - dotRadius) dotX = getWidth() - dotRadius; // Right boundary
        if (dotY > getHeight() - dotRadius) dotY = getHeight() - dotRadius; // Bottom boundary

        // Redraw the view
        invalidate();
    }
}
