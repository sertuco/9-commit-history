package part03;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        Path logFile = Path.of("pizza-commits.txt");
        String fileContents = Files.readString(logFile, StandardCharsets.UTF_8);

        // TODO: implement the logic described in the readme file

        // `fileContents` variable should contain the contents of the pizza log file
        // as in the previous parts. You can check the contents by uncommenting the
        // following line:

        // System.out.println(fileContents);
        HashMap<String, String> commitMap = new HashMap<>();
        HashMap<String, String> idMap = new HashMap<>();

        for (String commit : fileContents.split("\n\n")) {
            String lines = commit.split("\n")[0];
            String[] sections = lines.split(" ");
            String id = sections[1];
            String parent = sections[5].replace(")", "");
            commitMap.put(id, commit);
            idMap.put(parent, id);
        }
        String currentId = idMap.get("None");
        while (currentId != null) {
            System.out.println(commitMap.get(currentId) + "\n");
            currentId = idMap.get(currentId);
        }
    }
}