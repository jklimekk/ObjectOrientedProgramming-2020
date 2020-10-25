package pl.edu.agh.cs.lab3;

import pl.edu.agh.cs.lab2.*;

import java.util.List;

public class World {
    public static void main(String[] args) {
        Animal zwierze = new Animal();
        System.out.println(zwierze);

        OptionsParser parser = new OptionsParser();
        List<MoveDirection> ruchy = parser.parse(args);
        System.out.println(ruchy);

        for(MoveDirection ruch: ruchy) {
            zwierze.move(ruch);
        }

        System.out.println(zwierze);

    }
}
