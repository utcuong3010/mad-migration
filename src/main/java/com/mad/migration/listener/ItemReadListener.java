package com.mad.migration.listener;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.mad.migration.domain.MadItemData;
import com.mad.migration.event.ItemReadEvent;
import com.mad.migration.utils.FileUtils;

@Component
public class ItemReadListener implements ApplicationListener<ItemReadEvent>{

	@Value("${mad.migration.homeDir}")
	private String homeDirectory;

	@Override
	public void onApplicationEvent(ItemReadEvent event) {
		
		MadItemData itemData = (MadItemData)event.getItem();
		//write into log
		FileUtils.writeLog(homeDirectory + File.separator + "read-data" + File.separator + itemData.getVendor().getVendorKey() + ".csv",
				itemData.toString());

		
	}

}
