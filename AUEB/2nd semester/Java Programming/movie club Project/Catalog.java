import java.util.ArrayList;
public class Catalog {
    private ArrayList<Product> logP = new ArrayList<>();
    private ArrayList<Integer> logC = new ArrayList<>();

    public void add(Product p, int count) {
        logP.add(p);
        logC.add(count);
    }

    public Product get(int i) {
        return logP.get(i);
    }

    public boolean has(Product p) {
        if (logP.contains(p)) {
            int pos = logP.indexOf(p);
            if (logC.get(pos) > 0) return true;
        }
        return false;
    }

    public ArrayList<Product> getArray() {
        return logP;
    }

    public void enikiastike(Product p) {
        int pos = logP.indexOf(p);
        logC.set(pos, logC.get(pos)-1);
    }

    public void returned(Product p) {
        int pos = logP.indexOf(p);
        logC.set(pos, logC.get(pos)+1);
    }
}
