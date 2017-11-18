package com.to.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author :yangjun on 2017/4/14 0014.
 */
public class CompactUtil {
    public static List<String> getPayTimes(String t1, String t2, short number) {
        List<String> payTimes=new ArrayList<String>();
        Integer y1=Integer.parseInt(t1.substring(0,4));
        Integer y2=Integer.parseInt(t2.substring(0,4));
        Integer year=y2-y1;
        Integer m1=Integer.parseInt(t1.substring(5,7));
        Integer m2=Integer.parseInt(t2.substring(5,7));
        Integer month=m2-m1+year*12;
        Integer n=month/number;
        if(month%number==0){
            for(int i=1;i<n;i++){
                y1+=(m1+number)/13;
                m1=(m1+number)%12;
                if(m1==0){
                    m1=12;
                }
                if(m1<10){
                    payTimes.add(y1+"-0"+m1+t1.substring(7,10));
                }else{
                    payTimes.add(y1+"-"+m1+t1.substring(7,10));
                }
            }
        }else{
            for(int i=1;i<=n;i++){
                y1+=(m1+number)/13;
                m1=(m1+number)%12;
                if(m1==0){
                    m1=12;
                }
                if(m1<10){
                    payTimes.add(y1+"-0"+m1+t1.substring(7,10));
                }else{
                    payTimes.add(y1+"-"+m1+t1.substring(7,10));
                }
            }
        }
        payTimes.add(t2);
        return payTimes;
    }
}
