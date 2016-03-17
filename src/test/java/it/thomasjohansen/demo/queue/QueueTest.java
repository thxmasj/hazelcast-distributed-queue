package it.thomasjohansen.demo.queue;

import com.hazelcast.core.HazelcastInstance;
import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author thomas@thomasjohansen.it
 */
public class QueueTest {

    @Test
    public void competingConsumers() throws InterruptedException {
        HazelcastInstance hazelcastNode1 = new HazelcastInstanceBuilder()
                .port(10001)
                .member("localhost:10001")
                .member("localhost:10002")
                .build();
        HazelcastInstance hazelcastNode2 = new HazelcastInstanceBuilder()
                .port(10002)
                .member("localhost:10001")
                .member("localhost:10002")
                .build();
        HazelcastInstance hazelcastClient = new HazelcastClientBuilder()
                .address("localhost:10001")
                .address("localhost:10002")
                .connectionAttemptLimit(0)
                .build();
        BlockingQueue<String> p = hazelcastClient.getQueue("q");
        BlockingQueue<String> c1 = hazelcastNode1.getQueue("q");
        BlockingQueue<String> c2 = hazelcastNode2.getQueue("q");
        p.add("Hello, world!");
        assertEquals("Hello, world!", c2.take());
        assertTrue(c1.isEmpty());
        assertTrue(c2.isEmpty());
    }

}
