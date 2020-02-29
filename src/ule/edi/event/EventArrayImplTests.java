package ule.edi.event;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.*;

import ule.edi.model.*;
import ule.edi.model.Configuration.Type;

public class EventArrayImplTests {

	private DateFormat dformat = null;
	private EventArrayImpl e;
	private EventArrayImpl e1;
	

	private Date parseLocalDate(String spec) throws ParseException {
        return dformat.parse(spec);
	}

	public EventArrayImplTests() {
		
		dformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	@Before
	public void testBefore() throws Exception{
	    e = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 110);
	    e1 = new EventArrayImpl("Peppa Pig",parseLocalDate("24/02/2018 17:00:00") , 90,35.6,(byte)7);
	}
	
	@Test
	public void testEventoVacio() throws Exception {
		
	    Assert.assertTrue(e.getNumberOfAvailableSeats()==110);
	    Assert.assertEquals(e.getNumberOfAvailableSeats(), 110);
	    Assert.assertEquals(e.getNumberOfAttendingAdults(), 0);
	}
	
	@Test
	public void testSellSeat1Adult() throws Exception{
		
			
	    Assert.assertEquals(e.getNumberOfAttendingAdults(), 0);
		Assert.assertTrue(e.sellSeat(1, new Person("10203040A","Alice", 34),false));	//venta normal
	    Assert.assertEquals(e.getNumberOfAttendingAdults(), 1);  
	    Assert.assertEquals(e.getNumberOfNormalSaleSeats(), 1);
	  
	}
	

	
	@Test
	public void testgetCollection() throws Exception{
		Event  ep = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 4);
		Assert.assertEquals(ep.sellSeat(1, new Person("1010", "AA", 10), true),true);
		Assert.assertTrue(ep.getCollectionEvent()==75);					
	}
	
	// TODO EL RESTO DE MÉTODOS DE TESTS NECESARIOS PARA LA COMPLETA COMPROBACIÓN DEL BUEN FUNCIONAMIENTO DE TODO EL CÓDIGO IMPLEMENTADO
	
	@Test
	public void testGetName() {
		Assert.assertEquals(e.getName(),"The Fabulous Five");
	}
	
	@Test 
	public void testGetEventDate() throws Exception {
		Assert.assertEquals(e.getDateEvent(), parseLocalDate("24/02/2018 17:00:00"));
	}
	
	@Test
	public void testGetPrice() {
		Assert.assertEquals(e.getPrice() ,Configuration.DEFAULT_PRICE);
	}
	
	@Test
	public void testGetDiscountAdvanceSale() {
		
		Assert.assertEquals(e.getDiscountAdvanceSale(), Configuration.DEFAULT_DISCOUNT);
	}
	
	@Test
	public void testGetNumberOfSoldSeats() {
		Person p = new Person("Javier","857412369E",19);
		e.sellSeat(1, p, false);
		
		Assert.assertEquals(e.getNumberOfSoldSeats(),1);
	}
	
	@Test
	public void testGetNumberOfNormalSoldSeatsSell() {
		Person p = new Person("Javier","857412369E",19);
		e.sellSeat(1, p, false);
		
		Assert.assertEquals(e.getNumberOfNormalSaleSeats(),1);
		
	}
	
	@Test
	public void testGetNumberOfNormalSoldSeatsNotSell() {
		Assert.assertEquals(e.getNumberOfNormalSaleSeats(), 0);
		
	}
	
	@Test
	public void testGetNumberOfNotNormalSoldSeatsSell() {
		Person p = new Person("Javier","857412369E",19);
		e.sellSeat(1, p, true);
		
		Assert.assertEquals(e.getNumberOfNormalSaleSeats(),0);
		
	}
	
	
	
	
	@Test
	public void testGetNumberOfAdvancedSoldSeatsSell() {
		Person p = new Person("Javier","857412369E",19);
		e.sellSeat(1, p, true);
		
		Assert.assertEquals(e.getNumberOfAdvanceSaleSeats(),1);
		
	}
	
	@Test
	public void testGetNumberOfAdvancedSoldSeatsNotSell() {
		Assert.assertEquals(e.getNumberOfAdvanceSaleSeats(), 0);
		
	}
	
	@Test
	public void testGetNumberOfNotAdvancedSoldSeatsSell() {
		Person p = new Person("Javier","857412369E",19);
		e.sellSeat(1, p, false);
		
		Assert.assertEquals(e.getNumberOfAdvanceSaleSeats(),0);
		
	}
	
	@Test
	public void testGetNumberOfSeats() {
		Assert.assertEquals(e.getNumberOfSeats(), 110);
	}
	
	@Test
	public void testGetNumberOfAvailableSeatsNotSell() {
		Assert.assertEquals(e.getNumberOfAvailableSeats(), 110);
	}
	
	@Test
	public void testGetNumberOfAvailableSeatsSell() {
		Person p = new Person("Javier","857412369E",19);
 		e.sellSeat(1, p, false);
		Assert.assertEquals(e.getNumberOfAvailableSeats(), 109);
	}
	 
	@Test 
	public void testGetSeatNegativePosition() {
		Assert.assertNull(e.getSeat(0));
	}
	
	@Test 
	public void testGetSeatMorePosition() {
		Assert.assertNull(e.getSeat(200));
	}
	
	@Test
	public void testGetSeatNotSell() {
		Assert.assertNull(e.getSeat(1));
	}
	
	@Test
	public void testGetSeatSell() {
		Person p = new Person("Javier","857412369E",19);
		Seat seat = new Seat(e, 2, Type.ADVANCE_SALE, p);
		e.sellSeat(2, p, true);
		Assert.assertEquals(e.getSeat(2).toString(),seat.toString());
	}
	
	@Test
	public void testRefundSeatNegativePosition() {
		Assert.assertNull(e.refundSeat(0));
	}
	
	@Test
	public void testRefundSeatMorePosition() {
		Assert.assertNull(e.refundSeat(2000));
	}
	
	@Test
	public void testRefundSeatNotSell() {
		Assert.assertNull(e.refundSeat(3));
	}
	
	@Test
	public void testRefundSeatSell() {
		Person p = new Person("Javier","857412369E",19);
		e.sellSeat(2, p, true);
		Assert.assertEquals(e.refundSeat(2).toString(),p.toString());
	}
	
	
	@Test
	public void testSellSeatNegativePosition() {
		Person p = new Person("Javier","857412369E",19);
		Assert.assertEquals(e.sellSeat(0, p, true),false);
	}
	
	@Test
	public void testSellSeatMorePosition() {
		Person p = new Person("Javier","857412369E",19);
		Assert.assertEquals(e.sellSeat(2000, p, true),false);
	}
	
	@Test
	public void testSellSeatNotEmpty() {
		Person p = new Person("Javier","857412369E",19);
		e.sellSeat(2, p, true);
		
		Assert.assertEquals(e.sellSeat(2, p, true),false);

	}

	@Test
	public void testChildrenAttendingNoSell() {
		Assert.assertEquals(e.getNumberOfAttendingChildren(), 0);
	}
	
	@Test
	public void testChildrenAttendingSellAdult() {
		Person p = new Person("Javier","857412369E",19);
		e.sellSeat(2, p, true);
		
		Assert.assertEquals(e.getNumberOfAttendingChildren(),0);
	}
	
	
	@Test
	public void testChildrenAttendingSellChildren() {
		Person p = new Person("Javier","857412369E",6);
		e.sellSeat(2, p, true);
		
		Assert.assertEquals(e.getNumberOfAttendingChildren(),1);
	}
	
	@Test
	public void testAdultsAttendingSellAdults() {
		Person p = new Person("Javier","857412369E",19);
		e.sellSeat(2, p, true);
		
		Assert.assertEquals(e.getNumberOfAttendingAdults(),1);
	}
	
	@Test
	public void testAdultsAttendingSellChildren() {
		Person p = new Person("Javier","857412369E",5);
		e.sellSeat(2, p, true);
		
		Assert.assertEquals(e.getNumberOfAttendingAdults(),0);
	}
	
	
	@Test
	public void testAdultsAttendingSellElder() {
		Person p = new Person("Javier","857412369E",70);
		e.sellSeat(2, p, true);
		
		Assert.assertEquals(e.getNumberOfAttendingAdults(),0);
	}
	
	@Test
	public void testElderAttendingNotSell() {
		Assert.assertEquals(e.getNumberOfAttendingElderlyPeople(),0);
		
	}
	
	@Test
	public void testElderAttendingSellChildren() {
		Person p = new Person("Javier","857412369E",12);
		e.sellSeat(2, p, true);
		
		Assert.assertEquals(e.getNumberOfAttendingElderlyPeople(),0);
	}
	
	@Test
	public void testElderAttendingSellAdult() {
		Person p = new Person("Javier","857412369E",36);
		e.sellSeat(2, p, true);
		
		Assert.assertEquals(e.getNumberOfAttendingElderlyPeople(),0);
	}
	
	@Test
	public void testElderAttendingSellElder() {
		Person p = new Person("Javier","857412369E",85);
		e.sellSeat(2, p, true);
		
		Assert.assertEquals(e.getNumberOfAttendingElderlyPeople(),1);
	}
	
	@Test 
	public void testGetAvailableSeatsListNotSell() {
		Assert.assertNotNull(e.getAvailableSeatsList());
	}
	
	@Test
	public void testGetAvailableSeatsListSell() {
		Person p = new Person("Javier","857412369E",85);
		for(int i = 0; i<e.getNumberOfSeats()+1;i++) {
			e.sellSeat(i, p, false);
		}
		
		Assert.assertEquals(e.getAvailableSeatsList(),new ArrayList<Integer>());
	}
	
	@Test
	public void testGetAdvanceSeatsListNotSell() {
		
		Assert.assertEquals(e.getAdvanceSaleSeatsList(),new ArrayList<Integer>());

		
	}
	
	@Test
	public void testGetAdvanceSeatsListSellAdvanced() {
		Person p = new Person("Javier","857412369E",85);
		for(int i = 0; i<e.getNumberOfSeats()+1;i++) {
			e.sellSeat(i, p, true);
		}
		
		Assert.assertNotNull(e.getAdvanceSaleSeatsList());
	}
	
	@Test
	public void testGetAdvanceSeatsListSellNotAdvanced() {
		Person p = new Person("Javier","857412369E",85);
		for(int i = 0; i<e.getNumberOfSeats()+1;i++) {
			e.sellSeat(i, p, false);
		}
		
		Assert.assertEquals(e.getAdvanceSaleSeatsList(),new ArrayList<Integer>());

		
	}
	
	
	@Test
	public void testGetMaxConsecutiveNotSell() {
		Assert.assertEquals(e.getMaxNumberConsecutiveSeats(),110);
	}
	
	@Test
	public void testGetMaxConsecutiveSellFirst() {
		Person p = new Person("Javier","857412369E",85);
		e.sellSeat(1, p,true);
		Assert.assertEquals(e.getMaxNumberConsecutiveSeats(),109);
	}
	
	@Test
	public void testGetMaxConsecutiveSellNotFirst() {
		Person p = new Person("Javier","857412369E",85);
		e.sellSeat(5, p,true);
		Assert.assertEquals(e.getMaxNumberConsecutiveSeats(),105);
		
	}
	
	@Test
	public void testGetPriceNotSell() {
		Person p = new Person("Javier","857412369E",19);
		Seat seat = null;
	
		Assert.assertTrue(e.getPrice(seat)==0);
				
	}
	
	@Test
	public void testGetPriceNotThisEvent() {
		Person p = new Person("Javier","857412369E",19);
		Seat seat = new Seat(e1, 1 ,Type.NORMAL, p);
		
		e1.sellSeat(1, p, false);
		
		Assert.assertTrue(e.getPrice(seat)==0);
		
	}
	
	@Test 
	public void testGetCollectionNotThisEvent() {
//		Person p = new Person("Javier","857412369E",19);
		Assert.assertTrue(e.getCollectionEvent()==0);
		
	}

	
	
	
	
	
	
	
	
	

}
