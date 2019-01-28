package utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public final class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    //custom class that handles the HttpURLConnection, InputStream and output String which comes from URL

    //return string from inputStream using bufferedReader
    private static String readFromStream(InputStream stream)throws IOException
    {
        StringBuilder outputString= new StringBuilder();
        if(stream != null)
        {
            InputStreamReader sReader = new InputStreamReader(stream, Charset.forName("UTF-8"));
            BufferedReader buffReader = new BufferedReader(sReader);
            String line = buffReader.readLine();
            while(line!=null)
            {
                outputString.append(line);
                line = buffReader.readLine();
            }
        }


        return outputString.toString();
    }

    //output String which can be used to build JSONObject
    //by getting data from URL
    public static String getJSONFromURL(URL inputUrl) throws IOException
    {

        String JsonOutput = "";
        if(inputUrl == null) {return JsonOutput;}
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try
        {
            urlConnection = (HttpURLConnection)inputUrl.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");

            Log.e(LOG_TAG, "urlConnection ready");
            urlConnection.connect();
            Log.e(LOG_TAG, "urlConnection connecting");
            Log.e(LOG_TAG, "urlConnection connectcode: "+urlConnection.getResponseCode());
            if(urlConnection.getResponseCode() == 200)
            {
                inputStream = urlConnection.getInputStream();
                JsonOutput=readFromStream(inputStream);
            }else
            {
                Log.e(LOG_TAG,"internet connection error: "+
                        urlConnection.getResponseCode());
            }

        }catch (IOException e){
            Log.e(LOG_TAG,"error reading inputstream");
        }finally {
            if (urlConnection != null)
            {
                urlConnection.disconnect();
            }
            if(inputStream != null)
            {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }

        return JsonOutput;
    }
}
