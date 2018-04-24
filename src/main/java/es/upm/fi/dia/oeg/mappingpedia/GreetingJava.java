package es.upm.fi.dia.oeg.mappingpedia;

public class GreetingJava {
    Long id = null;
    String content = null;

    public GreetingJava(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Long getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }
}
