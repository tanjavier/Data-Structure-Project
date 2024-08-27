
class Vertex implements Comparable<Vertex> {
    public String vertex;
    public int distance;

    public Vertex(String vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    @Override
    public int compareTo(Vertex other) {
        return Integer.compare(distance, other.distance);
    }
}