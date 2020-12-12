package application;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;



public class Main extends Application {

	private ExpenseTracker expTrcker = new ExpenseTracker();
	private User currentSystemUser = null; // stores user currently logged in

	// stage
	Stage stage;

	// scenes
	Scene sceneHome;
	Scene sceneRegister1;
	Scene sceneRegister2;
	Scene sceneLogin;
	Scene sceneLoggedIn;
	Scene sceneUpdate;
	Scene sceneCheck;

	// pane
	private Pane homePane;
	private Pane registerPane1;
	private Pane registerPane2;
	private Pane loginPane;
	private Pane loggedInPane;
	private Pane updatePane;
	private Pane checkPane;

	@Override
	public void start(Stage primaryStage) throws Exception {

		// set stage
		stage = primaryStage;

		// set panes
		homePane = createHomePane();
		registerPane1 = createRegisterPane1();
		loginPane = createLoginPane();
		loggedInPane = createLoggedInPane();
		updatePane = createUpdatePane();
		// checkPane = createCheckPane();

		// set scenes
		sceneHome = new Scene(homePane, 750, 500);
		sceneRegister1 = new Scene(registerPane1, 750, 500);
		//sceneRegister2 = new Scene(registerPane2, 750, 500);
		sceneLogin = new Scene(loginPane, 750, 500);
		sceneLoggedIn = new Scene(loggedInPane, 750, 500);
		sceneUpdate = new Scene(updatePane, 750, 500);
		// sceneCheck = new Scene(checkPane, 750, 500);

		// start out at homepage
		primaryStage.setTitle("Expense Tracker");
		primaryStage.setScene(sceneHome);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	/* methods that create the scenes */

	// creates the default homepage where user can login/register
	public VBox createHomePane() {

		VBox pane = new VBox();
		pane.setAlignment(Pos.CENTER);
		pane.setSpacing(10);

		Text title = new Text("Expense Tracker");
		title.setFont(Font.font(48));

		HBox buttonContainer = new HBox();
		buttonContainer.setAlignment(Pos.CENTER);
		buttonContainer.setSpacing(5);

		Button btnRegister = new Button("Register");
		btnRegister.setFont(Font.font(18));
		btnRegister.setOnMouseClicked(e -> {
			goRegister();
		});

		Button btnLogin = new Button("Login");
		btnLogin.setFont(Font.font(18));
		btnLogin.setOnMouseClicked(e -> {
			goLogin();
		});

		buttonContainer.getChildren().addAll(btnRegister, btnLogin);

		pane.getChildren().addAll(title, buttonContainer);

		return pane;

	}

	// creates the first register page
	public HBox createRegisterPane1() {

		// the overarching hbox container for this page
		HBox pane = new HBox();
		pane.setAlignment(Pos.CENTER);
		pane.setSpacing(50);

		// the "title" of this page
		Text register = new Text("REGISTER");
		register.setFont(Font.font(48));
		register.setTextAlignment(TextAlignment.RIGHT);

		// a grid pane to contain labels and text fields
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);

		Label lblName = new Label("Name");
		lblName.setFont(Font.font(16));
		GridPane.setHalignment(lblName, HPos.RIGHT);
		grid.add(lblName, 0, 0);

		Label lblAddress = new Label("Custom Id");
		lblAddress.setFont(Font.font(16));
		GridPane.setHalignment(lblAddress, HPos.RIGHT);
		grid.add(lblAddress, 0, 1);


		TextField tfName = new TextField();
		tfName.setFont(Font.font(16));
		tfName.setPromptText("Enter first and last name.");
		grid.add(tfName, 1, 0);
		
		TextField tfId = new TextField();
		tfId.setFont(Font.font(16));
		tfId.setPromptText("Enter Custom Id");
		grid.add(tfId, 1, 1);

		// an hbox to contain the buttons
		HBox buttonContainer = new HBox();
		buttonContainer.setAlignment(Pos.TOP_RIGHT);
		buttonContainer.setSpacing(5);

		Button btnReturnToMain = new Button("Return to Main Menu");
		btnReturnToMain.setFont(Font.font(16));
		btnReturnToMain.setOnMouseClicked(e -> {
			goMainMenu();
		});

		Label lblError = new Label("");
		lblError.setFont(Font.font(16));
		GridPane.setHalignment(lblError, HPos.LEFT);
		grid.add(lblError, 1, 2);

		Button btnNext = new Button("Next");
		btnNext.setFont(Font.font(16));
		btnNext.setOnMouseClicked(e -> {
			String valName = tfName.getText();
			if(tfId.getText().isEmpty() || valName.isEmpty())
			{
				lblError.setText("FIELD(S) ARE EMPTY");
			}
			else if(!User.validName(valName))
			{
				tfName.clear();
				tfName.setPromptText("FULL NAME W/ SPACES");
			}
			else if(!validNum(tfId.getText(), 4))
			{
				tfId.clear();
				tfId.setPromptText("ENTER ONLY 4 DIGITS");
			}
			else
			{
				int id = Integer.parseInt(tfId.getText());
				currentSystemUser = new User(valName, id);
				expTrcker.createNewUser(currentSystemUser);
				goLoggedIn();
			}
			// if everything works
			// System.out.println(loggedInUser.toString());
		});

		buttonContainer.getChildren().addAll(btnReturnToMain, btnNext);

		// a vbox to contain grid and buttonContainer
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(10);
		vBox.getChildren().addAll(grid, buttonContainer);

		pane.getChildren().addAll(register, vBox);

		return pane;

	}

