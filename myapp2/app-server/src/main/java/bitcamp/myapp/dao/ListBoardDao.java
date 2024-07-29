package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Board;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ListBoardDao implements BoardDao {
  List<Board> boardList = new ArrayList<>();
  int seqNo = 0;

  public ListBoardDao() {
    try (XSSFWorkbook workbook = new XSSFWorkbook("data.xlsx")) {
      XSSFSheet sheet = workbook.getSheet("boards");

      int lastRow = sheet.getLastRowNum();
      Board board;
      Row row;

      for (int i = 0; i < lastRow; i++) {
        board = new Board();
        row = sheet.getRow(i + 1);

        board.setNo(Integer.parseInt(row.getCell(0).getStringCellValue()));
        board.setTitle(row.getCell(1).getStringCellValue());
        board.setContent(row.getCell(2).getStringCellValue());
        board.setCreatedDate(
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(row.getCell(3).getStringCellValue()));
        board.setViewCount(Integer.parseInt(row.getCell(4).getStringCellValue()));

        boardList.add(board);
        if (board.getNo() > seqNo) {
          seqNo = board.getNo();
        }
      }
    } catch (Exception e) {
      System.out.println("게시판 데이터 로딩 중 오류 발생!");
      e.printStackTrace();
    }
  }

  public void save(XSSFWorkbook workbook) {
    XSSFSheet sheet = workbook.createSheet("boards");

    Row row = sheet.createRow(0);
    String[] headers = {"no", "title", "content", "created_date", "view_count"};
    for (int i = 0; i < headers.length; i++) {
      row.createCell(i).setCellValue(headers[i]);
    }

    int rowNo = 1;
    for (Board board : boardList) {
      row = sheet.createRow(rowNo++);

      row.createCell(0).setCellValue(String.valueOf(board.getNo()));
      row.createCell(1).setCellValue(board.getTitle());
      row.createCell(2).setCellValue(board.getContent());
      row.createCell(3)
          .setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(board.getCreatedDate()));
      row.createCell(4).setCellValue(String.valueOf(board.getViewCount()));
    }
  }

  @Override
  public boolean insert(Board board) {
    board.setNo(++seqNo);
    boardList.add(board);
    return true;
  }

  @Override
  public Board findBy(int no) {
    int index = boardList.indexOf(new Board(no));

    if (index > -1) {
      return boardList.get(index);
    }

    return null;
  }

  @Override
  public List<Board> list() {
    return boardList.stream().toList();
  }

  @Override
  public boolean update(Board board) {
    int index = boardList.indexOf(board);

    if (index > -1) {
      boardList.set(index, board);
      return true;
    }

    return false;
  }

  @Override
  public boolean delete(Board board) {
    int index = boardList.indexOf(board);

    if (index > -1) {
      return boardList.remove(board);
    }

    return false;
  }
}
