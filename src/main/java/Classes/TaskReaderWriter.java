package Classes;

import Interfaces.ITaskReaderWriter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TaskReaderWriter implements ITaskReaderWriter {

    @Override
    public void writeUserToFile(User user) {
        String temp;
        ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            temp = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(User.file))){
            writer.write(temp);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public User readUserFromFile() {
        User user;
        ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
           user = mapper.readValue(User.file, User.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
