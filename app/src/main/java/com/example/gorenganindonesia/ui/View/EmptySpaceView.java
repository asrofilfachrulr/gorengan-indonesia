package com.example.gorenganindonesia.ui.View;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

public class EmptySpaceView extends View {

    public EmptySpaceView(Context context) {
        super(context);
        init();
    }

    private void init() {
        // Set the height to 40dp
        int heightInDp = 40;
        int heightInPixels = (int) (heightInDp * getResources().getDisplayMetrics().density);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, heightInPixels);

        // Set layout parameters for the view
        setLayoutParams(layoutParams);
    }
}
