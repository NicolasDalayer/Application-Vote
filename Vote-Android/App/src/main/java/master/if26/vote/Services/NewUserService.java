package master.if26.vote.Services;

import android.os.AsyncTask;

import master.if26.vote.Constants.WebServiceConstants;

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
 * + Name : NewUser.java
 * + Fichiers liés : Constants.WebServiceConstants
 * + Description : Service qui sera utilisé pour envoyer les informations
 * pour une création de compte. Si l'utilisateur n'existe pas, alors
 * le serveur retournera le token de l'utilisateur pour valider son inscription
 */

public class NewUserService extends AsyncTask<String, Void, String>
{
    @Override
    protected String doInBackground(String... params)
    {
        //Déclaration des paramètres requis pour envoyer la requête au serveur
        String email = params[0];
        String password = params[1];

        // Déclaration de la Base uri à utiliser
        String uri = WebServiceConstants.CREATE.URI;

        // Query string
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair(WebServiceConstants.CREATE.EMAIL, email));
        nameValuePairs.add(new BasicNameValuePair(WebServiceConstants.CREATE.PASSWORD, password));

        uri += "?" + URLEncodedUtils.format(nameValuePairs, "utf-8");

        HttpGet httpGet = new HttpGet(uri);
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        try
        {
            HttpResponse httpResponse = defaultHttpClient.execute(httpGet, new BasicHttpContext());
            String response = EntityUtils.toString(httpResponse.getEntity());

            JSONObject jsonObject = new JSONObject(response);
            return jsonObject.getString(WebServiceConstants.CREATE.TOKEN);
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
