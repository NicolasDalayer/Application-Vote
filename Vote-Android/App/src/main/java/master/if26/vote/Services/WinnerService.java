package master.if26.vote.Services;

import android.os.AsyncTask;

import master.if26.vote.Constants.WebServiceConstants;
import master.if26.vote.Model.Winner;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * + Projet : IF26 - Application de vote
 * + Date : Automne 2014
 * + Lieu : Université Technologique de Troyes (10000)
 * + Auteur : Nicolas D'ALAYER DE COSTEMORE D'ARC & Alexandre ORTIZ
 * -----------------------------------------------------------------
 * + Type : Service
 * + Name : WinnerService.java
 * + Fichiers liés : Constants.WebServiceConstants - Model.Winner
 * + Description : Service qui sera utilisé pour obtenir le nom
 * du candidat gagnant au vote terminé sélectionné.
 */


public class WinnerService extends AsyncTask<String, Void, ArrayList<Winner>>
{
    @Override
    protected ArrayList<Winner> doInBackground(String... params)
    {
        //Déclaration des paramètres requis pour la requête au serveur
        String token = params[0];
        String vote = params[1];
        String winner = params[2];

        // Déclaration de la Base uri à utiliser
        String uri = WebServiceConstants.WINNERS.URI;

        // Query string
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair(WebServiceConstants.WINNERS.TOKEN, token));
        nameValuePairs.add(new BasicNameValuePair(WebServiceConstants.WINNERS.VOTE, vote));
        nameValuePairs.add(new BasicNameValuePair(WebServiceConstants.WINNERS.WINNER, winner));
        uri += "?" + URLEncodedUtils.format(nameValuePairs, "utf-8");

        HttpGet httpGet = new HttpGet(uri);
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();

        ArrayList<Winner> winners = new ArrayList<Winner>();
        try
        {
            HttpResponse httpResponse = defaultHttpClient.execute(httpGet, new BasicHttpContext());
            String response = EntityUtils.toString(httpResponse.getEntity());

            JSONObject jsonObject = new JSONObject(response);
            if(jsonObject.has(WebServiceConstants.WINNERS.RESULTAT))
            {
                JSONArray jsonArray = jsonObject.getJSONArray(WebServiceConstants.WINNERS.RESULTAT);
                for(int index = 0; index < jsonArray.length(); index++)
                {
                    Winner gagnant = new Winner();
                    gagnant.candidat = jsonArray.getJSONObject(index).getString(WebServiceConstants.WINNERS.CANDIDAT);

                    winners.add(gagnant);
                }
            }
            return winners;
        }
        catch(JSONException jsonException)
        {

        }
        catch(ClientProtocolException clientProtocolException)
        {

        }
        catch(IOException ioException)
        {

        }
        return winners;
    }
}
