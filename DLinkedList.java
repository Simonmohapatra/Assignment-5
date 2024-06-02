import java.util.Scanner;
class Node{
    protected int regd_no;
    protected float mark;
    protected Node next;
    protected Node prev;

    Node(int regd_no,float mark){
        this.regd_no = regd_no;
        this.mark = mark;
        this.next = null;
        this.prev=null;
    }
}

public class assignment_5 {
    public static Node create(Node start, Node end) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter registration number: ");
        int regd_no = sc.nextInt();
        System.out.println("Enter marks: ");
        float mark = sc.nextFloat();
        Node newNode = new Node(regd_no, mark);

        if (start == null) {
            start = newNode;
            end = newNode;
        } else {
            end.next = newNode;
            newNode.prev = end;
            end = newNode;
        }
        return end;
    }

    public static void display(Node start) {
        Node current = start;
        while (current != null) {
            System.out.println("Regd No: " + current.regd_no + ", Marks: " + current.mark);
            current = current.next;
        }
    }

    public static Node insBeg(Node start, Node end) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter registration number: ");
        int regd_no = sc.nextInt();
        System.out.println("Enter marks: ");
        float mark = sc.nextFloat();
        Node newNode = new Node(regd_no, mark);

        if (start == null) {
            start = newNode;
            end = newNode;
        } else {
            newNode.next = start;
            start.prev = newNode;
            start = newNode;
        }
        return start;
    }

    public static Node insEnd(Node start, Node end) {
        return create(start, end);
    }

    public static Node insAny(Node start, Node end) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter position: ");
        int position = sc.nextInt();
        if (position == 1) {
            start = insBeg(start, end);
        } else {
            Node current = start;
            for (int i = 1; i < position - 1 && current != null; i++) {
                current = current.next;
            }
            if (current == null || current.next == null) {
                end = insEnd(start, end);
            } else {
                System.out.println("Enter registration number: ");
                int regd_no = sc.nextInt();
                System.out.println("Enter marks: ");
                float mark = sc.nextFloat();
                Node newNode = new Node(regd_no, mark);
                newNode.next = current.next;
                newNode.prev = current;
                current.next.prev = newNode;
                current.next = newNode;
            }
        }
        return start;
    }

    public static Node delBeg(Node start, Node end) {
        if (start == null) {
            System.out.println("List is empty");
        } else {
            start = start.next;
            if (start != null) {
                start.prev = null;
            } else {
                end = null;
            }
        }
        return start;
    }

    public static Node delEnd(Node start, Node end) {
        if (end == null) {
            System.out.println("List is empty");
        } else {
            end = end.prev;
            if (end != null) {
                end.next = null;
            } else {
                start = null;
            }
        }
        return end;
    }

    public static Node delAny(Node start, Node end) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter position: ");
        int position = sc.nextInt();
        if (position == 1) {
            start = delBeg(start, end);
        } else {
            Node current = start;
            for (int i = 1; i < position && current != null; i++) {
                current = current.next;
            }
            if (current == null || current.prev == null) {
                System.out.println("Invalid position");
            } else {
                current.prev.next = current.next;
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    end = current.prev;
                }
            }
        }
        return start;
    }

    public static void search(Node start) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter registration number to search: ");
        int regd_no = sc.nextInt();
        Node current = start;
        boolean found = false;

        while (current != null) {
            if (current.regd_no == regd_no) {
                found = true;
                System.out.println("Enter new marks: ");
                float newMark = sc.nextFloat();
                current.mark = newMark;
                System.out.println("Marks updated.");
                break;
            }
            current = current.next;
        }

        if (!found) {
            System.out.println("Node not found.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Node start = null;
        Node end = null;

        while (true) {
            System.out.println("****MENU*****");
            System.out.println("0: Exit");
            System.out.println("1: Creation");
            System.out.println("2: Display");
            System.out.println("3: Insert at beginning");
            System.out.println("4: Insert at end");
            System.out.println("5: Insert at any position");
            System.out.println("6: Delete from beginning");
            System.out.println("7: Delete from end");
            System.out.println("8: Delete from any position");
            System.out.println("9: Search and update");
            System.out.println("Enter your choice");
            int choice = sc.nextInt();

            switch (choice) {
                case 0:
                    System.exit(0);
                case 1:
                    end = create(start, end);
                    if (start == null) {
                        start = end;
                    }
                    break;
                case 2:
                    display(start);
                    break;
                case 3:
                    start = insBeg(start, end);
                    if (end == null) {
                        end = start;
                    }
                    break;
                case 4:
                    end = insEnd(start, end);
                    if (start == null) {
                        start = end;
                    }
                    break;
                case 5:
                    start = insAny(start, end);
                    break;
                case 6:
                    start = delBeg(start, end);
                    if (start == null) {
                        end = null;
                    }
                    break;
                case 7:
                    end = delEnd(start, end);
                    if (end == null) {
                        start = null;
                    }
                    break;
                case 8:
                    start = delAny(start, end);
                    break;
                case 9:
                    search(start);
                    break;
                default:
                    System.out.println("Wrong choice");
            }
        }
    }
}
