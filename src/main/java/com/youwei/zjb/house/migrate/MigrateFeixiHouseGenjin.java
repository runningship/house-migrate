package com.youwei.zjb.house.migrate;

import java.util.List;

import org.bc.sdak.CommonDaoService;
import org.bc.sdak.SimpDaoTool;
import org.bc.web.ThreadSession;

public class MigrateFeixiHouseGenjin {

	public void run(int startId , int endId){
		CommonDaoService dao = SimpDaoTool.getGlobalCommonDaoService();
		ThreadSession.setCityPY("feixiOld");
		List<OldGenJin> list = dao.listByParams(OldGenJin.class, "from OldGenJin where id>=? and id<=? order by id asc", startId , endId);
		for(OldGenJin old : list){
			ThreadSession.setCityPY("feixiOld");
			GenJin genjin = new GenJin();
			//找到新的uid,did,cid
			User oldUser = dao.get(User.class, old.uid);
			if(oldUser==null){
				System.out.println("genjin "+old.id+" not link to any user");
				continue;
			}
			ThreadSession.setCityPY("feixi");//设置到新库
			GenJin newHousePo = dao.getUniqueByKeyValue(GenJin.class, "oldId", old.id);
			if(newHousePo!=null){
				genjin = newHousePo;
			}
			genjin.oldId = old.id;
			User newUser = dao.getUniqueByKeyValue(User.class, "lname", oldUser.lname);
			genjin.uid = newUser.id;
			genjin.did = newUser.did;
			genjin.cid = newUser.cid;
			genjin.conts = old.conts;
			genjin.addtime = old.addtime;
			genjin.sh = old.sh;
			genjin.chuzu = old.chuzu;
			genjin.flag = old.flag;
			genjin.ztai = old.ztai;
			genjin.oldId = old.id;
			
			if(old.chuzu==null || old.chuzu==0){
				House house = dao.getUniqueByKeyValue(House.class, "oldId", old.hid);
				if(house==null){
					System.out.println("genjin "+old.id+" not link to any house");
					continue;
				}
				genjin.hid = house.id;
			}else{
				HouseRent rent = dao.getUniqueByKeyValue(HouseRent.class, "oldId", old.hid);
				if(rent==null){
					System.out.println("genjin "+old.id+" not link to any house");
					continue;
				}
				genjin.hid = rent.id;
			}
			ThreadSession.setCityPY("feixi");
			dao.saveOrUpdate(genjin);
			System.out.println("migrate genjin "+old.id+" success");
		}
	}
}
