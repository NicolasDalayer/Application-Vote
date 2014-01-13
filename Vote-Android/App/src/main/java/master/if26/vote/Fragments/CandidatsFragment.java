package master.if26.vote.Fragments;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import master.if26.vote.R;
import master.if26.vote.Model.Candidat;
import master.if26.vote.Constants.*;
import master.if26.vote.Services.CandidatService;
import master.if26.vote.Services.CandidatsService;

/**
 * + Projet : IF26 - Application de vote
 * + Date : Automne 2014
 * + Lieu : Université Technologique de Troyes (10000)
 * + Auteur : Nicolas D'ALAYER DE COSTEMORE D'ARC & Alexandre ORTIZ
 * -----------------------------------------------------------------
 * + Type : Fragment
 * + Name : CandidatsFragment.java
 * + Fichiers liés : layout.candidats_list_entry.xml - Constants.IntentConstants.java
 * Model.Candiddat.java - Services.CandidatsService.java - Services.CandidatService
 * + Description : Fragment utilisé lorsqu'un vote en cours est sélectionné pour afficher
 * la liste des candidats y participant. Elle permet aussi lors d'un clique sur un
 * candidat, d'envoyer le "A voté", tout en vérifiant si celui-ci a été envoyé.
 */

public class CandidatsFragment extends ListFragment
{
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        try
        {
            String token = this.getActivity().getIntent().getStringExtra(IntentConstants.TOKEN);
            int vote = this.getActivity().getIntent().getIntExtra(IntentConstants.VOTE, -1);

            CandidatsService candidatsService = new CandidatsService();
            ArrayList<Candidat> candidats = candidatsService.execute(token, String.valueOf(vote)).get();

            this.setListAdapter(new CandidatsAdapter(this.getActivity(), candidats));
        }
        catch(InterruptedException interruptedException)
        {

        }
        catch(ExecutionException executionException)
        {

        }
    }

    /*Lorsqu'un clique sur le nom du candidat est fait, il faut envoyer le vote. Une fois le vote
    est envoyé, le serveur retourne le candidat pour lequel l'utilisateur à voté et un Toast indique
    que le vote est bien envoyé. Mais si l'utilisateur a déjà voté, alors le serveur va retourner une erreur.
    Dans ce cas, il faut dire au candidat qu'il à déjà voté. Le nom du candidat pour lequel l'utilisateur a
    voté n'est pas clairement afficheé pour des raisons de confidentialité.
     */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        //Déclaration des variablies qui seront utiles pour l'envoie du vote soit :
        //Le token de l'utilisateur,
        String token = this.getActivity().getIntent().getStringExtra(IntentConstants.TOKEN);
        //Le vote (son id) que l'utilisateur à précédemment sélectionné,
        int vote = this.getActivity().getIntent().getIntExtra(IntentConstants.VOTE, -1);
        //Et l'id du candidat pour lequel il vote, soit celui sélectionné.
        Candidat candidat = (Candidat) this.getListAdapter().getItem(position);

        try {
            //Envoyer ces informations au service CandidatService pour préparer la requête et envoyer le vote
            CandidatService candidatService = new CandidatService();
            ArrayList<Candidat> candidatObject = candidatService.execute(token, String.valueOf(vote), String.valueOf(candidat.id)).get();

            //Si la réponse du serveur contient le nom du candidat alors c'est que l'utilisateur n'a pas encore voté pour ce vote
            if(candidatObject != null)
            {
                //Alors fficher un Toast informant que le vote est bien envoyé
                Toast.makeText(this.getActivity(), R.string.vote_ok, Toast.LENGTH_SHORT).show();
            }
            //Sinon : Afficher un Toast informant que l'utilisateur à déjà voté.
            else
            {
                Toast.makeText(this.getActivity(), R.string.vote_error, Toast.LENGTH_SHORT).show();
            }

        }
        catch (InterruptedException e)
        {

        }
        catch (ExecutionException e)
        {

        }


    }

    //Fonction permettant d'afficher les listes des noms des candidats du vote.
    private class CandidatsAdapter extends ArrayAdapter<Candidat>
    {
        public CandidatsAdapter(Context context, ArrayList<Candidat> candidats)
        {
            super(context, R.layout.candidats_list_entry, candidats);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            int layout = R.layout.candidats_list_entry;

            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this.getContext()).inflate(layout, null);

            ((TextView) viewGroup.findViewById(R.id.candidat)).setText(this.getItem(position).candidat);

            return viewGroup;
        }
    }
}
