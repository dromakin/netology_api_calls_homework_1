package org.dromakin;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    private static final String URI = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";

    public static void main(String[] args) {

        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build()) {

            HttpGet request = new HttpGet(URI);

            logger.info("Make request to url {}", URI);
            CloseableHttpResponse response = httpClient.execute(request);

            int status = response.getStatusLine().getStatusCode();

            logger.info("Status code: {}", status);

            if (status != 200) {
                throw new RequestException(String.format("Status code: %s", status));
            }

            String jsonString;

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                jsonString = EntityUtils.toString(entity);
                logger.debug(jsonString);

            } else {
                throw new RequestException("json response is null!");
            }

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            TypeFactory typeFactory = objectMapper.getTypeFactory();
            List<CatFact> catsFastsList = objectMapper.readValue(jsonString, typeFactory.constructCollectionType(List.class, CatFact.class));

            catsFastsList.stream().filter(value -> value.getUpvotes() != null && value.getUpvotes() > 0).forEach(logger::info);

        } catch (IOException | RequestException e) {
            logger.error(e.getMessage(), e);
        }

    }
}