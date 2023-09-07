public class Person {
    private String name;
    private String surName;
    private String eMail;
    public Person() {
    }

    public Person(String name, String surName, String eMail) {
        this.setName(name);
        this.setSurName(surName);
        this.seteMail(eMail);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }


}
