import java.util.Scanner;
import java.util.ArrayList;

public class Library {
    // Book class
    public class Book {
        String title;
        String author;
        boolean availability;

        // Constructor
        public Book(String title, String author) {
            this.title = title;
            this.author = author;
            this.availability = true;
        }

        // Display book details
        public void displayBook() {
            String status = availability ? "Available" : "Borrowed";
            System.out.println("  Title: " + title + " | Author: " + author + " | Status: " + status);
        }
    }

    // Student class
    public class Student {
        String stdName;
        String stdID;
        String regNo;
        ArrayList<String> borrowedBooks; // Track borrowed books

        // Constructor
        public Student(String stdName, String stdID, String regNo) {
            this.stdName = stdName;
            this.stdID = stdID;
            this.regNo = regNo;
            this.borrowedBooks = new ArrayList<>();
        }

        // Request a book from library
        public boolean requestBook(String bookTitle, ArrayList<Book> libraryBooks) {
            for (Book book : libraryBooks) {
                if (book.title.equalsIgnoreCase(bookTitle) && book.availability) {
                    book.availability = false;
                    borrowedBooks.add(bookTitle);
                    return true;
                }
            }
            return false;
        }

        // Return a borrowed book
        public boolean returnBook(String bookTitle, ArrayList<Book> libraryBooks) {
            if (borrowedBooks.contains(bookTitle)) {
                for (Book book : libraryBooks) {
                    if (book.title.equalsIgnoreCase(bookTitle)) {
                        book.availability = true;
                        borrowedBooks.remove(bookTitle);
                        return true;
                    }
                }
            }
            return false;
        }

        // Display student info
        public void displayInfo() {
            System.out.println(BLUE + "Name: " + stdName + " | ID: " + stdID + " | Reg#: " + regNo + RESET);
            System.out.println("Borrowed Books: " + borrowedBooks);
        }
    }

    // Librarian class
    public class Librarian {
        String name;
        String empID;

        // Constructor
        public Librarian(String name, String empID) {
            this.name = name;
            this.empID = empID;
        }

        // Add a new book to library
        public void addBook(String title, String author, ArrayList<Book> libraryBooks) {
            Book newBook = new Book(title, author);
            libraryBooks.add(newBook);
            System.out.println(GREEN + "✓ Book added successfully!" + RESET);
        }

        // Remove a book from library
        public boolean removeBook(String bookTitle, ArrayList<Book> libraryBooks) {
            for (Book book : libraryBooks) {
                if (book.title.equalsIgnoreCase(bookTitle)) {
                    libraryBooks.remove(book);
                    return true;
                }
            }
            return false;
        }

        // Display book count
        public void displayBookCount(ArrayList<Book> libraryBooks) {
            int available = 0, borrowed = 0;
            for (Book book : libraryBooks) {
                if (book.availability) {
                    available++;
                } else {
                    borrowed++;
                }
            }
            System.out.println(PURPLE + "Total Books: " + libraryBooks.size() + " | Available: " + available + " | Borrowed: " + borrowed + RESET);
        }

        // Display librarian info
        public void displayInfo() {
            System.out.println(YELLOW + "Librarian: " + name + " | Employee ID: " + empID + RESET);
        }
    }

