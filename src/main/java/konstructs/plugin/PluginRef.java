package konstructs.plugin;

import akka.actor.ActorRef;
import com.typesafe.config.Config;

public class PluginRef {
    private final ActorRef plugin;
    private final Config config;

    public PluginRef(ActorRef plugin, Config config) {
        this.plugin = plugin;
        this.config = config;
    }

    public ActorRef getPlugin() {
        return plugin;
    }

    public Config getConfig() {
        return config;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PluginRef pluginRef = (PluginRef) o;

        if (!plugin.equals(pluginRef.plugin)) return false;
        return config.equals(pluginRef.config);

    }

    @Override
    public int hashCode() {
        int result = plugin.hashCode();
        result = 31 * result + config.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PluginRef(" +
                "plugin=" + plugin +
                ", config=" + config +
                ')';
    }
}
