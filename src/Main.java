import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Content findContent(
            ArrayList<Content> contents,
            int type,
            String title
    ) {
        for (Content c : contents) {
            boolean typeMatch =
                    (type == 1 && c instanceof Book) ||
                            (type == 2 && c instanceof Movie) ||
                            (type == 3 && c instanceof Music);

            if (typeMatch && c.getTitle().equals(title)) {
                return c;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Content> contents = new ArrayList<>();

        while (true) {
            System.out.println("=== 콘텐츠 추가 ===");
            System.out.println("1. Book");
            System.out.println("2. Movie");
            System.out.println("3. Music");
            System.out.println("0. 종료");
            System.out.print("선택: ");
            String input = scanner.nextLine();

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요.\n");
                continue;
            }

            if (choice < 0 || choice > 3) {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.\n");
                continue;
            }

            if (choice == 0) break;

            System.out.print("제목: ");
            String title = scanner.nextLine();

            System.out.print("가격: ");
            int price;
            while (true) {
                String priceInput = scanner.nextLine();
                try {
                    price = Integer.parseInt(priceInput);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("가격은 숫자로 입력해주세요.");
                }
            }

            switch (choice) {
                case 1:
                    System.out.print("저자: ");
                    String author = scanner.nextLine();
                    try {
                        contents.add(new Book(title, price, author));
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("상영 시간(분): ");
                    int runtime = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        contents.add(new Movie(title, price, runtime));
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 3:
                    System.out.print("아티스트: ");
                    String artist = scanner.nextLine();
                    try {
                        contents.add(new Music(title, price, artist));
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                default:
                    System.out.println("잘못된 선택입니다.");
            }
            System.out.println();
        }

        System.out.println("\n=== 콘텐츠 목록 ===");
        for(Content c : contents) {
            c.showInfo();
        }

        System.out.println();

        while (true) {
            System.out.println("=== 구매 / 대여 ===");
            System.out.println("1. Book");
            System.out.println("2. Movie");
            System.out.println("3. Music");
            System.out.println("0. 종료");
            System.out.print("선택: ");

            String input = scanner.nextLine();
            int type;

            try {
                type = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다.\n");
                continue;
            }

            if (type == 0) break;
            if (type < 1 || type > 3) {
                System.out.println("잘못된 입력입니다.\n");
                continue;
            }

            System.out.print("제목 입력: ");
            String title = scanner.nextLine();

            Content target = findContent(contents, type, title);

            if (target == null) {
                System.out.println("해당 콘텐츠를 찾을 수 없습니다.\n");
                continue;
            }

            System.out.println("1. 구매");
            System.out.println("2. 대여");
            System.out.println("3. 대여 기간 연장");
            System.out.print("선택: ");

            String action = scanner.nextLine();

            switch (action) {
                case "1":
                    if (target instanceof Purchasable) {
                        ((Purchasable) target).buy();
                    } else {
                        System.out.println("구매할 수 없는 콘텐츠입니다.");
                    }
                    break;

                case "2":
                    if (target instanceof Rentable) {
                        ((Rentable) target).rent();
                    } else {
                        System.out.println("대여할 수 없는 콘텐츠입니다.");
                    }
                    break;

                case "3":
                    if (target instanceof Rentable) {
                        ((Rentable) target).extendRent();
                    } else {
                        System.out.println("대여 연장이 불가능한 콘텐츠입니다.");
                    }
                    break;

                default:
                    System.out.println("잘못된 선택입니다.");
            }

            System.out.println();
        }

        scanner.close();
    }
}