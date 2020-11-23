package pl.edu.agh.cs.lab7;

import pl.edu.agh.cs.lab2.Vector2d;
import pl.edu.agh.cs.lab5.IMapElement;

public interface IPositionChangeObserver {

    void positionChanged(IMapElement movedElement, Vector2d oldPosition, Vector2d newPosition);
}
