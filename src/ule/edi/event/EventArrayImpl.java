package ule.edi.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
		if (pos < 1 && pos > nSeats + 1) { // posicion no valida
			return null;
		} else if (this.seats[pos] == null) { // butaca no vendida
			return null;
		} else { // butaca vendida
			return this.seats[pos];
		}
	}

	@Override
	public Person refundSeat(int pos) {
		// TODO Auto-generated method stub
		Person aux = null;
		if (pos < 1 && pos > nSeats + 1) { // posicion no valida
			return null;
		} else if (this.seats[pos] == null) { // butaca no vendida
			return null;
		} else { // butaca vendida
			aux = this.seats[pos].getHolder();
			this.seats[pos] = null;
			return aux;
		}
	}

	@Override
	public boolean sellSeat(int pos, Person p, boolean advanceSale) {
		// TODO Auto-generated method stub
		if (pos < 1 && pos > nSeats + 1) {
			return false;
		} else if (this.seats[pos] != null) {
			return false;
		} else if (advanceSale) {
			Seat seat = new Seat(this, pos, Type.ADVANCE_SALE, p);
			this.seats[pos] = seat;
			return true;
		} else {
			Seat seat = new Seat(this, pos, Type.NORMAL, p);
			this.seats[pos] = seat;
			return true;
		}
	}

	@Override
	public int getNumberOfAttendingChildren() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getAdvanceSaleSeatsList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxNumberConsecutiveSeats() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Double getPrice(Seat seat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getCollectionEvent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPosPerson(Person p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isAdvanceSale(Person p) {
		// TODO Auto-generated method stub
		return false;
	}

}