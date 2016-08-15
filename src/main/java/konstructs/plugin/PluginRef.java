package konstructs.plugin;

import akka.actor.ActorRef;
import com.typesafe.config.Config;

/**
 * PluginRef represents a plugin and its local configuration to be used by other plugins.
 * If your plugin requires to keep a reference to other plugins, the server needs to know which plugins
 * that are required so that it can create the appropriate dependency graph between the plugins and start
 * them in order. When this class is used as an argument of the plugins {@link PluginConstructor} together
 * with either {@link konstructs.plugin.Config} or {@link ListConfig} the plugin loader will provide it with
 * bothe the ActorRef and a Config object for any other plugin depended on. Please note that the configuration
 * is not the configuration of the other plugin but the configuration of that plugin inside the configuration
 * of your own plugin. Let's look at a example:
 * <pre>
 *    listeners {
 *         some/other/plugin {
 *             some/event/type {}
 *             some/other/event/type {}
 *         }
 *    }
 * </pre>
 * <p>
 *     In the example above, listeners is defined as an argument of the props method using {@link ListConfig} and
 *     has the type List of PluginRef. This list will contain one instance of PluginRef with {@link #getPlugin()}
 *     returning the ActorRef of the plugin "some/other/plugin" and {@link #getConfig()} returning the configuration
 *     between the "{}" following the "some/other/plugin" definition, that is the two keys "some/event/type" and
 *     "some/other/event/type". In this example the plugin has defined a list into which other plugins can register
 *     themselves as listeners and it let's the plugins also register to specific event types by allowing it to list
 *     them in the configuration part of the listeners list entry.
 * </p>
 */
public class PluginRef {
    private final ActorRef plugin;
    private final Config config;

    /**
     * Creates a new immutable PluginRef instance
     * @param plugin The ActorRef pointing to the plugin
     * @param config The configuration for this PluginRef
     */
    public PluginRef(ActorRef plugin, Config config) {
        this.plugin = plugin;
        this.config = config;
    }

    /**
     * The ActorRef of the plugin
     * @return The ActorRef
     */
    public ActorRef getPlugin() {
        return plugin;
    }

    /**
     * The configuration related to the plugin's entry
     * @return The configuration
     */
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
