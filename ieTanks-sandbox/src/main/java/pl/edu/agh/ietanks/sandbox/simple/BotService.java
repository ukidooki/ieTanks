package pl.edu.agh.ietanks.sandbox.simple;

import java.util.List;

public interface BotService {
    public List<BotId> listAvailableBots();

    public StringBotRepresentation fetch(BotId botId);

    public List<StringBotRepresentation> fetch(List<BotId> botIds);
}