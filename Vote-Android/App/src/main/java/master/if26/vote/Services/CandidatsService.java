package master.if26.vote.Services;

import android.os.AsyncTask;

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

import master.if26.vote.Constants.*;
import master.if26.vote.Model.Candidat;

/**
 * + Projet : IF26 - Application de vote
 * + Date : Automne 2014
 * + Lieu : Université Technologique de Troyes (10000)
 * + Auteur : Nicolas D'ALAYER DE COSTEMORE D'ARC & Alexandre ORTIZ
 * -----------------------------------------------------------------
 * + Type : Service
 * + Name : Candidats.java
 * + Fichiers liés : Constants.WebServiceConstants - Model.Candidat
 * + Description : Service qui sera utilisé pour demander et récupérer la liste
 * des candidats participant au vote en cours précédement sélectionné
 */

public class CandidatsService extends AsyncTask<String, Void, ArrayList<Candidat>>
{
    @Override
    protected ArrayList<Candidat> doInBackground(String... params)
    {
        //Paramètres requis pour envoyer la demande au serveur.
        String token = params[0];
        String vote = params[1];

        // Déclaration de la Base uri à utiliser
        String uri = WebServiceConstants.CANDIDATS.URI;

        // Query string
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair(WebServiceConstants.CANDIDATS.TOKEN, token));
        nameValuePairs.add(new BasicNameValuePair(WebServiceConstants.CANDIDATS.VOTE, vote));

        uri += "?" + URLEncodedUtils.format(nameValuePairs, "utf-8");

        HttpGet httpGet = new HttpGet(uri);
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();

        ArrayList<Candidat> candidats = new ArrayList<Candidat>();
        try
        {
            HttpResponse httpResponse = defaultHttpClient.execute(httpGet, new BasicHttpContext());
            String response = EntityUtils.toString(httpResponse.getEntity());

            JSONObject jsonObject = new JSONObject(response);
            if(jsonObject.has(WebServiceConstants.CANDIDATS.CANDIDATS))
            {
                JSONArray jsonArray = jsonObject.getJSONArray(WebServiceConstants.CANDIDATS.CANDIDATS);
                for(int index = 0; index < jsonArray.length(); index++)
                {
                    Candidat candidat = new Candidat();
                    candidat.id = jsonArray.getJSONObject(index).getString(WebServiceConstants.CANDIDATS.ID);
                    candidat.candidat = jsonArray.getJSONObject(index).getString(WebServiceConstants.CANDIDATS.CANDIDAT);

                    candidats.add(candidat);
                }
            }
            return candidats;
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
        return candidats;
    }
}
