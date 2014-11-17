package pl.edu.agh.ietanks.gameplay.bot;

import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import pl.edu.agh.ietanks.engine.api.Action;
import pl.edu.agh.ietanks.engine.api.Board;
import pl.edu.agh.ietanks.engine.api.Bot;

public class BotExecutor implements Bot {

    private static final String BOT_CLASS = "MyBot";

    private PyObject botClass;

    public BotExecutor(String pythonAlgorithm) {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec(pythonAlgorithm);
        botClass = interpreter.get(BOT_CLASS);
    }

    @Override
    public Action performAction(Board board) {
        PyObject pythonBot = botClass.__call__();
        Bot bot = (Bot)pythonBot.__tojava__(Bot.class);

        return bot.performAction(board);
    }
}
