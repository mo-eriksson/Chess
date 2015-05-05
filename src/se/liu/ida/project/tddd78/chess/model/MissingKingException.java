package se.liu.ida.project.tddd78.chess.model;

import java.awt.*;

/**
 * Error handling class for if not a king of the specified color. If not found on the board then a big bug has occurred.
 * No need to catch, should terminate program.
 */

@SuppressWarnings({ "UncheckedExceptionClass", "UnusedDeclaration", "SuppressionAnnotation" })
public class MissingKingException extends RuntimeException {
    /**
     * Just for information when debugging.
     */
    @SuppressWarnings("FieldCanBeLocal") private final Color ofColor;

	public MissingKingException(String message, Color ofColor) {
	    super(message);
	    this.ofColor = ofColor;
	}
}
