package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.util.Arrays;

public class TileManager {
    GamePanel gp;
    Tile[] tiles;
    public int[][] mapTileNum;
    public int[][] originalMap;
    private String mapFile = "whiteMap.txt";

    String[] imagePaths = {
            "res/Leer/Leer.png",
            "res/Leer/LO.png",
            "res/Rot/LOr.png",
            "res/Blau/LOb.png",
            "res/Leer/LM.png",
            "res/Rot/LMr.png",
            "res/Blau/LMb.png",
            "res/Leer/LU.png",
            "res/Rot/LUr.png",
            "res/Blau/LUb.png",
            "res/Leer/MO.png",
            "res/Rot/MOr.png",
            "res/Blau/MOb.png",
            "res/Leer/MM.png",
            "res/Rot/MMr.png",
            "res/Blau/MMb.png",
            "res/Leer/MU.png",
            "res/Rot/MUr.png",
            "res/Blau/MUb.png",
            "res/Leer/RO.png",
            "res/Rot/ROr.png",
            "res/Blau/ROb.png",
            "res/Leer/RM.png",
            "res/Rot/RMr.png",
            "res/Blau/RMb.png",
            "res/Leer/RU.png",
            "res/Rot/RUr.png",
            "res/Blau/RUb.png", // 28



            "res/Menscairgerdichnicht/Haus/Blau/B.A.png", //29
            "res/Menscairgerdichnicht/Haus/Blau/B.B.png", // 30
            "res/Menscairgerdichnicht/Haus/Blau/B.C.png", //31
            "res/Menscairgerdichnicht/Haus/Blau/B.D.png", //32

            "res/Menscairgerdichnicht/Haus/Gelb/Gel.A.png", //33
            "res/Menscairgerdichnicht/Haus/Gelb/Gel.B.png", //34
            "res/Menscairgerdichnicht/Haus/Gelb/Gel.C.png", // 35
            "res/Menscairgerdichnicht/Haus/Gelb/Gel.D.png", //36

            "res/Menscairgerdichnicht/Haus/Grun/Grü.A.png", //37
            "res/Menscairgerdichnicht/Haus/Grun/Grü.B.png", //38
            "res/Menscairgerdichnicht/Haus/Grun/Grü.C.png", //39
            "res/Menscairgerdichnicht/Haus/Grun/Grü.D.png", // 40

            "res/Menscairgerdichnicht/Haus/Leer/Feld0.A.png", //41
            "res/Menscairgerdichnicht/Haus/Leer/Feld0.B.png", //42
            "res/Menscairgerdichnicht/Haus/Leer/Feld0.C.png", //43
            "res/Menscairgerdichnicht/Haus/Leer/Feld0.D.png", //44

            "res/Menscairgerdichnicht/Haus/Rot/R.A.png", //45
            "res/Menscairgerdichnicht/Haus/Rot/R.B.png", //46
            "res/Menscairgerdichnicht/Haus/Rot/R.C.png", //47
            "res/Menscairgerdichnicht/Haus/Rot/R.D.png", //48


            "res/Menscairgerdichnicht/Rand/EckLinksOben.png", //49
            "res/Menscairgerdichnicht/Rand/EckLinksUnten.png", //50
            "res/Menscairgerdichnicht/Rand/EckRechtsOben.png", //51
            "res/Menscairgerdichnicht/Rand/EckRechtsUnten.png", //52

            "res/Menscairgerdichnicht/Rand/Leeresbauteil.png", //53

            "res/Menscairgerdichnicht/Rand/RandLinks.png", //54
            "res/Menscairgerdichnicht/Rand/RandOben.png", //55
            "res/Menscairgerdichnicht/Rand/RandRechts.png", //56
            "res/Menscairgerdichnicht/Rand/Randunten.png", //57


            "res/Menscairgerdichnicht/Zugfelder/Feld0.B.png",
            "res/Menscairgerdichnicht/Zugfelder/Feld0.Gel.png",
            "res/Menscairgerdichnicht/Zugfelder/Feld0.Grü.png", //60
            "res/Menscairgerdichnicht/Zugfelder/Feld0.Leer.png", //61
            "res/Menscairgerdichnicht/Zugfelder/Feld0.R.png", //Alle Zahlen sin eins zu hoch


            "res/Menscairgerdichnicht/Wuefel/W.1.png", //62
            "res/Menscairgerdichnicht/Wuefel/W.2.png", //63
            "res/Menscairgerdichnicht/Wuefel/W.3.png", //64
            "res/Menscairgerdichnicht/Wuefel/W.4.png", //65
            "res/Menscairgerdichnicht/Wuefel/W.5.png", //66
            "res/Menscairgerdichnicht/Wuefel/W.6.png", //67

    };

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tiles = new Tile[imagePaths.length];
        mapTileNum = new int[gp.maxScreenCol][gp.getMaxScreenRow];

        getTileImage();
        loadMap();
    }

    public void getTileImage(){
        for (int i = 0; i < imagePaths.length; i++) {
            // Initialize the Tile object
            tiles[i] = new Tile();

            // Define the path to the current image
            Path imagePath = Paths.get(imagePaths[i]);

            // Print the absolute path for debugging
            System.out.println("Image path: " + imagePath.toAbsolutePath());

            // Check if the file exists and is readable
            if (Files.exists(imagePath) && Files.isReadable(imagePath)) {
                try {
                    // Read the image from the path
                    tiles[i].image = ImageIO.read(imagePath.toFile());

                    if (tiles[i].image != null) {
                        System.out.println("Image " + (i + 1) + " loaded successfully!");
                    } else {
                        System.out.println("Failed to load image " + (i + 1) + ". The file may not be a valid image.");
                    }
                } catch (IOException e) {
                    System.err.println("Error reading image file " + (i + 1) + ": " + e.getMessage());
                }
            } else {
                System.err.println("File does not exist or is not readable: " + imagePath.toAbsolutePath());
            }
        }
    }


    public void loadMap(){

        try{
            InputStream inputS = getClass().getResourceAsStream(mapFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputS));

            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol && row < gp.getMaxScreenRow){
                String line = br.readLine();

                while (col < gp.maxScreenCol){
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

            originalMap = new int[mapTileNum.length][];
            for (int i = 0; i < mapTileNum.length; i++) {
                originalMap[i] = mapTileNum[i].clone();
            }

        } catch (Exception e){
            System.out.println("error :)");
        }
    }

    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.getMaxScreenRow){

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tiles[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }

    public void switchMap(int map){
        switch (map){
            case 0:
                gp.setScreenSizeTiles(5,5, 8);
                mapTileNum = new int[gp.maxScreenCol][gp.getMaxScreenRow];
                mapFile = "whiteMap.txt";
                loadMap();
                break;
            case 1:
                gp.setScreenSizeTiles(5,5, 8);
                mapTileNum = new int[gp.maxScreenCol][gp.getMaxScreenRow];
                mapFile = "map.txt";
                loadMap();
                break;

            case 2:
                gp.setScreenSizeTiles(17,15, 3);
                mapTileNum = new int[gp.maxScreenCol][gp.getMaxScreenRow];
                mapFile = "menschBoard.txt";
                loadMap();
                break;
        }
    }
}
