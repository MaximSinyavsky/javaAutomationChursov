import org.junit.jupiter.api.Test;

public class ParallelJunitTest {
    @Test
    void firstTest() throws Exception {
        System.out.println("FirstParallelUnitTest first() start =>" + Thread.currentThread().getName());
        Thread.sleep(500);
        System.out.println("FirstParallelUnitTest first() end =>" + Thread.currentThread().getName());
    }

    @Test
    void secondTest() throws Exception {
        System.out.println("FirstParallelUnitTest first() start =>" + Thread.currentThread().getName());
        Thread.sleep(500);
        System.out.println("FirstParallelUnitTest first() end =>" + Thread.currentThread().getName());
    }
}