    // ANSI color codes for terminal colors
    public static final String RESET = "\033[0m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String BOLD = "\033[1m";
    
    static void displayTitle() {
        System.out.println(CYAN + "╔════════════════════════════════════════╗");
        System.out.println("║" + BOLD + "      LIBRARY MANAGEMENT SYSTEM" + RESET + CYAN + "      ║");
        System.out.println("║          Interactive Demo" + RESET + CYAN + "              ║");
        System.out.println("╚════════════════════════════════════════╝" + RESET);
        System.out.println();
    }

    static void displayMenu() {
        System.out.println(BOLD + "\n=== MAIN MENU ===" + RESET);
        System.out.println("1. Student Mode");
        System.out.println("2. Librarian Mode");
        System.out.println("3. View All Books");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    static void studentMenu() {
        System.out.println(BOLD + "\n=== STUDENT MENU ===" + RESET);
        System.out.println("1. Request a Book");
        System.out.println("2. Return a Book");
        System.out.println("3. View My Books");
        System.out.println("4. Back to Main Menu");
        System.out.print("Choose an option: ");
    }

    static void librarianMenu() {
        System.out.println(BOLD + "\n=== LIBRARIAN MENU ===" + RESET);
        System.out.println("1. Add a Book");
        System.out.println("2. Remove a Book");
        System.out.println("3. View Library Stats");
        System.out.println("4. Back to Main Menu");
        System.out.print("Choose an option: ");
    }

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // Create library books
        ArrayList<Book> libraryBooks = new ArrayList<>();
        libraryBooks.add(library.new Book("Java Programming", "Herbert Schildt"));
        libraryBooks.add(library.new Book("Data Structures", "Mark Allen Weiss"));
        libraryBooks.add(library.new Book("Web Development", "Jon Duckett"));
        libraryBooks.add(library.new Book("Algorithms", "Robert Sedgewick"));

        // Create a librarian
        Librarian librarian = library.new Librarian("Mr. John", "LIB001");

        // Create a student (example)
        Student student = null;
        boolean studentCreated = false;

        displayTitle();
        System.out.println(GREEN + "Welcome to the Library Management System!" + RESET);
        System.out.println("This system allows students to request and return books.");
        System.out.println("Librarians can manage the library inventory.\n");

        boolean running = true;
        while (running) {
            displayMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    // Student Mode
                    if (!studentCreated) {
                        System.out.print("\nEnter Student Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Student ID: ");
                        String id = scanner.nextLine();
                        System.out.print("Enter Registration Number: ");
                        String regNo = scanner.nextLine();
                        student = library.new Student(name, id, regNo);
                        studentCreated = true;
                        System.out.println(GREEN + "✓ Student registered!" + RESET);
                    }

                    boolean inStudentMode = true;
                    while (inStudentMode) {
                        studentMenu();
                        String studentChoice = scanner.nextLine().trim();

                        switch (studentChoice) {
                            case "1":
                                // Request a book
                                System.out.println("\n--- Available Books ---");
                                int count = 1;
                                for (Book book : libraryBooks) {
                                    if (book.availability) {
                                        System.out.println(count + ". " + book.title + " by " + book.author);
                                        count++;
                                    }
                                }
                                System.out.print("\nEnter book title to request: ");
                                String bookRequest = scanner.nextLine();
                                if (student.requestBook(bookRequest, libraryBooks)) {
                                    System.out.println(GREEN + "✓ Book issued successfully!" + RESET);
                                } else {
                                    System.out.println(RED + "✗ Book not available or not found!" + RESET);
                                }
                                break;

                            case "2":
                                // Return a book
                                System.out.print("Enter book title to return: ");
                                String bookReturn = scanner.nextLine();
                                if (student.returnBook(bookReturn, libraryBooks)) {
                                    System.out.println(GREEN + "✓ Book returned successfully!" + RESET);
                                } else {
                                    System.out.println(RED + "✗ Book not found in your borrowed list!" + RESET);
                                }
                                break;

                            case "3":
                                // View my books
                                System.out.println();
                                student.displayInfo();
                                break;

                            case "4":
                                // Back to main menu
                                inStudentMode = false;
                                break;

                            default:
                                System.out.println(RED + "Invalid choice!" + RESET);
                        }
                    }
                    break;

                case "2":
                    // Librarian Mode
                    boolean inLibrarianMode = true;
                    while (inLibrarianMode) {
                        librarianMenu();
                        String librarianChoice = scanner.nextLine().trim();

                        switch (librarianChoice) {
                            case "1":
                                // Add a book
                                System.out.print("\nEnter book title: ");
                                String newTitle = scanner.nextLine();
                                System.out.print("Enter author name: ");
                                String newAuthor = scanner.nextLine();
                                librarian.addBook(newTitle, newAuthor, libraryBooks);
                                break;

                            case "2":
                                // Remove a book
                                System.out.print("\nEnter book title to remove: ");
                                String removeTitle = scanner.nextLine();
                                if (librarian.removeBook(removeTitle, libraryBooks)) {
                                    System.out.println(GREEN + "✓ Book removed successfully!" + RESET);
                                } else {
                                    System.out.println(RED + "✗ Book not found!" + RESET);
                                }
                                break;

                            case "3":
                                // View stats
                                System.out.println();
                                librarian.displayInfo();
                                librarian.displayBookCount(libraryBooks);
                                break;

                            case "4":
                                // Back to main menu
                                inLibrarianMode = false;
                                break;

                            default:
                                System.out.println(RED + "Invalid choice!" + RESET);
                        }
                    }
                    break;

                case "3":
                    // View all books
                    System.out.println(BOLD + "\n--- ALL LIBRARY BOOKS ---" + RESET);
                    if (libraryBooks.isEmpty()) {
                        System.out.println("No books in library!");
                    } else {
                        for (int i = 0; i < libraryBooks.size(); i++) {
                            System.out.print((i + 1) + ". ");
                            libraryBooks.get(i).displayBook();
                        }
                    }
                    break;

                case "4":
                    // Exit
                    System.out.println(CYAN + "Thank you for using Library Management System!" + RESET);
                    running = false;
                    break;

                default:
                    System.out.println(RED + "Invalid choice! Please try again." + RESET);
            }
        }

            scanner.close();
        }
    }
