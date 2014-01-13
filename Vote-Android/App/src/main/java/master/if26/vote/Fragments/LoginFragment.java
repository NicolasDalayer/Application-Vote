package master.if26.vote.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;


import master.if26.vote.BuildConfig;
import master.if26.vote.R;

import master.if26.vote.Activity.MenuActivity;
import master.if26.vote.Constants.*;
import master.if26.vote.Services.LoginService;

/**
 * + Projet : IF26 - Application de vote
 * + Date : Automne 2014
 * + Lieu : Université Technologique de Troyes (10000)
 * + Auteur : Nicolas D'ALAYER DE COSTEMORE D'ARC & Alexandre ORTIZ
 * -----------------------------------------------------------------
 * + Type : Fragment
 * + Name : LoginFragment.java
 * + Fichiers liés : layout.fragment_login.xml - Constants.IntentConstants.java
 * Services.LoginService.java - Activity.MenuActivity.java
 * + Description : Fragment utilisé lorsque l'application est lancée. Va récupérer les
 * informations utilisateurs saisies pour les envoyer au serveur, et si l'utilisateur
 * est autorisé par le serveur, alors il peut accèder aux autres fonctionnalité de l'application.
 */

public class LoginFragment extends Fragment implements View.OnClickListener
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_login, null);
        viewGroup.findViewById(R.id.connect).setOnClickListener(this);

        if(BuildConfig.DEBUG)
        {
            ((EditText) viewGroup.findViewById(R.id.email)).setText("test1@test.fr");
            ((EditText) viewGroup.findViewById(R.id.password)).setText("test");
        }
        return viewGroup;
    }

    //Si l'utilisateur clique sur le bouton "Connexion", alors il faut vérifier les informations, et les envoyer au serveur.
    @Override
    public void onClick(View v)
    {
        //Déclaration des variablies qui seront utiles pour la demande de connexion au serveur soit :
        //L'adresse e-mail saisie par l'utilisateur
        String email = ((EditText) this.getView().findViewById(R.id.email)).getText().toString();
        //Et le mot de passe saisis par l'utilisateur
        String password = ((EditText) this.getView().findViewById(R.id.password)).getText().toString();

        // Vérification en local des informations utilisateurs saisies
        //Initialisation de l'erreur à fausse
        boolean error = false;

        //Si le mot de passe ne comporte pas de caractère, alors
        if(password.length() == 0)
        {
            //Afficher à l'utilisateur qu'il faut saisir un mot de passe
            ((EditText) this.getView().findViewById(R.id.password)).setError(this.getString(R.string.password_required));
            //Et mettre l'erreur à vraie
            error = true;
        }
        //Si l'adresse e-mail saisie par l'utilisateur n'est pas au format d'une adresse e-mail, alors
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            //Afficher à l'utilisateur qu'il faut correctement saisir l'adresse mail au bon format
            ((EditText) this.getView().findViewById(R.id.email)).setError(this.getString(R.string.email_error));
            //Et mettre l'erreur à vraie
            error = true;
        }
        //S'il y a une erreur, alors la retourner.
        if(error)
        {
            return;
        }


        LoginService loginService = new LoginService();
        try
        {
            //Si toutes les informations sont correctement saisie alors on peut envoyer au serveur et on attend en réponse le token de l'utilisateur
            String token = loginService.execute(email, password).get();
            //S'il y a un token en réponse, alors on peut ouvrir l'Activity "MenuActivity"
            if(token != null)
            {
                Intent intent = new Intent(this.getActivity(), MenuActivity.class);
                intent.putExtra(IntentConstants.TOKEN, token);
                this.startActivity(intent);
            }
            //Sinon, indiqué à l'utilisateur qu'il a sans doute fait une erreur de frappe.
            else
            {
                Toast.makeText(this.getActivity(), R.string.login_error, Toast.LENGTH_SHORT).show();
            }
        }
        catch(InterruptedException interruptedException)
        {

        }
        catch(ExecutionException executionException)
        {

        }
        catch(NullPointerException nullPointerException)
        {

        }
    }
}


