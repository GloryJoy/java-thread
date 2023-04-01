package priority_blocking_queue;

import java.util.concurrent.PriorityBlockingQueue;

class Student implements Comparable<Student>{
    String name;
    int rank;

    public Student(String name, int rank) {
        this.name = name;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rank=" + rank +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public int compareTo(Student o) {
        return this.rank - o.getRank();
    }
}
public class ThreadPriorityBlockingQueue    {
    public static void main(String[] args) {
        PriorityBlockingQueue<Student> priorityBlockingQueue =
                new PriorityBlockingQueue<Student>();
        priorityBlockingQueue.add(new Student("a", 1) );
        priorityBlockingQueue.add(new Student("b", 12));
        priorityBlockingQueue.add(new Student("c", 4));

        System.out.println(priorityBlockingQueue.poll());
        System.out.println(priorityBlockingQueue.poll());
        System.out.println(priorityBlockingQueue.poll());


    }
}
