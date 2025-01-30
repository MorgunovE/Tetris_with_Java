import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfTasks = scanner.nextInt();
        int[] taskIds = new int[numberOfTasks];
        int[] taskLoads = new int[numberOfTasks];

        for (int i = 0; i < numberOfTasks; i++) {
            taskIds[i] = scanner.nextInt();
            taskLoads[i] = scanner.nextInt();
        }

        List<Integer> queue1 = new ArrayList<>();
        List<Integer> queue2 = new ArrayList<>();
        int load1 = 0, load2 = 0;

        for (int i = 0; i < numberOfTasks; i++) {
            if (load1 <= load2) {
                queue1.add(taskIds[i]);
                load1 += taskLoads[i];
            } else {
                queue2.add(taskIds[i]);
                load2 += taskLoads[i];
            }
        }

        printQueue(queue1);
        printQueue(queue2);
    }

    private static void printQueue(List<Integer> queue) {
        for (int id : queue) {
            System.out.print(id + " ");
        }
        System.out.println();
    }
}