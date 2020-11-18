import java.util.ArrayList;
public class CatalogEni {
    private ArrayList<Enikiasi> logEni = new ArrayList<>();

    public void add(Enikiasi eni) {
        logEni.add(eni);
    }

    public Enikiasi get(int i) {
        return logEni.get(i);
    }

    public ArrayList<Enikiasi> getArray() {
        return logEni;
    }

    public void remove(Enikiasi eni) {
        logEni.remove(eni);
        eni.getCat().returned(eni.getEniProduct());
    }
}