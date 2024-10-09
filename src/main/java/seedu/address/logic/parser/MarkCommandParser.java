package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contactdate.ContactDate;
import seedu.address.model.person.Nric;

/**
 * Parses input arguments and creates a new MarkCommand object
 */
public class MarkCommandParser implements Parser<MarkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the MarkCommand
     * and returns an MarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NOTES);

        String preamble = argMultimap.getPreamble();
        String notes = argMultimap.getValue(PREFIX_NOTES).orElse("");
        ContactDate contactDate = ContactDate.createCurrentDate(notes);
        if (ParserUtil.isParsingIndex(preamble)) {
            Index index = ParserUtil.parseIndex(preamble);
            return new MarkCommand(index, contactDate);
        } else if (ParserUtil.isParsingNric(preamble)) {
            Nric nric = ParserUtil.parseNric(preamble);
            return new MarkCommand(nric, contactDate);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
        }
    }
}
