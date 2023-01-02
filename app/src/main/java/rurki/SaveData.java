package rurki;

import java.io.FileWriter;
import java.io.IOException;

public class SaveData {
    

    public SaveData(String data){
        try {
            FileWriter myWriter = new FileWriter("data.txt");
            myWriter.write(data);
            myWriter.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
    }


}
