package com.ambapharm.helpers;

import androidx.room.TypeConverter;

import java.util.Date;


/**
 * Contains TypeConverters for the Room database to handle conversions between complex data types
 * and types that can be readily stored in the database. Specifically, this class provides methods
 * for converting Date objects to and from Unix timestamps, which are represented as Long values.
 */
public class Converters {

    /**
     * Converts a Unix timestamp (Long) to a Date object.
     * This method is used by Room to read Date values from the database.
     *
     * @param value The Unix timestamp to be converted.
     * @return A Date object corresponding to the timestamp, or null if the input value is null.
     */
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }


    /**
     * Converts a Date object to a Unix timestamp (Long).
     * This method is used by Room to write Date values to the database.
     *
     * @param date The Date object to be converted.
     * @return A Unix timestamp corresponding to the Date object, or null if the Date is null.
     */
    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
