package ml.windleaf.easylib.plugin;

import ml.windleaf.easylib.logging.PluginLogger;
import ml.windleaf.easylib.registration.RegistrationManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * 基本插件接口
 */
public abstract class EasyLibPlugin extends JavaPlugin {
    /**
     * 插件的日志记录器
     */
    public static PluginLogger logger;

    /**
     * 插件实例对象
     */
    public static EasyLibPlugin instance;

    @NotNull
    @Nls
    protected abstract String[] getPluginMOTD();

    @Override
    public void onEnable() {
        instance = this;
        PluginInfo info = this.getClass().getAnnotation(PluginInfo.class);
        logger = new PluginLogger(this.getName());
        if (info != null) {
            logger.setLoggerColor(info.loggerColor());
            Arrays.stream(this.getPluginMOTD()).forEach(logger::logConsole);
            try {
                getConfig().options().copyDefaults();
                saveDefaultConfig();
            } catch (IllegalArgumentException ignore) {
            }
            RegistrationManager.init(info.packagePath());
        } else {
            logger.logConsole("#RED#Could not load plugin info, please check the annotation!");
            throw new IllegalArgumentException();
        }
    }
}