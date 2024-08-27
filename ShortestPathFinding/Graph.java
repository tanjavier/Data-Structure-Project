
import java.util.*;

class Graph {
    private Map<String, Map<String, Integer>> vertices;

    public Graph() {
        vertices = new HashMap<>();
    }

    public boolean containsVertex(String vertex) {
        return vertices.containsKey(vertex);
    }

    public void displayGraph() {
        if (vertices.isEmpty()) {
            System.out.println("\nThe graph is empty.");
            return;
        }
        System.out.println("\nGraph:");
        for (String vertex : vertices.keySet()) {
            System.out.print(vertex + " -> ");
            Map<String, Integer> neighbors = vertices.get(vertex);
            for (String neighbor : neighbors.keySet()) {
                int weight = neighbors.get(neighbor);
                System.out.print(neighbor + "(" + weight + ") ");
            }
            System.out.println();
        }
    }

    public void addVertex(String vertex) {
        if (vertex.trim().isEmpty()) {
            throw new IllegalArgumentException("\nVertex cannot be empty.");
        }

        vertices.put(vertex, new HashMap<>());
    }

    public void addEdge(String source, String destination, int weight) {
        if (!vertices.containsKey(source)) {
            throw new IllegalArgumentException("Source vertex does not exist.");
        }

        if (!vertices.containsKey(destination)) {
            throw new IllegalArgumentException("Destination vertex does not exist.");
        }

        if (weight <= 0) {
            throw new IllegalArgumentException("Invalid input. Please input a number more than zero.");
        }

        vertices.get(source).put(destination, weight);
        vertices.get(destination).put(source, weight);
    }

    public void deleteVertex(String vertex) {
        if (vertex.trim().isEmpty()) {
        throw new IllegalArgumentException("\nVertex cannot be empty.");
        }
        
        if (!vertices.containsKey(vertex)) {
            throw new IllegalArgumentException("\nVertex does not exist.");
        }

        vertices.remove(vertex);
        for (Map.Entry<String, Map<String, Integer>> entry : vertices.entrySet()) {
            entry.getValue().remove(vertex);
        }
    }

    public void deleteEdge(String source, String destination) {
        if (!vertices.containsKey(source)) {
            throw new IllegalArgumentException("Source vertex does not exist.");
        }

        if (!vertices.containsKey(destination)) {
            throw new IllegalArgumentException("Destination vertex does not exist.");
        }
        
        vertices.get(source).remove(destination);
        vertices.get(destination).remove(source);
    }

    public static class ShortestPathResult {
        private List<String> path;
        private int cost;

        public ShortestPathResult(List<String> path, int cost) {
            this.path = path;
            this.cost = cost;
        }

        public List<String> getPath() {
            return path;
        }

        public int getCost() {
            return cost;
        }
    }

    public ShortestPathResult findShortestPath(String startVertex, String endVertex) {
        if (vertices.isEmpty()) {
            throw new IllegalStateException("\nThe graph is empty.");
        }

        if (!vertices.containsKey(startVertex)) {
            throw new IllegalArgumentException("\nStarting vertex does not exist.");
        }

        if (!vertices.containsKey(endVertex)) {
            throw new IllegalArgumentException("\nEnding vertex does not exist.");
        }

        if (!hasEdges(startVertex) || !hasEdges(endVertex)) {
            throw new IllegalArgumentException("\nThere are no edges between the specified vertices.");
        }

        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();

        final int INFINITY = Integer.MAX_VALUE;

        for (String vertex : vertices.keySet()) {
            if (vertex.equals(startVertex)) {
                distances.put(vertex, 0);
                priorityQueue.add(new Vertex(vertex, 0));
            } else {
                distances.put(vertex, INFINITY);
                priorityQueue.add(new Vertex(vertex, INFINITY));
            }

            previous.put(vertex, null);
        }

        while (!priorityQueue.isEmpty()) {
            String currentVertex = priorityQueue.poll().vertex;

            if (currentVertex.equals(endVertex)) {
                break;
            }

            Map<String, Integer> neighbors = vertices.get(currentVertex);
            for (String neighbor : neighbors.keySet()) {
                int weight = neighbors.get(neighbor);
                int distance = distances.get(currentVertex) + weight;
                if (distance < distances.get(neighbor)) {
                    priorityQueue.remove(new Vertex(neighbor, distances.get(neighbor)));
                    distances.put(neighbor, distance);
                    priorityQueue.add(new Vertex(neighbor, distance));
                    previous.put(neighbor, currentVertex);
                }
            }
        }

        Deque<String> path = new LinkedList<>();
        String current = endVertex;
        while (current != null) {
            path.addFirst(current);
            current = previous.get(current);
        }

        int shortestPathCost = distances.get(endVertex);

        return new ShortestPathResult(new ArrayList<>(path), shortestPathCost);
    }

    private boolean hasEdges(String vertex) {
        Map<String, Integer> neighbors = vertices.get(vertex);
        return neighbors != null && !neighbors.isEmpty();
    }

    public void resetGraph() {
        vertices.clear();
    }
}