package ule.edi.event;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	    e1 = new EventArrayImpl("The faboulous five",parseLocalDate("24/02/2018 17:00:00") , 90,35.6,(byte)7);
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
		Person p = new Person("Javier","857412369E",19);
		Assert.assertNull(e.getSeat(0));
	}
	
	@Test 
	public void testGetSeatMorePosition() {
		Person p = new Person("Javier","857412369E",19);
		Assert.assertNull(e.getSeat(200));
	}
	
	@Test
	public void testGetSeatNotSell() {
		Person p = new Person("Javier","857412369E",19);
		Assert.assertNull(e.getSeat(1));
	}
	
	@Test
	public void testGetSeatSell() {
		Person p = new Person("Javier","857412369E",19);
		Seat seat = new Seat(e, 2, Type.ADVANCE_SALE, p);
		e.sellSeat(2, p, true);
		Assert.assertEquals(e.getSeat(2).toString(),seat.toString());
	}
	
	
	
	
	

}
