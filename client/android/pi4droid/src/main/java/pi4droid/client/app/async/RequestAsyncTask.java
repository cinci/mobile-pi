package pi4droid.client.app.async;

import android.os.AsyncTask;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import pi4droid.client.app.AsyncConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * REST async request
 * <p/>
 * Created by jc on 4. 7. 2014.
 */
public class RequestAsyncTask extends AsyncTask<Void, Void, Map<String, String>> {

    private static final int REQUEST_TIMEOUT = 5 * 1000;
    protected AsyncCallback asyncCallback;
    protected Map<String, String> result = new HashMap<String, String>();

    private String url;

    public RequestAsyncTask(AsyncCallback asyncCallback) {
        this.asyncCallback = asyncCallback;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    protected Map<String, String> doInBackground(Void... params) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(REQUEST_TIMEOUT);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        try {
            result.put(AsyncConstants.RESPONSE, restTemplate.getForObject(url, String.class));
        } catch (ResourceAccessException e) {
            result.put(AsyncConstants.RESPONSE, null);
        }

        return result;
    }

    @Override
    protected void onPostExecute(Map<String, String> result) {
        asyncCallback.setAsyncResult(result);
    }
}
