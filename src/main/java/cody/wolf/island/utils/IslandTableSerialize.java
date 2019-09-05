package cody.wolf.island.utils;

import cody.wolf.island.service.impl.IslandImpl;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class IslandTableSerialize extends StdSerializer<IslandImpl> {

    protected IslandTableSerialize() {
        super(IslandImpl.class);
    }


    @Override
    public void serialize(IslandImpl table, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();

        for (int i = 0; i < table.getHorizontalSize(); i++) {
            for (int j = 0; j < table.getVerticalSize(); j++) {
                jsonGenerator.writeObject(table.getCell(i, j));
            }
        }

        jsonGenerator.writeEndArray();
    }
}
