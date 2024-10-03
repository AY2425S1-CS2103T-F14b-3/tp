package seedu.address.model.contactdate;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a Person's contacted date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class ContactDate {
    public static final String MESSAGE_CONSTRAINTS =
            "Dates should be in the format of YYYY-MM-DD";
    public static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    public final LocalDate value;

    /**
     * Constructs a {@code ContactDate}.
     *
     * @param date A valid date.
     */
    public ContactDate(String date) {
        requireNonNull(date);
        checkArgument(isValidContactDate(date), MESSAGE_CONSTRAINTS);
        value = LocalDate.parse(date);
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
    public static ContactDate getCurrentDate() {
        return new ContactDate(LocalDate.now().toString());
    }

    @Override
    public String toString() {
        return value.toString();
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
        return value.equals(otherDate.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}