public class Enikiasi {
    private static long count;

    private final Product ENI_PRODUCT;
    private final Catalog CAT;
    private final String[] NAME_PHONE;//Size = 2 .0=name .1=phone
    private final String DATE;
    private final long CODE;
    private final int TIME_ENI;
    private final int COST_ENI_TIME;
    private final int COST_ENI_FULL;
    private final boolean IS_WEEK;

    public Enikiasi(Catalog _cat, Product _eniProduct, String[] _namePhone, String _date, int _timeEni, int _cost, boolean _isWeek) {
        _cat.enikiastike(_eniProduct);
        CAT = _cat;
        ENI_PRODUCT = _eniProduct;
        NAME_PHONE = _namePhone;
        DATE = _date;
        TIME_ENI = _timeEni;
        COST_ENI_TIME = _cost;
        COST_ENI_FULL = _cost * TIME_ENI;
        IS_WEEK = _isWeek;

        CODE = count++;
    }

    public String toString() {
        String text = Product.LINE + "\n";
        text += "Name and phone number: " + NAME_PHONE[0] + ", " + NAME_PHONE[1] + "\n";
        text += "Date enikiasis: " + DATE + "\n";
        if (IS_WEEK) {
            text += "Weeks enikiasis: " + TIME_ENI + "\n";
            text += "Cost per week: " + COST_ENI_TIME + "$\n";
        } else {
            text += "Days enikiasis: " + TIME_ENI + "\n";
            text += "Cost per day: " + COST_ENI_TIME + "$\n";
        }
        return text;
    }

    public Product getEniProduct() {
        return ENI_PRODUCT;
    }

    public long getCode() {
        return CODE;
    }

    public int getCost() {
        return COST_ENI_FULL;
    }

    public Catalog getCat() {
        return CAT;
    }

    public String getNp(){
        return NAME_PHONE[0] + " " + NAME_PHONE[1];
    }

    public String getDate(){
        return DATE;
    }

    public int getTime(){
        return TIME_ENI;
    }

    public int getCostOne(){
        return COST_ENI_TIME;
    }

    public String getWeek(){
        return (IS_WEEK) ? "true" : "false";
    }
}