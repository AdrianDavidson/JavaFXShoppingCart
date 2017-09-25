//	        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//	        Adrian Davidson
//			R00138984
//			OOP Assignment 2
//	        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++

package shop;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import static javafx.geometry.HPos.RIGHT;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Control;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
	GridPane grid = new GridPane();
    ImageView myImageView;

	//private Store data = new Store();
	private ArrayList<Product> data = new ArrayList<Product>();
	private final ArrayList<CartItem> cart = new ArrayList<CartItem>();
	
	class MyButton extends Button{
		int id;
		
		public MyButton(int id, String name){
			super(name);
			this.id = id;
		}
	}
		
	private StackPane createCell(Control c) {
        StackPane cell = new StackPane();

        cell.getChildren().add(c);
        cell.getStyleClass().add("cell");
        return cell;
    }
    
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("JavaFX Welcome");
		Product p = new Product("car 1","good car",12.3,"car1.jpeg");
		//data.add(p);
		BorderPane border = new BorderPane();
        
        border.setLeft(addVBox());
        
		//ScrollPane s1 = new ScrollPane();
		// s1.setPrefSize(200, 200);
		//GridPane grid = new GridPane();
             
		border.setCenter(grid);
		Scene scene = new Scene(border, 1050, 750);
		scene.getStylesheets().
		add("grid-with-borders.css");
		grid.getStyleClass().add("grid");
		for (int x = 0 ; x < 4 ; x++) 
		{
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(cc);
        }

        for (int y = 0 ; y < 10 ; y++) 
        {
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rc);
        }
        
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
	/*
	 * Creates a VBox with a list of links for the left region
	 */
	    private VBox addVBox() {
	        
	        VBox vbox = new VBox();
	        vbox.setPadding(new Insets(10)); // Set all sides to 10
	        vbox.setSpacing(8);              // Gap between nodes

	        Text title = new Text("Menu");
	        title.setFont(Font.font("Arial", FontWeight.THIN, 20));
	        vbox.getChildren().add(title);
	        
	        Button products= new Button("Products");
	        Button cart = new Button("Cart");
	        Button newProd = new Button("New Product");
	        vbox.getChildren().addAll(products,cart,newProd);
	        
	        products.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					showProducts();
				}
			});
	        
	        cart.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					showCart();
				}
			});
	        
	        newProd.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					newProduct();	
					
				}
			});
	        
	        return vbox;
	    }
	    
//        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//	    			ALLOWS USER TO INPUT NEW PRODUCT
//        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    
	    private void newProduct(){
	    	grid.getChildren().clear();
	    	grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25));
			
			Label Header = new Label("Please add a product:");
	        grid.add(Header, 0, 0);
	        Header.setFont(Font.font("Arial", FontWeight.THIN, 40));
			
			//Product Label - constrains use (child, column, row)
	        Label productLabel = new Label("Product:");
	        grid.add(productLabel, 0, 2);
	        //Product Input
	        TextField ProductInput = new TextField("");
	        grid.add(ProductInput, 2, 2);
	        
	        //ID Label
	        Label ProductPriceLabel = new Label("Price:");
	        grid.add(ProductPriceLabel, 0, 3);

	        //ID Input
	        TextField ProductPriceInput = new TextField("");
	        grid.add(ProductPriceInput, 2, 3);
			
			 //Description Label
	        Label ProductDescriptionLabel = new Label("Description:");
	        grid.add(ProductDescriptionLabel, 0, 4);

	        //Description Input
	        TextField ProductDescriptionInput = new TextField("");
	        grid.add(ProductDescriptionInput, 2, 4);
	        
	        //Add Button
	        Button OkButton = new Button("OK");
	        grid.add(OkButton, 3, 6);	
	        
//	        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//		    		ALLOWS USER TO LOAD IMAGE FROM FILE
//	        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	        
	        Button btnLoad = new Button("Load Image..");
	        grid.add(btnLoad, 0, 6);
	        
	        EventHandler<ActionEvent> btnLoadEventListener = new EventHandler<ActionEvent>()
	        {
	            @Override
	            public void handle(ActionEvent t) {
	                FileChooser fileChooser = new FileChooser();
	                 
	                //Set extension filter
	                FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpeg)", "*.JPEG");
	                FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
	                fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
	                  
	                //Show open file dialog
	                File file = fileChooser.showOpenDialog(null);
	            }
	        };
	        btnLoad.setOnAction(btnLoadEventListener);
	        
