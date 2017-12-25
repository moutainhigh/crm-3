package com.hefei.api.agg.test;

import com.hefei.api.agg.id.manager.IdManager;
import com.hefei.api.agg.id.manager.impl.IdManagerImpl;

public class GextIdTest {

	public static void main(String[] args) {
		IdManager im = new IdManagerImpl();
		try {
			Long id = im.getNextId();
			System.out.println(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
