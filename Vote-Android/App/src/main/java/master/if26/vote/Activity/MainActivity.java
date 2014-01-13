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
 * + Name : MainActivity.java
 * + Fichiers liés : layout.activity_main
 * + Description : Activity utilisée lors du lancement de l'application, et que
 * l'utilisateur n'est pas connecté.
 */

public class MainActivity extends FragmentActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Affichage de la vue "activity_main".
        this.setContentView(R.layout.activity_main);

        //Déclaration du bouton "Créer un compte".
        final Button CreateAccountActivity_Button = (Button) findViewById(R.id.create_account);

        //Ecouteur sur le bouton "Créer un compte"
        CreateAccountActivity_Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                //Démarrer l'Acitivity "CreateAccountActivity" quand l'utilisateur clique sur le bouton.
                startActivity(intent);
            }
        });
    }
}
