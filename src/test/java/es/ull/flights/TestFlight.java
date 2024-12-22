package es.ull.flights;

import es.ull.flights.Flight;
import es.ull.passengers.Passenger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestFlight {

    @Test
    public void testAdd() {
        String str = "Junit is working fine";
        assertEquals("Junit is working fine",str);
    }

    @Test
    void testValidFlightNumber() {
        assertDoesNotThrow(() -> new Flight("AB123", 100));
    }

    @Test
    void testInvalidFlightNumber() {
        Exception exception = assertThrows(RuntimeException.class, () -> new Flight("123AB", 100));
        assertEquals("Invalid flight number", exception.getMessage());
    }

    @Test
    void testAddPassengerSuccessfully() {
        Flight flight = new Flight("AB123", 2);
        Passenger passenger = new Passenger("P001", "Maria Diaz", "US");
        assertTrue(flight.addPassenger(passenger));
        assertEquals(1, flight.getNumberOfPassengers());
    }
    @Test
    void testAddPassengerExceedingCapacity() {
        Flight flight = new Flight("AB123", 1);
        Passenger passenger1 = new Passenger("P001", "Maria Diaz", "US");
        Passenger passenger2 = new Passenger("P002", "Maria Diaz", "US");
        flight.addPassenger(passenger1);

        Exception exception = assertThrows(RuntimeException.class, () -> flight.addPassenger(passenger2));
        assertEquals("Not enough seats for flight AB123", exception.getMessage());
    }

    @Test
    void testRemovePassengerSuccessfully() {
        Flight flight = new Flight("AB123", 2);
        Passenger passenger = new Passenger("P001", "Maria Diaz", "US");
        flight.addPassenger(passenger);
        assertTrue(flight.removePassenger(passenger));
        assertEquals(0, flight.getNumberOfPassengers());
    }

    @Test
    void testRemovePassengerNotInFlight() {
        Flight flight = new Flight("AB123", 2);
        Passenger passenger = new Passenger("P001", "Maria Diaz", "US");
        Passenger passenger2 = new Passenger("P002", "Maria Diaz", "US");
        flight.addPassenger(passenger);
        assertFalse(flight.removePassenger(passenger2));
    }

    @Test
    void testRemovePassengerFromEmptyFlight() {
        Flight flight = new Flight("AB123", 2);
        Passenger passenger = new Passenger("P001", "Maria Diaz", "US");
        assertFalse(flight.removePassenger(passenger));
    }

    @Test
    void testRemovePassengerTwice() {
        Flight flight = new Flight("AB123", 2);
        Passenger passenger = new Passenger("P001", "Maria Diaz", "US");
        flight.addPassenger(passenger);
        flight.removePassenger(passenger);
        assertFalse(flight.removePassenger(passenger));
    }

    // Miramos las instancias de los atributos, que no sean null y todo eso
    @Test
    void testFlightAttributes() {
        Flight flight = new Flight("AB123", 2);
        assertNotNull(flight.getFlightNumber());
        assertNotNull(flight.getNumberOfPassengers());
    }

    @Test
    void testFlightToString() {
        Flight flight = new Flight("AB123", 2);
        assertEquals("AB123", flight.getFlightNumber());
    }

    @Test
    void testFlightEquals() {
        Flight flight1 = new Flight("AB123", 2);
        Flight flight2 = new Flight("AB123", 2);
        assertEquals(flight1.getFlightNumber(), flight2.getFlightNumber());
    }

    @Test
    void testFlightHashCode() {
        Flight flight1 = new Flight("AB123", 2);
        Flight flight2 = new Flight("AB123", 2);
        assertNotEquals(flight1.hashCode(), flight2.hashCode());
    }

    @Test
    void testInstance() {
        Flight flight = new Flight("AB123", 2);
        assertNotNull(flight);
    }

    @Test
    void testFlightNumber() {
        Flight flight = new Flight("AB123", 2);
        assertEquals("AB123", flight.getFlightNumber());
    }

    @Test
    void testAddPassenger() {
        Flight flight = new Flight("AB123", 2);
        Passenger passenger = new Passenger("P001", "John Doe", "US");
        assertTrue(flight.addPassenger(passenger));
    }

    @Test
    void testRemovePassenger() {
        Flight flight = new Flight("AB123", 2);
        Passenger passenger = new Passenger("P001", "John Doe", "US");
        flight.addPassenger(passenger);
        assertTrue(flight.removePassenger(passenger));
    }

    @Test
    void testGetNumberOfPassengers() {
        Flight flight = new Flight("AB123", 2);
        Passenger passenger = new Passenger("P001", "Maria Diaz", "US");
        flight.addPassenger(passenger);
        assertEquals(1, flight.getNumberOfPassengers());
    }
}