package es.ull.passengers;

import es.ull.flights.Flight;
import es.ull.passengers.Passenger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestPassengers {

    @Test
    public void testAdd() {
        String str = "Junit is working fine";
        assertEquals("Junit is working fine",str);
    }

    @Test
    void testValidPassengerCreation() {
        assertDoesNotThrow(() -> new Passenger("P001", "Maria Diaz", "US"));
    }

    @Test
    void testInvalidCountryCode() {
        Exception exception = assertThrows(RuntimeException.class, () -> new Passenger("P001", "Maria Diaz", "ZZ"));
        assertEquals("Invalid country code", exception.getMessage());
    }

    @Test
    void testJoinFlightSuccessfully() {
        Flight flight = new Flight("AB123", 2);
        Passenger passenger = new Passenger("P001", "Maria Diaz", "US");
        assertDoesNotThrow(() -> passenger.joinFlight(flight));
        assertEquals(flight, passenger.getFlight());
        assertEquals(1, flight.getNumberOfPassengers());
    }

    @Test
    void testSwitchFlights() {
        Flight flight1 = new Flight("AB123", 2);
        Flight flight2 = new Flight("CD456", 2);
        Passenger passenger = new Passenger("P001", "Maria Diaz", "US");

        passenger.joinFlight(flight1);
        passenger.joinFlight(flight2);

        assertEquals(flight2, passenger.getFlight());
        assertEquals(0, flight1.getNumberOfPassengers());
        assertEquals(1, flight2.getNumberOfPassengers());
    }

    @Test
    void testLeaveFlight() {
        Flight flight = new Flight("AB123", 2);
        Passenger passenger = new Passenger("P001", "Maria Diaz", "US");
        passenger.joinFlight(flight);
        passenger.joinFlight(null);

        assertNull(passenger.getFlight());
        assertEquals(0, flight.getNumberOfPassengers());
    }

    @Test
    void testToString() {
        Passenger passenger = new Passenger("P001", "Maria Diaz", "US");
        String expected = "Passenger Maria Diaz with identifier: P001 from US";
        assertEquals(expected, passenger.toString());
    }

    // test para comprobar si los atributos son instancias de clase como String, ArrayList o lo que sean
    @Test
    void testStringId() {
        Passenger passenger = new Passenger("P001", "Maria Diaz", "US");
        assertNotNull(passenger.getIdentifier());
    }

    @Test
    void testName() {
        Passenger passenger = new Passenger("P001", "Maria Diaz", "US");
        assertNotNull(passenger.getName());
    }

    @Test
    void testCountryCode() {
        Passenger passenger = new Passenger("P001", "Maria Diaz", "US");
        assertNotNull(passenger.getCountryCode());
    }

    // que un passenger sea instancia de la clase Passenger
    @Test
    void testPassenger() {
        Passenger passenger = new Passenger("P001", "Maria Diaz", "US");
        assertTrue(passenger instanceof Passenger);
    }

    @Test
    void testFlight() {
        Flight flight = new Flight("AB123", 2);
        assertNotNull(flight);
    }

    @Test
    void testFlightNumber() {
        Flight flight = new Flight("AB123", 2);
        assertEquals("AB123", flight.getFlightNumber());
    }

    @Test
    void testFlightPassengers() {
        Flight flight = new Flight("AB123", 2);
        assertEquals(0, flight.getNumberOfPassengers());
    }

    @Test
    void testFlightAddPassenger() {
        Flight flight = new Flight("AB123", 2);
        Passenger passenger = new Passenger("P001", "Maria Diaz", "US");
        assertTrue(flight.addPassenger(passenger));
    }

    @Test
    void testFlightRemovePassenger() {
        Flight flight = new Flight("AB123", 2);
        Passenger passenger = new Passenger("P001", "Maria Diaz", "US");
        flight.addPassenger(passenger);
        assertTrue(flight.removePassenger(passenger));
    }

    @Test
    void testFlightPassengerCount() {
        Flight flight = new Flight("AB123", 2);
        Passenger passenger = new Passenger("P001", "Maria Diaz", "US");
        flight.addPassenger(passenger);
        assertEquals(1, flight.getNumberOfPassengers());
    }

    @Test
    void testFlightPassengerCountExceedingCapacity() {
        Flight flight = new Flight("AB123", 1);
        Passenger passenger1 = new Passenger("P001", "Maria Diaz", "US");
        Passenger passenger2 = new Passenger("P002", "Juan Perez", "US");
        flight.addPassenger(passenger1);
        Exception exception = assertThrows(RuntimeException.class, () -> flight.addPassenger(passenger2));
        assertEquals("Not enough seats for flight AB123", exception.getMessage());
    }

    // comparamos igualdad entre pasajeros
    @Test
    void testPassengerEquality() {
        Passenger passenger1 = new Passenger("P001", "Maria Diaz", "US");
        Passenger passenger2 = new Passenger("P001", "Maria Diaz", "US");
        assertEquals(passenger1.getIdentifier(), passenger2.getIdentifier());
    }

    // comparamos igualdad entre vuelos
    @Test
    void testFlightEquality() {
        Flight flight1 = new Flight("AB123", 2);
        Flight flight2 = new Flight("AB123", 2);
        assertEquals(flight1.getFlightNumber(), flight2.getFlightNumber());
    }
}