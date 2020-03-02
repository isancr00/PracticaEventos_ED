package ule.edi.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Vector;

import ule.edi.model.*;
import ule.edi.model.Configuration.Type;

public class EventArrayImpl implements Event {

	private String name;
	private Date eventDate;
	private int nSeats;

	private Double price; // precio de entradas
	private Byte discountAdvanceSale; // descuento en venta anticipada (0..100)

	private Seat[] seats;

	public EventArrayImpl(String name, Date date, int nSeats) {
		// TODO
		// utiliza los precios por defecto: DEFAULT_PRICE y DEFAULT_DISCOUNT definidos
		// en Configuration.java
		// Debe crear el array de butacas

		this.name = name;
		this.eventDate = date;
		this.price = Configuration.DEFAULT_PRICE;
		this.discountAdvanceSale = Configuration.DEFAULT_DISCOUNT;
		this.nSeats = nSeats;
		this.seats = new Seat[nSeats];

	}

	public EventArrayImpl(String name, Date date, int nSeats, Double price, Byte discount) {
		// TODO
		// Debe crear el array de butacas

		this.name = name;
		this.eventDate = date;
		this.price = price;
		this.discountAdvanceSale = discount;
		this.seats = new Seat[nSeats];

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public Date getDateEvent() {
		// TODO Auto-generated method stub
		return this.eventDate;
	}

	@Override
	public Double getPrice() {
		// TODO Auto-generated method stub
		return this.price;
	}

	@Override
	public Byte getDiscountAdvanceSale() {
		// TODO Auto-generated method stub
		return this.discountAdvanceSale;
	}

	@Override
	public int getNumberOfSoldSeats() {

		int soldSeats = 0;

		for (int i = 0; i < this.nSeats; i++) {
			if (this.seats[i] != null) {
				soldSeats += 1;
			}
		}

		return soldSeats;
	}

	@Override
	public int getNumberOfNormalSaleSeats() {
		// TODO Auto-generated method stub

		int normalSeatsSold = 0;

		for (int i = 0; i < this.nSeats; i++) {
			if (this.seats[i] != null) {
				if (this.seats[i].getType() == Type.NORMAL) {
					normalSeatsSold += 1;
				}
			}
		}

		return normalSeatsSold;
	}

	@Override
	public int getNumberOfAdvanceSaleSeats() {
		// TODO Auto-generated method stub
		int advanceSeatsSold = 0;

		for (int i = 0; i < this.nSeats; i++) {
			if (this.seats[i] != null) {
				if (this.seats[i].getType() == Type.ADVANCE_SALE) {
					advanceSeatsSold += 1;
				}
			}
		}

		return advanceSeatsSold;
	}

	@Override
	public int getNumberOfSeats() {
		// TODO Auto-generated method stub
		return this.nSeats;
	}

	@Override
	public int getNumberOfAvailableSeats() {
		// TODO Auto-generated method stub
		int availableSeats = 0;

		for (int i = 0; i < this.nSeats; i++) {
			if (this.seats[i] == null) {
				availableSeats += 1;
			}
		}

		return availableSeats;

	}

	@Override
	public Seat getSeat(int pos) {
		// TODO Auto-generated method stub
		if (pos < 1 || pos > nSeats + 1) { // posicion no valida
			return null;
		} else if (this.seats[pos-1] == null) { // butaca no vendida
			return null;
		} else { // butaca vendida
			return this.seats[pos-1];
		}
	}

	@Override
	public Person refundSeat(int pos) {
		// TODO Auto-generated method stub
		Person aux = null;
		if (pos < 1 || pos > nSeats) { // posicion no valida
			return null;
		} else if (this.seats[pos-1] == null) { // butaca no vendida
			return null;
		} else { // butaca vendida
			aux = this.seats[pos-1].getHolder();
			this.seats[pos-1] = null;
			return aux;
		}
	}

	@Override
	public boolean sellSeat(int pos, Person p, boolean advanceSale) {
		// TODO Auto-generated method stub
		if (pos < 1 || pos > nSeats) {
			return false;
		} else if (this.seats[pos-1] != null) {
			return false;
		} else if (advanceSale) {
			Seat seat = new Seat(this, pos-1, Type.ADVANCE_SALE, p);
			this.seats[pos-1] = seat;
			return true;
		} else {
			Seat seat = new Seat(this, pos-1, Type.NORMAL, p);
			this.seats[pos-1] = seat;
			return true;
		}
	}

	@Override
	public int getNumberOfAttendingChildren() {

		int childrenAttending = 0;

		for (int i = 0; i < this.nSeats; i++) {
			if (this.seats[i] != null) {
				if (this.seats[i].getHolder().getAge() < Configuration.CHILDREN_EXMAX_AGE) {
					childrenAttending += 1;
				}
			}
		}

		return childrenAttending;
	}

	@Override
	public int getNumberOfAttendingAdults() {
		int adultsAttending = 0;

		for (int i = 0; i < this.nSeats; i++) {
			if (this.seats[i] != null) {
				if (this.seats[i].getHolder().getAge() > Configuration.CHILDREN_EXMAX_AGE && this.seats[i].getHolder().getAge() < Configuration.ELDERLY_PERSON_INMIN_AGE) {
					adultsAttending += 1;
				}
			}
		}

		return adultsAttending;
	}

	@Override
	public int getNumberOfAttendingElderlyPeople() {
		int elderAttending = 0;

		for (int i = 0; i < this.nSeats; i++) {
			if (this.seats[i] != null) {
				if (this.seats[i].getHolder().getAge() > Configuration.ELDERLY_PERSON_INMIN_AGE) {
					elderAttending += 1;
				}
			}
		}

		return elderAttending;
	}

	@Override
	public List<Integer> getAvailableSeatsList() {
		
		List<Integer> availableSeats = new ArrayList<Integer>();
		
	
		for (int i = 0; i < this.nSeats; i++) {
			if (this.seats[i] == null) {
				availableSeats.add(i+1);
			}
		}
		
		return availableSeats;
	}

	@Override
	public List<Integer> getAdvanceSaleSeatsList() {
		List<Integer> advanceSellSeats = new ArrayList<Integer>();
		
		
		for (int i = 0; i < this.nSeats; i++) {
			if (this.seats[i] != null) {
				if(this.seats[i].getType()== Type.ADVANCE_SALE) {
					advanceSellSeats.add(i+1);
				}
			}
		}
		
		return advanceSellSeats;
	}


	@Override
	public int getMaxNumberConsecutiveSeats() {
		
		int aux = 0;	
		int maxConsecutive = 0;
		
		for (int i = 0; i < this.nSeats; i++) {
			if (this.seats[i] == null) {
				aux++;
				
				if(aux>maxConsecutive) {
					maxConsecutive = aux;
				}
				
			}else {
				aux = 0;
			}
		}

		
		return maxConsecutive;
	}

	@Override
	public Double getPrice(Seat seat) {
		
		Double finalPrice=(double) 0;
		if(seat == null || seat.getEvent() != this) {
			return (double) 0;
		}
		
		finalPrice = this.price - this.discountAdvanceSale;
		
		return finalPrice;
		
	}

	@Override
	public Double getCollectionEvent() {
		
		Double totalPrice = (double) 0;
			
		for (int i = 0; i < this.nSeats; i++) {
			if (this.seats[i] == null) {
				totalPrice += 0;
			}else {
				totalPrice += (this.price-this.discountAdvanceSale);
			}
		}		
		return totalPrice;
	}

	@Override
	public int getPosPerson(Person p) {
		
		int position = -1;
		
		int i=0;
		
		while(i<this.nSeats && this.seats[i] != null && this.seats[i].getHolder().equals(p)) {
			position = i+1;
			i++;
		}		
		return position;

	}

	@Override
	public boolean isAdvanceSale(Person p) {
		boolean advance = false;
		int i = 0;

		
		while(i<this.nSeats && this.seats[i]!= null && this.seats[i].getHolder().equals(p) && this.seats[i].getType() == Type.ADVANCE_SALE){
			advance = true;
			i++;
		}
		
		return advance;

	}

}