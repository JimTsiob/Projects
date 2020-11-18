public class Movie extends Product {
    private final String[] SCREEN_WRITER;
    private final String[] STAGE_DIRECTOR;
    private final String[] ACTORS;
    private final String DURATION;
    private final boolean IS_BR;

    private boolean isLatestRelease;

    public Movie(String _name, String[] _company, String _productionYear, String[] _category, String[] _screenWriter, String[] _stageDirector, String[] _actors, String _duration, boolean _isBr, boolean _isLatestRelease) {
        super(_name, _company, _productionYear, _category);
        SCREEN_WRITER = _screenWriter;
        STAGE_DIRECTOR = _stageDirector;
        ACTORS = _actors;
        DURATION = _duration;
        IS_BR = _isBr;
        isLatestRelease = _isLatestRelease;
    }

    public String toString() {
        String text = "";
        text += super.toString() + "\n" + Product.LINE;
        text += "\nScreen Writer: " + strArray(SCREEN_WRITER);
        text += "\nStage Director: " + strArray(STAGE_DIRECTOR);
        text += "\nActors: " + strArray(ACTORS);
        text += "\nDuration: " + DURATION;
        return text;
    }

    public boolean isBr() {
        return IS_BR;
    }

    public boolean isLatestRelease() {
        return isLatestRelease;
    }
}