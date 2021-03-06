package com.aarellanoh.navdrawer2;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        Fragment1.OnFragmentInteractionListener,
        Fragment2.OnFragmentInteractionListener,
        Fragment3.OnFragmentInteractionListener,
        Fragment4.OnFragmentInteractionListener,
        Fragment5.OnFragmentInteractionListener,
        Fragment6.OnFragmentInteractionListener{

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private String[] myMenu;
    private DrawerLayout myDrawerLayout;
    private ListView myDrawerList;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.left_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.left_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
//        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentManager fragmentManager = getFragmentManager();

        //Inicializar el fragment a Fragment1 (pantalla por default(?))
        Fragment fragment = new Fragment1();

        //Hacer que el fragment sea de diferente clase dependiendo del menu que fue seleccionado
        switch (position){
            case 0: {
                fragment = new Fragment1();
                break;
            }
            case 1: {
                fragment = new Fragment2();
                break;
            }
            case 2: {
                fragment = new Fragment3();
                break;
            }
            case 3: {
                fragment = new Fragment4();
                break;
            }
            case 4: {
                fragment = new Fragment5();
                break;
            }
            case 5: {
                fragment = new Fragment6();
                break;
            }
        }

        //Cambiar el fragment actual por el nuevo fragment
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }

    public void onSectionAttached(int number) {

        //El nombre de la pantalla corresponderia al de los menus (guardados en un array en strings.xml
        String myMenu[];
        myMenu = getResources().getStringArray(R.array.myMenu);
        Log.d("Prueba","onSectionAttached(number)=" + number);
        //TODO: onSectionAttached no se esta activando cuando un nuevo fragment es attached
        switch (number) {
            case 1:
                mTitle = myMenu[0];
                break;
            case 2:
                mTitle = myMenu[1];
                break;
            case 3:
                mTitle = myMenu[2];
                break;
            case 4:
                mTitle = myMenu[3];
                break;
            case 5:
                mTitle = myMenu[4];
                break;
            case 6:
                mTitle = myMenu[5];
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }

        /*@Override
        public void onAttach(Context context) {
            super.onAttach(context);
            ((MainActivity) context).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }*/
    }

    //Interface implementations from Fragments
    //Para cuando una accion sea hecha desde el fragment actual
    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d("Prueba","Un boton fue presionado");
    }
    public void onFragmentInteraction(){
        Log.d("Prueba","onFragmentInteraction sin parametros");
    }

    public void onFragmentInteraction(String string){
        Log.d("Prueba","" + string);
    }
}
