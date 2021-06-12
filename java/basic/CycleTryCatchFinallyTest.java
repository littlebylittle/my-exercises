import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


@Slf4j
public class CycleTryCatchFinallyTest {

    @Test
    void anotherTest() {
        int i = twoCycles();
        log.info("twoCycles guess, and the result is : {}", i);
        Assertions.assertEquals(i, ???);
    }

    private int twoCycles() {
        int i = 0;
        final int HALF = 2;
        final int FULL = HALF*2;
        int j = HALF;
        foo:
        for(; i< FULL; i++) {
            try {
                log.info("Inside first loop: i={}, j={}", i, j);
                if(i < HALF) {
                    continue  foo;
                }
                for(; j< FULL; j++) {
                    try {

                        log.info("\t\tInside second loop: i={}, j={}", i, j);
                        j++;
                        if(j >= FULL) {
                            log.info("return RuntimeException");
                            throw new RuntimeException("out of bound");
                        }
                        continue  foo;
                    } catch(Throwable t) {
                        log.info("return 5555");
                        return 5555;
                    } finally {
                        log.info("return 6666");
                        return 6666;
                    }
                }
                log.info("Closing first cycle");
            } catch (Throwable t) {
                log.info("return 7777");
                return 7777;
            } finally {
                continue foo;
            }

        }

        return 8888;
    }
}
