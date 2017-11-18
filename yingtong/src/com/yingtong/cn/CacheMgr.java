package com.yingtong.cn;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CacheMgr {
	private static Map cacheMap = new HashMap();
	private static Map cacheConfMap = new HashMap();

	private CacheMgr() {

	}

	private static CacheMgr cm = null;

	public static CacheMgr getInstance() {
		if (cm == null) {
			cm = new CacheMgr();
			Thread t = new ClearCache();
			t.start();
		}
		return cm;
	}

	/**
	 * ���ӻ���
	 * 
	 * @param key
	 * @param value
	 * @param ccm
	 *            �������
	 * @return
	 */
	public boolean addCache(Object key, Object value, CacheConfModel ccm) {
		boolean flag = false;
		cacheMap.put(key, value);
		cacheConfMap.put(key, ccm);
		return true;
	}

	/**
	 * ��ȡ����
	 * 
	 * @param key
	 * @return
	 */
	public Object getCache(Object key) {
		Object value = cacheMap.get(key);
		if (value == null) {
			return null;
		}
		return value;
	}

	/**
	 * ɾ������
	 * 
	 * @param key
	 * @return
	 */
	public boolean removeCache(Object key) {
		cacheMap.remove(key);
		cacheConfMap.remove(key);
		return true;
	}

	/**
	 * ����������
	 * 
	 * @author wanglj �̳�Thread�߳���
	 */
	private static class ClearCache extends Thread {
		public void run() {
			while (true) {
				Set tempSet = new HashSet();
				Set set = cacheConfMap.keySet();
				Iterator it = set.iterator();
				while (it.hasNext()) {
					Object key = it.next();
					CacheConfModel ccm = (CacheConfModel) cacheConfMap.get(key);
					// �Ƚ��Ƿ���Ҫ���
					if (!ccm.isForever()) {
						if ((new Date().getTime() - ccm.getBeginTime()) >= ccm
								.getDurableTime() * 60 * 1000) {
							// ����������ȼ�¼����
							tempSet.add(key);
						}
					}
				}
				// �������
				Iterator tempIt = tempSet.iterator();
				while (tempIt.hasNext()) {
					Object key = tempIt.next();
					cacheMap.remove(key);
					cacheConfMap.remove(key);

				}
				// ��Ϣ
				try {
					Thread.sleep(60 * 1000L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
