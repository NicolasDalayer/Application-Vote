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
import master.if26.vote.Model.VoteClose;

/**
 * + Projet : IF26 - Application de vote
 * + Date : Automne 2014
 * + Lieu : Université Technologique de Troyes (10000)
 * + Auteur : Nicolas D'ALAYER DE COSTEMORE D'ARC & Alexandre ORTIZ
 * -----------------------------------------------------------------
 * + Type : Service
 * + Name : VoteClose.java
 * + Fichiers liés : Constants.WebServiceConstants - Model.VoteClose
 * + Description : Service qui sera utilisé pour obtenu la liste des
 * votes qui sont terminés.
 */

public class VoteCloseService extends AsyncTask<String, Void, ArrayList<VoteClose>>
{
    @Override
    protected ArrayList<VoteClose> doInBackground(String... params)
    {
        // Déclaration des paramètres requis pour l'envoi de la requête au serveur.
        String token = params[0];

        // Déclaration de la Base uri a utiliser
        String uri = WebServiceConstants.VOTESCLOSE.URI;

        // Query string
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair(WebServiceConstants.VOTESCLOSE.TOKEN, token));

        uri += "?" + URLEncodedUtils.format(nameValuePairs, "utf-8");

        HttpGet httpGet = new HttpGet(uri);
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();

        ArrayList<VoteClose> votesClose = new ArrayList<VoteClose>();
        try
        {
            HttpResponse httpResponse = defaultHttpClient.execute(httpGet, new BasicHttpContext());
            String response = EntityUtils.toString(httpResponse.getEntity());

            JSONObject jsonObject = new JSONObject(response);
            if(jsonObject.has(WebServiceConstants.VOTESCLOSE.VOTES))
            {
                JSONArray jsonArray = jsonObject.getJSONArray(WebServiceConstants.VOTESCLOSE.VOTES);
                for(int index = 0; index < jsonArray.length(); index++)
                {
                    VoteClose voteClose = new VoteClose();
                    voteClose.id = jsonArray.getJSONObject(index).getInt(WebServiceConstants.VOTESCLOSE.ID);
                    voteClose.name = jsonArray.getJSONObject(index).getString(WebServiceConstants.VOTESCLOSE.NAME);
                    voteClose.description = jsonArray.getJSONObject(index).getString(WebServiceConstants.VOTESCLOSE.DESC);
                    voteClose.date = jsonArray.getJSONObject(index).getString(WebServiceConstants.VOTESCLOSE.DEADLINE);

                    votesClose.add(voteClose);
                }
            }
        }
        catch(Exception exception)
        {
            Log.e("VoteCloseService", exception.getMessage(), exception);
        }
        /*
        catch(JSONException jsonException)
        {

        }
        catch(ClientProtocolException clientProtocolException)
        {

        }
        catch(IOException ioException)
        {

        }
        */
        return votesClose;
    }
}

