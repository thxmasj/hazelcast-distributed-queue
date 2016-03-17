package it.thomasjohansen.demo.queue;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;

/**
 * @author thomas@thomasjohansen.it
 */
public class QueueConsumer<T> {

    private HazelcastInstance hazelcast;
    private IQueue<T> queue;

    public QueueConsumer(String queueName) {
        this.hazelcast = Hazelcast.newHazelcastInstance();
        this.queue = hazelcast.getQueue(queueName);
    }

    public T dequeue() throws InterruptedException {
        return queue.take();
    }

}
