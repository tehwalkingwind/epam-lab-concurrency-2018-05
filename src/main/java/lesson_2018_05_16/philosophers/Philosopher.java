package lesson_2018_05_16.philosophers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Philosopher extends Thread {

    private int sectionIndex;

    public Philosopher(String name) {
        super(name);
    }

    public void inviteTo(Table table) {
        table.sit(this);
        start();
    }
}
