package me.appdemo.DataModel;



public class Torrents {
    private String description;
    private String encoding;


    public Torrents() {
    }

    public Torrents(String description, String encoding) {
        this.description = description;
        this.encoding = encoding;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
