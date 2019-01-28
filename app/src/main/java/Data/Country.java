package Data;

public class Country {


    private String name;
    private String code;

    public Country(String inputName, String inputCode)
    {
        name = inputName;
        code = inputCode;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

}
