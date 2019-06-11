package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {


    String PORT;
    String MEMORY_LIMIT;
    String CF_INSTANCE_INDEX;
    String CF_INSTANCE_ADDR;

    public EnvController(@Value("${PORT:NOT SET}") String PORT,
                         @Value("${MEMORY_LIMIT:NOT SET}") String MEMORY_LIMIT,
                         @Value("${CF_INSTANCE_INDEX:NOT SET}") String CF_INSTANCE_INDEX,
                         @Value("${CF_INSTANCE_ADDR:NOT SET}") String CF_INSTANCE_ADDR) {
        this.PORT = PORT;
        this.MEMORY_LIMIT = MEMORY_LIMIT;
        this.CF_INSTANCE_INDEX = CF_INSTANCE_INDEX;
        this.CF_INSTANCE_ADDR = CF_INSTANCE_ADDR;
    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {
        Map<String, String> ergebnis = new HashMap<>();
        ergebnis.put("PORT", PORT);
        ergebnis.put("MEMORY_LIMIT", MEMORY_LIMIT);
        ergebnis.put("CF_INSTANCE_INDEX", CF_INSTANCE_INDEX);
        ergebnis.put("CF_INSTANCE_ADDR", CF_INSTANCE_ADDR);
        return ergebnis;
    }
    
}


/*package test.pivotal.pal.tracker;

        import org.junit.Test;

        import java.util.Map;
        import io.pivotal.pal.tracker.EnvController;

        import static org.assertj.core.api.Assertions.assertThat;

public class EnvControllerTest {
    @Test
    public void getEnv() throws Exception {
        EnvController controller = new EnvController(
                "8675",
                "12G",
                "34",
                "123.sesame.street"
        );

        Map<String, String> env = controller.getEnv();

        assertThat(env.get("PORT")).isEqualTo("8675");
        assertThat(env.get("MEMORY_LIMIT")).isEqualTo("12G");
        assertThat(env.get("CF_INSTANCE_INDEX")).isEqualTo("34");
        assertThat(env.get("CF_INSTANCE_ADDR")).isEqualTo("123.sesame.street");
    }

}*/
