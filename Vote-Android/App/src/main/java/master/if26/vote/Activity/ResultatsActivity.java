package master.if26.vote.Activity;

import android.os.Bundle;

import android.support.v4.app.FragmentActivity;

import master.if26.vote.R;

/**
 * + Projet : IF26 - Application de vote
 * + Date : Automne 2014
 * + Lieu : Université Technologique de Troyes (10000)
 * + Auteur : Nicolas D'ALAYER DE COSTEMORE D'ARC & Alexandre ORTIZ
 * -----------------------------------------------------------------
 * + Type : Activity
 * + Name : RésultatsActivity.java
 * + Fichiers liés : layout.activity_résultats.xml
 * + Description : Activity utilisée lorsqu'un vote terminé est sélectionné
 * à partir de l'onglet "Votes terminés"
 */

public class ResultatsActivity extends FragmentActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_resultats);

    }
}
