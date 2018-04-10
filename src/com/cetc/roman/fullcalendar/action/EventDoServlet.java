package com.cetc.roman.fullcalendar.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cetc.roman.fullcalendar.dao.CalendarDao;
import com.cetc.roman.fullcalendar.domain.Calendar;
import com.cetc.roman.fullcalendar.util.DateUtil;

@WebServlet("/EventDoServlet")
public class EventDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EventDoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		if("add".equals(action)){
			String events = request.getParameter("event");//事件内容
			String isallday = request.getParameter("isallday");//是否是全天事件
			String isend = request.getParameter("isend");//是否有结束时间
			String startdate = request.getParameter("startdate");
			String enddate = request.getParameter("enddate");
			String s_time = request.getParameter("s_hour") + ":" + request.getParameter("s_minute") + ":00";
			String e_time = request.getParameter("e_hour") + ":" + request.getParameter("e_minute") + ":00";
			
			String start = "";
			String end = "";
			if("1".equals(isallday) && "1".equals(isend)){
				start = startdate;
				end = enddate;
			}else if("1".equals(isallday) && isend == null){
				start = startdate;
			}else if(isallday == null && "1".equals(isend)){
				start = startdate + " " + s_time;
				end = enddate + " " + e_time;
				isallday = "0";
			}else {
				start = startdate + " " + s_time;
				isallday = "0";
			}
			
			String[] colors = {"#360","#f30","#06c"};
			int index = (int)(Math.random()*colors.length);
			Calendar calendar = new Calendar();
			calendar.setTitle(events);
			calendar.setStart(start);
			calendar.setEnd(end);
			calendar.setAllDay(Integer.parseInt(isallday));
			calendar.setColor(colors[index]);
			boolean b = new CalendarDao().add(calendar);
			if(b){
				out.print("1");
			}else {
				out.print("写入失败！");
			}
		}else if("edit".equals(action)) {
			Integer id =  Integer.parseInt(request.getParameter("id"));
			String events = request.getParameter("event");//事件内容
			String isallday = request.getParameter("isallday");//是否是全天事件
			String isend = request.getParameter("isend");//是否有结束时间
			String startdate = request.getParameter("startdate");
			String enddate = request.getParameter("enddate");
			String s_time = request.getParameter("s_hour") + ":" + request.getParameter("s_minute") + ":00";
			String e_time = request.getParameter("e_hour") + ":" + request.getParameter("e_minute") + ":00";
			
			String start = "";
			String end = "";
			if("1".equals(isallday) && "1".equals(isend)){
				start = startdate;
				end = enddate;
			}else if("1".equals(isallday) && isend == null){
				start = startdate;
			}else if(isallday == null && "1".equals(isend)){
				start = startdate + " " + s_time;
				end = enddate + " " + e_time;
				isallday = "0";
			}else {
				start = startdate + " " + s_time;
				isallday = "0";
			}
			
			String[] colors = {"#360","#f30","#06c"};
			int index = (int)(Math.random()*colors.length);
			Calendar calendar = new Calendar();
			calendar.setTitle(events);
			calendar.setStart(start);
			calendar.setEnd(end);
			calendar.setAllDay(Integer.parseInt(isallday));
			calendar.setColor(colors[index]);
			calendar.setId(id);
			boolean b = new CalendarDao().modify(calendar);
			if(b){
				out.print("1");
			}else {
				out.print("写入失败！");
			}
		}else if("del".equals(action)){
			Integer id =  Integer.parseInt(request.getParameter("id"));
			if(id > 0){
				boolean b = new CalendarDao().del(id);
				if(b){
					out.print("1");
				}else {
					out.print("删除失败！");
				}
			}else {
				out.print("事件不存在！");
			}
		}else if("drag".equals(action)) {
			Integer id =  Integer.parseInt(request.getParameter("id"));
			Integer daydiff = Integer.parseInt(request.getParameter("daydiff")) * 24 * 60 * 60;
			Integer minudiff = Integer.parseInt(request.getParameter("minudiff")) * 60;
			String allday = request.getParameter("allday");
			Calendar calendar = new CalendarDao().findById(id);
			String start = calendar.getStart();
			long lstart = DateUtil.string2long(start);
			
			String end = calendar.getEnd();
			String sql = "";
			if("true".equals(allday)){
				if("".equals(end)){
					sql = "update calendar set start = '" + DateUtil.long2string(lstart + daydiff)  + "' where id = " + id;
				}else {
					long lend = DateUtil.string2long(end);
					sql = "update calendar set start =  '" + DateUtil.long2string(lstart + daydiff) + "', end = '" + DateUtil.long2string(lend + daydiff) + "' where id = " + id;
				}
			}else {
				Integer difftime = daydiff + minudiff;
				if("".equals(end)){
					sql = "update calendar set start = '" + DateUtil.long2string(lstart + difftime) + "' where id = " + id;
				}else {
					long lend = DateUtil.string2long(end);
					sql = "update calendar set start = '" + DateUtil.long2string(lstart + difftime) + "', end = '" + DateUtil.long2string(lend + difftime) + "' where id = " + id;
				}
			}
	//		System.out.println(sql);
			boolean b = new CalendarDao().modify(sql);
			if(b){
				out.print("1");
			}else {
				out.print("拖拽失败！");
			}
		}else if("resize".equals(action)){
			Integer id =  Integer.parseInt(request.getParameter("id"));
			Integer daydiff = Integer.parseInt(request.getParameter("daydiff")) * 24 * 60 * 60;
			Integer minudiff = Integer.parseInt(request.getParameter("minudiff")) * 60;
			Calendar calendar = new CalendarDao().findById(id);
			String start = calendar.getStart();
			long lstart = DateUtil.string2long(start);
			String end = calendar.getEnd();
			Integer difftime = daydiff + minudiff;
			String sql = "";
			if("".equals(end)){
				sql = "update calendar set end = '" + DateUtil.long2string(lstart + difftime) + "' where id = " + id;
			}else {
				long lend = DateUtil.string2long(end);
				sql = "update calendar set end = '" + DateUtil.long2string(lend + difftime) + "' where id = " + id;
			}
	//		System.out.println(sql);
			boolean b = new CalendarDao().modify(sql);
			if(b){
				out.print("1");
			}else {
				out.print("缩放失败！");
			}
		}
	}
}
