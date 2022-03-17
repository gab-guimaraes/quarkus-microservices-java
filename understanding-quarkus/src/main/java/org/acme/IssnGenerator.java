package org.acme;

import org.acme.qualifiers.EightDigits;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@EightDigits
@ApplicationScoped
public class IssnGenerator implements NumberGenerator {
    @Override
    public String generateNumber() {
        return "8-" + Math.abs(new Random().nextInt());
    }
}
