class Repository { // Repository class to store the number
    int num; // Number to be stored

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
    Repository repository; // Repository object

    public Counter(Repository repository) { // Constructor
        this.repository = repository;
    }

    public void run() { // Run method (Counts to 999 for example)
        try {
            for (int i = 0; i <= 999; i++) {
                Thread.sleep(1000); // Sleep for 1 second
                repository.setNum(i); // Add the number to the repository
            }
        } catch (InterruptedException e) { // Catch the exception
            e.printStackTrace();
        }
    }
}

class Publisher extends Thread { // Publisher class
    Repository repository; // Repository object

    public Publisher(Repository repository) { // Constructor
        this.repository = repository;
    }

    public void run() { // Run method
        while (true) { // Infinite loop
            System.out.println("Number in Repository: " + repository.getNum()); // Print the number in the repository
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

// This could be better by using a single thread and a timer
// OR
// By using synchronized methods and wait/notify
