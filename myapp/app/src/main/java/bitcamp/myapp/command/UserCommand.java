package bitcamp.myapp.command;

import bitcamp.myapp.util.LinkedList;
import bitcamp.myapp.util.Prompt;
import bitcamp.myapp.vo.User;

public class UserCommand implements Command {

    String menuTitle;
    String[] menus = {"등록", "목록", "조회", "변경", "삭제"};

    LinkedList userList = new LinkedList();

    public UserCommand(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public void execute() {
        printMenus();

        while (true) {
            String command = Prompt.input(String.format("메인/%s>", menuTitle));
            if (command.equals("menu")) {
                printMenus();
                continue;
            } else if (command.equals("9")) { // 이전 메뉴 선택
                return;
            }

            try {
                int menuNo = Integer.parseInt(command);
                String menuName = getMenuTitle(menuNo);
                if (menuName == null) {
                    System.out.println("유효한 메뉴 번호가 아닙니다.");
                    continue;
                }

                processMenu(menuName);

            } catch (NumberFormatException ex) {
                System.out.println("숫자로 메뉴 번호를 입력하세요.");
            }
        }
    }

    private void processMenu(String menuName) {
        System.out.printf("[%s]\n", menuName);
        switch (menuName) {
            case "등록":
                this.addUser();
                break;
            case "조회":
                this.viewUser();
                break;
            case "목록":
                this.listUser();
                break;
            case "변경":
                this.updateUser();
                break;
            case "삭제":
                this.deleteUser();
                break;
        }
    }

    private String getMenuTitle(int menuNo) {
        return isValidateMenu(menuNo) ? menus[menuNo - 1] : null;
    }

    private boolean isValidateMenu(int menuNo) {
        return menuNo >= 1 && menuNo <= menus.length;
    }

    private void printMenus() {
        System.out.printf("[%s]\n", menuTitle);
        for (int i = 0; i < menus.length; i++) {
            System.out.printf("%d. %s\n", (i + 1), menus[i]);
        }
        System.out.println("9. 이전");
    }

    private void addUser() {
        User user = new User();
        user.setName(Prompt.input("이름?"));
        user.setEmail(Prompt.input("이메일?"));
        user.setPassword(Prompt.input("암호?"));
        user.setTel(Prompt.input("연락처?"));
        user.setNo(User.getNextSeqNo());
        userList.add(user);
    }

    private void listUser() {
        System.out.println("번호 이름 이메일");
        for (Object obj : userList.toArray()) {
            User user = (User) obj;
            System.out.printf("%d %s %s\n", user.getNo(), user.getName(), user.getEmail());
        }
    }

    private void viewUser() {
        int userNo = Prompt.inputInt("회원번호?");
        User user = (User) userList.get(userList.indexOf(new User(userNo)));
        if (user == null) {
            System.out.println("없는 회원입니다.");
            return;
        }

        System.out.printf("이름: %s\n", user.getName());
        System.out.printf("이메일: %s\n", user.getEmail());
        System.out.printf("연락처: %s\n", user.getTel());
    }

    private void updateUser() {
        int userNo = Prompt.inputInt("회원번호?");
        User user = (User) userList.get(userList.indexOf(new User(userNo)));
        if (user == null) {
            System.out.println("없는 회원입니다.");
            return;
        }

        user.setName(Prompt.input("이름(%s)?", user.getName()));
        user.setEmail(Prompt.input("이메일(%s)?", user.getEmail()));
        user.setPassword(Prompt.input("암호?"));
        user.setTel(Prompt.input("연락처(%s)?", user.getTel()));
        System.out.println("변경 했습니다.");
    }

    private void deleteUser() {
        int userNo = Prompt.inputInt("회원번호?");
        User deletedUser = (User) userList.get(userList.indexOf(new User(userNo)));
        if (deletedUser != null) {
            userList.remove(userList.indexOf(deletedUser));
            System.out.printf("'%s' 회원을 삭제 했습니다.\n", deletedUser.getName());
        } else {
            System.out.println("없는 회원입니다.");
        }
    }

    public LinkedList getUserList() {
        return userList;
    }

}
