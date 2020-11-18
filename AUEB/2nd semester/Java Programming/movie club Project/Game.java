public class Game extends Product{
    private final String PLATFORM;

    public Game(String _name, String[] _company, String _productionYear, String[] _category, String _platform) {
        super(_name, _company, _productionYear, _category);
        PLATFORM = _platform;
    }

    public String getPlatform() {
        return PLATFORM;
    }
}