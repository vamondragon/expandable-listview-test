package me.appdemo.DataModel;


public class Artist {
    private Number id;
    private String name;
    private String type;

    public Artist(Number id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
