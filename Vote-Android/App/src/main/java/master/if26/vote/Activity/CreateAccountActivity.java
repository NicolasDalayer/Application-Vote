package master.if26.vote.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import master.if26.vote.R;

/**
 * + Projet : IF26 - Application de vote
 * + Date : Automne 2014
 * + Lieu : Université Technologique de Troyes (10000)
 * + Auteur : Nicolas D'ALAYER DE COSTEMORE D'ARC & Alexandre ORTIZ
 * -----------------------------------------------------------------
 * + Type : Activity
 * + Name : CreateAccountActivity.java
 * + Fichiers liés : layout.activity_createaccount.xml
 * + Description : Activity utilisée lorsque l'utilisateur clique
 * sur le bouton "Créer un compte" sur la page de connexion de l'application.
 */

public class CreateAccountActivity extends FragmentActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //Appel au layout "activity_createaccount.xml".
        this.setContentView(R.layout.activity_createaccount);

        //Déclaration du bouton "Annuler".
        final Button CreateAccountActivity_AnnulerButton = (Button) findViewById(R.id.cancel_account);
        //Ecouteur sur le bouton "Annuler".
        CreateAccountActivity_AnnulerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                //Execution de l'activity.
                startActivity(intent);
            }
        });
    }
}