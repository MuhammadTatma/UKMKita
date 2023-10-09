/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.ImageProcessing;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.file.StandardCopyOption;


/**
 *
 * @author HP
 */
public class ImageHandling {
    public static void copyImageToSrc(File imageSrc,String fileName) throws IOException{
        Path target = (Path) Paths.get("src/Image/" + fileName);
        Files.copy(imageSrc.toPath(), target,StandardCopyOption.REPLACE_EXISTING);
    }
}
