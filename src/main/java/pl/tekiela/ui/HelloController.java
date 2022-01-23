package tekiela.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tekiela.greetings.Greeter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HelloController {
    private Greeter greeter;

    public HelloController(Greeter greeter) {
        this.greeter = greeter;
    }

    @GetMapping("/hello")
    List<String> hello() {
        return Arrays.asList(
                "Kuba",
                "Michal",
                "Krzysztof",
                "Alicja"
        ).stream()
                .map(name -> greeter.hello(name))
                .collect(Collectors.toList());
    }
}
