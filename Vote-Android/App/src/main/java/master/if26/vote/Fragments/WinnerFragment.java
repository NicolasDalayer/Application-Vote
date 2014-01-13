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

import master.if26.vote.Constants.IntentConstants;
import master.if26.vote.Model.Winner;
import master.if26.vote.R;
import master.if26.vote.Services.WinnerService;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * + Projet : IF26 - Application de vote
 * + Date : Automne 2014
 * + Lieu : Université Technologique de Troyes (10000)
 * + Auteur : Nicolas D'ALAYER DE COSTEMORE D'ARC & Alexandre ORTIZ
 * -----------------------------------------------------------------
 * + Type : Fragment
 * + Name : WinnerFragment.java
 * + Fichiers liés : layout.winner_list_entry.xml - Constants.IntentConstants.java
 * Services.WinnerService.java - Model.Winner
 * + Description : Fragment utilisé lorsqu'un vote terminé est sélectionné. Il va
 * permettre d'afficher en haut de la page le nom du candidat qui est le gagnant du vote.
 */
public class WinnerFragment extends ListFragment
{
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        try
        {

            String token = this.getActivity().getIntent().getStringExtra(IntentConstants.TOKEN);
            int vote = this.getActivity().getIntent().getIntExtra(IntentConstants.VOTE, -1);
            int winner = this.getActivity().getIntent().getIntExtra(IntentConstants.WINNER, 1);

            WinnerService winnersService = new WinnerService();
            ArrayList<Winner> winners = winnersService.execute(token, String.valueOf(vote), String.valueOf(winner)).get();

            this.setListAdapter(new WinnersAdapter(this.getActivity(), winners));
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

    //Fonction permettant d'afficher le nom du candidat.
    private class WinnersAdapter extends ArrayAdapter<Winner>
    {
        public WinnersAdapter(Context context, ArrayList<Winner> winners)
        {
            super(context, R.layout.winner_list_entry, winners);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {

            int layout = R.layout.winner_list_entry;

            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this.getContext()).inflate(layout, null);

            ((TextView) viewGroup.findViewById(R.id.candidatwinner)).setText(this.getItem(position).candidat);

            return viewGroup;

        }

    }
}
