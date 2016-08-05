package java83.example.controller.json;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import java83.example.service.BoardService;
import java83.example.vo.Board;

@Controller
@RequestMapping("/board/")
public class BoardController {

  @Autowired BoardService boardService;
  
  @RequestMapping(path="list", produces="application/json;charset=UTF-8")  //한글 처리 
  @ResponseBody //FrontController 에게 jsp를 찾지 말고 클라이언트에게 바로 보낼거라고 알려줌. 안써주면 return값을 jsp 경로라고 착각한다.
  public String list(
      @RequestParam(defaultValue="1") int pageNo, 
      @RequestParam(defaultValue="3") int pageSize){
    HashMap<String, Object> result = new HashMap<>();
    try{
      List<Board> list = boardService.getBoardList(pageNo, pageSize);
      result.put("status", "success");
      result.put("data", list);
    } catch (Exception e) {
      result.put("status", "failure");
    }  // 그냥 출력하지 말고 상태 정보와 list를 다시 map에 담아서 하면, 에러를 방지할 수 있다.
    return new Gson().toJson(result); //list값을 출력!
  }
  
  @RequestMapping(
      path="detail", 
      produces="application/json;charset=UTF-8")
  @ResponseBody
  public String detail(int no){
    HashMap<String, Object> result = new HashMap<>();
    try{
      result.put("data", boardService.getBoard(no));
      result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
    }  // 그냥 출력하지 말고 상태 정보와 list를 다시 map에 담아서 하면, 에러를 방지할 수 있다.
    return new Gson().toJson(result); //list값을 출력!
  }
  
  @RequestMapping(path="add", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
  @ResponseBody
  public String add(Board board){
    HashMap<String, Object> result = new HashMap<>();
    try{
      boardService.addBoard(board);
      result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
    }  // 그냥 출력하지 말고 상태 정보와 list를 다시 map에 담아서 하면, 에러를 방지할 수 있다.
    return new Gson().toJson(result); //list값을 출력!
  }
  
  @RequestMapping(path="update", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
  @ResponseBody
  public String update(Board board){
    HashMap<String, Object> result = new HashMap<>();
    try{
      boardService.updateBoard(board);
      result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
    }  // 그냥 출력하지 말고 상태 정보와 list를 다시 map에 담아서 하면, 에러를 방지할 수 있다.
    return new Gson().toJson(result); //list값을 출력!
  }

  
  @RequestMapping(
      path="delete", 
      produces="application/json;charset=UTF-8")
  @ResponseBody
  public String delete(int no){
    HashMap<String, Object> result = new HashMap<>();
    try{
      boardService.deleteBoard(no);
      result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
    }  // 그냥 출력하지 말고 상태 정보와 list를 다시 map에 담아서 하면, 에러를 방지할 수 있다.
    return new Gson().toJson(result); //list값을 출력!
  }
  
}
