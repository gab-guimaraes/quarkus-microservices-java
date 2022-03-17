package org.acme;

import org.acme.qualifiers.ThirteenDigits;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.util.Random;

@ApplicationScoped
@Default
@ThirteenDigits
public class IsbnGenerator implements NumberGenerator {
    @Override
    public String generateNumber() {
        return "13-84356-" + Math.abs(new Random().nextInt());
    }
}
