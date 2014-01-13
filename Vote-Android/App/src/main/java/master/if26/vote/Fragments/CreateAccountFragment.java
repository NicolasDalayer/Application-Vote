package master.if26.vote.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import master.if26.vote.Activity.MainActivity;
import master.if26.vote.BuildConfig;
import master.if26.vote.Constants.IntentConstants;
import master.if26.vote.R;
import master.if26.vote.Services.NewUserService;

import java.util.concurrent.ExecutionException;

/**
 * + Projet : IF26 - Application de vote
 * + Date : Automne 2014
 * + Lieu : Université Technologique de Troyes (10000)
 * + Auteur : Nicolas D'ALAYER DE COSTEMORE D'ARC & Alexandre ORTIZ
 * -----------------------------------------------------------------
 * + Type : Fragment
 * + Name : CreateAccountFragment.java
 * + Fichiers liés : layout.fragment_creacteaccount.xml - Services.NewUserService.java
 * Constants.IntentConstants.java - Activity.MainActivity.java
 * + Description : Fragment utilisé lorsqu'un utilisateur à cliqué sur le bouton
 * "Créer un compte" sur la page de connexion. Ce fragment va récupérer les valeurs
 * saisies dans les champs correspondant, tout en les vérifiant. Pour ensuite les
 * envoyer au serveur pour inscription.
 */
public class CreateAccountFragment extends Fragment implements View.OnClickListener
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_createaccount, null);
        viewGroup.findViewById(R.id.valide_create_account).setOnClickListener(this);

        if(BuildConfig.DEBUG)
        {
            ((EditText) viewGroup.findViewById(R.id.email_new)).setText("test1@test.fr");
            ((EditText) viewGroup.findViewById(R.id.password_new)).setText("test");
        }
        return viewGroup;
    }

    @Override
    public void onClick(View v)
    {
        //Déclaration des variablies qui seront utiles pour l'envoie du vote soit :
        //L'adresse e-mail saisie par l'utilisateur"
        String email = ((EditText) this.getView().findViewById(R.id.email_new)).getText().toString();
        //Et le mot de passe saisis par l'utilisateur
        String password = ((EditText) this.getView().findViewById(R.id.password_new)).getText().toString();

        // Vérification des informations saisies de manière locale
        boolean error = false;
        //Vérification si des caractères sont saisies dans la case du mot de passe
        if(password.length() == 0)
        {
            //S'il n'y a pas de caractére, alors afficher un Toast, et en retournant une erreur.
            ((EditText) this.getView().findViewById(R.id.password_new)).setError(this.getString(R.string.password_required));
            error = true;
        }
        //Vérification si le texte saisi dans e-mail, correspond à une adresse e-mail au format xxx@xxx.xx
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            //Si ce n'est pas le cas, alors retourner une erreur.
            ((EditText) this.getView().findViewById(R.id.email_new)).setError(this.getString(R.string.email_error));
            error = true;
        }
        //S'il y a une erreur, la retourner
        if(error)
        {
            return;
        }

        //Déclaration du service de creation de compte.
        NewUserService newUserServiceService = new NewUserService();
        try
        {
            String token = newUserServiceService.execute(email, password).get();
            //Si le serveur retourne un token, c'est que le compte est bien créé, alors
            if(token != null)
            {
                //Afficher à l'utilisateur que le compte est bien créé, et l'application retourne à la page de connexion
                Toast.makeText(this.getActivity(), R.string.createaccount_ok, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this.getActivity(), MainActivity.class);
                intent.putExtra(IntentConstants.TOKEN, token);
                this.startActivity(intent);
            }
            else
            {
                //Sinon, si l'adresse email est déjà enregistrée, l'indiquer à l'utilisateur.
                Toast.makeText(this.getActivity(), R.string.createaccount_error2, Toast.LENGTH_SHORT).show();
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
