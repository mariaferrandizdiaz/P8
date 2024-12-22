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
 * @file Passenger.java
 * @brief This file contains the implementation of the Passenger class, which represents
 *        a passenger with identification details and flight association.
 *
 * The Passenger class stores information about individual passengers, validates
 * their country codes, and manages their association with a flight.
 *
 * @date 22/12/2024
 */
package es.ull.passengers;

import java.util.Arrays;
import java.util.Locale;
import es.ull.flights.Flight;

/**
 * @class Passenger
 * @brief Represents a passenger with unique identification and flight association.
 */
public class Passenger {

    private String identifier; ///< Unique identifier for the passenger.
    private String name; ///< Name of the passenger.
    private String countryCode; ///< ISO country code of the passenger.
    private Flight flight; ///< Flight associated with the passenger.

    /**
     * @brief Constructs a new Passenger object.
     *
     * @param identifier A string representing the unique identifier of the passenger.
     * @param name A string representing the name of the passenger.
     * @param countryCode A string representing the ISO country code of the passenger.
     *
     * @throws RuntimeException if the provided country code is invalid.
     */
    public Passenger(String identifier, String name, String countryCode) {
        if (!Arrays.asList(Locale.getISOCountries()).contains(countryCode)) {
            throw new RuntimeException("Invalid country code");
        }

        this.identifier = identifier;
        this.name = name;
        this.countryCode = countryCode;
    }

    // getters
    /**
     * @brief Retrieves the passenger's unique identifier.
     *
     * @return A string representing the identifier.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @brief Retrieves the passenger's name.
     *
     * @return A string representing the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @brief Retrieves the passenger's country code.
     *
     * @return A string representing the ISO country code.
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @brief Retrieves the flight associated with the passenger.
     *
     * @return The Flight object associated with the passenger.
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * @brief Associates the passenger with a new flight.
     *
     * If the passenger is already associated with a flight, they are removed from it before
     * being added to the new flight.
     *
     * @param flight The new Flight object to associate with the passenger.
     *
     * @throws RuntimeException if the passenger cannot be added to or removed from a flight.
     */
    public void joinFlight(Flight flight) {
        Flight previousFlight = this.flight;
        if (null != previousFlight) {
            if (!previousFlight.removePassenger(this)) {
                throw new RuntimeException("Cannot remove passenger");
            }
        }
        setFlight(flight);
        if (null != flight) {
            if (!flight.addPassenger(this)) {
                throw new RuntimeException("Cannot add passenger");
            }
        }
    }

    /**
     * @brief Sets the flight associated with the passenger.
     *
     * @param flight The Flight object to associate with the passenger.
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * @brief Provides a string representation of the passenger.
     *
     * @return A formatted string containing the passenger's name, identifier, and country code.
     */
    @Override
    public String toString() {
        return "Passenger " + getName() + " with identifier: " + getIdentifier() + " from " + getCountryCode();
    }
}