	// creates the page where user can login
	public HBox createLoginPane() {

		// the overarching hbox container for this page
		HBox pane = new HBox();
		pane.setAlignment(Pos.CENTER);
		pane.setSpacing(50);

		// the "title" of this page
		Text login = new Text("LOGIN");
		login.setFont(Font.font(48));
		login.setTextAlignment(TextAlignment.RIGHT);

		// a grid pane to contain labels and text fields
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);

		Label lblName = new Label("Name");
		lblName.setFont(Font.font(16));
		GridPane.setHalignment(lblName, HPos.RIGHT);
		grid.add(lblName, 0, 0);

		Label lblID = new Label("Custom ID");
		lblID.setFont(Font.font(16));
		GridPane.setHalignment(lblID, HPos.RIGHT);
		grid.add(lblID, 0, 1);

		TextField tfName = new TextField();
		tfName.setFont(Font.font(16));
		tfName.setPromptText("Enter first and last name.");
		grid.add(tfName, 1, 0);

		TextField tfID = new TextField();
		tfID.setFont(Font.font(16));
		tfID.setPromptText("Enter ID");
		grid.add(tfID, 1, 1);

		// an hbox to contain the buttons
		HBox buttonContainer = new HBox();
		buttonContainer.setAlignment(Pos.TOP_RIGHT);
		buttonContainer.setSpacing(5);

		Button btnReturnToMain = new Button("Return to Main Menu");
		btnReturnToMain.setFont(Font.font(16));
		btnReturnToMain.setOnMouseClicked(e -> {
			goMainMenu();
		});

		Label lblError = new Label("");
		lblError.setFont(Font.font(16));
		GridPane.setHalignment(lblError, HPos.LEFT);
		grid.add(lblError, 1, 5);

		Button btnLogin = new Button("Login");
		btnLogin.setFont(Font.font(16));
		btnLogin.setOnMouseClicked(e -> {
			String valName = tfName.getText();
			

			if(tfID.getText().isEmpty() || valName.isEmpty())
			{
				lblError.setText("FIELD(S) ARE EMPTY");
			}
			else if(!User.validName(valName))
			{
				tfName.clear();
				tfName.setPromptText("FULL NAME W/ SPACES");
			}
			else if(!validNum(tfID.getText(), 4))
			{
				tfID.clear();
				tfID.setPromptText("ENTER ONLY 4 DIGITS");
			}
			else 
			{
				int valID = Integer.parseInt(tfID.getText());
				User testName = new User(valName, valID);
				if(expTrcker.loginUser(testName))
				{
					int id = Integer.parseInt(tfID.getText());
					currentSystemUser = expTrcker.getUser(testName);
					System.out.println(currentSystemUser.getName() + currentSystemUser.getID());
					goLoggedIn();
				}
			}
			
		});

