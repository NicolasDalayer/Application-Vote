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

import master.if26.vote.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import master.if26.vote.Constants.*;
import master.if26.vote.Services.VoteOpenService;
import master.if26.vote.Model.Vote;
import master.if26.vote.Activity.CandidatsActivity;

/**
 * + Projet : IF26 - Application de vote
 * + Date : Automne 2014
 * + Lieu : Université Technologique de Troyes (10000)
 * + Auteur : Nicolas D'ALAYER DE COSTEMORE D'ARC & Alexandre ORTIZ
 * -----------------------------------------------------------------
 * + Type : Fragment
 * + Name : VoteOpenFragment.java
 * + Fichiers liés : layout.votesclose_list_entry.xml - Services.VoteOpenService
 * Constants.IntentConstants.java - Activity.CandidatsActivity - Model.Vote
 * + Description : Fragment utilisé lorsqu'un utilisateur a cliqué sur l'onglet
 * "Vote terminés" pour afficher la liste des votes terminés.
 */
public class VoteOpenFragment extends ListFragment
{

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        try
        {
            //Pour obtenir cette liste, il faut envoyer le token de l'utilisateur au serveur
            String token = this.getActivity().getIntent().getStringExtra(IntentConstants.TOKEN);

            VoteOpenService votesService = new VoteOpenService();
            ArrayList<Vote> votes = votesService.execute(token).get();

            this.setListAdapter(new VotesAdapter(this.getActivity(), votes));
        }
        catch(InterruptedException interruptedException)
        {

        }
        catch(ExecutionException executionException)
        {

        }
    }
    //Une fois la liste obtenue, si l'utilisateur clique sur un nom de vote, alors il faut afficher les candidats participants au vote
    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        String token = this.getActivity().getIntent().getStringExtra(IntentConstants.TOKEN);
        Vote vote = (Vote) this.getListAdapter().getItem(position);

        Intent intent = new Intent(this.getActivity(), CandidatsActivity.class);
        intent.putExtra(IntentConstants.TOKEN, token);
        intent.putExtra(IntentConstants.VOTE, vote.id);
        intent.putExtra(IntentConstants.TITLE, vote.name);
        this.startActivity(intent);
    }
    //Fonction permettant d'afficher les listes des votes en cours récupérés par le serveur (Nom du vote, description du vote, et sa date de fin)
    private class VotesAdapter extends ArrayAdapter<Vote>
    {
        public VotesAdapter(Context context, ArrayList<Vote> votes)
        {
            super(context, R.layout.votes_list_entry, votes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this.getContext()).inflate(R.layout.votes_list_entry, null);

            ((TextView) viewGroup.findViewById(R.id.name)).setText(this.getItem(position).name);
            ((TextView) viewGroup.findViewById(R.id.description)).setText(this.getItem(position).description);
            ((TextView) viewGroup.findViewById(R.id.date)).setText(this.getItem(position).date);

            return viewGroup;
        }
    }
}

