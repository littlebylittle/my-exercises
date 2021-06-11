//You have tests below, what will they return?

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class FinallyBlockAfterReturnTestTest {
    @Test
    void firstTest() {
        int i = someMagicMethod();
        log.info("First guess and the result is : {}", i);
        // assertEquals(i, ??);
    }

    @Test
    void secondTest() {
        int i = cycleFail();
        log.info("Second guess, and the result is : {}", i);
        // assertEquals(i, ??);
    }

    int someMagicMethod() {
        double random = Math.random();
        for(int j = 0; j<=0; j++) {
            try {
                log.info("Random value is : {}", random);
                if(random <= .5) {
                    log.info("And we are going to send: {}", 1);
                    return 1;
                } else {
                    log.info("And we are going to send: {}", "exception");
                    throw new RuntimeException("Some runtime exception");
                }
            } catch (Throwable t) {
                log.info("And we are going to send: {}", 2);
                return 2;
            } finally {
                log.info("And we are going to send: {}", 3);
                return 3;

            }
        }
        return 7;
    }

    int cycleFail() {

        int[] numbers= new int[]{100,18,21,30};
        int i = 0;
        //Outer loop checks if number is multiple of 2
        OUTER:  //outer label
        for(; i<numbers.length; i++){
                log.info("Odd number: " + i + ", continue from OUTER label");
                try {
                    if(i == 1) {
                        log.info("Trying to throw");
                        throw new RuntimeException("What the heck, `i` canot be equal to 1!");
                    }
                    return 1;
                } catch (Throwable t) {
                    log.info("Trying to return 2");
                    return 2;
                } finally {
                    log.info("go to OUTER");
                    continue OUTER;
                }

        }
        log.info("Trying to return i: {}", i);
        return i;
    }
}
