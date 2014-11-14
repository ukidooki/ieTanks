package pl.edu.agh.ietanks.engine.testutils;

import pl.edu.agh.ietanks.engine.api.MutableBoard;
import pl.edu.agh.ietanks.engine.api.Position;
import pl.edu.agh.ietanks.engine.simple.SimpleBoard;
import pl.edu.agh.ietanks.engine.api.Board;

import java.util.HashMap;
import java.util.Map;

public class BoardBuilder {

    public static MutableBoard fromASCII(String... asciiRepresentation) {
        int height = asciiRepresentation.length;
        int width = asciiRepresentation[0].length();

        Map tanks = new HashMap();

        for(int i=0; i<height; ++i) {
            for(int j=0; j<width; ++j) {
                char element = asciiRepresentation[i].charAt(j);

                if(element == '.') {
                    // nothing to do
                }
                else if('0' <= element && element <= '9') {
                    int tankNumber = Character.digit(element, 10);
                    tanks.put(tankNumber, new Position(i, j));
                }
                else {
                    throw new IllegalArgumentException();
                }
            }
        }

        return new SimpleBoard(width, height, tanks);
    }
}
