import java.util.LinkedList;
import java.util.Queue;

import java.util.LinkedList;
import java.util.Queue;

class Animal {
    private String name;
    private int order;  // To keep track of arrival order

    public Animal(String name) {
        this.name = name;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getOrder() {
        return this.order;
    }

    public String getName() {
        return this.name;
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
}

class AnimalShelter {
    private Queue<Dog> dogQueue;
    private Queue<Cat> catQueue;
    private int order; // Global counter to track the order of animals

    public AnimalShelter() {
        dogQueue = new LinkedList<>();
        catQueue = new LinkedList<>();
        order = 0;
    }

    // Enqueue a dog
    public void enqueue(Dog dog) {
        dog.setOrder(order++);
        dogQueue.add(dog);
    }

    // Enqueue a cat
    public void enqueue(Cat cat) {
        cat.setOrder(order++);
        catQueue.add(cat);
    }

    // Dequeue any animal (the oldest among cats and dogs)
    public Animal dequeueAny() {
        if (dogQueue.isEmpty()) {
            return dequeueCat();
        } else if (catQueue.isEmpty()) {
            return dequeueDog();
        }

        // Compare order of the oldest dog and cat
        Dog oldestDog = dogQueue.peek();
        Cat oldestCat = catQueue.peek();

        if (oldestDog.getOrder() < oldestCat.getOrder()) {
            return dequeueDog();
        } else {
            return dequeueCat();
        }
    }

    // Dequeue the oldest dog
    public Dog dequeueDog() {
        return dogQueue.poll();
    }

    // Dequeue the oldest cat
    public Cat dequeueCat() {
        return catQueue.poll();
    }

    // Check if shelter is empty
    public boolean isEmpty() {
        return dogQueue.isEmpty() && catQueue.isEmpty();
    }
}

 class Main {
    public static void main(String[] args) {
        AnimalShelter shelter = new AnimalShelter();

        shelter.enqueue(new Dog("Princess"));
        shelter.enqueue(new Cat("Alice"));
        shelter.enqueue(new Dog("Elizabeth"));
        shelter.enqueue(new Cat("Bowie"));

        System.out.println("Dequeued Any: " + shelter.dequeueAny().getName()); // Should dequeue Buddy (the oldest)
        System.out.println("Dequeued Cat: " + shelter.dequeueCat().getName()); // Should dequeue Whiskers
        System.out.println("Dequeued Dog: " + shelter.dequeueDog().getName()); // Should dequeue Rover
    }
}

