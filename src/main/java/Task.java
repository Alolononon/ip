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
  
  private String getIsTaskDone() {
    return "[" + (this.isDone ? "X" : " ") + "] ";
  }

  public void markTask() {
    this.isDone = true;
  }

  public void unmarkTask() {
    this.isDone = false;
  }

  public String toString() {
    return this.getIsTaskDone() + this.taskName;
  }

}