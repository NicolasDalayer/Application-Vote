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
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

import master.if26.vote.Constants.*;

/**
 * + Projet : IF26 - Application de vote
 * + Date : Automne 2014
 * + Lieu : Université Technologique de Troyes (10000)
 * + Auteur : Nicolas D'ALAYER DE COSTEMORE D'ARC & Alexandre ORTIZ
 * -----------------------------------------------------------------
 * + Type : Service
 * + Name : LoginService.java
 * + Fichiers liés : Constants.WebServiceConstants
 * + Description : Service qui sera utilisé pour demander au serveur
 * si l'utilisateur peut se connecter. Si c'est le cas, le serveur
 * retournera en réponse le token de l'utilisateur qui lui sera utile pour
 * la suite des actions dans l'application
 */

public class LoginService extends AsyncTask<String, Void, String>
{
    @Override
    protected String doInBackground(String... params)
    {
        //Paramètre requis pour envoyer la demande de connexion au serveur
        String email = params[0];
        String password = params[1];

        // Déclaration de la Base uri à utiliser
        String uri = WebServiceConstants.LOGIN.URI;

        // Query string
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair(WebServiceConstants.LOGIN.EMAIL, email));
        nameValuePairs.add(new BasicNameValuePair(WebServiceConstants.LOGIN.PASSWORD, password));

        uri += "?" + URLEncodedUtils.format(nameValuePairs, "utf-8");

        HttpGet httpGet = new HttpGet(uri);
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        try
        {
            HttpResponse httpResponse = defaultHttpClient.execute(httpGet, new BasicHttpContext());
            String response = EntityUtils.toString(httpResponse.getEntity());

            JSONObject jsonObject = new JSONObject(response);
            return jsonObject.getString(WebServiceConstants.LOGIN.TOKEN);
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

