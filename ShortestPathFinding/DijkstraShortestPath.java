
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class DijkstraShortestPath {
    public static void main(String[] args) {
        Graph graph = new Graph();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Display graph");
            System.out.println("2. Add vertex");
            System.out.println("3. Add edge");
            System.out.println("4. Delete vertex");
            System.out.println("5. Delete edge");
            System.out.println("6. Read from text file");
            System.out.println("7. Find shortest path");
            System.out.println("8. Reset graph");
            System.out.println("9. Quit");
            System.out.print("Enter your choice (1-9): ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        graph.displayGraph();
                        break;
                    case 2:
                        System.out.print("Enter vertex: ");
                        String vertex = scanner.nextLine();
                        try {
                            graph.addVertex(vertex);
                            System.out.println("\nVertex added successfully.");
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.print("Enter source vertex: ");
                        String source = scanner.nextLine();
                        System.out.print("Enter destination vertex: ");
                        String destination = scanner.nextLine();
                        System.out.print("Enter weight: ");
                        int weight = Integer.parseInt(scanner.nextLine());
                        try {
                            graph.addEdge(source, destination, weight);
                            System.out.println("\nEdge added successfully.");
                        } catch (IllegalArgumentException e) {
                            System.out.println();
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 4:
                        System.out.print("Enter vertex to delete: ");
                        vertex = scanner.nextLine();
                        try {
                            graph.deleteVertex(vertex);
                            System.out.println("\nVertex deleted successfully.");
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 5:
                        System.out.print("Enter source vertex: ");
                        source = scanner.nextLine();
                        System.out.print("Enter destination vertex: ");
                        destination = scanner.nextLine();
                        try {
                            graph.deleteEdge(source, destination);
                            System.out.println("\nEdge deleted successfully.");
                        } catch (IllegalArgumentException e) {
                            System.out.println();
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 6:
                        System.out.print("Enter file path: ");
                        String filePath = scanner.nextLine();
                        try {
                            readFromFile(graph, filePath);
                        } catch (FileNotFoundException e) {
                            System.out.println("\nFile not found. Please enter a valid file path.");
                        }
                        break;
                    case 7:
                        System.out.print("Enter starting vertex: ");
                        String startVertex = scanner.nextLine();
                        System.out.print("Enter ending vertex: ");
                        String endVertex = scanner.nextLine();
                        try {
                            Graph.ShortestPathResult result = graph.findShortestPath(startVertex, endVertex);
                            List<String> shortestPath = result.getPath();
                            int shortestPathCost = result.getCost();
                            System.out.println("\nShortest path: " + String.join(" -> ", shortestPath));
                            System.out.println("Total path cost: " + shortestPathCost);
                        } catch (IllegalStateException e) {
                            System.out.println(e.getMessage());
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 8:
                        graph.resetGraph();
                        System.out.println("\nGraph reset successfully.");
                        break;
                    case 9:
                        System.out.print("\nQuitiing...");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("\nInvalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input. Please enter a number.");
            }
        }
    }

    private static void readFromFile(Graph graph, String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        
        System.out.println("\nGraph data read from file successfully.");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            if (parts.length == 3) {
                String source = parts[0];
                String destination = parts[1];
                int weight = Integer.parseInt(parts[2]);
                if (!graph.containsVertex(source)) {
                    graph.addVertex(source);
                }
                if (!graph.containsVertex(destination)) {
                    graph.addVertex(destination);
                }
                graph.addEdge(source, destination, weight);
            } else {
                System.out.println("Invalid line format: " + line);
                break;
            }
        }
        scanner.close();
    }
}