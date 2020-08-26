package tk.tarajki.entities.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties("abc")
public class AppSettings {
    private static AppSettings app;

    private final String test;

    public static String getTest() {
        return app.test;
    }

    private AppSettings(String test) {
        this.test = test;
        app = this;
    }
}
