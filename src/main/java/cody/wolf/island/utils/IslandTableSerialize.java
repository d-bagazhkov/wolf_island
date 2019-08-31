package cody.wolf.island.utils;

import cody.wolf.island.model.IslandTable;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class IslandTableSerialize extends StdSerializer<IslandTable> {

    protected IslandTableSerialize() {
        super(IslandTable.class);
    }


    @Override
    public void serialize(IslandTable table, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();

        for (int i = 0; i < table.getHorizontalSize(); i++) {
            for (int j = 0; j < table.getVerticalSize(); j++) {
                jsonGenerator.writeObject(table.get(i, j));
            }
        }

        jsonGenerator.writeEndArray();
    }
}
