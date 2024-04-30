package com.millrocious.fitness_jet_app.core.data.type_converter

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

object TimestampTypeConverter {
    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    @TypeConverter
    fun toOffsetDateTime(value: String?): OffsetDateTime? = value?.let {
        return formatter.parse(it, OffsetDateTime::from)
    }

    @TypeConverter
    fun fromOffsetDateTime(date: OffsetDateTime?) = date?.format(formatter)

    @TypeConverter
    fun toLocalDate(value: String?): LocalDate? = value?.let {
        return LocalDate.parse(value, formatter)
    }

    @TypeConverter
    fun fromLocalDate(date: LocalDate?) = date?.format(formatter)

    @TypeConverter
    fun toLocalDate(offsetDateTime: OffsetDateTime?): LocalDate? {
        return offsetDateTime?.toLocalDate()
    }
}