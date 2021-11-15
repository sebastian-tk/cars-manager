package tkaczyk.sebastian.persistence.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tkaczyk.sebastian.persistence.converter.exception.JsonConverterException;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

public abstract class JsonConverter<T> {
    private final String jsonFileName;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Type type = ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    public JsonConverter(String jsonFileName) {
        this.jsonFileName = jsonFileName;
    }

    public void toJson(final T element){
        try(FileWriter fileWriter = new FileWriter(jsonFileName)){
            gson.toJson(element,fileWriter);
        }catch (Exception exception){
            throw new JsonConverterException(exception.getMessage());
        }
    }

    public Optional<T> fromJson(){
        try(FileReader fileReader = new FileReader(jsonFileName)){
            return Optional.of(gson.fromJson(fileReader,type));
        }catch (Exception exc){
            throw new JsonConverterException(exc.getMessage());
        }
    }
}
