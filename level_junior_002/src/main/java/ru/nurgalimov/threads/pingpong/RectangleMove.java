package main.java.ru.nurgalimov.threads.pingpong;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        boolean checkFor = false;

        while (true) {
            if (this.rect.getX() == 0 || this.rect.getY() == 0) {
                checkFor = false;
            } else if (this.rect.getX() == 300 || this.rect.getY() == 300) {
                checkFor = true;
            }
            if (checkFor) {
                this.rect.setX(this.rect.getX() - 1);
                this.rect.setY(this.rect.getY() - 1);
            } else {
                this.rect.setX(this.rect.getX() + 1);
                this.rect.setY(this.rect.getY() + 1);
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
