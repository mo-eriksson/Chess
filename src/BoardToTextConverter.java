/**
 * Created by ame on 2015-02-05.
 */
public class BoardToTextConverter
{
    public static String convertToText(Board board) {
	StringBuilder builder = new StringBuilder();

	for (int i = 0; i < board.getBoardHeight(); i++) {
	    builder.append("\n");
	    for (int j = 0; j < board.getBoardWidth(); j++) {
		switch (board.getPieceFromCoordinate(i, j)) {
		    case EMPTY_BLACK:
			builder.append(" BLACK ");
			break;
		    case EMPTY_WHITE:
			builder.append(" WHITE ");
			break;

		    case KING:
			builder.append("%");
			break;
		    case KNIGHT:
			builder.append("&");
			break;
		    case QUEEN:
			builder.append("@");
			break;
		    case ROOK:
			builder.append("£");
			break;
		    case PAWN:
			builder.append("$");
			break;
		    case BISHOP:
			builder.append("+");
			break;
		    default:
			builder.append("BADDDD");
			break;
		}
	    }
	}
	String result = builder.toString();
	return result;
    }
}
