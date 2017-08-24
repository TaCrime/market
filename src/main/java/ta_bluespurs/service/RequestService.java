package ta_bluespurs.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import ta_bluespurs.domain.Location;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@Component
public class RequestService {

    public JSONObject getRequestResponse(Location location, String name) {
        JSONObject jsonObject;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            URIBuilder builder = new URIBuilder()
                    .setScheme(location.getScheme())
                    .setHost(location.getHost())
                    .setPath(location.getPath(location, name));
            builder.setParameter(location.getSearchByNameParamName(), name);
            Map<String, String> parameters = location.getParameters();
            for (String key: parameters.keySet()) {
                builder.setParameter(key, parameters.get(key));
            }
            URI uri = builder.build();
            HttpGet httpget = new HttpGet(uri);

            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            jsonObject = new JSONObject(responseBody);
        } catch (IOException | URISyntaxException e) {
            //todo log
            return null;
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }
}