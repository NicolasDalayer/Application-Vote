package master.if26.vote.Services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import android.os.AsyncTask;

import master.if26.vote.Constants.WebServiceConstants;
import master.if26.vote.Model.Candidat;

/**
 * + Projet : IF26 - Application de vote
 * + Date : Automne 2014
 * + Lieu : Université Technologique de Troyes (10000)
 * + Auteur : Nicolas D'ALAYER DE COSTEMORE D'ARC & Alexandre ORTIZ
 * -----------------------------------------------------------------
 * + Type : Service
 * + Name : CandidatService.java
 * + Fichiers liés : Constants.WebServiceConstants - Model.Candidat
 * + Description : Service qui sera utilisé quand l'utilisateur va vouloir
 * voté pour un candidat du vote précédemment sélectionné
 */

public class CandidatService extends AsyncTask<String, Void, ArrayList<Candidat>>
{
    @Override
    protected ArrayList<Candidat> doInBackground(String... params)
    {
        //Paramètres requis pour envoyer le vote au serveur
        String token = params[0];
        String vote = params[1];
        String candidat = params[2];

        // Déclaration de la Base uri à utiliser
        String uri = WebServiceConstants.CANDIDAT.URI;

        // Query string
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair(WebServiceConstants.CANDIDAT.TOKEN, token));
        nameValuePairs.add(new BasicNameValuePair(WebServiceConstants.CANDIDAT.VOTE, vote));
        nameValuePairs.add(new BasicNameValuePair(WebServiceConstants.CANDIDAT.CANDIDAT , candidat));

        uri += "?" + URLEncodedUtils.format(nameValuePairs, "utf-8");

        HttpGet httpGet = new HttpGet(uri);
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();

        ArrayList<Candidat> candidats = new ArrayList<Candidat>();
        try
        {
            HttpResponse httpResponse = defaultHttpClient.execute(httpGet, new BasicHttpContext());
            String response = EntityUtils.toString(httpResponse.getEntity());

            JSONObject jsonObject = new JSONObject(response);
            if(jsonObject.has(WebServiceConstants.CANDIDAT.VOTE))
            {

                JSONArray jsonArray = jsonObject.getJSONArray(WebServiceConstants.CANDIDAT.VOTE);
                for(int index = 0; index < jsonArray.length(); index++)
                {

                    Candidat candidatObject = new Candidat();
                    candidatObject.idvote = jsonArray.getJSONObject(index).getString(WebServiceConstants.CANDIDAT.IDVOTE);
                    candidatObject.idcandidat = jsonArray.getJSONObject(index).getString(WebServiceConstants.CANDIDAT.IDCANDIDAT);
                    candidats.add(candidatObject);

                    return candidats;

                }

            }



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
        return null;
    }
}
