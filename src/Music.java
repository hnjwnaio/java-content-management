public class Music extends Content implements Purchasable {
    String singer;

    public Music(String title, int price, String singer) {
        super(title, price);
        this.singer = singer;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Singer: " + singer);
    }

    @Override
    public void buy() {
        System.out.println("[Music] " + getTitle() + " 구매 완료");
    }
}
