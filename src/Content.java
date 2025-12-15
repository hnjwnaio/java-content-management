public class Content {
    protected String title;
    protected int price;

    public Content(String title, int price) {
        this.title = title;
        // this.price = price;
        setPrice(price);
    }

    public void showInfo() {
        System.out.println("Title: " + title + " / Price: " + price);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("가격은 음수가 될 수 없습니다.");
        }
        this.price = price;
    }
}
