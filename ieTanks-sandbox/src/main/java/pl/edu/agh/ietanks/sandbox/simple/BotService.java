package pl.edu.agh.ietanks.sandbox.simple;

import java.util.List;

public interface BotService {
    public List<String> listAvailableBots();

    public StringBotRepresentation getById(String id);
}