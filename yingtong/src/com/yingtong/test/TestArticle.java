package com.yingtong.test;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.yingtong.dao.ArticleDao;
import com.yingtong.entity.Article;

public class TestArticle {

	public static void main(String[] args) {
		int arr[][] = {{1,2,3},{4,5,6,7},{9}};
		boolean found = false;
		for(int i=0;i<arr.length && !found;i++)	{
				for(int j=0;j<arr[i].length;j++){
					if(j==0){
						
					break;
					}
					System.out.println(arr[1].length);
				}
			} 
	}
	
}
