import java.util.Arrays;
public class Product {
    private final String NAME;
    private final String[] COMPANY;
    private final String PRODUCTION_YEAR;
    private final String[] CATEGORY;

    static final String LINE = "----------------------------------------------------------------------------";

    public Product(String _name, String[] _company, String _productionYear, String[] _category) {
        NAME = _name;
        COMPANY = _company;
        PRODUCTION_YEAR = _productionYear;
        CATEGORY = _category;
    }

    public String toString() {
        String text = LINE + "\n";
        text += "Production company: " + strArray(COMPANY);
        text += "\nProduction year: " + PRODUCTION_YEAR;
        text += "\nCategory: " + strArray(CATEGORY);
        return text;
    }

    String strArray(String[] _array) {
        String text = "";
        for (String i : _array) {
            text += i;
            if (!_array[_array.length-1].equals(i)) text += ", ";
        }
        return text;
    }

    public String getName() {
        return NAME;
    }
}