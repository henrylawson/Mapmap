package net.henrylawson.mapmap;

import org.junit.Test;

import java.util.Map;

import static net.henrylawson.mapmap.MapmapTest.MapmapBuilder.aMapmap;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class MapmapTest {

    private static final int KEY_1 = 5;
    private static final int KEY_2 = 6;
    private static final int SUB_KEY_1 = 1;
    private static final int SUB_KEY_2 = 2;
    private static final String VALUE_1 = "A Value";
    private static final String VALUE_2 = "Another Value";

    @Test
    public void shouldAllowASingleEntryToBePutInTheSubMap() {
        Mapmap<Integer, Integer, String> mapmap = aMapmap()
            .with(KEY_1, SUB_KEY_1, VALUE_1)
            .build();

        Map<Integer,String> subMap = mapmap.get(KEY_1);

        assertThat(subMap.size(), equalTo(1));
        assertThat(subMap.get(SUB_KEY_1), equalTo(VALUE_1));
    }

    @Test
    public void shouldAllowMultipleEntriesToBePutInTheSubMap() {
        Mapmap<Integer, Integer, String> mapmap = aMapmap()
            .with(KEY_1, SUB_KEY_1, VALUE_1)
            .with(KEY_1, SUB_KEY_2, VALUE_2)
            .build();

        Map<Integer,String> subMap = mapmap.get(KEY_1);

        assertThat(subMap.size(), equalTo(2));
        assertThat(subMap.get(SUB_KEY_1), equalTo(VALUE_1));
        assertThat(subMap.get(SUB_KEY_2), equalTo(VALUE_2));
    }

    @Test
    public void shouldAllowEntriesToBeRetrievedByBothKeys() {
        Mapmap<Integer, Integer, String> mapmap = aMapmap()
            .with(KEY_1, SUB_KEY_1, VALUE_1)
            .build();

        String value = mapmap.get(KEY_1, SUB_KEY_1);

        assertThat(value, equalTo(VALUE_1));
    }

    @Test
    public void shouldHaveASizeOfOneWhenASingleKeyIsAdded() {
        Mapmap<Integer, Integer, String> mapmap = aMapmap()
            .with(KEY_1, SUB_KEY_1, VALUE_1)
            .build();

        int size = mapmap.size();

        assertThat(size, equalTo(1));
    }

    @Test
    public void shouldHaveASizeOfOneWhenASingleKeyIsAddedButMultipleSubKeysAreToo() {
        Mapmap<Integer, Integer, String> mapmap = aMapmap()
            .with(KEY_1, SUB_KEY_1, VALUE_1)
            .with(KEY_1, SUB_KEY_2, VALUE_1)
            .build();

        int size = mapmap.size();

        assertThat(size, equalTo(1));
    }

    @Test
    public void shouldHaveASizeOfTwoWhenTwoKeysAreAdded() {
        Mapmap<Integer, Integer, String> mapmap = aMapmap()
            .with(KEY_1, SUB_KEY_1, VALUE_1)
            .with(KEY_2, SUB_KEY_1, VALUE_1)
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
