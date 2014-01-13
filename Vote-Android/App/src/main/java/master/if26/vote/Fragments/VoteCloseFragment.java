package master.if26.vote.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import master.if26.vote.Activity.ResultatsActivity;
import master.if26.vote.Model.VoteClose;
import master.if26.vote.R;
import master.if26.vote.Services.VoteCloseService;
import master.if26.vote.Constants.IntentConstants;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


/**
 * + Projet : IF26 - Application de vote
 * + Date : Automne 2014
 * + Lieu : Université Technologique de Troyes (10000)
 * + Auteur : Nicolas D'ALAYER DE COSTEMORE D'ARC & Alexandre ORTIZ
 * -----------------------------------------------------------------
 * + Type : Fragment
 * + Name : VoteCloseFragment.java
 * + Fichiers liés : layout.votesclose_list_entry.xml - Services.VoteCloseService
 * Constants.IntentConstants.java - Activity.ResultatsActivity - Model.VoteClose
 * + Description : Fragment utilisé lorsqu'un utilisateur a cliqué sur l'onglet
 * "Vote terminés" pour afficher la liste des votes terminés.
 */
public class VoteCloseFragment extends ListFragment
{

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        try
        {
            //Pour obtenir cette liste, il faut envoyer le token de l'utilisateur au serveur
            String token = this.getActivity().getIntent().getStringExtra(IntentConstants.TOKEN);

            VoteCloseService votesService = new VoteCloseService();
            ArrayList<VoteClose> votesClose = votesService.execute(token).get();

            this.setListAdapter(new VotesAdapter(this.getActivity(), votesClose));
        }
        catch(InterruptedException interruptedException)
        {

        }
        catch(ExecutionException executionException)
        {

        }
    }

    //Une fois la liste obtenue, si l'utilisateur clique sur un nom de vote, alors il faut afficher les résultats du votes
    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        String token = this.getActivity().getIntent().getStringExtra(IntentConstants.TOKEN);
        VoteClose voteClose = (VoteClose) this.getListAdapter().getItem(position);

        Intent intent = new Intent(this.getActivity(), ResultatsActivity.class);
        intent.putExtra(IntentConstants.TOKEN, token);
        intent.putExtra(IntentConstants.VOTE, voteClose.id);
        intent.putExtra(IntentConstants.TITLE, voteClose.name);
        this.startActivity(intent);
    }

    //Fonction permettant d'afficher les listes des votes terminées récupérés par le serveur (Nom du vote et description du vote)
    private class VotesAdapter extends ArrayAdapter<VoteClose>
    {
        public VotesAdapter(Context context, ArrayList<VoteClose> votesClose)
        {
            super(context, R.layout.votes_list_entry, votesClose);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this.getContext()).inflate(R.layout.votes_list_entry, null);

            ((TextView) viewGroup.findViewById(R.id.name)).setText(this.getItem(position).name);
            ((TextView) viewGroup.findViewById(R.id.description)).setText(this.getItem(position).description);

            return viewGroup;
        }
    }
}
