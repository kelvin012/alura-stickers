public class Filme {
    private final String title;
    private final String image;
    private final String imDbRating;

    public Filme(String title, String image, String imDbRating) {
        this.title = title;
        this.image = image;
        this.imDbRating = imDbRating;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getImDbRating() {
        return imDbRating;
    }
}
