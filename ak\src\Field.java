import processing.core.PApplet;

class Field {
    private final static int COLOR = 0xff22d6b8;
    private int width, height;

    Field(int width, int height) {
        this.width = width;
        this.height = height;
    }

    void draw(PApplet applet) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                float screenX = getScreenX(applet, x);
                float screenY = getScreenY(applet, y);
                float cellSize = getCellSize(applet);

                applet.fill(COLOR);
                applet.rect(screenX, screenY, cellSize, cellSize);
            }
        }
    }


    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    float getScreenX(PApplet applet, int x) {
        float cellSize = getCellSize(applet);
        float fieldScreenWidth = cellSize * width;
        float centerXShift = (applet.width - fieldScreenWidth) / 2;

        return centerXShift + x * cellSize;
    }

    float getCellSize(PApplet applet) {
        return PApplet.min(applet.width / width, applet.height / height);
    }

    float getScreenY(PApplet applet, int y) {
        float cellSize = getCellSize(applet);
        float fieldScreenHeight = cellSize * width;
        float centerXShift = (applet.width - fieldScreenHeight) / 2;

        return centerXShift + y * cellSize;
    }

    boolean isInside(int x, int y) {
        return x >= 0 && x < width &&
                y >= 0 && y < height;
    }
}
