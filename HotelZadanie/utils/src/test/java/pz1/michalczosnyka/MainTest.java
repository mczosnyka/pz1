package pz1.michalczosnyka;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    @Test
    void testMyMapDefaultConstructor() {
        MyMap<String, Integer> m = new MyMap<String, Integer>();
        int size1 = m.values().size();
        int size2 = m.keys().size();
        assertEquals(0, size1);
        assertEquals(0, size2);
    }

    @Test
    void testMyMapPut(){
        MyMap<String, Integer> m = new MyMap<String, Integer>();
        assertEquals(0, m.values().size());
        assertEquals(0, m.keys().size());
        assertFalse(m.put(null,2));
        m.put("abc",1);
        assertEquals(1, m.get("abc"));
        assertEquals(1, m.values().size());
        assertEquals(1, m.keys().size());
        m.put("abc",2);
        assertEquals(2, m.get("abc"));
        assertEquals(1, m.values().size());
        assertEquals(1, m.keys().size());
    }

    @Test
    void testMyMapGet(){
        MyMap<String, Integer> m = new MyMap<String, Integer>();
        m.put("abc",1);
        assertEquals(1, m.get("abc"));
        assertNull(m.get("fgh"));
    }

    @Test
    void testMyMapRemove(){
        MyMap<String, Integer> m = new MyMap<String, Integer>();
        assertFalse(m.remove("abc"));
        assertEquals(0, m.values().size());
        assertEquals(0, m.keys().size());
        m.put("abc",1);
        assertEquals(1, m.values().size());
        assertEquals(1, m.keys().size());
        assertTrue(m.remove("abc"));
        assertEquals(0, m.values().size());
        assertEquals(0, m.keys().size());
    }

    @Test
    void testMyMapKeys(){
        ArrayList<String> ks = new ArrayList<String>();
        ks.add("abc");
        ks.add("def");
        ks.add("ghi");
        MyMap<String, Integer> m = new MyMap<String, Integer>();
        m.put("abc",1);
        m.put("def",2);
        m.put("ghi",3);
        assertEquals(m.keys(), ks);
    }

    @Test
    void testMyMapValues(){
        ArrayList<Integer> vs = new ArrayList<Integer>();
        vs.add(69);
        vs.add(420);
        vs.add(2137);
        MyMap<String, Integer> m = new MyMap<String, Integer>();
        m.put("abc",69);
        m.put("def",420);
        m.put("ghi",2137);
        assertEquals(m.values(), vs);
    }

    @Test
    void testMyMapContains(){
        MyMap<String, Integer> m = new MyMap<String, Integer>();
        assertFalse(m.contains("abc"));
        m.put("abc",1);
        assertTrue(m.contains("abc"));
    }







}
