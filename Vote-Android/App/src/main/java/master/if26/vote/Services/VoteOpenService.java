package master.if26.vote.Services;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import master.if26.vote.Constants.*;
import master.if26.vote.Model.Vote;

/**
 * + Projet : IF26 - Application de vote
 * + Date : Automne 2014
 * + Lieu : Université Technologique de Troyes (10000)
 * + Auteur : Nicolas D'ALAYER DE COSTEMORE D'ARC & Alexandre ORTIZ
 * -----------------------------------------------------------------
 * + Type : Service
 * + Name : VoteOpen.java
 * + Fichiers liés : Constants.WebServiceConstants - Model.Vote
 * + Description : Service qui sera utilisé pour obtenir la liste
 * des votes qui sont en cours auprè du serveur.
 */

public class VoteOpenService extends AsyncTask<String, Void, ArrayList<Vote>>
{
    @Override
    protected ArrayList<Vote> doInBackground(String... params)
    {
        // Déclaration des paramètres requis pour envoyer la requête au serveur
        String token = params[0];

        // Déclaration de la Base uri à utiliser
        String uri = WebServiceConstants.VOTE.URI;

        // Query string
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair(WebServiceConstants.VOTE.TOKEN, token));

        uri += "?" + URLEncodedUtils.format(nameValuePairs, "utf-8");

        HttpGet httpGet = new HttpGet(uri);
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();

        ArrayList<Vote> votes = new ArrayList<Vote>();
        try
        {
            HttpResponse httpResponse = defaultHttpClient.execute(httpGet, new BasicHttpContext());
            String response = EntityUtils.toString(httpResponse.getEntity());

            JSONObject jsonObject = new JSONObject(response);
            if(jsonObject.has(WebServiceConstants.VOTE.VOTES))
            {
                JSONArray jsonArray = jsonObject.getJSONArray(WebServiceConstants.VOTE.VOTES);
                for(int index = 0; index < jsonArray.length(); index++)
                {
                    Vote vote = new Vote();
                    vote.id = jsonArray.getJSONObject(index).getInt(WebServiceConstants.VOTE.ID);
                    vote.name = jsonArray.getJSONObject(index).getString(WebServiceConstants.VOTE.NAME);
                    vote.description = jsonArray.getJSONObject(index).getString(WebServiceConstants.VOTE.DESC);
                    vote.date = jsonArray.getJSONObject(index).getString(WebServiceConstants.VOTE.DEADLINE);

                    votes.add(vote);
                }
            }
        }
        catch(Exception exception)
        {

        }

        return votes;
    }
}
