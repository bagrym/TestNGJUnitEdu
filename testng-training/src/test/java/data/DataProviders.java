package data;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by VEREMENYUK_P on 11/29/2017.
 */
public class DataProviders {
    @DataProvider (name = "StaticValues")
    public static Object[][] staticValues() {
        return new Object[][]{{"name1.txt"}, {"name2.txt"}};
    }

    @DataProvider (name = "GeneratedValues")
    public static Iterator<Object[]> generatedValues() {
        List<Object[]> filesNames = new ArrayList<Object[]>();

        for (int i = 0; i < 5; i++) {
            filesNames.add(new Object[]{generatedFileName()});
        }
        return filesNames.iterator();
    }

    @DataProvider (name = "FileValues")
    public static Iterator<Object[]> fileValues() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(
                DataProviders.class.getResourceAsStream("/fileNames.data")));

        List<Object[]> fileNames = new ArrayList<Object[]>();
        String line = input.readLine();
        while (line != null) {
            fileNames.add(new Object[]{line});
            line = input.readLine();
        }

        return fileNames.iterator();
    }

    private static Object generatedFileName() {
        return "name" + new Random().nextInt() + ".txt";
    }
}
