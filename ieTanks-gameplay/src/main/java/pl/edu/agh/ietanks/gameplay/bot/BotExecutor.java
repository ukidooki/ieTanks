package pl.edu.agh.ietanks.gameplay.bot;

import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import pl.edu.agh.ietanks.engine.api.Action;
import pl.edu.agh.ietanks.engine.api.Board;
import pl.edu.agh.ietanks.engine.api.Bot;
import pl.edu.agh.ietanks.gameplay.bot.api.adapters.BoardAdapter;
import pl.edu.agh.ietanks.gameplay.bot.api.converters.ActionConverter;

public class BotExecutor implements Bot {

    private static final String BOT_CLASS = "MyBot";

    private Integer id;
    private PyObject botClass;
    private ActionConverter actionConverter;

    public BotExecutor(Integer id, String pythonAlgorithm) {
        this.id = id;
        this.actionConverter = new ActionConverter();
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec(pythonAlgorithm);
        this.botClass = interpreter.get(BOT_CLASS);
    }

    @Override
    public Action performAction(Board board) {
        PyObject pythonBot = botClass.__call__(new PyInteger(id));
        pl.edu.agh.ietanks.gameplay.bot.api.Bot javaBot = (pl.edu.agh.ietanks.gameplay.bot.api.Bot) pythonBot.__tojava__(pl.edu.agh.ietanks.gameplay.bot.api.Bot.class);
        pl.edu.agh.ietanks.gameplay.bot.api.Action action = javaBot.performAction(new BoardAdapter(board));
        return actionConverter.convertToEngineAction(action);
    }

}
