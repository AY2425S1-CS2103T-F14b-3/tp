package seedu.address.model.contactdate;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import seedu.address.model.person.CallFrequency;

/**
 * Represents a Person's contacted date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidContactDate(String)}
 */
public class ContactDate {
    public static final String MESSAGE_CONSTRAINTS =
            "Dates should be in the format of YYYY-MM-DD";
    public static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    public final LocalDate value;
    private final String notes;

    /**
     * Constructs a {@code ContactDate}.
     *
     * @param date A valid date and time.
     */
    public ContactDate(String date, String notes) {
        requireNonNull(date);
        requireNonNull(notes);
        checkArgument(isValidContactDate(date), MESSAGE_CONSTRAINTS);
        value = LocalDate.parse(date);
        this.notes = notes;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidContactDate(String test) {
        try {
            if (!test.matches(VALIDATION_REGEX)) {
                return false;
            }
            LocalDate.parse(test);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns the current date.
     */
    public static ContactDate createCurrentDate(String notes) {
        return new ContactDate(LocalDate.now().toString(), notes);
    }

    /**
     * Returns a new {@code ContactDate} that is a specified number of days after the current date.
     * The number of days is determined by the {@code callFrequency}.
     *
     * @param callFrequency The frequency of calls, represented as a {@code CallFrequency} object,
     *                      which determines how many days to add to the current date.
     * @return A new {@code ContactDate} that is {@code callFrequency} days after the current date.
     */
    public ContactDate add(CallFrequency callFrequency) {
        int daysToAdd = Integer.parseInt(callFrequency.value); // Parse the number of days from callFrequency
        return new ContactDate(value.plusDays(daysToAdd).toString(), ""); // Create and return a new ContactDate
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ContactDate)) {
            return false;
        }

        ContactDate otherDate = (ContactDate) other;
        return value.equals(otherDate.value) && notes.equals(otherDate.notes);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
