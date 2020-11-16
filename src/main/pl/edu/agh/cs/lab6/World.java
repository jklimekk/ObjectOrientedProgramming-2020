package pl.edu.agh.cs.lab6;

import pl.edu.agh.cs.lab2.MoveDirection;
import pl.edu.agh.cs.lab2.Vector2d;
import pl.edu.agh.cs.lab3.Animal;
import pl.edu.agh.cs.lab3.OptionsParser;
import pl.edu.agh.cs.lab4.IWorldMap;
import pl.edu.agh.cs.lab5.GrassField;

import java.util.List;

public class World {
    public static void main(String[] args) {
        try {
            List<MoveDirection> directions = new OptionsParser().parse(args);
            IWorldMap grassField = new GrassField(10);
            grassField.place(new Animal(grassField, new Vector2d(2,3)));
            grassField.place(new Animal(grassField, new Vector2d(2,3)));
            grassField.run(directions);
        } catch(IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
