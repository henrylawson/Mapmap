package net.henrylawson.mapmap;

import org.junit.Test;

import java.util.Map;

import static net.henrylawson.mapmap.MapmapTest.MapmapBuilder.aMapmap;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class MapmapTest {

    @Test
    public void shouldAllowASingleEntryToBePutInTheSubMap() {
        Mapmap<Integer, Integer, String> mapmap = aMapmap()
            .with(5, 6, "Hello")
            .build();

        Map<Integer,String> subMap = mapmap.get(5);

        assertThat(subMap.size(), equalTo(1));
        assertThat(subMap.get(6), equalTo("Hello"));
    }

    @Test
    public void shouldAllowMultipleEntriesToBePutInTheSubMap() {
        Mapmap<Integer, Integer, String> mapmap = aMapmap()
            .with(5, 6, "Hello")
            .with(5, 7, "Hello Girl")
            .with(5, 8, "Hello Boy")
            .build();

        Map<Integer,String> subMap = mapmap.get(5);

        assertThat(subMap.size(), equalTo(3));
        assertThat(subMap.get(6), equalTo("Hello"));
        assertThat(subMap.get(7), equalTo("Hello Girl"));
        assertThat(subMap.get(8), equalTo("Hello Boy"));
    }

    @Test
    public void shouldAllowEntriesToBeRetrievedByBothKeys() {
        Mapmap<Integer, Integer, String> mapmap = aMapmap()
            .with(5, 6, "Hello")
            .build();

        String value = mapmap.get(5, 6);

        assertThat(value, equalTo("Hello"));
    }

    @Test
    public void shouldHaveASizeOfOneWhenASingleKeyIsAdded() {
        Mapmap<Integer, Integer, String> mapmap = aMapmap()
            .with(5, 6, "Hello")
            .build();

        int size = mapmap.size();

        assertThat(size, equalTo(1));
    }

    @Test
    public void shouldHaveASizeOfOneWhenASingleKeyIsAddedButMultipleSubKeysAreToo() {
        Mapmap<Integer, Integer, String> mapmap = aMapmap()
            .with(5, 6, "Hello")
            .with(5, 7, "Hello")
            .build();

        int size = mapmap.size();

        assertThat(size, equalTo(1));
    }

    @Test
    public void shouldHaveASizeOfTwoWhenTwoKeysAreAdded() {
        Mapmap<Integer, Integer, String> mapmap = aMapmap()
            .with(5, 6, "Hello")
            .with(6, 6, "Hello")
            .build();

        int size = mapmap.size();

        assertThat(size, equalTo(2));
    }

    public static class MapmapBuilder {

        private final Mapmap<Integer, Integer, String> mapmap;

        public MapmapBuilder() {
            mapmap = Mapmaps.newHashMapmap();
        }

        public MapmapBuilder with(Integer key, Integer subKey, String value) {
            mapmap.put(key, subKey, value);
            return this;
        }

        public Mapmap<Integer, Integer, String> build() {
            return mapmap;
        }

        public static MapmapBuilder aMapmap() {
            return new MapmapBuilder();
        }
    }
}
