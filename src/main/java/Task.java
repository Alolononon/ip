public class Task {
  private String taskName;
  private boolean isDone;

  public Task(String taskName) {
    this.taskName = taskName;
    this.isDone = false;
  }

  public String getTaskName() {
    return this.taskName;
  }
  
  public boolean getIsTaskDone() {
    return this.isDone;
  }

  public void markTask() {
    this.isDone = true;
  }

  public void unmarkTask() {
    this.isDone = false;
  }

  public String toString() {
    return "[" + (this.isDone ? "X" : " ") + "] " + this.taskName;
  }

}