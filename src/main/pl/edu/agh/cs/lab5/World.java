package pl.edu.agh.cs.lab5;

import pl.edu.agh.cs.lab2.MoveDirection;
import pl.edu.agh.cs.lab2.Vector2d;
import pl.edu.agh.cs.lab3.OptionsParser;

import java.util.List;

public class World {
    public static void main(String[] args) {
        List<MoveDirection> directions = new OptionsParser().parse(args);
        IWorldMap grassField = new GrassField(10);
        grassField.place(new Animal(grassField));
        grassField.place(new Animal(grassField, new Vector2d(11,12)));
        System.out.println(grassField);
        grassField.run(directions);
        System.out.println(grassField);
    }
}
