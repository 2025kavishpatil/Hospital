import java.util.Scanner;

class Node {
    String detail;
    int id;
    Node next;
    Node prev;

    public Node(String details, int ids) {
        this.detail = details;
        this.id = ids;
        this.next = null;
        this.prev = null;
    }
}

public class HospitalStack {
    static Node top = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n Hospital Emergency Stack System ");
            System.out.println("1. Insert Patient");
            System.out.println("2. Display All Patients");
            System.out.println("3. Update Patient by ID");
            System.out.println("4. Remove Patient by ID");
            System.out.println("5. Peek Top Patient");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Patient ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Emergency Detail: ");
                    String data = scanner.nextLine();
                    push(data, id);
                    break;

                case 2:
                    System.out.println("\nCurrent Patient Stack:");
                    display();
                    break;

                case 3:
                    System.out.print("Enter ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new Detail: ");
                    String newDetail = scanner.nextLine();
                    update(updateId, newDetail);
                    break;

                case 4:
                    System.out.print("Enter ID to remove: ");
                    int removeId = scanner.nextInt();
                    scanner.nextLine();
                    remove(removeId);
                    break;

                case 5:
                    peek();
                    break;

                case 6:
                    System.out.println("Exiting System.");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 6);

        scanner.close();
    }

    static void push(String data, int id) {
        Node newNode = new Node(data, id);
        if (top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top.prev = newNode;
            top = newNode;
        }
        System.out.println("Patient record inserted.");
    }

    static void display() {
        if (top == null) {
            System.out.println("Stack is empty.");
            return;
        }
        Node current = top;
        while (current != null) {
            System.out.println("ID: " + current.id + " | Detail: " + current.detail);
            current = current.next;
        }
    }

    static void update(int targetId, String newDetail) {
        Node current = top;
        while (current != null) {
            if (current.id == targetId) {
                current.detail = newDetail;
                System.out.println("Updated ID " + targetId);
                return;
            }
            current = current.next;
        }
        System.out.println("ID not found.");
    }

    static void remove(int targetId) {
        if (top == null) {
            System.out.println("List is empty.");
            return;
        }

        Node current = top;

        while (current != null && current.id != targetId) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("ID not found.");
            return;
        }

        if (current == top) {
            top = top.next;
            if (top != null) {
                top.prev = null;
            }
        } else {
            current.prev.next = current.next;
            if (current.next != null) {
                current.next.prev = current.prev;
            }
        }

        System.out.println("Removed Patient ID: " + targetId);
    }

    static void peek() {
        if (top == null) {
            System.out.println("Stack is empty.");
            return;
        }
        System.out.println("Top -> ID: " + top.id + ", Detail: " + top.detail);
    }
}