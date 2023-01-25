package ml.windleaf.easylib.register;

import ml.windleaf.easylib.interfaces.CommandInfo;
import ml.windleaf.easylib.interfaces.ICommand;
import ml.windleaf.easylib.interfaces.IListener;
import ml.windleaf.easylib.utils.ClassUtils;
import ml.windleaf.easylib.utils.PluginUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 注册管理器
 * TODO 更名为 `RegistrationManager`
 */
public class RegisterManager {
    /**
     * 存储的命令和对应的 `ICommand`
     */
    public static final Map<String, ICommand> commands = new HashMap<>();

    public RegisterManager(String packagePath) {
        // 注册命令
        ClassUtils.getSubClasses(ICommand.class, packagePath).forEach(command -> {
            ICommand instance = ClassUtils.newInstance(command);
            CommandInfo info = command.getAnnotation(CommandInfo.class);
            if (info != null) {
                Arrays.asList(info.value()).forEach(cmd -> RegisterManager.commands.put(cmd, instance));
            }
        });

        // 注册事件
        ClassUtils.getSubClasses(IListener.class, packagePath).forEach(listener -> {
            IListener instance = ClassUtils.newInstance(listener);
            PluginUtils.registerEvent(instance);
        });

        PluginUtils.registerEvent(new CommandListener());
    }
}
