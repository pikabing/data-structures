public class UnionFind {

    private int size;
    private int[] cost;
    private int[] parent;
    private int numComponents;

    public UnionFind(int size) {
        if(size <= 0) throw new IllegalArgumentException("size cannot be <= 0");

        this.size = numComponents = size;
        cost = new int[size];
        parent = new int[size];

        for(int i=0; i<size;i++) {
            parent[i] = i;
            cost[i] = 1;
        }
    }

    public int find(int p) {
        int root = p;
        while(root != parent[root]) root = parent[root];

        // Path compression
        while(p != root) {
            int next = parent[p];
            parent[p] = root;
            p = next;
        }

        return root;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int componentSize(int p) {
        return cost[find(p)];
    }

    public int size() {return size;}

    public int components() {return numComponents;}

    public void unify(int p, int q) {
        int root1 = find(p);
        int root2 = find(q);

        if(root1 == root2) return;

        if(cost[root1] < cost[root2]) {

            cost[root2] += cost[root1];
            parent[root1] = root2;

        } else {

            cost[root1] += cost[root2];
            parent[root2] = root1;

        }

        numComponents--;
    }
}