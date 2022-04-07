package dtos;

public class JokeDTO {
    private String value;
    private String url;

    public JokeDTO(String value, String url) {
        this.value = value;
        this.url = url;
    }

    public String getValue() {
        return value;
    }

    public String getUrl() {
        return url;
    }
}
