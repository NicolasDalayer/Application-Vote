package master.if26.vote.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import master.if26.vote.R;

/**
 * + Projet : IF26 - Application de vote
 * + Date : Automne 2014
 * + Lieu : Université Technologique de Troyes (10000)
 * + Auteur : Nicolas D'ALAYER DE COSTEMORE D'ARC & Alexandre ORTIZ
 * -----------------------------------------------------------------
 * + Type : Activity
 * + Name : AboutFragment.java
 * + Fichiers liés : layout.fragment_about
 * + Description : Fragment utilisé lorsque l'onglet "A propos" de l'application
 * est sélectionné pour afficher quelques information concernant l'application.
 */

public class AboutFragment  extends Fragment {

    private String name;
    private int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    public String getName()
    {
        return name;
    }

}
