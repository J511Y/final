package com.example.gym.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.gym.service.BranchService;
import com.example.gym.service.CalendarService;
import com.example.gym.service.CustomerService;
import com.example.gym.service.ReservationService;
import com.example.gym.vo.Branch;
import com.example.gym.vo.ProgramDate;
import com.example.gym.vo.ProgramReservation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ReservationController {	
	ObjectMapper mapper = new ObjectMapper();
	@Autowired CalendarService calendarService;
	@Autowired ReservationService reservationService;
	@Autowired CustomerService customerService;
	@Autowired BranchService branchService;
	
	// 캘린더 출력
	@GetMapping("/calendar")
	public String calendar(Model model, HttpSession session,
			 			   @RequestParam(required = false) Integer targetYear ,
			 			   @RequestParam(required = false) Integer targetMonth			 			   		 			   
			 			   ) {
		List<Branch> branchList = branchService.branch();
		
		System.out.println("접속성공");					
		Map<String, Object> calendarMap = calendarService.getCalendar(targetYear, targetMonth, session);
		model.addAttribute("calendarMap", calendarMap);
		model.addAttribute("branchList", branchList);
			
		return "reservation/calendar";
	}
	
	
	// 예약 리스트
	@GetMapping("/reservationList")
	public String reservationList(HttpSession session, Model model, Integer targetDay, Integer targetYear, Integer targetMonth,									
								 @RequestParam(defaultValue = "1") int currentPage	
							      ) throws JsonProcessingException {
	/*
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		 if (loginCustomer == null) {		
		    return "customer/login";
		     }
	
		Map<String, Object> paymentNo = customerService.customerOne(loginCustomer);
		 	if(paymentNo == null) {		
		    return "redirect:/home"; 		 		
		 	}
	*/
		String targetYear2 = mapper.writeValueAsString(targetYear);
		String targetMonth2 = mapper.writeValueAsString(targetMonth);
		String targetDay2 = mapper.writeValueAsString(targetDay);
	
	
		String date = targetYear2+targetMonth2+targetDay2;
		if(targetMonth2.length()== 1){
			date = targetYear2+"0"+targetMonth2+targetDay2;
		}
		System.out.println(date +"날짜" );
		
		int rowPerPage = 5;		
		int beginRow = (currentPage - 1) * rowPerPage ;
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("paymentNo", 1);
		paramMap.put("programDate", date );
		
		System.out.println(paramMap + "<-- paramMap");
		
		
		Map<String, Object> list = reservationService.selectReservationList(paramMap);
		Object reservationList = list.get("reservationList");
		
		System.out.println(reservationList +"<---reservationList");
		model.addAttribute("reservationList",mapper.writeValueAsString(reservationList));
		
		int totalRow = (int) list.get("totalRow");
		int lastPage = totalRow / rowPerPage;
		if (totalRow % rowPerPage != 0) {
			lastPage += 1;
		}		
		model.addAttribute("targetYear", targetYear);
		model.addAttribute("targetMonth", targetMonth);
		model.addAttribute("targetDay", targetDay);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("totalRow", totalRow);
		model.addAttribute("rowPerPage", rowPerPage);
		System.out.println(targetYear + "<-- targetYear");
		System.out.println(targetMonth + "<-- targetMonth");
		System.out.println(targetDay + "<-- targetDay");
		

	
	    return "reservation/reservationList";
		
	}
	
	// 임시 프로그램 리스트
	@GetMapping("/testList")
	public String programList(ProgramDate programDate, Model model) throws JsonProcessingException {
		List<Map<String, Object>> programList= reservationService.selectProgram(programDate);

		model.addAttribute("programList", mapper.writeValueAsString(programList));		
		
		System.out.println(programList +"<----프로그램");
		return "reservation/testList";
		
	}
		
		
	// 예약 추가
	@GetMapping("/insertReservation")
	public String insertReservation(HttpSession session, Model model, 
									ProgramDate programDate) throws JsonProcessingException {
		List<Branch> branchList = branchService.branch();
		List<Map<String, Object>> programList= reservationService.selectProgram(programDate);
		model.addAttribute("programList", mapper.writeValueAsString(programList));
		model.addAttribute("branchList", mapper.writeValueAsString(branchList));
		
		return "reservation/insertReservation";
	}
	
	@GetMapping("/program/{program_no}/reservationInfo")
	@ResponseBody
	public ResponseEntity<List<ProgramDate>> reservationInfos(@PathVariable int program_no) {
		var result = reservationService.selectProgramDates(program_no);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/insertReservation")
	@ResponseBody
    public ResponseEntity<?> insertReservation2(@RequestBody ProgramReservation reservation) {
		log.info(reservation.toString());
		// TODO: PaymentNo 세팅하는 코드 내부 정책에 맞춰 적절히 수정하기
		reservation.setPaymentNo(1);
		reservationService.insertReservation(reservation);
        return ResponseEntity.ok("예약이 정상적으로 완료되었습니다.");
    }
	
	
	
	// 예약 삭제
	@GetMapping("/deleteReservationList")
	public String deleteReservation(ProgramReservation reservation,Integer targetDay, Integer targetYear, Integer targetMonth) {
		System.out.println(targetYear + " " + targetMonth + " " + targetDay + " " + "<--날짜");
		
		reservationService.deleteReservation(reservation);
		System.out.println(reservation.getProgramReservationNo()+"<-- 예약번호");
		return "redirect:/reservationList?targetYear=" + targetYear + "&targetMonth=" + targetMonth + "&targetDay=" + targetDay;
	}
	
	
	
}