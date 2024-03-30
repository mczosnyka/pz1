package pz1.michalczosnyka;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    @Test
    void testLibraryConstructor() {
       Library library = new Library();
       assertEquals(0, library.readersInside);
       assertFalse(library.writerInside);
       assertEquals(5, library.readersSemaphore.availablePermits());
       assertEquals(1, library.writersSemaphore.availablePermits());
    }

    @Test
    void testWriterConstructor(){
        Library library = new Library();
        Writer writer = new Writer(library, 6);
        assertEquals(6, writer.id);
        assertEquals(library, writer.library);
    }

    @Test
    void testReaderConstructor(){
        Library library = new Library();
        Reader reader = new Reader(library, 6);
        assertEquals(6, reader.id);
        assertEquals(library, reader.library);
    }

    @Test
    void LibraryRequestWrite() throws InterruptedException {
        Library library = new Library();
        Writer writer = new Writer(library, 1);
        writer.library.requestWrite();
        assertTrue(library.writerInside);
        assertEquals(0, library.writersSemaphore.availablePermits());
        assertEquals(0, library.readersInside);
        assertEquals(5, library.readersSemaphore.availablePermits());
    }

    @Test
    void LibraryRequestRead() throws InterruptedException {
        Library library = new Library();
        Reader reader = new Reader(library, 1);
        reader.library.requestRead();
        assertEquals(1, library.readersInside);
        assertEquals(4, library.readersSemaphore.availablePermits());
        assertFalse(library.writerInside);
        assertEquals(1, library.writersSemaphore.availablePermits());
    }

    @Test
    void LibraryFinishWrite() throws InterruptedException {
        Library library = new Library();
        Writer writer = new Writer(library, 1);
        writer.library.requestWrite();
        assertTrue(library.writerInside);
        assertEquals(0, library.writersSemaphore.availablePermits());
        assertEquals(0, library.readersInside);
        assertEquals(5, library.readersSemaphore.availablePermits());
        writer.library.finishWrite();
        assertEquals(0, library.readersInside);
        assertEquals(5, library.readersSemaphore.availablePermits());
        assertFalse(library.writerInside);
        assertEquals(1, library.writersSemaphore.availablePermits());
    }


    @Test
    void LibraryFinishRead() throws InterruptedException {
        Library library = new Library();
        Reader reader = new Reader(library, 1);
        reader.library.requestRead();
        assertFalse(library.writerInside);
        assertEquals(1, library.writersSemaphore.availablePermits());
        assertEquals(1, library.readersInside);
        assertEquals(4, library.readersSemaphore.availablePermits());
        reader.library.finishRead();
        assertEquals(0, library.readersInside);
        assertEquals(5, library.readersSemaphore.availablePermits());
        assertFalse(library.writerInside);
        assertEquals(1, library.writersSemaphore.availablePermits());
    }

    @Test
    void testReaderRun() throws InterruptedException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        try {
            Library library = new Library();
            Reader reader = new Reader(library, 1);
            Thread readerThread = new Thread(reader);
            readerThread.start();
            Thread.sleep(1000);
            assertEquals("Czytelnik numer 1 wszedl do czytelni.", outputStream.toString().trim());
            Thread.sleep(4000);
            assertEquals("Czytelnik numer 1 wszedl do czytelni. \nCzytelnik numer 1 opuscil czytelnie.", outputStream.toString().trim());
            readerThread.interrupt();
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testWriterRun() throws InterruptedException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        try {
            Library library = new Library();
            Writer writer = new Writer(library, 1);
            Thread writerThread = new Thread(writer);
            writerThread.start();
            Thread.sleep(1000);
            assertEquals("Pisarz numer 1 wszedl do czytelni.", outputStream.toString().trim());
            Thread.sleep(6000);
            assertEquals("Pisarz numer 1 wszedl do czytelni. \nPisarz numer 1 opuscil czytelnie.", outputStream.toString().trim());
            writerThread.interrupt();
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testMainDefault(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        String input = "0\n0";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        try {
            Main main1 = new Main();
            String[] args = new String[0];
            System.setIn(inputStream);
            main1.main(args);
            assertTrue(outputStream.toString().trim().contains("liczbe czytelnikow"));
            assertTrue(outputStream.toString().trim().contains("liczbę pisarzy"));
            int reader_count = 0;
            int writer_count = 0;
            Map<Thread, StackTraceElement[]> allThreads = Thread.getAllStackTraces();
            for (Thread thread : allThreads.keySet()) {
                if (thread instanceof Reader) {
                    reader_count ++;
                    thread.interrupt();
                }
                if (thread instanceof Writer){
                    writer_count ++;
                    thread.interrupt();
                }
            }
            assertEquals(10, reader_count);
            assertEquals(3, writer_count);
        }
        finally{
            System.setOut(originalOut);
        }
        }

    @Test
    void testMainCustomInput(){
        String input = "6\n2";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Main main1 = new Main();
        String[] args = new String[0];
        System.setIn(inputStream);
        main1.main(args);
        int reader_count = 0;
        int writer_count = 0;
        Map<Thread, StackTraceElement[]> allThreads = Thread.getAllStackTraces();
        for (Thread thread : allThreads.keySet()) {
            if (thread instanceof Reader) {
                reader_count ++;
                thread.interrupt();
            }
            if (thread instanceof Writer){
                writer_count ++;
                thread.interrupt();
            }
        }
        assertEquals(6, reader_count);
        assertEquals(2, writer_count);
    }


}
