package taskHandler;
import static java.lang.System.out;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TaskHandler implements Handler {
  private ThreadPoolExecutor threadPoolExecutor;

  public TaskHandler(int threadPoolSize) {
    this.threadPoolExecutor = new ThreadPoolExecutor(
        threadPoolSize, threadPoolSize * 2, 5L,
        TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
  }

  public void executeTask(Task task) {
    out.println(String.format("Executing task %s", task.getTaskName()));
    this.threadPoolExecutor.execute(task);
  }
}
