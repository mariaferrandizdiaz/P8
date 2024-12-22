/*
 * ========================================================================
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */

/**
 * @file Flight.java
 * @brief This file contains the implementation of the Flight class, which manages
 *        flight details and passenger operations.
 *
 * The Flight class is responsible for storing flight information, validating
 * flight numbers, and managing passenger lists. It ensures that the number of
 * passengers does not exceed the available seats and maintains a set of
 * passengers assigned to the flight.
 *
 * @date 22/12/2024
 */
package es.ull.flights;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.ull.passengers.Passenger;

/**
 * @class Flight
 * @brief Represents a flight with a unique flight number, capacity, and passenger list.
 */
public class Flight {

    private String flightNumber; ///< The unique flight number for this flight.
    private int seats; ///< The total number of available seats on the flight.
    private Set<Passenger> passengers = new HashSet<>(); ///< Set of passengers on the flight.


    private static String flightNumberRegex = "^[A-Z]{2}\\d{3,4}$"; ///< Regex pattern for validating flight numbers.
    private static Pattern pattern = Pattern.compile(flightNumberRegex); ///< Compiled regex pattern.

    /**
     * @brief Constructs a new Flight object.
     *
     * @param flightNumber A string representing the flight number (e.g., "AB123").
     *                     Must match the regex pattern.
     * @param seats The total number of available seats on the flight.
     *
     * @throws RuntimeException if the flight number is invalid.
     */
    public Flight(String flightNumber, int seats) {
        Matcher matcher = pattern.matcher(flightNumber);
        if (!matcher.matches()) {
            throw new RuntimeException("Invalid flight number");
        }
        this.flightNumber = flightNumber;
        this.seats = seats;
    }

    /**
     * @brief Retrieves the flight number of this flight.
     *
     * @return A string representing the flight number.
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * @brief Retrieves the total number of available seats on this flight.
     *
     * @return An integer representing the number of available seats.
     */
    public int getNumberOfPassengers() {
        return passengers.size();
    }

    /**
     * @brief Adds a passenger to the flight.
     *
     * @param passenger The Passenger object to be added.
     *
     * @return True if the passenger was added successfully, false otherwise.
     *
     * @throws RuntimeException if there are no available seats.
     */
    public boolean addPassenger(Passenger passenger) {
        if (getNumberOfPassengers() >= seats) {
            throw new RuntimeException("Not enough seats for flight " + getFlightNumber());
        }
        passenger.setFlight(this);
        return passengers.add(passenger);
    }

    /**
     * @brief Removes a passenger from the flight.
     *
     * @param passenger The Passenger object to be removed.
     *
     * @return True if the passenger was removed successfully, false otherwise.
     */
    public boolean removePassenger(Passenger passenger) {
        passenger.setFlight(null);
        return passengers.remove(passenger);
    }
}
