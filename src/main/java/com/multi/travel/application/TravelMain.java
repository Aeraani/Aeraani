package com.multi.travel.application;


import com.multi.travel.model.bean.TravelVO;
import com.multi.travel.model.service.TravelService;
import com.multi.travel.model.service.TravelServiceImpl;

import java.util.List;
import java.util.Scanner;
import static java.lang.System.in;

public class TravelMain {
    private static final TravelService travelService = TravelServiceImpl.getTravelService();
    private static Scanner sc = new Scanner(in);

    public static void main(String[] args) {
        System.out.println("------- 관광지 정보 시스템 --------");
        while (true) {
            menu();
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    travelByPage();
                    break;
                case 2:
                    travelByDistrict();
                    break;
                case 3:
                    travelByTitle();
                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("0부터 3까지의 숫자 중 선택해주세요.");
            }
        }
    }

    private static void menu() {
        System.out.println("1. 전체 목록 페이지 별로 보기");
        System.out.println("2. 권역별 관광지 목록 보기(권역:수도권, 충청권, 전라권, 경상권, 강원권, 제주권)");
        System.out.println("3. 검색별 관광지 목록 보기(장소명 검색)");
        System.out.println("0. 프로그램 종료");
        System.out.println("-------------------------------------");
        System.out.print("메뉴 선택 : ");
    }

    private static void travelByPage() {
        int total = travelService.countAll();
        final int pageSize = 10; // 한 페이지 10개 고정
        int totalPages = (total + pageSize - 1) / pageSize;

        System.out.println("총 " + totalPages + "개의 페이지");
        if (total == 0) {
            System.out.println("(데이터가 없습니다)");
            return;
        }
        System.out.println("페이지 번호를 입력하세요 (1~" + totalPages + ") : ");
        int page = sc.nextInt();
        sc.nextLine();

        if (page < 1 || page > totalPages) {
            System.out.println("페이지 번호를 다시 입력하세요");
            return;
        }
        while (true) {
            List<TravelVO> rows = travelService.searchListAll(page, pageSize);

            System.out.printf("페이지 %d / %d (총 %d 건)%n", page, totalPages, total);
            System.out.printf("%s | %s | %s | %s | %s%n", "번호", "권역", "장소명", "주소", "전화번호");
            System.out.println("----------------------------------");
            for (TravelVO vo : rows) {

                System.out.printf("%-4d | %-5s | %-10s | %-20s | %-15s%n",
                        vo.getNo(),
                        vo.getDistrict(),
                        vo.getTitle(),
                        vo.getAddress(),
                        vo.getPhone()
                );
                System.out.printf("- description: %s\n\n", vo.getDescription());
            }

            System.out.print("[n]다음 [p]이전 [q]종료 : ");
            String cmd = sc.nextLine().trim();

            if (cmd.equalsIgnoreCase("q")) break;
            else if (cmd.equalsIgnoreCase("n")) {
                if (page < totalPages) page++;
                else System.out.println("마지막 페이지입니다.");
            } else if (cmd.equalsIgnoreCase("p")) {
                if (page > 1) page--;
                else System.out.println("첫 페이지입니다.");
            }
        }
    }

    private static void travelByDistrict() {
        String[] districts = {"수도권", "충청권", "전라권", "경상권", "강원권", "제주권"};
        System.out.println("사용 가능한 권역:");
        for (int i = 0; i < districts.length; i++) {
            System.out.printf("%d. %s\n", i + 1, districts[i]);
        }
        System.out.print("\n권역 번호를 선택하세요 (1-" + districts.length + "): ");
        int choice = sc.nextInt();
        sc.nextLine(); // 개행 문자 제거
        if (choice < 1 || choice > districts.length) {
            System.out.println("잘못된 번호입니다.");
            return;
        }
        String district = districts[choice - 1]; // 선택한 권역명
        int total = travelService.countByDistrict(district);
        final int pageSize = 10;
        int totalPages = (total + pageSize - 1) / pageSize;
        System.out.println("총 " + totalPages + "개의 페이지");
        if (total == 0) {
            System.out.println("(해당 권역 데이터가 없습니다)");
            return;
        }
        System.out.print("페이지 번호를 입력하세요 (1~" + totalPages + ") : ");
        int page = sc.nextInt();
        sc.nextLine();
        if (page < 1 || page > totalPages) {
            System.out.println("페이지 번호를 다시 입력하세요");
            return;
        }
        while (true) {
            List<TravelVO> rows = travelService.searchListByDistrict(district, page, pageSize);
            System.out.printf("페이지 %d / %d (총 %d 건) [권역: %s]%n", page, totalPages, total, district);
            System.out.printf("%s | %s | %s | %s | %s%n", "번호", "권역", "장소명", "주소", "전화번호");
            System.out.println("----------------------------------");
            for (TravelVO vo : rows) {
                System.out.printf("%-4d | %-5s | %-10s | %-20s | %-15s%n",
                        vo.getNo(),
                        vo.getDistrict(),
                        vo.getTitle(),
                        vo.getAddress(),
                        vo.getPhone()
                );
                System.out.printf("- description: %s\n\n", vo.getDescription());
            }
            System.out.print("[n]다음 [p]이전 [q]종료 : ");
            String cmd = sc.nextLine().trim();
            if (cmd.equalsIgnoreCase("q")) {
                break;
            }else if (cmd.equalsIgnoreCase("n")) {
                if (page < totalPages) page++;
                else System.out.println("마지막 페이지입니다.");
            } else if (cmd.equalsIgnoreCase("p")) {
                if (page > 1) page--;
                else System.out.println("첫 페이지입니다.");
            }
        }
    }

    private static void travelByTitle() {
        System.out.println("장소명 검색: ");
        String title = sc.nextLine().trim();
        int total = travelService.countByTitle(title);
        final int pageSize = 10;
        int totalPages = (total + pageSize - 1) / pageSize;
        System.out.println("총 " + totalPages + "개의 페이지");
        if (total == 0) {
            System.out.println("(해당 장소명 데이터가 없습니다)");
            return;
        }
        System.out.print("페이지 번호를 입력하세요 (1~" + totalPages + ") : ");
        int page = sc.nextInt();
        sc.nextLine();
        if (page < 1 || page > totalPages) {
            System.out.println("페이지 번호를 다시 입력하세요");
            return;
        }

        while (true) {
            List<TravelVO> rows = travelService.searchListByTitle(title, page, pageSize);

            System.out.printf("페이지 %d / %d (총 %d 건) [관광지명: %s]%n", page, totalPages, total, title);
            System.out.printf("%s | %s | %s | %s | %s%n", "번호", "권역", "장소명", "주소", "전화번호");
            System.out.println("----------------------------------");
            for (TravelVO vo : rows) {
                System.out.printf("%-4d | %-5s | %-10s | %-20s | %-15s%n",
                        vo.getNo(),
                        vo.getDistrict(),
                        vo.getTitle(),
                        vo.getAddress(),
                        vo.getPhone()
                );
                System.out.printf("- description: %s\n\n", vo.getDescription());
            }
            System.out.print("[n]다음 [p]이전 [q]종료 : ");
            String cmd = sc.nextLine().trim();

            if (cmd.equalsIgnoreCase("q")) {
                break;
            }
            else if (cmd.equalsIgnoreCase("n")) {
                if (page < totalPages) page++;
                else System.out.println("마지막 페이지입니다.");
            } else if (cmd.equalsIgnoreCase("p")) {
                if (page > 1) page--;
                else System.out.println("첫 페이지입니다.");
            }
        }
    }
}





