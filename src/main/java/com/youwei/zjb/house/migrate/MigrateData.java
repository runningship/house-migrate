package com.youwei.zjb.house.migrate;


public class MigrateData {

	public static void main(String[] args){
		if(args.length<4){
			System.out.println("参数不够，需要4个参数，依次为 jobName city startId endId");
			return;
		}
		StartUpListener.initDataSource();
		String jobName = args[0];
		String city = args[1];
		int start = Integer.valueOf(args[2]);
		int end = Integer.valueOf(args[3]);
		if("House".equalsIgnoreCase(jobName)){
			if("feixi".equalsIgnoreCase(city)){
				MigrateFeixiHouse job = new MigrateFeixiHouse();
				job.run(start , end);
			}else if("fuyang".equalsIgnoreCase(city)){
				MigrateFuyangHouse job = new MigrateFuyangHouse();
				job.run(start , end);
			}
		}else if ("HouseRent".equalsIgnoreCase(jobName)){
			if("feixi".equalsIgnoreCase(city)){
				MigrateFeixiHouseRent job = new MigrateFeixiHouseRent();
				job.run(start , end);
			}else if("fuyang".equalsIgnoreCase(city)){
				
			}
		}else if ("GenJin".equalsIgnoreCase(jobName)){
			if("feixi".equalsIgnoreCase(city)){
				MigrateFeixiHouseGenjin job = new MigrateFeixiHouseGenjin();
				job.run(start , end);
			}else if("fuyang".equalsIgnoreCase(city)){
				
			}
		}
		
	}
}
