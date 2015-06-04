package com.youwei.zjb.house.migrate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bc.sdak.CommonDaoService;
import org.bc.sdak.SimpDaoTool;
import org.bc.web.ThreadSession;

public class MigrateFeixiHouse {

	public void run(int startId , int endId){
		CommonDaoService dao = SimpDaoTool.getGlobalCommonDaoService();
		ThreadSession.setCityPY("feixiOld");
		List<OldHouse> list = dao.listByParams(OldHouse.class, "from OldHouse where id>=? and id<=? order by id asc", startId , endId);
		for(OldHouse old : list){
			ThreadSession.setCityPY("feixiOld");
			House house = new House();
			//找到新的uid,did,cid
			User oldUser = dao.get(User.class, old.uid);
			if(oldUser==null){
				continue;
			}
			ThreadSession.setCityPY("feixi");
			House newHousePo = dao.getUniqueByKeyValue(House.class, "oldId", old.id);
			if(newHousePo!=null){
				house = newHousePo;
			}
			house.oldId = old.id;
			User newUser = dao.getUniqueByKeyValue(User.class, "lname", oldUser.lname);
			house.uid = newUser.id;
			house.did = newUser.did;
			house.cid = newUser.cid;
			house.ztai = old.ztai;
			house.quyu = old.quyu;
			house.area = old.area;
			house.dhao = old.dhao;
			house.fhao = old.fhao;
			house.address = old.address;
			house.lceng = old.lceng;
			house.zceng = old.zceng;
			house.lxing = old.lxing;
			house.hxf = old.hxf;
			house.hxw = old.hxw;
			house.hxt = old.hxt;
			house.zxiu = old.zxiu;
			house.mji = old.mji;
			house.zjia = old.zjia;
			house.djia = old.djia;
			house.lxr = old.lxr;
			house.tel = old.tel;
			house.forlxr = old.forlxr;
			house.fortel = old.fortel;
			house.beizhu = old.beizhu;
			int dateyear = 0;
			if(StringUtils.isNotEmpty(old.dateyear)){
				try{
					dateyear = Integer.valueOf(old.dateyear.replace("年", ""));
				}catch(Exception ex){
					System.out.println("house "+old.id+" dateyear not a number");
				}
			}
			house.dateyear = dateyear;
			house.dateadd = old.dateadd;
			house.fav = old.fav;
			house.seeFH = old.seeFH;
			house.seeGX = old.seeGX;
			house.seeHM = old.seeHM;
			house.isdel = old.isdel;
			house.sh = old.sh;
			house.dategjlock = old.dategjlock;
			ThreadSession.setCityPY("feixi");
			dao.saveOrUpdate(house);
			System.out.println("migrate house "+old.id+" success");
		}
	}
}
