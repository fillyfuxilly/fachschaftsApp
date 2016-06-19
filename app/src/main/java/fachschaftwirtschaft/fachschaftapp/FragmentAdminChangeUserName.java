package fachschaftwirtschaft.fachschaftapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/** Fragment fuer den Admin, um den Namen eines Nutzers zu aendern.
 * @author Matthias Heinen
 */
public class FragmentAdminChangeUserName extends Fragment {
    /** (non-Javadoc)
     * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_admin_change_username, container, false);
    }
}
