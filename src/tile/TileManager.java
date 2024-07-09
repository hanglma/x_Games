package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.*;

public class TileManager {
    GamePanel gp;
    Tile[] tiles;
    public int[][] mapTileNum;

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
            "res/Blau/RUb.png"

//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Leer\\Leer.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Leer\\LO.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Rot\\LOr.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Blau\\LOb.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Leer\\LM.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Rot\\LMr.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Blau\\LMb.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Leer\\LU.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Rot\\LUr.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Blau\\LUb.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Leer\\MO.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Rot\\MOr.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Blau\\MOb.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Leer\\MM.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Rot\\MMr.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Blau\\MMb.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Leer\\MU.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Rot\\MUr.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Blau\\MUb.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Leer\\RO.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Rot\\ROr.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Blau\\ROb.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Leer\\RM.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Rot\\RMr.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Blau\\RMb.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Leer\\RU.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Rot\\RUr.png",
//            "C:\\Users\\Martin\\IdeaProjects\\X_Games\\res\\Blau\\RUb.png"
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
            InputStream inputS = getClass().getResourceAsStream("map.txt");
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
}
