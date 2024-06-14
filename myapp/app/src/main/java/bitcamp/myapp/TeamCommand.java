package bitcamp.myapp;

public class TeamCommand {

  static final int MAX_SIZE = 100;
  static Team[] teams = new Team[MAX_SIZE];
  static int teamLength = 0;

  static void executeTeamCommand(String subMenuTitle) {
    switch (subMenuTitle) {
      case "등록":
        addTeam();
        break;
      case "목록":
        listTeam();
        break;
      case "조회":
        viewTeam();
        break;
      case "변경":
        updateTeam();
        break;
      case "삭제":
        deleteTeam();
        break;
    }
  }

  static void addTeam() {
    Team team = new Team();
    team.name = Prompt.input("팀명? ");
    while (true) {
      int userNo = Integer.parseInt(Prompt.input("추가할 팀원 번호 선택?(종료: 0)"));

      if (userNo == 0) {
        teams[teamLength++] = team;
        System.out.println("등록했습니다.");
        break;
      } else if (userNo > UserCommand.userLength) {
        System.out.println("없는 팀원입니다.");
        continue;
      }

      String userName = UserCommand.users[userNo - 1].name;
      int included = 0;

      for (int i = 0; i < team.userCnt; i++) {
        if (team.users[i].equals(userName)) {
          included = 1;
          break;
        }
      }

      if (included == 0) {
        team.users[team.userCnt] = userName;
        team.userCnt++;
        System.out.printf("'%s'님을 추가했습니다.\n", userName);
      } else {
        System.out.printf("'%s'님은 현재 팀원입니다.\n", userName);
      }
    }
  }

  static void listTeam() {
    System.out.println("번호 팀명");
    for (int i = 0; i < teamLength; i++) {
      System.out.printf("%d %s\n", (i + 1), teams[i].name);
    }
  }

  static void viewTeam() {
    int teamNo = Integer.parseInt(Prompt.input("팀 번호? "));
    if (teamNo < 1 || teamNo > teamLength) {
      System.out.println("없는 팀입니다.");
      return;
    }

    Team team = teams[teamNo - 1];
    System.out.printf("팀명: %s\n", team.name);
    System.out.println("팀원");
    for (int i = 0; i < team.userCnt; i++) {
      System.out.printf("- %s\n", team.users[i]);
    }
  }

  static void updateTeam() {
    int teamNo = Integer.parseInt(Prompt.input("팀 번호? "));
    if (teamNo < 1 || teamNo > teamLength) {
      System.out.println("없는 팀입니다.");
      return;
    }

    Team team = teams[teamNo - 1];
    String isDelete = "";
    team.name = Prompt.input(String.format("팀명(%s)? ", team.name));

    // 팀원 삭제
    for (int i = 0; i < team.userCnt; i++) {
      isDelete = Prompt.input(String.format("팀원(%s) 삭제? ", team.users[i]));

      if (isDelete.equals("y")) {
        System.out.printf("'%s' 팀원을 삭제합니다.\n", team.users[i]);
        for (int j = i; j < team.userCnt; j++) {
          team.users[j] = team.users[j + 1];
        }
        team.users[--team.userCnt] = null;
      } else if (isDelete.equals("n")) {
        System.out.printf("'%s' 팀원을 유지합니다.\n", team.users[i]);
      }
    }

    // 팀원 추가
    while (true) {
      int userNo = Integer.parseInt(Prompt.input("추가할 팀원 번호 선택?(종료: 0)"));

      if (userNo == 0) {
        System.out.println("변경했습니다.");
        break;
      } else if (userNo > UserCommand.userLength) {
        System.out.println("없는 팀원입니다.");
        continue;
      }

      String userName = UserCommand.users[userNo - 1].name;
      int included = 0;

      for (int i = 0; i < team.userCnt; i++) {
        if (team.users[i].equals(userName)) {
          included = 1;
          break;
        }
      }

      if (included == 0) {
        team.users[team.userCnt] = userName;
        team.userCnt++;
        System.out.printf("'%s'님을 추가했습니다.\n", userName);
      } else {
        System.out.printf("'%s'님은 현재 팀원입니다.\n", userName);
      }
    }
  }

  static void deleteTeam() {
    int teamNo = Integer.parseInt(Prompt.input("팀 번호? "));
    if (teamNo < 1 || teamNo > teamLength) {
      System.out.println("없는 팀입니다.");
      return;
    }

    for (int i = teamNo; i < teamLength; i++) {
      teams[i - 1] = teams[i];
    }
    teams[--teamLength] = null;
    System.out.println("삭제했습니다.");
  }
}
