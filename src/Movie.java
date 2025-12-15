public class Movie extends Content implements Purchasable, Rentable {
    int runningTime;
    private boolean rented = false;

    public Movie(String title, int price, int runningTime) {
        super(title, price);
        this.runningTime = runningTime;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Running Time: " + runningTime+ "min");
    }

    @Override
    public void buy() {
        System.out.println("[Movie] " + getTitle() + " 구매 완료");
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
