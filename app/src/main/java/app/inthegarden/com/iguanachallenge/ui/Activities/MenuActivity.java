package app.inthegarden.com.iguanachallenge.ui.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import app.inthegarden.com.iguanachallenge.R;
import app.inthegarden.com.iguanachallenge.pojo.Contact;
import app.inthegarden.com.iguanachallenge.ui.Fragments.ContactListFragment;
import app.inthegarden.com.iguanachallenge.ui.Fragments.DetailFragment;

public class MenuActivity extends AppCompatActivity implements ContactListFragment.Notify{
    private Toolbar toolbar;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        loadToolbar();

        ContactListFragment contactsFragment = new ContactListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,contactsFragment).commit();
    }


    private void loadToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        toolbar.setTitle(R.string.app_name);
    }

    @Override
    public void goToDescription(String user_id) {
        Intent intent = new Intent(this,DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(DetailFragment.USERID,user_id);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
