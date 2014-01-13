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
 * + Name : CandidatsActivity.java
 * + Fichiers liés : layout.activity_candidats.xml
 * + Description : Activity utilisée lorsqu'un vote est sélectionné, pour afficher
 * la liste des candidats participant au vote en question.
 */

public class CandidatsActivity extends FragmentActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_candidats);

    }
}
