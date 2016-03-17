package it.thomasjohansen.demo.queue;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author thomas@thomasjohansen.it
 */
public class QueueTest {

    @Test
    public void test() throws InterruptedException {
        QueueProducer<String> producer = new QueueProducer<>("q");
        QueueConsumer<String> consumer = new QueueConsumer<>("q");
        producer.enqueue("Hello");
        assertEquals("Hello", consumer.dequeue());
    }

}
