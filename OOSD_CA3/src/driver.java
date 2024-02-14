
class Repository { // Repository class to store the number

    int num; // Number to be stored

    boolean isPrinted = false; // Boolean to check if the number has been printed (false by default)

    public Repository() { // Constructor
        this.num = 0; // Initialize the number to 0
    }

    public void setNum(int num) { // Setter
        this.num = num;
    }

    public int getNum() { // Getter
        return this.num;
    }
}

class Counter extends Thread { // Counter class
    final int COUNT = 100; // variable to count to (adds a limit to the loop)
    Repository repository; // Repository object

    public Counter(Repository repository) { // Constructor
        this.repository = repository;
    }

    public void run() { // Run method
        try {
            synchronized (repository) { // Synchronize the repository, ensures one thread can access at one time
                for (int i = 0; i <= COUNT; i++) { // Loop from 0 to 999 (prevent infinite loop)
                    repository.setNum(i); // Set the current number to the repository
                    repository.notify(); // Notify the waiting Publisher thread that a new number has been set
                    repository.wait(); // Wait until notified by the Publisher thread

                }
            }
        } catch (InterruptedException e) { // Catch exception
            e.printStackTrace(); // Print the stack trace if interrupted
        }
    }
}

class Publisher extends Thread { // Publisher class
    Repository repository; // Repository object

    public Publisher(Repository repository) { // Constructor
        this.repository = repository;
    }

    public void run() { // Run method
        try {
            synchronized (repository) {
                while (true) { // Infinite loop
                    System.out.println("Repo Number: " + repository.getNum()); // Print the repo number
                    repository.isPrinted = true; // Set the isPrinted boolean to true
                    repository.notify(); // Notify waiting threads
                    repository.wait(); // Wait until notified
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class driver {
    public static void main(String[] args) {

        Repository repository = new Repository(); // Repository instance

        Counter counter = new Counter(repository); // Counter instance

        Publisher publisher = new Publisher(repository); // Publisher instance

        counter.start(); // Start the counter thread
        publisher.start(); // Start the publisher thread
    }
}
