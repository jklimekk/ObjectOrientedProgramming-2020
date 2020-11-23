package pl.edu.agh.cs.lab7;

public interface IPositionChangedPublisher {

    void addObserver(IPositionChangeObserver observer);

    void removeObserver(IPositionChangeObserver observer);
}
