package fachschaftwirtschaft.fachschaftapp;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import java.util.List;
import java.util.Vector;


/** Bildet den Rahmen fuer die Admin Fragmente
 * @author Matthias Heinen
 */
public class AdminActivity extends FragmentActivity {


    /** maintains the pager adapter*/
    private MyPagerAdapter mPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_admin_base);

        this.initialisePaging();

    }
    /**
     * Initialise the fragments to be paged
     */
    private void initialisePaging() {

        List<Fragment> fragments = new Vector<>();
        fragments.add(Fragment.instantiate(this, FragmentAdminNewAppointment.class.getName()));
        fragments.add(Fragment.instantiate(this, FragmentAdminChangeUserName.class.getName()));
        this.mPagerAdapter  = new MyPagerAdapter(super.getSupportFragmentManager(), fragments);
        //
        ViewPager pager = (ViewPager)super.findViewById(R.id.pager);
        pager.setAdapter(this.mPagerAdapter);


    }

}


