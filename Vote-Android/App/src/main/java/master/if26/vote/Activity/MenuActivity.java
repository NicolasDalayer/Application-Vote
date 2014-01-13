package master.if26.vote.Activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import master.if26.vote.Fragments.VoteOpenFragment;
import master.if26.vote.Fragments.VoteCloseFragment;
import master.if26.vote.Fragments.AboutFragment;
import master.if26.vote.R;

/**
 * + Projet : IF26 - Application de vote
 * + Date : Automne 2014
 * + Lieu : Université Technologique de Troyes (10000)
 * + Auteur : Nicolas D'ALAYER DE COSTEMORE D'ARC & Alexandre ORTIZ
 * -----------------------------------------------------------------
 * + Type : Activity
 * + Name : MenuActivity.java
 * + Fichiers liés : layout.activity_menu.xml - Fragments.VoteOpenFragment.java
 * Fragments.VoteCloseFragment.java - Fragments.AboutFragment.java
 * + Description : Activity utilisée à partir du moment qu'un utilisateur est
 * connecté. Cette activity permet d'afficher une barre d'onglet en haut de
 * l'écran, et de naviguer dans l'application par 3 onglets.
 */

public class MenuActivity extends ActionBarActivity implements ActionBar.TabListener {
    //Définition des titres des onglets
    private  static final String TAB_1 = "Votes en cours";
    private  static final String TAB_2 = "Votes terminés";
    private  static final String TAB_3 = "A propos";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        //Définition de la vue
        setContentView(R.layout.activity_menu);

        //Configuration de la barre d'onglet
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        //Définition du contenu dans chacun des onglets
        //Onglet 1 : "Vote en cours"
        VoteOpenFragment fragment1 = ((VoteOpenFragment) getSupportFragmentManager().findFragmentById(R.id.fragment1));
        ActionBar.Tab tab1 = getSupportActionBar().newTab();
        tab1.setText(TAB_1);
        tab1.setTabListener(this);
        getSupportActionBar().addTab(tab1);

        //Onglet 2 : "Vote terminés"
        VoteCloseFragment fragment2 = ((VoteCloseFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2));
        ActionBar.Tab tab2 = getSupportActionBar().newTab();
        tab2.setText(TAB_2);
        tab2.setTabListener(this);
        getSupportActionBar().addTab(tab2);

        //Onglet 3 : A propos
        AboutFragment fragment3 = ((AboutFragment) getSupportFragmentManager().findFragmentById(R.id.fragment3));
        ActionBar.Tab tab3 = getSupportActionBar().newTab();
        tab3.setText(TAB_3);
        tab3.setTabListener(this);
        getSupportActionBar().addTab(tab3);

        //Initialisation de la vue : Fragment1 affiché et les autres cachés, afin de ne pas tout afficher et gagner en performance.
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.show(fragment1);
        fragmentTransaction.hide(fragment2);
        fragmentTransaction.hide(fragment3);
        //Commit de l'initialisation
        fragmentTransaction.commit();
    }

    //Fonction utilisée dans le cas où un onglet est sélectionné
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        //Affiche un Toast contennat le nom de l'onglet sélectionné
        Toast.makeText(this, tab.getText() + " selectionné", Toast.LENGTH_SHORT).show();

        VoteOpenFragment fragment1 = ((VoteOpenFragment) getSupportFragmentManager().findFragmentById(R.id.fragment1));
        VoteCloseFragment fragment2 = ((VoteCloseFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2));
        AboutFragment fragment3 = ((AboutFragment) getSupportFragmentManager().findFragmentById(R.id.fragment3));

        //Si l'onglet 1 est sélectionné alors l'afficher
        if (tab.getText().equals(TAB_1)){
            fragmentTransaction.show(fragment1);

        }
        //Sinon, si c'est l'onglet 2 qui est sélectionné, alors afficher son contenu
        else if (tab.getText().equals(TAB_2)){
            fragmentTransaction.show(fragment2);
        }
        //Sinon, si c'est l'onglet 3 qui est sélectionné, alors afficher son contenu
        else if (tab.getText().equals(TAB_3)){
            fragmentTransaction.show(fragment3);
        }
    }

    //Fonction dans le cas où un onglet n'est plus sélectionné
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        VoteOpenFragment fragment1 = ((VoteOpenFragment) getSupportFragmentManager().findFragmentById(R.id.fragment1));
        VoteCloseFragment fragment2 = ((VoteCloseFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2));
        AboutFragment fragment3 = ((AboutFragment) getSupportFragmentManager().findFragmentById(R.id.fragment3));

        //Si c'était l'onglet 1 qui était sélectionné, alors cacher son contenu
        if (tab.getText().equals(TAB_1)){
            fragmentTransaction.hide(fragment1);
        }
        //Sinon si c'était l'onglet 2 qui était sélectionné, alors cacher son contenu
        else if (tab.getText().equals(TAB_2)){
            fragmentTransaction.hide(fragment2);
        }
        //Sinon si c'était l'onglet 3 qui était sélectionné, alors cacher son contenu
        else if (tab.getText().equals(TAB_3)){
            fragmentTransaction.hide(fragment3);
        }
    }

    //Fonction dans le cas un onglet est resélectionné (non utilisée pour l'application)
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        //Affiche un Toast contennat le nom de l'onglet sélectionné
        Toast.makeText(this, tab.getText() + " selectionné", Toast.LENGTH_SHORT).show();

        VoteOpenFragment fragment1 = ((VoteOpenFragment) getSupportFragmentManager().findFragmentById(R.id.fragment1));
        VoteCloseFragment fragment2 = ((VoteCloseFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2));
        AboutFragment fragment3 = ((AboutFragment) getSupportFragmentManager().findFragmentById(R.id.fragment3));

        //Si l'onglet 1 est sélectionné alors l'afficher
        if (tab.getText().equals(TAB_1)){
            fragmentTransaction.show(fragment1);

        }
        //Sinon, si c'est l'onglet 2 qui est sélectionné, alors afficher son contenu
        else if (tab.getText().equals(TAB_2)){
            fragmentTransaction.show(fragment2);
        }
        //Sinon, si c'est l'onglet 3 qui est sélectionné, alors afficher son contenu
        else if (tab.getText().equals(TAB_3)){
            fragmentTransaction.show(fragment3);
        }
    }


}
