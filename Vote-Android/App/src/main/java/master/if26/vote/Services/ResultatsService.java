package master.if26.vote.Services;

import android.os.AsyncTask;

import master.if26.vote.Constants.WebServiceConstants;
import master.if26.vote.Model.Resultat;

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
 * + Name : ResultatsService.java
 * + Fichiers liés : Constants.WebServiceConstants - Model.Résultat
 * + Description : Service qui sera utilisé pour récupérer les résultats du
 * vote que l'utilisateur à sélectionné.
 */

public class ResultatsService extends AsyncTask<String, Void, ArrayList<Resultat>>
{
    @Override
    protected ArrayList<Resultat> doInBackground(String... params)
    {
        // Déclaration des paramètres requis pour la requête vers le serveur.
        String token = params[0];
        String vote = params[1];

        // Déclaration de la Base uri à utiliser
        String uri = WebServiceConstants.RESULTATS.URI;

        // Query string
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair(WebServiceConstants.RESULTATS.TOKEN, token));
        nameValuePairs.add(new BasicNameValuePair(WebServiceConstants.RESULTATS.VOTE, vote));

        uri += "?" + URLEncodedUtils.format(nameValuePairs, "utf-8");

        HttpGet httpGet = new HttpGet(uri);
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();

        ArrayList<Resultat> resultats = new ArrayList<Resultat>();
        try
        {
            HttpResponse httpResponse = defaultHttpClient.execute(httpGet, new BasicHttpContext());
            String response = EntityUtils.toString(httpResponse.getEntity());

            JSONObject jsonObject = new JSONObject(response);
            if(jsonObject.has(WebServiceConstants.RESULTATS.RESULTAT))
            {
                JSONArray jsonArray = jsonObject.getJSONArray(WebServiceConstants.RESULTATS.RESULTAT);
                for(int index = 0; index < jsonArray.length(); index++)
                {
                    Resultat resultat = new Resultat();
                    resultat.candidat = jsonArray.getJSONObject(index).getString(WebServiceConstants.RESULTATS.CANDIDAT);
                    resultat.nbvoies = jsonArray.getJSONObject(index).getString(WebServiceConstants.RESULTATS.NBVOIES);

                    resultats.add(resultat);
                }
            }
            return resultats;
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
        return resultats;
    }
}
