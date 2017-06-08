package view;

import java.awt.Toolkit;

public interface Values {
	
	double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	String LOGO = "img/logo.png";
	int FONT_MIN_SIZE = 10;
	int EXIT_APP = 0;
	String DB_ERROR = "Connection not stablished.";
	String INVALID_FIELD = "Username or password incorrect.";
	String INVALID_CHARACTERS = "Characters like (\"  \'  ;) are not allowed.";
	String ITEM_NOT_FOUND = "Content not found";
	String EMAIL_IS_USED = "This email is already used";
	String RENTAL_FINALIZED = "Finalized";
	String RENTAL_IN_PROGRESS = "In Progress";
	String DELETED_USER_INFORMATIONS = "Data not found.";
	String DELETED_USER = "User not found.";
	String NEW_REQUEST_MESSAGE = "Hey, you have a new request";
}
