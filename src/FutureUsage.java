import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class FutureUsage {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(10);

        Future<List<Integer>> future1 = service.submit(() -> {

            System.out.println("Thread : "+Thread.currentThread().getName());
            delay(1);
            return Arrays.asList(1, 2, 3, 4, 5);
        });

        Future<List<Integer>> future2 = service.submit(() -> {

            System.out.println("Thread : "+Thread.currentThread().getName());
            delay(1);
            return Arrays.asList(1, 2, 3, 4, 5);
        });

        Future<List<Integer>> future3 = service.submit(() -> {

            System.out.println("Thread : "+Thread.currentThread().getName());
            delay(1);
            return Arrays.asList(1, 2, 3, 4, 5);
        });

       // List<Integer> list = future1.get();
        future1.get();
        future2.get();
        future3.get();
       // System.out.println(list);


        CompletableFuture<String>  completableFuture = new CompletableFuture<>();

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello Java");

        CompletableFuture<Integer> transformedFuture = future.thenApplyAsync(s -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
            return s.length();
        });

        transformedFuture.thenAccept(length -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
            System.out.println("Length of Hello Java: " + length);
        });


        completableFuture.get();
        completableFuture.complete("return some dummy data....");  // forcefully complete the task and return some value. So we don't need to block the thread

    }

   private static void delay(int min){
         try {
            TimeUnit.MINUTES.sleep(min);
         } catch (InterruptedException e) {
            throw new RuntimeException(e);
         }
    }
}