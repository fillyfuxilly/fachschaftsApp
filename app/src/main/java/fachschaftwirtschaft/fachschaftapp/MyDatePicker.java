package fachschaftwirtschaft.fachschaftapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.DatePicker;


/** Da der DatePicker in AdminActivity innerhalb eines ScrollView liegt, muss eine Methode ueberschrieben werden, um innerhalb des View scrollen zu koennen
 * @author Matthias Heinen
 */
public class MyDatePicker extends DatePicker {
    public MyDatePicker(Context context, AttributeSet attrs, int
            defStyle)
    {
        super(context, attrs, defStyle);
    }

    public MyDatePicker(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public MyDatePicker(Context context)
    {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        if (ev.getActionMasked() == MotionEvent.ACTION_DOWN)
        {
            ViewParent p = getParent();
            if (p != null)
                p.requestDisallowInterceptTouchEvent(true);
        }

        return false;
    }
}

