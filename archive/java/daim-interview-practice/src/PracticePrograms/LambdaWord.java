package PracticePrograms;

import java.util.Optional;

public class LambdaWord {
    public static void main(String[] args) {
        Optional<String> amr = Optional.ofNullable("samr");

        System.out.println(amr.isPresent());
        System.out.println(amr.isEmpty());

        amr.ifPresent(seippu -> {
            System.out.println(seippu);
        });
    }
}
