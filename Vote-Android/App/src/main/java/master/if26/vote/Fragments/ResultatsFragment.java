package master.if26.vote.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import master.if26.vote.Constants.WebServiceConstants;
import master.if26.vote.R;

import master.if26.vote.Constants.IntentConstants;
import master.if26.vote.Model.Resultat;
import master.if26.vote.Services.ResultatsService;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * + Projet : IF26 - Application de vote
 * + Date : Automne 2014
 * + Lieu : Université Technologique de Troyes (10000)
 * + Auteur : Nicolas D'ALAYER DE COSTEMORE D'ARC & Alexandre ORTIZ
 * -----------------------------------------------------------------
 * + Type : Fragment
 * + Name : ResultatsFragment.java
 * + Fichiers liés : layout.fragment_resultats.xml - Constants.IntentConstants.java
 * Services.ResultatService.java - Activity.ResultatsActivity.java
 * + Description : Fragment utilisé lorsque l'utilisateur a cliqué sur un vote terminé.
 * Dans ce cas, il faut afficher les résultats du vote pour chacun des candidats, soit
 * leur nombre de voies.
 */

public class ResultatsFragment extends ListFragment
{
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        try
        {
            //Déclaration des variablies qui seront utiles pour l'envoie de la requête au serveur afin d'avoir en réponse la liste des candidat
            // et leur nombre de voies, soit :
            //Le token de l'utilisateur obtenu lors de sa connexion
            String token = this.getActivity().getIntent().getStringExtra(IntentConstants.TOKEN);
            //Et l'id du vote qu'il vient de sélectionner
            int vote = this.getActivity().getIntent().getIntExtra(IntentConstants.VOTE, -1);

            ResultatsService resultatsService = new ResultatsService();
            ArrayList<Resultat> resultats = resultatsService.execute(token, String.valueOf(vote)).get();

            this.setListAdapter(new ResultatsAdapter(this.getActivity(), resultats));
        }
        catch(InterruptedException interruptedException)
        {

        }
        catch(ExecutionException executionException)
        {

        }
    }

    public void onListItemClick(ListView l, View v, int position, long id)
    {

    }

    //Fonction permettant d'afficher les listes des noms des candidats et leurs nombres de voies pour chacun deux.
    private class ResultatsAdapter extends ArrayAdapter<Resultat>
    {
        public ResultatsAdapter(Context context, ArrayList<Resultat> resultats)
        {
            super(context, R.layout.resultats_list_entry, resultats);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {

            int layout = R.layout.resultats_list_entry;

            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this.getContext()).inflate(layout, null);

            ((TextView) viewGroup.findViewById(R.id.candidat)).setText(this.getItem(position).candidat);
            ((TextView) viewGroup.findViewById(R.id.nbvoies)).setText(this.getItem(position).nbvoies);
            return viewGroup;

        }

    }

