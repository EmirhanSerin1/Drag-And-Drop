package pl.pollub.draganddrop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView drag, drop;
    TextView total, success;
    int totalCount, successCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drag = findViewById(R.id.drag);
        drop = findViewById(R.id.drop);
        total = findViewById(R.id.total);
        success = findViewById(R.id.success);

        drag.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent arg1) {
                // newPlainText(CharSequence label, CharSequence text)
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow =
                        new View.DragShadowBuilder(drag);
                // startDrag(data to be dragged, drag shadow, local data, flags)
                v.startDragAndDrop(data, shadow, null, 0);
                return true;
            }
        });

        drop.setOnDragListener(new View.OnDragListener() {
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        {
                            successCount = successCount + 1;
                            success.setText("Successful drops: " +  successCount);
                            break;
                        }
                        case DragEvent.ACTION_DRAG_ENDED:
                            {
                                totalCount = totalCount + 1;
                                total.setText("Total drops: " + totalCount);
                                break;
                            }

                } return true;
            }
        });

    }

}
