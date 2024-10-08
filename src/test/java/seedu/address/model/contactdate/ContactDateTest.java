package seedu.address.model.contactdate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.CallFrequency;

public class ContactDateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ContactDate(null));
    }

    @Test
    public void constructor_invalidContactDate_throwsIllegalArgumentException() {
        String invalidContactDate = "";
        assertThrows(IllegalArgumentException.class, () -> new ContactDate(invalidContactDate));
    }

    @Test
    public void isValidContactDate() {
        // null tag contact date
        assertThrows(NullPointerException.class, () -> ContactDate.isValidContactDate(null));

        // invalid contact date
        assertFalse(ContactDate.isValidContactDate("2020-13-01"));
        assertFalse(ContactDate.isValidContactDate("2020-01-32"));
        assertFalse(ContactDate.isValidContactDate(""));

        // valid contact date
        assertTrue(ContactDate.isValidContactDate("2020-01-01"));
    }

    @Test
    public void addCallFrequency_validCallFrequency_success() {
        ContactDate contactDate = new ContactDate("2020-01-01");
        CallFrequency callFrequency = new CallFrequency("7");
        ContactDate newContactDate = contactDate.add(callFrequency);
        ContactDate targetContactDate = new ContactDate("2020-01-08");
        assertEquals(newContactDate, targetContactDate);
    }

    @Test
    public void equals() {
        ContactDate contactDate = new ContactDate("2020-01-01");

        // same values -> returns true
        ContactDate contactDateCopy = new ContactDate("2020-01-01");
        assertTrue(contactDate.equals(contactDateCopy));

        // same object -> returns true
        assertTrue(contactDate.equals(contactDate));

        // null -> returns false
        assertFalse(contactDate.equals(null));

        // different type -> returns false
        assertFalse(contactDate.equals(5.0f));

        // different contact date -> returns false
        ContactDate differentContactDate = new ContactDate("2020-01-02");
        assertFalse(contactDate.equals(differentContactDate));
    }

}