		buttonContainer.getChildren().addAll(btnReturnToMain, btnLogin);

		// a vbox to contain grid and buttonContainer
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(10);
		vBox.getChildren().addAll(grid, buttonContainer);

		pane.getChildren().addAll(login, vBox);

		return pane;

	}

	// creates the page displayed when a user finishes registering/logging in
	public BorderPane createLoggedInPane() {

		BorderPane pane = new BorderPane();

		// default message for the center
		VBox defaultMessage = new VBox();
		defaultMessage.setAlignment(Pos.CENTER);
		defaultMessage.setSpacing(20);

		Text selectTitle = new Text("Select one of the options above to display here!");
		selectTitle.setFont(Font.font("", FontWeight.BOLD, 24));
		selectTitle.setUnderline(true);

		// box to hold description for the Update button at top
		VBox updateBox = new VBox();
		updateBox.setAlignment(Pos.CENTER);

		// description about the Update button at top
		Text updateTitle = new Text("Update Expenses and Budget");
		updateTitle.setFont(Font.font("", FontWeight.BOLD, 18));
		updateTitle.setTextAlignment(TextAlignment.CENTER);
		Text updateDescription = new Text(
				"Update your personal budget and the modify your total expenses within the tracker.");
		updateDescription.setFont(Font.font("", FontPosture.ITALIC, 18));
		updateDescription.setWrappingWidth(400);
		updateDescription.setTextAlignment(TextAlignment.CENTER);
		updateBox.getChildren().addAll(updateTitle, updateDescription);

		// box to hold description for Check button at top
		VBox checkBox = new VBox();
		checkBox.setAlignment(Pos.CENTER);

		// description about the Check button at top
		Text checkTitle = new Text("Check Expenses and Budget");
		checkTitle.setFont(Font.font("", FontWeight.BOLD, 18));
		checkTitle.setTextAlignment(TextAlignment.CENTER);
		Text checkDescription = new Text("See your current budget and a list of all expenses indicating a statistical"
				+ " display of spending habits");
		checkDescription.setFont(Font.font("", FontPosture.ITALIC, 18));
		checkDescription.setWrappingWidth(400);
		checkDescription.setTextAlignment(TextAlignment.CENTER);
		checkBox.getChildren().addAll(checkTitle, checkDescription);

		// set the default of the center pane to be these messages
		defaultMessage.getChildren().addAll(selectTitle, updateBox, checkBox);
		pane.setCenter(defaultMessage);

		// hbox for top buttons
		HBox hBoxTop = new HBox();
		hBoxTop.setAlignment(Pos.CENTER);
		hBoxTop.setSpacing(5);
		hBoxTop.setPadding(new Insets(20, 0, 0, 0));

		Button btnUpdateStatus = new Button("Update Expenses and Budget");
		btnUpdateStatus.setFont(Font.font(16));
		btnUpdateStatus.setOnMouseClicked(e -> {
			goUpdateStatus();
		});

		Button btnCheckStatus = new Button("Check Expenses and Budget");
		btnCheckStatus.setFont(Font.font(16));
		btnCheckStatus.setOnMouseClicked(e -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Budget Error");
			alert.setHeaderText("Budget is not set.");
			alert.setContentText("Set Budget in Update Expenses and Budget");
			if(currentSystemUser.getBudget() == null)
			{
				alert.showAndWait();
			}
			else
				goCheckStatus();
		});

		hBoxTop.getChildren().addAll(btnUpdateStatus, btnCheckStatus);

		pane.setTop(hBoxTop);

		// put a Return to Main Menu at the bottom of the scene
		StackPane bottomPane = new StackPane();
		bottomPane.setAlignment(Pos.CENTER);
		Button btnReturnMain = new Button("Return to Main Menu");
		btnReturnMain.setFont(Font.font(16));
		btnReturnMain.setOnMouseClicked(e -> {
			goMainMenu();
		});

		bottomPane.getChildren().add(btnReturnMain);

		pane.setBottom(bottomPane);
		pane.setPadding(new Insets(0, 0, 20, 0));

		// return the completed pane
		return pane;

	}

	// creates the page for user to update their status
	public VBox createUpdatePane() {

		// holds section for update status and add interactions
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.BASELINE_LEFT);
		vBox.setSpacing(10);
		vBox.setPadding(new Insets(10, 10, 10, 10));

		// hbox for submitting new interactions
		HBox hBoxID = new HBox();
		hBoxID.setAlignment(Pos.BASELINE_LEFT);
		hBoxID.setSpacing(26);

		Label lblID = new Label("Order Id");
		lblID.setFont(Font.font(16));

		TextField tfID = new TextField();
		tfID.setPromptText("Enter ID");
		tfID.setPrefWidth(250);
		tfID.setFont(Font.font(16));
		
		hBoxID.getChildren().addAll(lblID, tfID);
		
		HBox hBoxPrice = new HBox();
		hBoxPrice.setAlignment(Pos.BASELINE_LEFT);
		hBoxPrice.setSpacing(50);
		
		Label lblPrice = new Label("Price");
		lblPrice.setFont(Font.font(16));

		TextField tfPrice = new TextField();
		tfPrice.setPromptText("Enter Price");
		tfPrice.setPrefWidth(250);
		tfPrice.setFont(Font.font(16));
		
		hBoxPrice.getChildren().addAll(lblPrice, tfPrice);
		
		HBox hBoxName = new HBox();
		hBoxName.setAlignment(Pos.BASELINE_LEFT);
		hBoxName.setSpacing(5);
		
		Label lblName = new Label("Item Name");
		lblName.setFont(Font.font(16));

		TextField tfName = new TextField();
		tfName.setPromptText("Enter Item Name");
		tfName.setPrefWidth(250);
		tfName.setFont(Font.font(16));

		Button btSubmit = new Button("Submit Expense");
		btSubmit.setFont(Font.font(16));
		
		
		btSubmit.setOnMouseClicked(e -> {
			String interaction = tfID.getText() + "/" + tfPrice.getText() + "/" + tfName.getText();
			
			if(tfID.getText().isEmpty() || tfPrice.getText().isEmpty() || tfName.getText().isEmpty())
			{
				//tfBudget.setPromptText("FIELD IS EMPTY ");
			}
			else if(!validNum(tfID.getText(), 3))
			{
				tfID.clear();
				tfID.setPromptText("ENTER ONLY 3 DIGITS");
			}
			else if(!validPrice(tfPrice.getText()))
			{
				tfPrice.clear();
				tfPrice.setPromptText("INVALID FORMAT EX: 59.99");
			}
			else if(!expTrcker.addCheckDB(currentSystemUser, tfPrice.getText()))
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Add Expense Error");
				alert.setHeaderText("Budget is not set /or The Expense entered will go Overbudget.");
				alert.setContentText("Set Budget /or Increase Budget or do not add Expense.");
				alert.showAndWait();
			}
			else
			{
				expTrcker.addExpenseDB(currentSystemUser, interaction);
				currentSystemUser.setBudget(currentSystemUser.getBudget() - Double.parseDouble(tfPrice.getText()));
				tfName.clear();
				tfID.clear();
				tfPrice.clear();
				tfPrice.setPromptText("Enter Price");
				tfID.setPromptText("Enter ID");
			}
		});

		hBoxName.getChildren().addAll(lblName, tfName, btSubmit);

		HBox hBox2 = new HBox();
		hBox2.setAlignment(Pos.BASELINE_LEFT);
		hBox2.setSpacing(5);

		Label lblName2 = new Label("Budget");
		lblName2.setFont(Font.font(16));
		
		TextField tfBudget = new TextField();
		tfBudget.setPromptText("Enter Budget ");
		tfBudget.setPrefWidth(250);
		tfBudget.setFont(Font.font(16));
		
		Button btSubmitBudget = new Button("Submit Budget");
		btSubmitBudget.setFont(Font.font(16));
		
		btSubmitBudget.setOnMouseClicked(e -> {
			//int newBudget = Integer.parseInt(tfBudget.getText());
			if(tfBudget.getText().isEmpty())
			{
				tfBudget.setPromptText("FIELD IS EMPTY ");
			}
			else if(!validPrice(tfBudget.getText()))
			{
				tfBudget.clear();
				tfBudget.setPromptText("INVALID FORMAT EX: 1000.00");
			}
			else
			{
				currentSystemUser.setBudget(Double.parseDouble(tfBudget.getText()));
				System.out.println(currentSystemUser.getBudget());
				tfBudget.clear();
				tfBudget.setPromptText("Enter Budget ");
			}
		});
		
		hBox2.getChildren().addAll(lblName2, tfBudget, btSubmitBudget);
		hBox2.setPadding(new Insets(10, 25, 25, 25));
		
		HBox hBox3 = new HBox();
		hBox3.setAlignment(Pos.BASELINE_LEFT);
		hBox3.setSpacing(5);

		Label lblRemove = new Label("Remove");
		lblRemove.setFont(Font.font(16));
		
		TextField tfRemove = new TextField();
		tfRemove.setPromptText("Enter Order ID ");
		tfRemove.setPrefWidth(250);
		tfRemove.setFont(Font.font(16));
		
		Button btRemove = new Button("Remove Expense");
		btRemove.setFont(Font.font(16));
		
		btRemove.setOnMouseClicked(e -> {
			//int newBudget = Integer.parseInt(tfBudget.getText());
			if(!expTrcker.removeExpenseDB(currentSystemUser, tfRemove.getText()))
			{
				tfRemove.clear();
				tfRemove.setPromptText("INVALID ORDER ID");
				
			}
			else
			{
				System.out.println(expTrcker.getExpenses());
				tfRemove.clear();
				tfRemove.setPromptText("Enter Order ID ");
			}
		});
		
		hBox3.getChildren().addAll(lblRemove, tfRemove, btRemove);
		hBox3.setPadding(new Insets(10, 25, 25, 18));
		// add button at bottom to return to logged in screen
		StackPane pane = new StackPane();
		pane.setPadding(new Insets(100, 0, 0, 0));
		pane.setAlignment(Pos.BASELINE_CENTER);

		// returns to loggedInPane; make so "Done" updates status if selected (works)
		Button btReturn = new Button("Done");
		btReturn.setFont(Font.font(16));
		btReturn.setAlignment(Pos.CENTER);
		btReturn.setOnMouseClicked(e -> {
			
			goLoggedIn();
		});

		pane.getChildren().add(btReturn);

		vBox.getChildren().addAll(hBoxID, hBoxPrice, hBoxName, hBox2, hBox3, pane);

		return vBox;

	}

	// creates the page to display user's exposure status
	public Pane createCheckPane() {

		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(50);
		vBox.setPadding(new Insets(50, 0, 50, 0));

		GridPane pane = new GridPane();
		pane.setVgap(10);
		pane.setHgap(10);
		pane.setAlignment(Pos.CENTER);

		// labels
		Label lblTestStatus = new Label("Current Budget:");
		lblTestStatus.setFont(Font.font("", FontWeight.BOLD, 16));
		GridPane.setHalignment(lblTestStatus, HPos.RIGHT);
		pane.add(lblTestStatus, 0, 0);

		Label lblExposureStatus = new Label("Expense List:");
		lblExposureStatus.setFont(Font.font("", FontWeight.BOLD, 16));
		GridPane.setHalignment(lblExposureStatus, HPos.RIGHT);
		pane.add(lblExposureStatus, 0, 1);

		Label lblInteractionList = new Label("Overspending:");
		lblInteractionList.setFont(Font.font("", FontWeight.BOLD, 16));
		GridPane.setHalignment(lblInteractionList, HPos.RIGHT);
		pane.add(lblInteractionList, 0, 2);

		// actual values
		Text testStatus = new Text();
		testStatus.setFont(Font.font(16));
		testStatus.setText("$" + String.valueOf(String.format("%.2f", currentSystemUser.getBudget())));
		pane.add(testStatus, 1, 0);

		Label exposureStatus = new Label(expTrcker.getExpenses());
		exposureStatus.setWrapText(true);
		exposureStatus.setFont(Font.font(16));
//		exposureStatus.setText(expTrcker.getExpenses());
		pane.add(exposureStatus, 1, 1);

		Text interactions = new Text(expTrcker.overSpendingDB(currentSystemUser).toUpperCase());
		interactions.setFont(Font.font(16));
		//interactions.setText(checkPastInteractions());
		interactions.setWrappingWidth(400);
		pane.add(interactions, 1, 2);

		Button btReturn = new Button("Done");
		btReturn.setFont(Font.font(16));
		btReturn.setOnMouseClicked(e -> {
			goLoggedIn();
		});
		btReturn.setAlignment(Pos.CENTER);

		// add button at bottom to return to logged in screen
		StackPane stackPane = new StackPane();
		stackPane.setPadding(new Insets(100, 0, 0, 0));
		stackPane.setAlignment(Pos.BASELINE_CENTER);
		stackPane.getChildren().add(btReturn);

		vBox.getChildren().addAll(pane, stackPane);

		return vBox;
	}

	/* methods for buttons to go to a certain scene */

	// for the button that sends user back to login/register screen
	public void goMainMenu() {

		stage.setScene(sceneHome);
		stage.show();

	}

	// for the button that sends user to first register page
	public void goRegister() {

		stage.setScene(sceneRegister1);
		stage.show();

	}

	// for the button that sends user to next register page
	public void goNext() {

		stage.setScene(sceneRegister2);
		stage.show();

	}

	// for the button that sends user to the login page
	public void goLogin() {

		stage.setScene(sceneLogin);
		stage.show();

	}

	// for the button that logs the user in and sends them to the page to choose
	// update/check status
	public void goLoggedIn() {
		stage.setScene(sceneLoggedIn);
		stage.show();

	}

	// for the button so user can go update status and interactions
	public void goUpdateStatus() {

		stage.setScene(sceneUpdate);
		stage.show();

	}

	// lets user check their status and interactions if they are logged in
	public void goCheckStatus() {

		checkPane = createCheckPane();
		sceneCheck = new Scene(checkPane, 750, 500);
		stage.setScene(sceneCheck);
		stage.show();

	}

	/**
	 * Determines if login information matches a user in the database and if it
	 * does, stores user identification information in loggedInUser.
	 *
	 * @param user User to check if could login
	 */
	public void login(User currentUser) {
		currentSystemUser = currentUser;
		checkPane = createCheckPane();
		sceneCheck = new Scene(checkPane, 750, 500);

	}

	public void registerUser(User user, String status, String interactions) {
		interactions = interactions.replace("\n", "");

		checkPane = createCheckPane();
		sceneCheck = new Scene(checkPane, 750, 500);
	}



	public boolean validNum(String str, int size) {
		int length = str.length();
		if (length != size) {
			return false;
		}
		// Traverse the string from
		// start to end
		for (int i = 0; i < length; i++) {

			// Check if character is
			// not digit from 0-9
			// then return false
			if (str.charAt(i) < '0' || str.charAt(i) > '9') {
				return false;
				
			}

		}
		return true;
	}

	public boolean validPrice(String str)
	{
		char decimal = '.';
		int count = 0;
		 
		for (int i = 0; i < str.length(); i++) {
		    if (str.charAt(i) == decimal) {
		        count++;
		    }
		}
		if(count != 1)
		{
			return false;
		}
		//System.out.println(str);
		String[] split = str.split("\\.");

		//System.out.println(split[1]);
		if(split[1].length() > 2)
			return false;
		
		for(int k = 0; k < split.length; k++)
		{
			//System.out.println(split[k]);
			if(!validNum(split[k], split[k].length()))
			{
				return false;
			}
		}
		return true;
	}
    

    
}
