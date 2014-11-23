package pl.edu.agh.ietanks.sandbox.simple;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HttpBotService implements BotService {

    private final String apiAddress;
    private final GenericUrl getBotsUrl;
    private final HttpRequestFactory httpRequestFactory;
    private final ObjectMapper objectMapper;

    public HttpBotService(String apiAddress,
                          HttpRequestFactory httpRequestFactory,
                          ObjectMapper objectMapper) {
        this.apiAddress = apiAddress;
        this.httpRequestFactory = httpRequestFactory;
        this.objectMapper = objectMapper;
        this.getBotsUrl = new GenericUrl(apiAddress + "/rest/bots");
    }

    private GenericUrl getBotUrl(String id) {
        return new GenericUrl(apiAddress + "/rest/bots/" + id);
    }

    private <T> T handleResponse(HttpResponse response, Class<T> clazz) throws IOException {
        String body = response.parseAsString();
        T result = objectMapper.readValue(body, clazz);

        return result;
    }

    @Override
    public List<BotId> listAvailableBots() {
        try {
            HttpRequest request = httpRequestFactory.buildGetRequest(getBotsUrl);
            BotId[] botIds = handleResponse(request.execute(), BotId[].class);

            return Arrays.asList(botIds);
        } catch (IOException e) {
            throw new BotServiceUnavailableException(e);
        }
    }

    @Override
    public StringBotRepresentation fetch(BotId botId) {
        try {
            HttpRequest request = httpRequestFactory.buildGetRequest(getBotUrl(botId.id()));
            String botCode = request.execute().parseAsString();
            StringBotRepresentation result = new StringBotRepresentation(botId, botCode);

            return result;
        } catch (IOException e) {
            throw new BotServiceUnavailableException(e);
        }
    }

    @Override
    public List<StringBotRepresentation> fetch(List<BotId> botIds) {
        return botIds.stream().map(id -> fetch(id)).collect(Collectors.toList());
    }
}
