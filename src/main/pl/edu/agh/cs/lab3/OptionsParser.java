package pl.edu.agh.cs.lab3;

import pl.edu.agh.cs.lab2.*;

import java.util.LinkedList;
import java.util.List;

public class OptionsParser {

    public List<MoveDirection> parse(String[] kierunki) {
        List<MoveDirection> lista = new LinkedList<>();

        for(String kierunek: kierunki) {
            switch (kierunek) {
                case "r" -> lista.add(MoveDirection.RIGHT);
                case "l" -> lista.add(MoveDirection.LEFT);
                case "f" -> lista.add(MoveDirection.FORWARD);
                case "b" -> lista.add(MoveDirection.BACKWARD);
                default -> throw new IllegalArgumentException(kierunek + " is not legal move specification");
            }
        }

        return lista;
    }
}
