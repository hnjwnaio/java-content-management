public class Book extends Content implements Purchasable, Rentable {
    String author;
    private boolean rented = false;

    public Book(String title, int price, String author) {
        super(title, price);
        this.author = author;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Author: " + author);
    }

    @Override
    public void buy() {
        System.out.println("[Book] " + getTitle() + " 구매 완료");
    }

    @Override
    public void rent() {
        if (rented) {
            System.out.println("이미 대여 중입니다.");
            return;
        }
        rented = true;
        System.out.println("[Book] " + getTitle() + " 대여 완료");
    }

    @Override
    public void extendRent() {
        if (!rented) {
            System.out.println("대여 중인 상품이 아닙니다.");
            return;
        }
        System.out.println("대여 기간 연장 완료");
    }
}
