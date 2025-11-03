package training.iqgateway;

public class LanguageBean {

    private String name;
    private String language;

    public LanguageBean() {

    }

    public LanguageBean(String name, String language) {
        super();
        this.name = name;
        this.language = language;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public String getLanguageComments() {
        if (language.equals("Java")) {
            return " The King of OO Language ";
        } else if (language.equals("C++")) {
            return " Rather TOO Complex but Called as OO Language ";
        } else if (language.equals("Perl")) {
            return " Ok if you like incomprehensible Code.";
        } else {
            return "sorry I Have never heard of this Language....";
        }
    }

}

