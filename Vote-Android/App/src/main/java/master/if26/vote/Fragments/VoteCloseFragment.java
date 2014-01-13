package master.if26.vote.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import master.if26.vote.Activity.CandidatsActivity;
import master.if26.vote.Activity.ResultatsActivity;
import master.if26.vote.Model.VoteClose;
import master.if26.vote.Model.Vote;
import master.if26.vote.R;
import master.if26.vote.Services.VoteCloseService;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import master.if26.vote.Constants.*;

/**
 * Created by Nico on 18/12/13.
 */
public class VoteCloseFragment extends ListFragment
{

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        try
        {
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
            ((TextView) viewGroup.findViewById(R.id.date)).setText(this.getItem(position).date);

            return viewGroup;
        }
    }
}
