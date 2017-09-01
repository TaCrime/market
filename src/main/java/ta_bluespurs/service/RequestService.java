package ta_bluespurs.service;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ta_bluespurs.domain.Location;
import ta_bluespurs.service.uri_builder.LocationURIBuilderService;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public class RequestService {

    private final Logger logger = LoggerFactory.getLogger(RequestService.class);

    private LocationURIBuilderService uriBuilderService;

    @Autowired
    public RequestService(LocationURIBuilderService uriBuilderService) {
        this.uriBuilderService = uriBuilderService;
    }

    @Nullable
    public JSONObject getRequestResponse(Location location, String name) {
        JSONObject jsonObject;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            URI uri = uriBuilderService.getURIBuilder(location).build(location, name);
            HttpGet httpget = new HttpGet(uri);

            ResponseHandler<String> responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            jsonObject = new JSONObject(responseBody);
        } catch (IOException | URISyntaxException e) {
            logger.warn("Error while response handling " + e.getMessage());
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