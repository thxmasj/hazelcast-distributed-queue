package it.thomasjohansen.demo.queue;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastInstanceBuilder {

    private Config config;

    public HazelcastInstanceBuilder() {
        config = new Config();
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        config.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(true);
    }

    public HazelcastInstanceBuilder member(String member) {
        config.getNetworkConfig().getJoin().getTcpIpConfig().addMember(member);
        return this;
    }

    public HazelcastInstanceBuilder port(int port) {
        config.getNetworkConfig().setPort(port);
        return this;
    }

    public HazelcastInstance build() {
        return Hazelcast.newHazelcastInstance(config);
    }

}
