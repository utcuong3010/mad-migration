package com.mad.migration.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.mad.migration.event.ItemReadEvent;

@Component
public class ItemReadListener implements ApplicationListener<ItemReadEvent>{

	@Override
	public void onApplicationEvent(ItemReadEvent event) {
		// TODO Auto-generated method stub
		

		int count = 0;
		while(++count<10) {
			
		System.err.println("event reader");
		System.err.println("dd");
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	}

}
