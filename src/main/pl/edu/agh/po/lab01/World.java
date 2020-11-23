package pl.edu.agh.po.lab01;

import pl.edu.agh.cs.lab01.Direction;

public class World {

    static void run(Direction[] args) {

        for(Direction arg : args) {

            switch (arg) {
                case f -> System.out.println("Zwierzak idzie do przodu");
                case b -> System.out.println("Zwierzak idzie do tyłu");
                case r -> System.out.println("Zwierzak idzie w prawo");
                case l -> System.out.println("Zwierzak idzie w lewo");
            }
        }
    }


    static Direction[] zamiana(String[] args) {

        Direction[] argss = new Direction[args.length];
        int i = 0;

        for(String arg : args) {
            switch (arg) {
                case "f" -> argss[i] = Direction.f;
                case "b" -> argss[i] = Direction.b;
                case "r" -> argss[i] = Direction.r;
                case "l" -> argss[i] = Direction.l;
            }

            i += 1;
        }

        return argss;
    }


    public static void main(String[] args) {
        System.out.println("Start");

        Direction[] argss = zamiana(args);

        run(argss);

        System.out.println("Stop");

    }
}