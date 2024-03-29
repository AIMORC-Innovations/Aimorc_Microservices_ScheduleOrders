package com.Checkout.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.Checkout.Repository.DateRepository;
import com.Checkout.Repository.DeliveryRepository;
import com.Checkout.entity.Check;
import com.Checkout.entity.Date;
import com.Checkout.entity.DeliveryAddress;


@Service
public class CheckoutServices{

	@Autowired
	private DateRepository daterepository;
	@Autowired
	private DeliveryRepository deliveryrepository;
	
	public Date getScheduler(int userid) {
		return daterepository.fetchDate(userid);
	}
	
	
	public Date schedulingPickUp(Check checkout, int userid) {
		Date date = new Date();
		date.setUserid(userid);
		date.setCountry(checkout.getCountry());
		date.setState(checkout.getState());
		date.setCity(checkout.getCity());
		date.setDate(checkout.getDate());
		date.setDel_address1(checkout.getDel_address1());
		date.setZip(checkout.getZip());
		
		return daterepository.save(date);
	}
	 

	
	@Transactional
	public int updatePickUp(String date,String del_address1,  int userid) {
		System.out.println(del_address1);
		return daterepository.updateDate(date,del_address1,userid);
	}


	
	public ResponseEntity<String> cancelPickUp(Date date) {
		daterepository.delete(date.getUserid());
		return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
		
	}


	public Map<String, Object> deliveryProfileAddr( int userid) {
		//DeliveryAddress delAddress = new DeliveryAddress();
		//delAddress.setUserid(userid);
		//delAddress.setDel_profileaddr(checkout.getDel_profileaddr());
		return this.deliveryrepository.getAddress(userid);
	}


	@Transactional
	public int updateDeliveryAddr3(String del_address1,String del_address2, int userid) {
		//DeliveryAddress delAddr2 =new DeliveryAddress();
		//delAddr2.setUserid(userid);
		//delAddr2.setDel_address2(del_address2);
		return deliveryrepository.updateAddress3(del_address1,del_address2,userid);
	}
	
	@Transactional
	public int updateDeliveryAddr1(String del_address1, int userid) {
		//DeliveryAddress delAddr2 =new DeliveryAddress();
		//delAddr2.setUserid(userid);
		//delAddr2.setDel_address2(del_address2);
		return deliveryrepository.updateAddress1(del_address1,userid);
	}
	
	@Transactional
	public int updateDeliveryAddr2(String del_address1, int userid) {
		//DeliveryAddress delAddr1 =new DeliveryAddress();
	//	delAddr1.setUserid(userid);
		//delAddr1.setDel_address3(del_address1);
		return this.deliveryrepository.updateAddress2(del_address1,userid);
		
	}


	public Map<String, Object> displaydeliveryAddress(int userid) {
		
		return this.deliveryrepository.getAddress(userid);
	}





	public int updatePickUpAddr(String city, String country, String del_address1, String state, String zip,
			int userid) {
		// TODO Auto-generated method stub
		return daterepository.saveAddress(del_address1,country,city,state,zip,userid);
	}
	
	/*
	 * public List<Object> displaydelAddress1(int userid) {
	 * 
	 * return this.deliveryrepository.getAddress1(userid); } public List<Object>
	 * displaydelAddress2(int userid) {
	 * 
	 * return this.deliveryrepository.getAddress2(userid); }
	 */
 	
	

		

}
