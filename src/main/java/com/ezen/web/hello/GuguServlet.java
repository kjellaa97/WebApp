package com.ezen.web.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/gugu")
public class GuguServlet extends HttpServlet 
{
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();	//응답할 때 문자를 빼낼거니까
		
		request.setCharacterEncoding("utf-8");
		String strDan = request.getParameter("dan"); 
		
		int dan = strDan ==null? 2: Integer.parseInt(strDan);
		//int dan = Integer.parseInt(strDan); // unboxing
		
		
		
		
		String html = "";
		html += "<! doctype html>";
		html += "<html>";
		html +="<head>";
		html +="<title>구구단 학습기</title>";
		html +="</head>";
		html +="<body>";
		html += "<h3>구구단을 암기하자 </h3>";

		for(int i=1; i<=9; i++)
		{
			html += String.format("%d * %d = %d<br>", dan, i, dan*i);
		}

		// 위에는 구구단 출력, 밑에는 링크를 제시하여 이용자가 클릭하면 해당 수의 구구단이 표시되도록 한다
		html += "<p>"; //paragraph 의 약자로 새로운 문단을 나타냄, 즉 한행을 빈행으로 둠
		html += "[";	//장식용임
		html += " <a href= gugu?dan=2> 2 </a> ";	//a태그는 링크 태그임, href 는 속성
		html += "]";	//장식용
		html += "[";	//장식용임
		html += " <a href= gugu?dan=3> 3 </a> ";
		html += "]";	//장식용
		html += "[";	//장식용임
		html += " <a href= gugu?dan=4> 4 </a> "; 
		html += "]";	//장식용
		html +="</body>";
		html += "<p>"; 
		
		html += "<form action= 'gugu' method='post'>"; // gugu라는 servlet이 밑에서 이용자가 입력해서 제출한 걸 받음
		//html += "단수 <input type= 'text' name='dan'>"; // input태그는 입력하는 칸 만들어줌, type은 text및 숫자 포함 - 입력된 걸 dan으로 받아줌 
		html += "<select name= 'dan'>";
		html += "<option >2</option>";
		html += "<option >3</option>";
		html += "<option >4</option>";
		html += "<option >5</option>";
		html += "<option >6</option>";
		html += "<option >7</option>";
		html += "<option >8</option>";
		html += "<option >9</option>";
		
		html += "</select>";
		html += "<button type='submit'>구구단 보기</button>";
		html += "</form>";

		
		html +="</html>";
		
		out.println(html);
		out.flush();
		// 일반 이용자가 접근하려면 웹 주소를 적어야함 http://127.0.0.1/JavaWeb/gugu
		// http://localhost/JavaWeb/gugu?dan=5 --> get방식 (url을 이용), ?를 사용하면 데이터 전달( 5라는 숫자가 gugu라는 servlet 안으로 들어감)
												//특징: 주소창에 남음 , 따라서 보안성 요구 되는 데이터는 get방식 사용안함 --> 극복하기 위해  post방식 사용!
												//post 방식은 body 안에 넣어서 안전하게 주소창 열 수 있음 (form을 작성해서 담당자에게 제출하는 방식)
	
	
		
	}

}
