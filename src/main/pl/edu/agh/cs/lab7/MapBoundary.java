package pl.edu.agh.cs.lab7;

import pl.edu.agh.cs.lab2.Vector2d;
import pl.edu.agh.cs.lab5.IMapElement;

import java.util.ArrayList;
import java.util.List;

public class MapBoundary implements IPositionChangeObserver {
    private final List<IMapElement> Xordered = new ArrayList<>();
    private final List<IMapElement> Yordered = new ArrayList<>();

    public Vector2d loverLeftCorner(){
        return new Vector2d(Xordered.get(0).getPosition().x, Yordered.get(0).getPosition().y);
    }

    public Vector2d upperRightCorner(){
        return new Vector2d(Xordered.get(Xordered.size() - 1).getPosition().x, Yordered.get(Yordered.size() - 1).getPosition().y);
    }

    @Override
    public void positionChanged(IMapElement movedElement, Vector2d oldPosition, Vector2d newPosition) {
        if(oldPosition.x != newPosition.x) {
            // zmieniamy tylko Xordered

            if(newPosition.x > Xordered.get(Xordered.size() - 1).getPosition().x) {
                // rozszerzony "górny" koniec listy - przesuwam element na ten koniec
                Xordered.remove(movedElement);
                Xordered.add(movedElement);

            } else if(newPosition.x < Xordered.get(0).getPosition().x) {
                // rozszerzony drugi koniec listy - przesuwam element na początek
                Xordered.remove(movedElement);
                Xordered.add(0,movedElement);

            } else if(oldPosition.x > newPosition.x && Xordered.get(Xordered.size() - 1).equals(movedElement)) {
                // zmniejszony "górny" koniec listy
                Xordered.remove(movedElement);
                int i = Xordered.size()-1;
                while(Xordered.get(i).getPosition().x > newPosition.x)
                    i--;
                Xordered.add(i+1, movedElement);

            } else if(oldPosition.x < newPosition.x && Xordered.get(Xordered.size() - 1).equals(movedElement)) {
                // zmniejszony drugi koniec listy
                Xordered.remove(movedElement);
                int i = 0;
                while(Xordered.get(i).getPosition().x < newPosition.x)
                    i++;
                Xordered.add(i, movedElement);
            }

        } else {
            // zmieniamy tylko Yordered

            if(newPosition.y > Yordered.get(Xordered.size() - 1).getPosition().y) {
                // rozszerzony "górny" koniec listy - przesuwam element na ten koniec
                Yordered.remove(movedElement);
                Yordered.add(movedElement);

            } else if(newPosition.y < Yordered.get(0).getPosition().y) {
                // rozszerzony drugi koniec listy - przesuwam element na początek
                Yordered.remove(movedElement);
                Yordered.add(0,movedElement);

            } else if(oldPosition.y > newPosition.y && Yordered.get(Xordered.size() - 1).equals(movedElement)) {
                // zmniejszony "górny" koniec listy
                Yordered.remove(movedElement);
                int i = Yordered.size()-1;
                while(Yordered.get(i).getPosition().y > newPosition.y)
                    i--;
                Yordered.add(i+1, movedElement);

            } else if(oldPosition.y < newPosition.y && Yordered.get(Yordered.size() - 1).equals(movedElement)) {
                // zmniejszony drugi koniec listy
                Yordered.remove(movedElement);
                int i = 0;
                while(Yordered.get(i).getPosition().y < newPosition.y)
                    i++;
                Yordered.add(i, movedElement);
            }
        }

    }

    public void addElement(IMapElement toAdd) {
        if(Xordered.size() == 0) {
            Xordered.add(toAdd);
            Yordered.add(toAdd);
            return;
        }

        // wstawianie do Xordered
        for(IMapElement onList : Xordered) {

            /*
            onList x < toAdd x -> sprawdź kolejny el na liście
            onList x > toAdd x -> wstaw
            onList x = toAdd x -> porównaj y
                onList y < toAdd y -> sprawdź kolejny el na liście
                onList y > toAdd y -> wstaw
                onList y < toAdd y -> porównaj typ
                    onList grass & toAdd grass -> niemożliwe - najpierw robię listę traw bez powtórzeń i dopiero tutaj uzupełniam
                    onList zwierze & toAdd zwierze -> niemożliwe - najpierw sprawdzam canMoveTo() i dopiero uzupełniam
                    onList zwierze & toAdd grass -> niemożliwe - trawy generujemy na smaym początku, potem wstawiamy zwierzęta
                    onList grass & toAdd zwierze -> wstaw
             */

            if(onList.getPosition().x > toAdd.getPosition().x) {
                Xordered.add(Xordered.indexOf(onList), toAdd);
                break;
            } else if(onList.getPosition().x == toAdd.getPosition().x && onList.getPosition().y > toAdd.getPosition().y) {
                Xordered.add(Xordered.indexOf(onList), toAdd);
                break;
            } else if(onList.getPosition().x == toAdd.getPosition().x && onList.getPosition().y == toAdd.getPosition().y) {
                Xordered.add(Xordered.indexOf(onList) + 1, toAdd);
                break;
            } else if(Xordered.indexOf(onList) == Xordered.size() - 1) {
                //onList x - ostatni na liście
                Xordered.add(toAdd);
                break;
            }
        }

        // wstawianie do Yordered
        for(IMapElement onList : Yordered) {

            // analogiczne rozumowanie

            if(onList.getPosition().y > toAdd.getPosition().y) {
                Yordered.add(Yordered.indexOf(onList), toAdd);
                break;
            } else if(onList.getPosition().y == toAdd.getPosition().y && onList.getPosition().x > toAdd.getPosition().x) {
                Yordered.add(Yordered.indexOf(onList), toAdd);
                break;
            } else if(onList.getPosition().y == toAdd.getPosition().y && onList.getPosition().x == toAdd.getPosition().x) {
                Yordered.add(Yordered.indexOf(onList) + 1, toAdd);
                break;
            } else if(Yordered.indexOf(onList) == Yordered.size() - 1) {
                //onList y - ostatni na liście
                Yordered.add(toAdd);
                break;
            }
        }
    }
}