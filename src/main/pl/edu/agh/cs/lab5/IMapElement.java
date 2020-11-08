package pl.edu.agh.cs.lab5;

import pl.edu.agh.cs.lab2.Vector2d;

public interface IMapElement {

    Vector2d getPosition();

    String toString();

    /*

    W GrassField można by trzymać listę obiektów klasy IMapElement.
    Wtedy w isOccupied, objectAt i corners (moja pomocnicza funkcja) nie trzebaby sprawdzać najpierw elementów listy typu
    Animal a potem Grass, tylko jednej listy wszystkiego.
    Ale nie jestem przekonana co do tego rozwiązania. W canMoveTo sprawdzając czy miejsce jest zajmowane
    w dalszym ciągu potrzebujemy rozróżnienia obiektów klasy Animal od tych Grass
    (zwierze na zwierze nie wejdzie ale na trawkę owszem).
    Więc albo trzebaby trzymać osobną listę dla Grass albo sprawdzać za każdym razem typ obiektu.

    A w klasie abstrakcyjnej AbstractWorldMapElement moglibyśmy tak naprawdę zdefiniować tylko metodę getPosition,
    czyli robilibyśmy osobną klasę abstrakcyjną tylko po to żeby oszczędzić linijkę kodu w obu klasach.

     */

}
