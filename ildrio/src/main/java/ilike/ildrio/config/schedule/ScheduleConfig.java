package ilike.ildrio.config.schedule;

import java.io.File;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ilike.ildrio.common.StringUtil;


@EnableScheduling
@SpringBootApplication
public class ScheduleConfig {

	/**
	 * Cron 표현식을 사용한 작업 예약
	 * 초(0-59) 분(0-59) 시간(0-23) 일(1-31) 월(1-12) 요일(0-7)
	 * @throws Exception 
	 */
	@Scheduled(cron = "0 5 14,20 * * *")
	public void run() throws Exception {
		System.out.println("proc_comsExcelDn");
		
		
		String timeStamp = StringUtil.getNow("timeStamp");

		String FileRootPath = "C:/LOCAL_FILE/DnFile/goodsExcel";
		File desti = new File("/image/IMG_SVR/DnFile/goodsExcel");
		if (desti.exists()) {
			
			//FileRootPath = "/image/IMG_SVR/DnFile/goodsExcel";
			//apiGoodsExcel_comsmart.proc_comsExcelDn();
			//goodsImageDownload.proc_supplyGoods_image("lightcom");
		}
		
	}
	
	
	

}