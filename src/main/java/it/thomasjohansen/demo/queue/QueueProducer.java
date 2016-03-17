package it.thomasjohansen.demo.queue;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;

/**
 * @author thomas@thomasjohansen.it
 */
public class QueueProducer<T> {

    private HazelcastInstance hazelcast;
    private IQueue<T> queue;

    public QueueProducer(String queueName) {
        this.hazelcast = Hazelcast.newHazelcastInstance();
        this.queue = hazelcast.getQueue(queueName);
    }

    public void enqueue(T element) throws InterruptedException {
        queue.put(element);
    }

}
