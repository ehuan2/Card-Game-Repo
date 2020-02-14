public class GUI extends Application{

  Stage window;
  Scene scene;
  Button[] buttonCards;
  static int cardChoice;
  
  public GUI(){
  
  window = new Stage();
  
  }
  
  public static void main(String [] args){
    
   launch(args);
    
  }

  @Override 
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("CHIMP");
    
    StackPane layout = new StackPane();
    
    buttonCards = new Button[3];
    for(int i = 0; i < 3; i++){
      buttonCards[i] = new Button();
      buttonCards[i].setText("Card Choice " + (i+1));
      
      buttonCards[i].setOnAction(e -> cardChoice = i);
      
      layout.getChildren().add(buttonCards[i]);
    }
    
    
    
  }
  
}
