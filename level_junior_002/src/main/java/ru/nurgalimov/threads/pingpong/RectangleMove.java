package main.java.ru.nurgalimov.threads.pingpong;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        boolean checkForX = false;
        boolean checkForY = false;
        while (true) {
            if (this.rect.getX() == 0) {
                checkForX = false;
            } else if (this.rect.getX() == 300) {
                checkForX = true;
            }
            if (this.rect.getY() == 0) {
                checkForY = false;
            } else if (this.rect.getY() == 300) {
                checkForY = true;
            }
            if (checkForX) {
                this.rect.setX(this.rect.getX() - 1);
            } else {
                this.rect.setX(this.rect.getX() + 1);
            }

            if (checkForY) {
                this.rect.setY(this.rect.getY() - 1);
            } else {
                this.rect.setY(this.rect.getY() + 1);
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