//	        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//	        WHEN "OK" IS CLICKED IT ADDS PRODUCT TO PRODUCT ARRAYLIST
//	        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	        OkButton.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent arg0) {

		        	Product tempProd = new Product(ProductInput.getText(), null, Double.parseDouble(ProductPriceInput.getText()), ProductDescriptionInput.getText());
		        	tempProd.print();
		        	
		        	//Populates the ArrayList//
		        	//=======================//
		        	data.add(tempProd);
		        	
		        	
		        	//Saves to a file//
		        	//=======================//
		        	String storeInfo = "";
		    		
		    		//loop through the array
		    		for (int i = 0; i < data.size(); i++){
		    			storeInfo = storeInfo + tempProd;
		    		}
		    		
		    		try
		    		{
		    			PrintWriter pr = new PrintWriter("UTF-8");
		    			pr.println(storeInfo);
		    			pr.close();
		    			
		    		}catch(FileNotFoundException e){
		    			e.printStackTrace();
		    		}
		    	  }
	        });
			
	    }
//         ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//        			DISPLAYS ALL THE PRODUCTS IN THE LIST
//         ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    private void showProducts(){
	    	grid.getChildren().clear();
	    	grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25));
			
			Label Header = new Label("Your Products:");
	        grid.add(Header, 0, 0);
	        Header.setFont(Font.font("Arial", FontWeight.THIN, 40));
	        
	        Label Item = new Label("Item");
	        grid.add(Item, 0, 1);
	        Label Price = new Label("Price");
	        grid.add(Price, 1, 1);
	        Label Description = new Label("Description");
	        grid.add(Description, 2, 1);
			       
	        for(int i = 0; i < data.size() ; i++)
	        {
	        	Label ProdName = new Label(data.get(i).getName());
	 	        grid.add(ProdName, 0, 2 +i);
	 	        
	 	        Label ProdPrice = new Label(data.get(i).getPrice() + "");
	 	        grid.add(ProdPrice, 1, 2 +i);
	 	        
	 	        Label ProdDesc = new Label(data.get(i).getDescription());
	 	        grid.add(ProdDesc, 2, 2 +i);
	 	        
	 	       Button Buy = new Button("Buy Now");
		       grid.add(Buy, 3, 2 + i);
		       
		       
//		  ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//		  WHEN "BUY" IS CLICKED IT ADDS PRODUCT TO THE CART ARRAYLIST
//		  ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		       Buy.setOnAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent arg0) {

						CartItem addCartTemp = new CartItem(ProdName.getText(), null, Double.parseDouble(ProdPrice.getText()), ProdDesc.getText());
			        	addCartTemp.print();
			        	
			        	//Populates the ArrayList//
			        	//=======================//
			        	cart.add(addCartTemp);
					}
		        });
		       
		        	        
	        }
			
	    }
	    
//        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//        			DISPLAYS ALL ITEMS IN THE CART ARRAYLIST
//        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    private void showCart(){
	    	grid.getChildren().clear();
	    	grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			
			Label Header = new Label("Your Shopping Cart:");
	        grid.add(Header, 0, 0);
	        Header.setFont(Font.font("Arial", FontWeight.THIN, 40));
	        
	        Label Item = new Label("Item");
	        grid.add(Item, 0, 1);
	        Label Price = new Label("Price");
	        grid.add(Price, 1, 1);
	        Label Description = new Label("Description");
	        grid.add(Description, 2, 1);
			
	        for(int i = 0; i < cart.size() ; i++)
	        {
	        	Label ProdName = new Label(cart.get(i).getName());
	 	        grid.add(ProdName, 0, 2 +i);
	 	        
	 	        Label ProdPrice = new Label(cart.get(i).getPrice() + "");
	 	        grid.add(ProdPrice, 1, 2 +i);
	 	        
	 	        Label ProdDesc = new Label(cart.get(i).getDescription());
	 	        grid.add(ProdDesc, 2, 2 +i);
	 	        
	 	       Button Remove = new Button("Remove from cart");
	 	       Remove.setId(i + "");
		       grid.add(Remove, 3, 2 + i);
		       
//				  ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//				  WHEN "REMOVE" IS CLICKED IT REMOVES THE PRODUCT FROM CART ARRAYLIST
//				  ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				       Remove.setOnAction(new EventHandler<ActionEvent>(){

							@Override
							public void handle(ActionEvent arg0) {

								cart.remove(Integer.parseInt(Remove.getId()));
								showCart();
							}
				        });
	        }
	        
	    }
}
