package Task3;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by VEREMENYUK_P on 11/14/2017.
 */

@Test
public class CreateFileTest {
    private Path dirPath = null;
    private String sDirPath = null;
    private String fileName = "test.txt";
    private String wrongFileName = "<>.txt";

    @BeforeClass
    //Create temp directory
    public void setUp() {
        System.out.println("SetUp:");

        try {
            dirPath = Files.createTempDirectory("tempDir_");
            sDirPath = dirPath.toString();
            System.out.println("\t" + "New temp directory: " + sDirPath);
        } catch (IOException exception) {
            System.out.println(exception.toString());
        }
    }

    @AfterClass
    //Delete temp directory (should be empty prior deletion)
    public void tearDown() {
        System.out.println("TearDown:");

        dirPath.toFile().delete();
        System.out.println("\t" + "Temp directory deleted: " + !(dirPath.toFile().exists()));
    }

    //Positive test: file creation
    public void newFileTest() {
        File file;
        boolean aBoolean;

        System.out.println("New file test:");

        try {
            file = new File(sDirPath, fileName);
            aBoolean = file.createNewFile();
            System.out.println("\t" + "File created: " + aBoolean);
        } catch (IOException exception){
            System.out.println("\t" + exception.getMessage());
        }
    }

    //Positive&negative test: attempt to create file with the same name, file deletion
    public void theSameFileTest() {
        File file;
        boolean aBoolean;

        System.out.println("The same filename test:");

        try  {
            file = new File(sDirPath, fileName);
            aBoolean = file.createNewFile();
            System.out.println("\t" + "File created: " + aBoolean);

            aBoolean = file.delete();
            System.out.println("\t" + "File deleted: " + aBoolean);

            aBoolean = file.createNewFile();
            System.out.println("\t" + "File created: " + aBoolean);

            aBoolean = file.delete();
            System.out.println("\t" + "File deleted: " + aBoolean);
        } catch (IOException exception) {
            System.out.println("\t" + exception.getMessage());
        }
    }

    //Negative test - attempt to create file with the wrong filename
    public void wrongFileNameTest() {
        File file;
        boolean aBoolean;

        System.out.println("Wrong filename test:");

        try {
            file = new File(sDirPath, wrongFileName);
            aBoolean = file.createNewFile();
            System.out.println("\t" + "File created: " + aBoolean);
        } catch (IOException exception) {
            System.out.println("\t" + "Expected error message: " + exception.getMessage());
        }
    }

    //Negative test - attempt to create file in non existing directory
    public void nonExistingDirTest() {
        File file;
        boolean aBoolean;

        System.out.println("Non existing directory test:");

        try {
            file = new File(sDirPath + "blablabla", fileName);
            aBoolean = file.createNewFile();
            System.out.println("\t" + "File created: " + aBoolean);
        } catch (IOException exception) {
            System.out.println("\t" + "Expected error message: " + exception.getMessage());
        }
    }
}