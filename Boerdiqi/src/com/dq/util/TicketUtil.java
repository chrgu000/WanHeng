package com.dq.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dq.entity.Ticket;

public class TicketUtil {
	public static List<Ticket> getTickets(List<Ticket> tickets)  {
		List<Ticket> ts = new ArrayList<Ticket>();
		for (Ticket ticket : tickets) {
             if(getTicket(ticket)!=null){
            	 ts.add(ticket);
             }
		}
		return ts;
	}

	public static Ticket getTicket(Ticket t)  {
		
          if(isInDate(t)==2&&t.getFlag()!=2){
        	  t.setFlag(2);
        	  return t;
          }else if(isInDate(t)==3&&t.getFlag()!=3){
        	  t.setFlag(3);
        	  return t;
          }
		return null;
	}

	public static int isInDate(Ticket t) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Long date = new Date().getTime();
		Long beginDate=null;
		Long endDate=null;
		try {
			beginDate = sdf.parse(t.getStart_time()).getTime();
			endDate= sdf.parse(t.getEnd_time()).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
	    if(date<beginDate){
	    	return 1;
	    }else if(date>=beginDate&&date<endDate){
	    	return 2;
	    }else{
	    	return 3;
	    }
	}
}
