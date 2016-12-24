package com.coherentlogic.geofred.client.core.factories;

import java.util.Map;

import com.coherentlogic.coherent.data.adapter.core.factories.TypedFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

public class GsonFactory implements TypedFactory<Gson> {

    private final Gson gson;

    public GsonFactory(Map<Class<?>, JsonDeserializer<?>> typeAdapterMap) {

        GsonBuilder builder = new GsonBuilder();

        typeAdapterMap.forEach(
            (clazz, typeAdapter) -> {
                builder.registerTypeAdapter(clazz, typeAdapter);
            }
        );

        gson = builder.create();
    }

    @Override
    public Gson getInstance() {
        return gson;
    }
}
