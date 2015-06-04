package com.youwei.zjb.house.migrate;

import java.util.List;

import org.bc.sdak.CommonDaoService;
import org.bc.sdak.SimpDaoTool;
import org.bc.web.ThreadSession;

public class MigrateFuyangHouseRent {

	public void run(int startId , int endId){
		CommonDaoService dao = SimpDaoTool.getGlobalCommonDaoService();
		ThreadSession.setCityPY("fuyangOld");
		List<OldHouseRent> list = dao.listByParams(OldHouseRent.class, "from OldHouseRent where id>=? and id<=? order by id asc", startId , endId);
		for(OldHouseRent old : list){
			ThreadSession.setCityPY("fuyangOld");
			HouseRent rent = new HouseRent();
			//找到新的uid,did,cid
			User oldUser = dao.get(User.class, old.uid);
			if(oldUser==null){
				continue;
			}
			ThreadSession.setCityPY("fuyang");
			HouseRent newHousePo = dao.getUniqueByKeyValue(HouseRent.class, "oldId", old.id);
			if(newHousePo!=null){
				rent = newHousePo;
			}
			rent.oldId = old.id;
			User newUser = dao.getUniqueByKeyValue(User.class, "lname", oldUser.lname);
			rent.uid = newUser.id;
			rent.did = newUser.did;
			rent.cid = newUser.cid;
			rent.ztai = old.ztai;
			rent.quyu = old.quyu;
			rent.area = old.area;
			rent.dhao = old.dhao;
			rent.fhao = old.fhao;
			rent.address = old.address;
			rent.lceng = old.lceng;
			rent.zceng = old.zceng;
			rent.lxing = old.lxing;
			rent.hxf = old.hxf;
			rent.hxw = old.hxw;
			rent.hxt = old.hxt;
			rent.zxiu = old.zxiu;
			rent.mji = old.mji;
			rent.zjia = old.zjia;
			rent.djia = old.djia;
			rent.lxr = old.lxr;
			rent.tel = old.tel;
			rent.forlxr = old.forlxr;
			rent.fortel = old.fortel;
			rent.beizhu = old.beizhu;
			rent.dateyear = old.dateyear;
			rent.dateadd = old.dateadd;
			rent.fav = old.fav;
			rent.seeFH = old.seeFH;
			rent.seeGX = old.seeGX;
			rent.seeHM = old.seeHM;
			rent.isdel = old.isdel;
			rent.sh = old.sh;
			
			rent.ruku = 1;
			ThreadSession.setCityPY("fuyang");
			dao.saveOrUpdate(rent);
			System.out.println("migrate house rent "+old.id+" success");
		}
	}
}
